package hello.jpa.relation;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @ManyToOne
    @JoinColumn(name = "team_id", insertable = false, updatable = false)
    private Team team;

    @OneToOne(mappedBy = "member")
    private Locker locker;

    @ManyToMany
    @JoinTable(
            name = "member_product",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<>();

    protected Member() {
    }

    public Member(String username) {
        this.username = username;
    }

    public void addProduct(Product product) {
        products.add(product);
    }
}
