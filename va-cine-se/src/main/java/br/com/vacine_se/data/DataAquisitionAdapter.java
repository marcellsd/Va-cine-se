package br.com.vacine_se.data;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface DataAquisitionAdapter{
	//public Data data = new Data();
	
	public Data readFile(String file) throws IOException, FileNotFoundException;

}
