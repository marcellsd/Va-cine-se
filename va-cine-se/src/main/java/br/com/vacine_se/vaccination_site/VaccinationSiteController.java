package br.com.vacine_se.vaccination_site;

import org.springframework.web.bind.annotation.DeleteMapping;
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
    VaccinationSite newVaccinationSite(@RequestBody VaccinationSite vaccinationSite) {
        return service.create(vaccinationSite);
    }

    @PutMapping("/vaccinationSites/{id}")
    VaccinationSite updateVaccinationSite(@RequestBody VaccinationSite vaccinationSite, @PathVariable String id) {
        return service.update(id, vaccinationSite);
    }

    @DeleteMapping("/vaccinationSites/{id}")
    void deleteVaccinationSite(@PathVariable String id) {
        service.delete(id);
    }
    
    
}
