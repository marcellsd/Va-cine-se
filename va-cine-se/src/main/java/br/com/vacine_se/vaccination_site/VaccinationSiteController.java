package br.com.vacine_se.vaccination_site;

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
public class VaccinationSiteController {
    private final VaccinationSiteService service;
    
    VaccinationSiteController(VaccinationSiteService service) {
        this.service = service;
    }

    @GetMapping("/vaccinationSites")
    Iterable<VaccinationSite> all() {
        return service.findAll();
    }

    @GetMapping("/vaccinationSites/{id}")
    VaccinationSite one(@PathVariable String id) {
        return service.find(id).orElseThrow();
    }

    @PostMapping("/vaccinationSites")
    VaccinationSite newVaccinationSite(@Valid @RequestBody VaccinationSite vaccinationSite) {
        return service.create(vaccinationSite);
    }

    @PutMapping("/vaccinationSites/{id}")
    VaccinationSite updateVaccinationSite(@Valid @RequestBody VaccinationSite vaccinationSite, @PathVariable String id) {
        return service.update(id, vaccinationSite);
    }

    @DeleteMapping("/vaccinationSites/{id}")
    void deleteVaccinationSite(@PathVariable String id) {
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
