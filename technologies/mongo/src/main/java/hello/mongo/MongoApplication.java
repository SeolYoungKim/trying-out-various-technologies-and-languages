package hello.mongo;

import hello.mongo.relation2.Author;
import hello.mongo.relation2.AuthorRepository;
import hello.mongo.relation2.Product;
import hello.mongo.relation2.ProductRepository;
import java.util.List;
import java.util.stream.IntStream;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MongoApplication {
	private final AuthorRepository authorRepository;
	private final ProductRepository productRepository;

	public MongoApplication(final AuthorRepository authorRepository,
			final ProductRepository productRepository) {
		this.authorRepository = authorRepository;
		this.productRepository = productRepository;
	}

	@Bean
	public ApplicationRunner applicationRunner() {
		return args -> {
			productRepository.deleteAll();
			authorRepository.deleteAll();

			final Product product = productRepository.save(
					new Product("title", "status", "ADULT_ONLY"));

			final List<Author> painters = IntStream.rangeClosed(1, 10)
					.mapToObj(i -> new Author("name" + i, "nickname" + i))
					.toList();

			final List<Author> writers = IntStream.rangeClosed(11, 20)
					.mapToObj(i -> new Author("name" + i, "nickname" + i))
					.toList();

			authorRepository.saveAll(painters);
			authorRepository.saveAll(writers);

			product.addPainters(painters);
			product.addWriters(writers);
			productRepository.save(product);

			final Product findProduct = productRepository.findById(product.getId()).orElseThrow();
			findProduct.getPainters().forEach(System.out::println);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(MongoApplication.class, args);
	}
}
