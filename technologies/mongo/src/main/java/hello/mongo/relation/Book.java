package hello.mongo.relation;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Getter
@Document
public class Book {
    @Id
    private String id;

    private String isbn;

    private String title;

    private int pages;

    @DocumentReference(lazy = true)  // LazyLoading을 적용하여 proxy로 조회
    private Publisher publisher;

    public Book(final String isbn, final String title, final int pages, final Publisher publisher) {
        this.isbn = isbn;
        this.title = title;
        this.pages = pages;
        this.publisher = publisher;
    }
}
