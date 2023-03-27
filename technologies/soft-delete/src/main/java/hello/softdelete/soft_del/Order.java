package hello.softdelete.soft_del;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@SQLDelete(sql = "update orders set deleted = true where id = ?")
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderName;

    private String orderer;

    private String address;

    private boolean deleted = false;

    public Order() {
    }

    public Order(String orderName, String orderer, String address) {
        this.orderName = orderName;
        this.orderer = orderer;
        this.address = address;
    }
}
