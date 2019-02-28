package fpt.edu.vn.sfafinal.services;

import fpt.edu.vn.sfafinal.entities.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findById(Integer id);

    Product save(Product product);

    void deleteById(Integer id);
}
