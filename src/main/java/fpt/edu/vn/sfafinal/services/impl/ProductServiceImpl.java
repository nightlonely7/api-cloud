package fpt.edu.vn.sfafinal.services.impl;

import fpt.edu.vn.sfafinal.entities.Category;
import fpt.edu.vn.sfafinal.entities.Product;
import fpt.edu.vn.sfafinal.exceptions.NotFoundException;
import fpt.edu.vn.sfafinal.repositories.CategoryRepository;
import fpt.edu.vn.sfafinal.repositories.ProductRepository;
import fpt.edu.vn.sfafinal.repositories.SupplierRepository;
import fpt.edu.vn.sfafinal.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository,
                              SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Product save(Product product) {

        if (!categoryRepository.existsById(product.getCategory().getId())) {
            throw new NotFoundException("Category not found!");
        }
        if (!supplierRepository.existsById(product.getSupplier().getId())) {
            throw new NotFoundException("Supplier not found!");
        }

        return productRepository.save(product);
    }

    @Override
    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findAllByCategory_Id(Integer categoryId) {
        return productRepository.findAllByCategory_Id(categoryId);
    }
}
