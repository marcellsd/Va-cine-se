package br.com.vacine_se.scheduling;

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
public class SchedulingController {
    private final SchedulingServiceGripe service;
    
    SchedulingController(SchedulingServiceGripe service) {
        this.service = service;
    }

    @GetMapping("/schedulings")
    Iterable<Scheduling> all() {
        return service.findAll();
    }

    @GetMapping("/schedulings/{id}")
    Scheduling one(@PathVariable int id) {
        return service.find(id).orElseThrow();
    }

    @PostMapping("/schedulings")
    Scheduling newScheduling(@Valid @RequestBody Scheduling scheduling) {
        return service.create(scheduling);
    }

    @PutMapping("/schedulings/{id}")
    Scheduling updateScheduling(@Valid @RequestBody Scheduling scheduling, @PathVariable int id) {
        return service.update(id, scheduling);
    }

    @DeleteMapping("/schedulings/{id}")
    void deleteScheduling(@PathVariable int id) {
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
