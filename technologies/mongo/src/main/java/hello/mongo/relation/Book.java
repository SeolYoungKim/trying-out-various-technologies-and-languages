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

    private String publisherId;

    public Book(final String isbn, final String title, final int pages, final String publisherId) {
        this.isbn = isbn;
        this.title = title;
        this.pages = pages;
        this.publisherId = publisherId;
    }
}
