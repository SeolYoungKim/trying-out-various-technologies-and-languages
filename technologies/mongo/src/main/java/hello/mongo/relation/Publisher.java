package hello.mongo.relation;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
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

    // 아래와 같이 지정할 경우 addBook을 통해 따로 Book을 저장해줄 필요가 없음.
    // 즉, Book에 Publisher를 지정 해주기만 하면, 조회 시 알아서 조회해온다.
    @ReadOnlyProperty
    @DocumentReference(lookup = "{'publisher':?#{#self._id}}")  // this.id를 가지는 Publisher를 가진 Book을 알아서 조회 해온다.
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
