package hello.jpa.isnew.non_generated;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class NonGeneratedItem {
    @Id
    private String id;

    protected NonGeneratedItem() {
    }

    public NonGeneratedItem(String id) {
        this.id = id;
    }
}
