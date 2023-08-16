package hello.jpa.relation;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Locker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    protected Locker() {
    }

    public Locker(String name) {
        this.name = name;
    }
}
