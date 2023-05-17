package hello.mongo.relation;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Getter
@Document
public class Publisher {
    @Id
    private String id;

    private String name;

    private String acronym;

    private int foundationYear;

    @DocumentReference
    List<Book> books = new ArrayList<>();

    public Publisher(final String name, final String acronym, final int foundationYear) {
        this.name = name;
        this.acronym = acronym;
        this.foundationYear = foundationYear;
    }

    public void addBook(final Book book) {
        books.add(book);
    }
}
