package br.com.vacine_se.AppInitializer;

import br.com.vacine_se.data.DataAquisitionAdapter;
import br.com.vacine_se.data.DataAquisitionXLS;
import br.com.vacine_se.district.DistrictService;
import br.com.vacine_se.vaccination_site.VaccinationSiteService;
import br.com.vacine_se.vaccine_distribution.DistributionStrategy;
import br.com.vacine_se.vaccine_distribution.StrategyPopulationProportional;

public class DataInitializer {
	private DataAquisitionAdapter dataAquisitionAdapter;
	private DistributionStrategy distributeStrategy;
    private DistrictService districtService;
    private VaccinationSiteService vacinationSiteService;
	
	public DataInitializer(DistrictService ds, VaccinationSiteService vs) {
		this.distributeStrategy = new StrategyPopulationProportional(ds, vs);
		this.dataAquisitionAdapter = new DataAquisitionXLS();
	}

	public DataAquisitionAdapter getDataAquisitionAdapter() {
		return dataAquisitionAdapter;
	}

	public DistributionStrategy getDistributeStrategy() {
		return distributeStrategy;
	}

}
