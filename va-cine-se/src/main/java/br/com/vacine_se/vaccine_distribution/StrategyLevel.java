package br.com.vacine_se.vaccine_distribution;


import br.com.vacine_se.data.Data;
import br.com.vacine_se.vaccination_site.VaccinationSite;
import br.com.vacine_se.vaccination_site.VaccinationSiteRepository;

public class StrategyLevel implements DistributionStrategy{

	@Override
	public void distribute(Data data, VaccinationSiteRepository vaccinationSites) {
		int level = 20;
		int quantityOfDoses = data.getVaccinesQuantity();
		for(VaccinationSite vacSite : vaccinationSites.getAll()) {
			int dosesForMinLevel = level - vacSite.getTotalOfVaccines();	
			if (quantityOfDoses>dosesForMinLevel && vacSite.getTotalOfVaccines() < level ) {
				vacSite.setTotalOfVacines(dosesForMinLevel);
				quantityOfDoses -= dosesForMinLevel;
			}
		}
		data.setVaccinesQuantity(quantityOfDoses);
	}

}