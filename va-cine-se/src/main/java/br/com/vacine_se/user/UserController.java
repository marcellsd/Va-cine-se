package br.com.vacine_se.user;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService service;
    
    UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    Iterable<User> all() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    User one(@PathVariable String id) {
        return service.find(id).orElseThrow();
    }

    @PostMapping("/users")
    User newUser(@RequestBody User user) {
        return service.create(user);
    }

    @PutMapping("/users/{id}")
    User updateUser(@RequestBody User user, @PathVariable String id) {
        return service.update(id, user);
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable String id) {
        service.delete(id);
    }
    
    
}
