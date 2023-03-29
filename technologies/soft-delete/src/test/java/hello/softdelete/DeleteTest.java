package hello.softdelete;

import static org.assertj.core.api.Assertions.*;

import hello.softdelete.hard_del.Shop;
import hello.softdelete.hard_del.ShopRepository;
import hello.softdelete.soft_del.Order;
import hello.softdelete.soft_del.OrderRepository;
import hello.softdelete.soft_del.where.Item;
import hello.softdelete.soft_del.where.ItemRepository;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class DeleteTest {
    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    EntityManager em;

    @DisplayName("Hard Delete")
    @Test
    void hardDelete() {
        final Shop shop = new Shop("가게 이름", "가게 주소");
        final Shop savedShop = shopRepository.save(shop);

        assertThat(savedShop.getId()).isNotNull();
        assertThat(savedShop.isDeleted()).isFalse();

        shopRepository.delete(savedShop);  // 아래 설명 참고
        em.flush();
        em.clear();

        final Optional<Shop> afterDelete = shopRepository.findById(savedShop.getId());
        assertThat(afterDelete).isEmpty();
    }


    @DisplayName("Soft Delete")
    @Test
    void softDelete() {
        final Order order = new Order("주문1", "주문자1", "주문자 주소1");
        final Order savedOrder = orderRepository.save(order);  // insert

        assertThat(savedOrder.getId()).isNotNull();
        assertThat(savedOrder.isDeleted()).isFalse();

        orderRepository.delete(savedOrder);  // select + update

        // 같은 트랜잭션 내에서 flush, clear를 사용해야 delete 쿼리가 보이는 이유
        // 현재 savedOrder는 영속성 컨텍스트에 존재하고, 영속성 컨텍스트에 존재하는 엔티티는 트랜잭션이 끝날 때까지 DB에 반영되지 않는다.
        // 따라서, delete 쿼리가 보이지 않는다. delete는 영속성 컨텍스트에 존재하는 엔티티를 삭제하는 것이 아니라,
        // 영속성 컨텍스트에 존재하는 엔티티의 상태를 변경하는 것이기 때문이다.
        // 따라서, delete 쿼리가 보이려면, 영속성 컨텍스트에 존재하는 엔티티를 삭제하고, DB에 반영해야 한다.
        // 이를 위해 flush, clear를 사용한다.

        em.flush();  // 쿼리를 DB에 보낸다
        em.clear();  // 영속성 컨텍스트를 초기화한다 -> 다음 조회 시 영속성 컨텍스트에 있는 엔티티가 조회되지 않도록 한다.

        final Optional<Order> afterDelete = orderRepository.findById(savedOrder.getId());  // select
        assertThat(afterDelete).isNotEmpty();
        assertThat(afterDelete.get().isDeleted()).isTrue();
    }

    @DisplayName("deleted=false 인 data 조회")
    @Test
    void where() {
        final List<Item> newItems = List.of(
                new Item("아이템1"),
                new Item("아이템2"),
                new Item("아이템3"));

        itemRepository.saveAll(newItems);

        itemRepository.delete(newItems.get(0));

        final List<Item> items = itemRepository.findAll();
        assertThat(items).hasSize(2);
        assertThat(items.get(0).getName()).isEqualTo("아이템2");
    }
}