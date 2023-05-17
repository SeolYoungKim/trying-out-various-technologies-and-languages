package hello.mongo.relation2;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Getter
@Document
public class Product {
    @Id
    private String id;

    private String title;

    private String status;

    private String ageLimit;

    @DocumentReference
    private List<Author> painters = new ArrayList<>();

    @DocumentReference
    private List<Author> writers = new ArrayList<>();

    public Product(final String title, final String status, final String ageLimit) {
        this.title = title;
        this.status = status;
        this.ageLimit = ageLimit;
    }

    public void addPainters(final List<Author> painters) {
        this.painters.addAll(painters);
    }

    public void addWriters(final List<Author> writers) {
        this.writers.addAll(writers);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", status='" + status + '\'' +
                ", ageLimit='" + ageLimit + '\'' +
                ", painters=" + painters +
                ", writers=" + writers +
                '}';
    }
}
