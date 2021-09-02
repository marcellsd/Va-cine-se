package br.com.vacine_se.vaccine_distribution;


import br.com.vacine_se.data.Data;
import br.com.vacine_se.vaccination_site.VaccinationSite;
import br.com.vacine_se.vaccination_site.VaccinationSiteRepository;

public class StrategyEqual implements DistributionStrategy{

	@Override
	public void distribute(Data data, VaccinationSiteRepository vaccinationSites) {
		
		int dosesForEachVaccinationSite = data.getVaccinesQuantity()/vaccinationSites.getAll().size();
		int remain = data.getVaccinesQuantity()%vaccinationSites.getAll().size();
		for(VaccinationSite vacSite : vaccinationSites.getAll()) {
			vacSite.setTotalOfVacines(dosesForEachVaccinationSite);
		}
		if (remain > 0) {
			vaccinationSites.getById(1).orElseThrow().setTotalOfVacines(dosesForEachVaccinationSite+remain);
		}
	}

}