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

import br.com.vacine_se.district.DistrictService;
import br.com.vacine_se.scheduling.SchedulingService;
import br.com.vacine_se.user.UserService;

@RestController
public class VaccinationSiteController {
    private final VaccinationSiteService vaccinationSiteService;
    private final UserService userService;
    private final SchedulingService schedulingService;
    
    VaccinationSiteController(VaccinationSiteService vaccinationSiteService, UserService userService, SchedulingService schedulingService) {
        this.vaccinationSiteService = vaccinationSiteService;
        this.userService = userService;
        this.schedulingService = schedulingService;
    }

    @GetMapping("/vaccinationSites")
    Iterable<VaccinationSite> all() {
        return vaccinationSiteService.findAll();
    }

    @GetMapping("/vaccinationSites/{id}")
    VaccinationSite one(@PathVariable int id) {
        return vaccinationSiteService.find(id).orElseThrow();
    }
    @GetMapping("/vaccinationSites/{vaccinationSiteId}/applydose/{userId}")
    String applyVaccine(@PathVariable int vaccinationSiteId, @PathVariable int userId) {
    	return vaccinationSiteService.applyVaccine(vaccinationSiteId, userId, userService, schedulingService);
    }
    

    @PostMapping("/vaccinationSites")
    VaccinationSite newVaccinationSite(@Valid @RequestBody VaccinationSite vaccinationSite) {
        return vaccinationSiteService.create(vaccinationSite);
    }

    @PutMapping("/vaccinationSites/{id}")
    VaccinationSite updateVaccinationSite(@Valid @RequestBody VaccinationSite vaccinationSite, @PathVariable int id) {
        return vaccinationSiteService.update(id, vaccinationSite);
    }

    @DeleteMapping("/vaccinationSites/{id}")
    void deleteVaccinationSite(@PathVariable int id) {
    	vaccinationSiteService.delete(id);
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
