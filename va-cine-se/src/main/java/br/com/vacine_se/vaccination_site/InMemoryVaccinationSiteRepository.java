package br.com.vacine_se.vaccination_site;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InMemoryVaccinationSiteRepository extends CrudRepository<VaccinationSite, String>{}

