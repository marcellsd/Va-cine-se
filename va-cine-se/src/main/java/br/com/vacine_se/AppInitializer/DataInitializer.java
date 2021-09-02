package br.com.vacine_se.AppInitializer;

import br.com.vacine_se.data.DataAquisitionAdapter;
import br.com.vacine_se.data.DataAquisitionCSV;
import br.com.vacine_se.vaccine_distribution.DistributionStrategy;
import br.com.vacine_se.vaccine_distribution.StrategyEqual;

public class DataInitializer {
	private String dataPath;
	private DataAquisitionAdapter dataAquisitionAdapter;
	private DistributionStrategy distributeStrategy;
	
	public DataInitializer(String dataPath) {
		this.dataPath = dataPath;
	}
	
	public DataInitializer() {
		this.dataPath = "/Users/marcellsd/Documents/Va-cine-se/va-cine-se/src/main/java/br/com/vacine_se/data/data.csv";
		this.distributeStrategy = new StrategyEqual();
		this.dataAquisitionAdapter = new DataAquisitionCSV();
	}
	public String getDataPath() {
		return dataPath;
	}

	public DataAquisitionAdapter getDataAquisitionAdapter() {
		return dataAquisitionAdapter;
	}

	public DistributionStrategy getDistributeStrategy() {
		return distributeStrategy;
	}

}
