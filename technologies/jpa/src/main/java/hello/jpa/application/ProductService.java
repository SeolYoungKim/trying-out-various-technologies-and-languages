package hello.jpa.application;

import hello.jpa.domain.Author;
import hello.jpa.domain.AuthorRepository;
import hello.jpa.domain.AuthorType;
import hello.jpa.domain.Product;
import hello.jpa.domain.ProductAuthor;
import hello.jpa.domain.ProductAuthorRepository;
import hello.jpa.domain.ProductRepository;
import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final AuthorRepository authorRepository;

    private final ProductAuthorRepository productAuthorRepository;

    public ProductService(ProductRepository productRepository, AuthorRepository authorRepository,
            final ProductAuthorRepository productAuthorRepository) {
        this.productRepository = productRepository;
        this.authorRepository = authorRepository;
        this.productAuthorRepository = productAuthorRepository;
    }

    public Product getFirstProduct() {
        return productRepository.findByIdFetchJoin(1L).orElseThrow();
    }

    public Author getFirstAuthor() {
        return authorRepository.findById(1L).orElseThrow();
    }

    public ProductAuthor getFirstProductAuthor() {
        final ProductAuthor productAuthor = productAuthorRepository.findById(1L).orElseThrow();
        log.info("AuthorClass: {}", productAuthor.getAuthor().getClass());
        log.info("ProductClass: {}", productAuthor.getProduct().getClass());

        return productAuthor;
    }

    @PostConstruct
    public void init() {
        final Product product1 = new Product("Product 1", "Description 1");
        final Product product2 = new Product("Product 2", "Description 2");
        productRepository.save(product1);
        productRepository.save(product2);

        final Author author1 = new Author("Author 1");
        final Author author2 = new Author("Author 2");
        authorRepository.save(author1);
        authorRepository.save(author2);

        final List<ProductAuthor> productAuthors = List.of(
                new ProductAuthor(product1, author1, AuthorType.WRITER),
                new ProductAuthor(product1, author2, AuthorType.WRITER),
                new ProductAuthor(product1, author2, AuthorType.PAINTER),
                new ProductAuthor(product2, author1, AuthorType.WRITER),
                new ProductAuthor(product2, author1, AuthorType.PAINTER));

        productAuthorRepository.saveAll(productAuthors);
    }

//    @PostConstruct
//    public void initForManyToMany() {
//        final Product product1 = new Product("Product 1", "Description 1");
//        final Product product2 = new Product("Product 2", "Description 2");
//
//        final Author author1 = new Author("Author 1");
//        final Author author2 = new Author("Author 2");
//        authorRepository.save(author1);
//        authorRepository.save(author2);
//
//        product1.addAuthor(author1);  // 참조는 저장된 후 해야함
//        product1.addAuthor(author2);
//        product2.addAuthor(author2);
//
//        productRepository.save(product1);
//        productRepository.save(product2);
//    }
}
