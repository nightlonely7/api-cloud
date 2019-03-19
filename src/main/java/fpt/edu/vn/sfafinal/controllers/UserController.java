package fpt.edu.vn.sfafinal.controllers;

import fpt.edu.vn.sfafinal.entities.User;
import fpt.edu.vn.sfafinal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> readAll() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> readById(@PathVariable("id") Integer id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/check")
    public ResponseEntity checkUsernameExist(@RequestParam("username") String username) {
        return ResponseEntity.ok(userService.isExist(username));
    }

    @PostMapping
    public ResponseEntity create(
            @Valid @RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(
            @PathVariable("id") Integer id,
            @Valid @RequestBody User user) {
        user.setId(id);
        userService.save(user);
        return ResponseEntity.ok().build();
    }
}
