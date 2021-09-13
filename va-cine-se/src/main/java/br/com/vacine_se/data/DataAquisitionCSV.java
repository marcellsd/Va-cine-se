package br.com.vacine_se.data;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;



public class DataAquisitionCSV implements DataAquisitionAdapter{

	@Override
	public Data readFile() throws IOException, FileNotFoundException{
		String line;
		Data data = new Data();
		try (BufferedReader br = new BufferedReader(new FileReader("C:\\UFRN\\PDS\\Va-cine-se\\va-cine-se\\src\\main\\java\\br\\com\\vacine_se\\data\\data.csv"))){
			line = br.readLine();
			String[] split = line.split(",");
			data.setVaccinesQuantity(Integer.parseInt(split[0]));
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
			data.setDateOfArrival(LocalDate.parse(split[1], formatter));
		}
		
		return data;
	}

}
