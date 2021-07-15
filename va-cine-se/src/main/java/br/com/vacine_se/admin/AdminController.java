package br.com.vacine_se.admin;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    private final AdminService service;
    
    AdminController(AdminService service) {
        this.service = service;
    }

    @GetMapping("/admins")
    Iterable<Admin> all() {
        return service.findAll();
    }

    @GetMapping("/admins/{id}")
    Admin one(@PathVariable String id) {
        return service.find(id).orElseThrow();
    }

    @PostMapping("/admins")
    Admin newAdmin(@RequestBody Admin admin) {
        return service.create(admin);
    }

    @PutMapping("/admins/{id}")
    Admin updateAdmin(@RequestBody Admin admin, @PathVariable String id) {
        return service.update(id, admin);
    }

    @DeleteMapping("/admins/{id}")
    void deleteAdmin(@PathVariable String id) {
        service.delete(id);
    }
    
    
}
