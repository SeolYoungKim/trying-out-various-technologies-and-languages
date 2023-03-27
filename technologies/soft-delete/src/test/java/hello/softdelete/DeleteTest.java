package hello.softdelete;

import static org.assertj.core.api.Assertions.*;

import hello.softdelete.hard_del.Shop;
import hello.softdelete.hard_del.ShopRepository;
import hello.softdelete.soft_del.Order;
import hello.softdelete.soft_del.OrderRepository;
import hello.softdelete.soft_del.where.Item;
import hello.softdelete.soft_del.where.ItemRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class DeleteTest {
    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @DisplayName("Hard Delete")
    @Test
    void hardDelete() {
        final Shop shop = new Shop("가게 이름", "가게 주소");
        final Shop savedShop = shopRepository.save(shop);

        assertThat(savedShop.getId()).isNotNull();
        assertThat(savedShop.isDeleted()).isFalse();

        shopRepository.delete(savedShop);

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