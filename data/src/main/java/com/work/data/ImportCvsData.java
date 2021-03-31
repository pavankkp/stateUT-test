package com.work.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class ImportCvsData {
	
	
	private File getResource() {
		return new File(this.getClass().getClassLoader().getResource("sample.csv").getFile());
	}

	public static List<String[]> getInputData() throws FileNotFoundException, IOException, CsvException {
		
		ImportCvsData icd = new ImportCvsData();
		 File file =  icd.getResource();
		
		String fileName = file.getAbsolutePath();
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            List<String[]> inputCsvData = reader.readAll();
         // String[] header = inputCsvData.get(0);
         // System.out.println(Arrays.toString(header));
          // r.forEach(x -> System.out.println(Arrays.toString(x)));
          
          return inputCsvData;
        }

	}

}
