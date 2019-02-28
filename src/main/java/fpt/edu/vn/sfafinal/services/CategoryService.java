package fpt.edu.vn.sfafinal.services;

import fpt.edu.vn.sfafinal.entities.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category findById(Integer id);

    void save(Category category);

    void deleteById(Integer id);
}
