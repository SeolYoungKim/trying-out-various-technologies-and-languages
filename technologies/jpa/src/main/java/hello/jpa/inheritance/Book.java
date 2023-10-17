package hello.jpa.inheritance;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Book extends Item {
    private String isbn;
    private String author;

    public void setTitle(String title) {
        super.setTitle(title);
    }
}
