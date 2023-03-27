package hello.softdelete.soft_del.where;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@SQLDelete(sql = "update item set deleted = true where id = ?")
@Where(clause = "deleted = false")
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean deleted = false;

    public Item() {
    }

    public Item(String name) {
        this.name = name;
    }
}
