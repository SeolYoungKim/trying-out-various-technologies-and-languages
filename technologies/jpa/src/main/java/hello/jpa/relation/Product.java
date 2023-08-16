package hello.jpa.relation;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity(name = "product2")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "products")
    private List<Member> members = new ArrayList<>();

    protected Product() {
    }

    public Product(String name) {
        this.name = name;
    }

    public void addMember(Member member) {
        members.add(member);
    }
}
