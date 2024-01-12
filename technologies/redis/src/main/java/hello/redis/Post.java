package hello.redis;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String title;
    private String content;
    private LocalDateTime registeredAt;

    protected Post() {
    }

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
        this.registeredAt = LocalDateTime.now();
    }
}
