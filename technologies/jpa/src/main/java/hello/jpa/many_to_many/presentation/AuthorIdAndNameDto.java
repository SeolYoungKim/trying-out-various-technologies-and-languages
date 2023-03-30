package hello.jpa.many_to_many.presentation;

import hello.jpa.many_to_many.domain.ProductAuthor;
import lombok.Getter;

@Getter
public class AuthorIdAndNameDto {
    private Long id;

    private String authorName;

    private AuthorIdAndNameDto() {
    }

    public AuthorIdAndNameDto(ProductAuthor productAuthor) {
        this.id = productAuthor.getId();
        this.authorName = productAuthor.getAuthor().getName();
    }
}
