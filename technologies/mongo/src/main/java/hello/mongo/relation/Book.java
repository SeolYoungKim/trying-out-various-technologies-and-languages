package hello.mongo.relation;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document
public class Book {
    @Id
    private String id;

    private String isbn;

    private String title;

    private int pages;

    private Publisher publisher;

    public Book(final String isbn, final String title, final int pages, final Publisher publisher) {
        this.isbn = isbn;
        this.title = title;
        this.pages = pages;
        this.publisher = publisher;
    }
}
