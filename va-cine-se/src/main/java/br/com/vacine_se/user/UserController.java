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
    private final InMemoryUserRepository repository;
    
    UserController(InMemoryUserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/users")
    Iterable<User> all() {
        return repository.findAll();
    }

    @GetMapping("/users/{id}")
    User one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }

    @PostMapping("/users")
    User newUser(@RequestBody User user) {
        // TODO: resolver problema com ids não geradas automaticamente
        Long id = repository.count() + 1;
        user.setId(id);
        return repository.save(user);
    }

    @PutMapping("/users/{id}")
    User updateUser(@RequestBody User user, @PathVariable Long id) {
        user.setId(id);
        return repository.save(user);
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }
    
    
}
