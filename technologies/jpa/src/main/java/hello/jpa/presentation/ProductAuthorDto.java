package hello.jpa.presentation;

import hello.jpa.domain.AuthorType;
import hello.jpa.domain.ProductAuthor;
import lombok.Getter;

@Getter
public class ProductAuthorDto {
    private Long id;

    private String productName;

    private String authorName;

    private AuthorType authorType;


    private ProductAuthorDto() {
    }

    public ProductAuthorDto(ProductAuthor productAuthor) {
        this.id = productAuthor.getId();
        this.productName = productAuthor.getProduct().getName();
        this.authorName = productAuthor.getAuthor().getName();
        this.authorType = productAuthor.getAuthorType();
    }
}
