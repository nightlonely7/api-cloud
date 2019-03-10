package fpt.edu.vn.sfafinal.controllers;

import fpt.edu.vn.sfafinal.entities.Product;
import fpt.edu.vn.sfafinal.repositories.ProductRepository;
import fpt.edu.vn.sfafinal.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
            @PageableDefault Pageable pageable,
            @RequestParam(name = "searchValue", required = false, defaultValue = "") String searchValue) {
        Page<Product> products = productRepository.findByNameContaining(searchValue, pageable);
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

    @PostMapping("/image")
    public ResponseEntity generateImgURL(@RequestParam("file") MultipartFile multipartFile) {
        String folder = "src/main/resources/static/product-image/";
        int indexOfSlash = multipartFile.getContentType().indexOf("/");
        String imgType = multipartFile.getContentType().substring(indexOfSlash + 1);
        String fileName = "product-" + System.currentTimeMillis() + "." + imgType;
        String imgURL = null;
        try {
            byte[] bytes = multipartFile.getBytes();
            Path path = Paths.get(folder + fileName);
            String serverRequest = "http://localhost:8080/image?name=";
            imgURL = serverRequest + fileName;
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(imgURL);
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

    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity getProductsBySupplier(@PathVariable("supplierId") Integer id) {
        List<Product> products = productService.findAllBySupplier_Id(id);
        return ResponseEntity.ok(products);
    }
}
