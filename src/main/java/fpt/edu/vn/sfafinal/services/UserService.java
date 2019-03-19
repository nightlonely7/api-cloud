package fpt.edu.vn.sfafinal.services;

import fpt.edu.vn.sfafinal.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {


    List<User> findAll();

    User findById(Integer id);

    void save(User user);

    void deleteById(Integer id);

    boolean isExist(String username);

}
