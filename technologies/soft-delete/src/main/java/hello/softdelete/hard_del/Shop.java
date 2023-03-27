package hello.softdelete.hard_del;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private boolean deleted = false;

    public Shop() {
    }

    public Shop(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
