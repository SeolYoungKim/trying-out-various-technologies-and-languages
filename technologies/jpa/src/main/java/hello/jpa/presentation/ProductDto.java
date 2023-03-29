package hello.jpa.presentation;

import hello.jpa.domain.AuthorType;
import hello.jpa.domain.Product;
import hello.jpa.domain.ProductAuthor;
import java.util.List;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class ProductDto {
    private Long id;
    private String name;
    private String description;

    private List<ProductAuthorDto> writers;

    private List<ProductAuthorDto> painters;

    private ProductDto() {
    }

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();

        final List<ProductAuthorDto> productAuthorDtos = product.getProductAuthors().stream()
                .map(ProductAuthorDto::new)
                .toList();

        this.writers = productAuthorDtos.stream()
                .filter(productAuthorDto -> productAuthorDto.getAuthorType() == AuthorType.WRITER)
                .toList();

        this.painters = productAuthorDtos.stream()
                .filter(productAuthorDto -> productAuthorDto.getAuthorType() == AuthorType.PAINTER)
                .toList();
    }
}
