package com.work.data;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVWriter;

public class ExportCsvData {
	
	private static final String DATE_FORMAT = "dd-MMM-yy";

	private static final String MIN_DATE = "01-JAN-80";
	
	private static final SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT); 
	 
	

	public static void export(List<String[]> inputData, Map<String, String> mapdata) throws Exception {
	
			List<String[]> csvData = createCsvData(inputData,mapdata);

        // default all fields are enclosed in double quotes
        // default separator is a comma
			try (CSVWriter writer = new CSVWriter(new FileWriter("output"+System.currentTimeMillis()+".csv"))) {
	            writer.writeAll(csvData);
	        }
	}

	private static List<String[]> createCsvData(List<String[]> inputData, Map<String, String> mapdata) throws Exception {
		
		String[] header = inputData.get(0);
		System.out.println("Before convert ");
		Arrays.stream(header).forEach(e->System.out.print(e+" "));
		List<String> columnsdata = new ArrayList<>();
		List<Integer> columnCount =new ArrayList<>();
		
		for (int i=0; i<header.length; i++) {
			if(mapdata.get(header[i])!= null) {
				columnsdata.add(mapdata.get(header[i]));
				columnCount.add(i);
			}
		}
		Date minDate = format.parse(MIN_DATE);
		
		Date maxDate = format.parse(format.format(new Date()));
		
        List<String[]> list = new ArrayList<>();
        list.add(columnsdata.toArray(new String[0]));
        System.out.println("\nAfter convert");
        columnsdata.forEach(e-> System.out.print(e+" "));
         for(int i=1; i< inputData.size(); i++) {
        	 
        	 String[] rcd = inputData.get(i);
        	 String[] newRcd = new String[columnCount.size()];
        	 for (int j=0; j<columnCount.size();j++) {
        		String columndt = rcd[columnCount.get(j)];
        		if(isDateFormat(columndt)) {
        			Date date = format.parse(columndt);
        			if(date.compareTo(minDate) >= 0 && date.compareTo(maxDate) <= 0) {
        				newRcd[j]= format.format(date);
        			}else {
        				newRcd[j]= "Date not in range";
        				
        			}
        			
        		}else {
        			 newRcd[j]= columndt.toLowerCase();
        		}
			}
        	 list.add(newRcd); 
         }
        return list;
	}

	private static boolean isDateFormat(String columndt) {
		try {
		format.parse(columndt);
		}
		catch (Exception e) {
		 return false;
		}
		return true;
	}

}
