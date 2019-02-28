package fpt.edu.vn.sfafinal.repositories;

import fpt.edu.vn.sfafinal.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
