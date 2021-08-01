package br.com.vacine_se.user;

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
import br.com.vacine_se.vaccination_site.VaccinationSite;
import br.com.vacine_se.vaccination_site.VaccinationSiteService;


@RestController
public class UserController {
    private final UserService userService;
    private final SchedulingService schedulingService;
    private final DistrictService districtService;
    private final VaccinationSiteService vaccinationSiteService;
    
    UserController(UserService userService,
    				SchedulingService schedulingService,
    				DistrictService districtService,
    				VaccinationSiteService vaccinationSiteService) {
        this.userService = userService;
        this.schedulingService = schedulingService;
        this.districtService = districtService;
        this.vaccinationSiteService = vaccinationSiteService;
    }

    @GetMapping("/users")
    Iterable<User> all() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    User one(@PathVariable int id) {
        return userService.find(id).orElseThrow();
    }
 
    @GetMapping("/users/{id}/nearestsVaccinationSites")
    Iterable<VaccinationSite> orderedByProximity(@PathVariable int id){
    	return userService.getNearestVaccinationSites(userService.find(id).orElseThrow(), districtService, vaccinationSiteService);
    }
    
    @PostMapping("/users")
    User newUser(@Valid @RequestBody User user) {
    	int schedId = userService.setUserScheduling(user, districtService, vaccinationSiteService, schedulingService);
        user.setSchedulingId(schedId);
    	return userService.create(user);
    }

    @PutMapping("/users/{id}")
    User updateUser(@Valid @RequestBody User user, @PathVariable int id) {
        return userService.update(id, user);
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable int id) {
    	userService.delete(id);
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
    

