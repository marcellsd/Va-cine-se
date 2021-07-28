package br.com.vacine_se.notification;

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
public class NotificationController {
    private final NotificationService service;
    
    NotificationController(NotificationService service) {
        this.service = service;
    }

    @GetMapping("/notifications")
    Iterable<Notification> all() {
        return service.findAll();
    }

    @GetMapping("/notifications/{id}")
    Notification one(@PathVariable int id) {
        return service.find(id).orElseThrow();
    }

    @PostMapping("/notifications")
    Notification newNotification(@Valid @RequestBody Notification notification) {
        return service.create(notification);
    }

    @PutMapping("/notifications/{id}")
    Notification updateNotification(@Valid @RequestBody Notification notification, @PathVariable int id) {
        return service.update(id, notification);
    }

    @DeleteMapping("/notifications/{id}")
    void deleteNotification(@PathVariable int id) {
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
