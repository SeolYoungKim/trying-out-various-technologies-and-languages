package hello.jpa.relation;

import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int orderAmount;

    protected Order() {
    }

    public Order(Member member, Product product) {
        this.member = member;
        this.product = product;
    }

    public void setOrderAmount(int amount) {
        this.orderAmount = amount;
    }
}
