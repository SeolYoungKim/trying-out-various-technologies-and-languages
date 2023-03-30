package hello.jpa.many_to_many.presentation;

import hello.jpa.many_to_many.application.ProductService;
import hello.jpa.many_to_many.domain.Author;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManyToManyController {
    private final ProductService productService;

    public ManyToManyController(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product")
    public ProductDto getProduct() {
        return new ProductDto(productService.getFirstProduct());
    }

    @GetMapping("/author")
    public Author getAuthor() {
        return productService.getFirstAuthor();
    }

    @GetMapping("/product-author")
    public AuthorIdAndNameDto getProductAuthor() {
        return new AuthorIdAndNameDto(productService.getFirstProductAuthor());
    }
}
