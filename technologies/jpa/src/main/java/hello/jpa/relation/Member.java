package hello.jpa.relation;

import jakarta.persistence.*;
import lombok.Getter;

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

    protected Member() {
    }

    public Member(String username) {
        this.username = username;
    }
}
