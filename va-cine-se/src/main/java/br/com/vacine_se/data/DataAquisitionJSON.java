package br.com.vacine_se.data;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileNotFoundException;



public class DataAquisitionJSON implements DataAquisitionAdapter{

	@Override
	public Data readFile() throws IOException, FileNotFoundException{
		Data data = new Data();
		JSONParser parser = new JSONParser();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		try {
			JSONArray arr = (JSONArray) parser.parse(new FileReader("/Users/marcellsd/Documents/Va-cine-se/va-cine-se/src/main/java/br/com/vacine_se/data/data.json"));
			for (Object obj : arr){
				JSONObject dataJSON = (JSONObject) obj;
				data.setVaccinesQuantity(Integer.parseInt((String) dataJSON.get("quantidadeDeDoses")));
				String dateOfArrival = (String) dataJSON.get("dataDeChegada");
				data.setDateOfArrival(LocalDate.parse(dateOfArrival, formatter));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

}
