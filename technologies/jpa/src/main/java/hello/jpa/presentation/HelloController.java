package hello.jpa.presentation;

import hello.jpa.application.ProductService;
import hello.jpa.domain.Author;
import hello.jpa.domain.Product;
import hello.jpa.domain.ProductAuthor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private final ProductService productService;

    public HelloController(final ProductService productService) {
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
    public ProductAuthorDto getProductAuthor() {
        return new ProductAuthorDto(productService.getFirstProductAuthor());
    }
}
