package fr.ecoleql.utilities;

import java.io.FileReader;
import java.util.List;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class CSV {

	public static List<String[]> getCsvData(String filePath, String separator) {
		List<String[]> allData = null;
		try {
			// Create an object of file reader class with CSV file as a parameter.
			FileReader filereader = new FileReader(filePath);
			// create csvParser object with
			CSVParser parser = new CSVParserBuilder().withSeparator(separator.charAt(0)).build();
			// Create csvReader object with parameter filereader and parser
			CSVReader csvReader = new CSVReaderBuilder(filereader).withCSVParser(parser).build();
			// Read all data at once
			allData = csvReader.readAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allData;
	}

	public static void readDataFromCustomSeparator(String filePath, String separator) {
		try {
			// Create an object of file reader class with CSV file as a parameter.
			FileReader filereader = new FileReader(filePath);
			// create csvParser object with
			CSVParser parser = new CSVParserBuilder().withSeparator(separator.charAt(0)).build();
			// Create csvReader object with parameter filereader and parser
			CSVReader csvReader = new CSVReaderBuilder(filereader).withCSVParser(parser).build();
			// Read all data at once
			List<String[]> allData = csvReader.readAll();
			// Print Data
			for (String[] row : allData) {
				for (String cell : row) {
					System.out.print(cell + "\t");
				}
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
