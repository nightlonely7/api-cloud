package fpt.edu.vn.sfafinal.repositories;

import fpt.edu.vn.sfafinal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    Boolean existsByUsername(String username);
    User findByUsername(String name);
}
