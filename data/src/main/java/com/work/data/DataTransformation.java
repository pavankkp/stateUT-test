package com.work.data;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.exceptions.CsvException;
import com.work.data.model.Columns;
@RestController
public class DataTransformation {

	private static final Map<String, String> mapData =new HashMap<>();
	
	
	private File getResource() {
		return new File(this.getClass().getClassLoader().getResource("mappings.json").getFile());
	}

	
	@GetMapping(value = "/")
	public String getUrl() {
		
		return "http://localhost:8080/start";
		
	}
	
	@GetMapping(value = "/start")
	public String start() throws Exception {
		
		main(null);
		return "Job Successfully completed.";
		
	}
	
	
	public static void main(String[] args) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		
		DataTransformation tr= new DataTransformation();
		File file = tr.getResource();

		try {

			// Convert JSON string from file to Object 
			
			Columns columns =
			mapper.readValue(file,
					Columns.class);
			System.out.println(columns);
			
			List<Map<String, String>> clms = columns.getColumns();
			for (Map<String, String> map : clms) {
				Iterator<String> it = map.keySet().iterator();        
				while(it.hasNext())  
				{  
				String key=it.next();  
			//	System.out.println("Key : "+key+"     Value : "+map.get(key));  
				mapData.put(key, map.get(key));
				} 
			}
					//System.out.println(mapData);
				
					
				List<String[]> inputData = ImportCvsData.getInputData();
				ExportCsvData.export(inputData,mapData);	
				
			System.out.println("\nsuccussfully completed");	
				
			
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
