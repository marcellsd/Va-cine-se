package br.com.vacine_se.AppInitializer;

import br.com.vacine_se.data.DataAquisitionAdapter;
import br.com.vacine_se.data.DataAquisitionJSON;
import br.com.vacine_se.vaccine_distribution.DistributionStrategy;
import br.com.vacine_se.vaccine_distribution.StrategyLevel;

public class DataInitializer {
	private DataAquisitionAdapter dataAquisitionAdapter;
	private DistributionStrategy distributeStrategy;
	
	public DataInitializer() {
		this.distributeStrategy = new StrategyLevel();
		this.dataAquisitionAdapter = new DataAquisitionJSON();
	}

	public DataAquisitionAdapter getDataAquisitionAdapter() {
		return dataAquisitionAdapter;
	}

	public DistributionStrategy getDistributeStrategy() {
		return distributeStrategy;
	}

}
