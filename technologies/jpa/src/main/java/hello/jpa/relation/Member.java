package hello.jpa.relation;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

//    @ManyToOne
//    @JoinColumn(name = "team_id", insertable = false, updatable = false)
//    private Team team;
//
//    @OneToOne(mappedBy = "member")
//    private Locker locker;

//    @BatchSize(size = 5)
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
    private List<Order> orders = new ArrayList<>();

    protected Member() {
    }

    public Member(String username) {
        this.username = username;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }
}
