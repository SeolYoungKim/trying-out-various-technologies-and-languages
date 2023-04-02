package hello.jpa.isnew.generated;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class GeneratedItem {
    @Id
    @jakarta.persistence.GeneratedValue(strategy = IDENTITY)
    private Long id;
}
