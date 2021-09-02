package br.com.vacine_se.vaccine_distribution;


import br.com.vacine_se.data.Data;
import br.com.vacine_se.vaccination_site.VaccinationSiteRepository;

public interface DistributionStrategy {
	public void distribute(Data data, VaccinationSiteRepository repository);
}
