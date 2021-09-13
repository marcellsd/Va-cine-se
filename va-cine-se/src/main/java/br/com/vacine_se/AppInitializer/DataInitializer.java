package br.com.vacine_se.AppInitializer;

import br.com.vacine_se.data.DataAquisitionAdapter;
import br.com.vacine_se.data.DataAquisitionCSV;
import br.com.vacine_se.vaccine_distribution.DistributionStrategy;
import br.com.vacine_se.vaccine_distribution.StrategyEqual;

public class DataInitializer {
	private DataAquisitionAdapter dataAquisitionAdapter;
	private DistributionStrategy distributeStrategy;
	
	public DataInitializer() {
		this.distributeStrategy = new StrategyEqual();
		this.dataAquisitionAdapter = new DataAquisitionCSV();
	}

	public DataAquisitionAdapter getDataAquisitionAdapter() {
		return dataAquisitionAdapter;
	}

	public DistributionStrategy getDistributeStrategy() {
		return distributeStrategy;
	}

}
