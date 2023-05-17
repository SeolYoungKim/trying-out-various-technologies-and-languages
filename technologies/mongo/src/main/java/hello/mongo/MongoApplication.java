package hello.mongo;

import hello.mongo.relation.Book;
import hello.mongo.relation.BookRepository;
import hello.mongo.relation.Publisher;
import hello.mongo.relation.PublisherRepository;
import java.util.List;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MongoApplication {
	private final BookRepository bookRepository;
	private final PublisherRepository publisherRepository;

	public MongoApplication(final BookRepository bookRepository,
			final PublisherRepository publisherRepository) {
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}

	@Bean
	public ApplicationRunner applicationRunner() {
		return args -> {
			publisherRepository.deleteAll();
			bookRepository.deleteAll();

			final Publisher publisher = publisherRepository.save(new Publisher("kim", "ss", 2023));

			final Book book = new Book("123", "title", 100, publisher);
			bookRepository.save(book);

			final Book book2 = new Book("456", "t", 1000, publisher);
			bookRepository.save(book2);

			System.out.println("=====Book에 PublisherId를 저장=====");
			final Book findedBook = bookRepository.findById(book.getId()).orElseThrow();
			final Publisher findedBookPublisher = findedBook.getPublisher();

			System.out.println(publisher.getId());
			System.out.println(findedBookPublisher);
			System.out.println(findedBookPublisher.getId());

			System.out.println("=====Publisher에 List<String>형태로 BookId를 저장=====");
			publisher.addBook(book);
			publisher.addBook(book2);
			publisherRepository.save(publisher);

			final Publisher findedPublisher = publisherRepository.findById(publisher.getId())
					.orElseThrow();

			final List<Book> books = findedPublisher.getBooks();
			System.out.println(books);  // 프록시
			books.forEach(b -> System.out.println(b.getId()));  // 실제 객체
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(MongoApplication.class, args);
	}
}
