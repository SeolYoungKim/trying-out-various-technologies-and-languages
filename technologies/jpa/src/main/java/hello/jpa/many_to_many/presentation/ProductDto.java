package hello.jpa.many_to_many.presentation;

import hello.jpa.many_to_many.domain.AuthorType;
import hello.jpa.many_to_many.domain.Product;
import java.util.List;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class ProductDto {
    private Long id;
    private String name;
    private String description;

    private List<AuthorIdAndNameDto> writers;

    private List<AuthorIdAndNameDto> painters;

    private ProductDto() {
    }

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();

        this.writers = product.getProductAuthors().stream()
                .filter(productAuthor -> productAuthor.getAuthorType() == AuthorType.WRITER)
                .map(AuthorIdAndNameDto::new)
                .toList();

        this.painters = product.getProductAuthors().stream()
                .filter(productAuthor -> productAuthor.getAuthorType() == AuthorType.PAINTER)
                .map(AuthorIdAndNameDto::new)
                .toList();
    }
}
