package br.com.vacine_se.scheduling;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SchedulingController {
    private final SchedulingService service;
    
    SchedulingController(SchedulingService service) {
        this.service = service;
    }

    @GetMapping("/schedulings")
    Iterable<Scheduling> all() {
        return service.findAll();
    }

    @GetMapping("/schedulings/{id}")
    Scheduling one(@PathVariable String id) {
        return service.find(id).orElseThrow();
    }

    @PostMapping("/schedulings")
    Scheduling newScheduling(@RequestBody Scheduling scheduling) {
        return service.create(scheduling);
    }

    @PutMapping("/schedulings/{id}")
    Scheduling updateScheduling(@RequestBody Scheduling scheduling, @PathVariable String id) {
        return service.update(id, scheduling);
    }

    @DeleteMapping("/schedulings/{id}")
    void deleteScheduling(@PathVariable String id) {
        service.delete(id);
    }
    
    
}
