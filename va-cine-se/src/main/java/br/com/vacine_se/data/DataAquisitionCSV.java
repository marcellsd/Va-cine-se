package br.com.vacine_se.data;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;



public class DataAquisitionCSV implements DataAquisitionAdapter{

	@Override
	public Data readFile(String file) throws IOException, FileNotFoundException{
		String line;
		try (BufferedReader br = new BufferedReader(new FileReader(file))){
			line = br.readLine();
			String[] split = line.split(",");
			DataAquisitionAdapter.data.setVaccinesQuantity(Integer.parseInt(split[0]));
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
			DataAquisitionAdapter.data.setDateOfArrival(LocalDate.parse(split[1], formatter));
		}
		
		return DataAquisitionAdapter.data;
	}

}
