package hello.jpa.relation;

import jakarta.persistence.*;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    protected Member() {
    }

    public Member(String username, Team team) {
        this.username = username;
        this.team = team;

        team.addMember(this);
    }
}
