package br.com.vacine_se.data;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;



public class DataAquisitionXLS implements DataAquisitionAdapter{

	@Override
	public Data readFile() throws IOException, FileNotFoundException{
		Data data = new Data();
		String filePath = "C:\\UFRN\\PDS\\Va-cine-se\\va-cine-se\\src\\main\\java\\br\\com\\vacine_se\\data\\data.xls";

		FileInputStream fis = new FileInputStream(new File(filePath));
		HSSFWorkbook wb = new HSSFWorkbook(fis);
		HSSFSheet sheet = wb.getSheetAt(0);
		data.setVaccinesQuantity((int)sheet.getRow(1).getCell(0).getNumericCellValue());
		String date = sheet.getRow(1).getCell(1).getStringCellValue();
		data.setDateOfArrival(LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy")));

		return data;
	}

}
