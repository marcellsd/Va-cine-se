package br.com.vacine_se.data;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface DataAquisitionAdapter{
	
	public Data readFile() throws IOException, FileNotFoundException;

}
