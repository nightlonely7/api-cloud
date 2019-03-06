package fpt.edu.vn.sfafinal.controllers;

import fpt.edu.vn.sfafinal.entities.Product;
import fpt.edu.vn.sfafinal.repositories.ProductRepository;
import fpt.edu.vn.sfafinal.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @GetMapping
    public ResponseEntity<Page<Product>> readAll(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "searchValue", required = false, defaultValue = "") String searchValue) {
        Page<Product> products = productRepository.findByNameContaining(searchValue, PageRequest.of(page, 2));
        return ResponseEntity.ok(products);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> readAll() {
        List<Product> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> readById(@PathVariable("id") Integer id) {
        Product product = productService.findById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity create(
            @Valid @RequestBody Product product) {
        product.setId(null);
        Product savedProduct = productService.save(product);
        return ResponseEntity.ok(savedProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(
            @PathVariable("id") Integer id,
            @Valid @RequestBody Product product) {
        product.setId(id);
        Product savedProduct = productService.save(product);
        return ResponseEntity.ok(savedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity getProductsByCategory(@PathVariable("categoryId") Integer id) {
        List<Product> products = productService.findAllByCategory_Id(id);
        return ResponseEntity.ok(products);

    }
}
