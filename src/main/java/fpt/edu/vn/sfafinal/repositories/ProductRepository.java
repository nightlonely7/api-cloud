package fpt.edu.vn.sfafinal.repositories;

import fpt.edu.vn.sfafinal.entities.Category;
import fpt.edu.vn.sfafinal.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Page<Product> findByNameContaining(String name, Pageable pageable);

    List<Product> findAllByCategory_Id(Integer categoryId);

    List<Product> findAllBySupplier_Id(Integer supplierId);
}
