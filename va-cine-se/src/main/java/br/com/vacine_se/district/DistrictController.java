package br.com.vacine_se.district;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DistrictController {
    private final DistrictService service;
    
    DistrictController(DistrictService service) {
        this.service = service;
    }

    @GetMapping("/districts")
    Iterable<District> all() {
        return service.findAll();
    }

    @GetMapping("/districts/{id}")
    District one(@PathVariable Long id) {
        return service.find(id).orElseThrow();
    }
    
}
