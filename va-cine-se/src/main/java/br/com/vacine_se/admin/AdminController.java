package br.com.vacine_se.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
    Admin one(@PathVariable int id) {
        return service.find(id).orElseThrow();
    }

    @PostMapping("/admins")
    Admin newAdmin(@Valid @RequestBody Admin admin) {
        return service.create(admin);
    }

    @PutMapping("/admins/{id}")
    Admin updateAdmin(@Valid @RequestBody Admin admin, @PathVariable int id) {
        return service.update(id, admin);
    }

    @DeleteMapping("/admins/{id}")
    void deleteAdmin(@PathVariable int id) {
        service.delete(id);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        Map<String, String> map = new HashMap<>(errors.size());
        errors.forEach((error) -> {
            String key = ((FieldError) error).getField();
            String val = error.getDefaultMessage();
            map.put(key, val);
        });
        return ResponseEntity.badRequest().body(map);
    }
    
}
