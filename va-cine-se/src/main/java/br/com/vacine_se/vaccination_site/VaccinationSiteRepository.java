package br.com.vacine_se.vaccination_site;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import br.com.vacine_se.InMemoryRepository;

@Repository
public class VaccinationSiteRepository implements InMemoryRepository<VaccinationSite> {

    private List<VaccinationSite> vaccinationSites = new ArrayList<>();

    @Override
    public Optional<VaccinationSite> getById(int id) {
        return Optional.ofNullable(vaccinationSites.get(id));
    }

    @Override
    public Collection<VaccinationSite> getAll() {
        return vaccinationSites.stream()
            .filter(Objects::nonNull)
            .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    @Override
    public VaccinationSite save(VaccinationSite vaccinationSite) {
        vaccinationSites.add(vaccinationSite);
        int index = vaccinationSites.size() - 1;
        vaccinationSite.setId(index);
        return vaccinationSite;
    }

    @Override
    public VaccinationSite update(VaccinationSite vaccinationSite) {
       return vaccinationSites.set(vaccinationSite.getId(), vaccinationSite);
    }

    @Override
    public void delete(VaccinationSite vaccinationSite) {
        vaccinationSites.set(vaccinationSite.getId(), null);
    }
    
}
