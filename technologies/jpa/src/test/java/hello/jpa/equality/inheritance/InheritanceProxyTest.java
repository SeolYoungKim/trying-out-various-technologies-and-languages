package hello.jpa.equality.inheritance;

import hello.jpa.inheritance.Book;
import hello.jpa.inheritance.Item;
import hello.jpa.inheritance.OrderItem;
import jakarta.persistence.EntityManager;
import org.hibernate.proxy.HibernateProxy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class InheritanceProxyTest {
    @Autowired
    private EntityManager em;

    @DisplayName("Book을 Item 프록시로 조회하면, 프록시의 타입은 Book이 아닌 Item이다.")
    @Transactional
    @Test
    void test() {
        Book book = new Book();
        book.setTitle("title");
        book.setAuthor("author");
        book.setIsbn("isbn");
        em.persist(book);

        em.flush();
        em.clear();

        Item proxy = em.getReference(Item.class, book.getId());
        System.out.println("proxy = " + proxy.getClass());

        if (proxy instanceof Book bookProxy) {
            System.out.println("bookProxy.getAuthor() = " + bookProxy.getAuthor());
        }

        assertThat(proxy).isNotInstanceOf(Book.class);
        assertThat(proxy).isInstanceOf(Item.class);
    }

    @DisplayName("상속관계와 프록시 도메인 모델")
    @Transactional
    @Test
    void test2() {
        Book book = new Book();
        book.setTitle("title");
        book.setAuthor("author");
        book.setIsbn("isbn");
        em.persist(book);

        OrderItem orderItem = new OrderItem();
        orderItem.setItem(book);
        em.persist(orderItem);

        em.flush();
        em.clear();

        OrderItem findOrderItem = em.find(OrderItem.class, orderItem.getId());
        Item item = findOrderItem.getItem();

        System.out.println("item.getClass() = " + item.getClass());

        assertThat(item.getClass()).isNotSameAs(Book.class);
        assertThat(item).isNotInstanceOf(Book.class);
        assertThat(item).isInstanceOf(Item.class);
    }

    @DisplayName("원본 엔티티를 얻는 방법")
    @Transactional
    @Test
    void test3() {
        Book book = new Book();
        book.setTitle("title");
        book.setAuthor("author");
        book.setIsbn("isbn");
        em.persist(book);

        OrderItem orderItem = new OrderItem();
        orderItem.setItem(book);
        em.persist(orderItem);

        em.flush();
        em.clear();

        OrderItem findOrderItem = em.find(OrderItem.class, orderItem.getId());
        Item proxyItem = findOrderItem.getItem();

        System.out.println("proxyItem.getClass() = " + proxyItem.getClass());


        assertThat(proxyItem.getClass()).isNotSameAs(Book.class);
        assertThat(proxyItem).isNotInstanceOf(Book.class);
        assertThat(proxyItem).isInstanceOf(Item.class);

        Book realItem = (Book) HibernateProxy.extractLazyInitializer(proxyItem).getImplementation();
        System.out.println("realItem.getClass() = " + realItem.getClass());
        assertThat(realItem.getClass()).isSameAs(Book.class);
    }
}
