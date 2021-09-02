package br.com.vacine_se.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
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
    ResponseEntity<User> one(@PathVariable int id) {
    	try {
    		return new ResponseEntity<>(userService.find(id).orElseThrow(), HttpStatus.OK);
    	} catch(NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch(IndexOutOfBoundsException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }
 
    @GetMapping("/users/{id}/nearestsVaccinationSites")
    ResponseEntity<Iterable<VaccinationSite>> getNearests(@PathVariable int id){
    	try{
    		return new ResponseEntity<>(userService.getNearestVaccinationSites(userService.find(id).orElseThrow(), districtService, vaccinationSiteService), HttpStatus.OK);
    	} catch(NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch(IndexOutOfBoundsException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }
    
    @PostMapping("/users")
    ResponseEntity<User> newUser(@Valid @RequestBody User user) {
        try {
        	return new ResponseEntity<>(userService.create(user), HttpStatus.OK);
        } catch(Exception e) {
        	return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/users/{id}")
    ResponseEntity<User> updateUser(@Valid @RequestBody User user, @PathVariable int id) {
        try {
        	return new ResponseEntity<>(userService.update(id, user), HttpStatus.OK);
		} catch(NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch(IndexOutOfBoundsException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }
    @DeleteMapping("/users/{id}")
    ResponseEntity<Boolean> deleteUser(@PathVariable int id) {
    	try {
    		return new ResponseEntity<>(userService.delete(id),HttpStatus.OK);
    	}catch(NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch(IndexOutOfBoundsException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    
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
    

