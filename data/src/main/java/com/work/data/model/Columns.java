package com.work.data.model;

import java.util.List;
import java.util.Map;

public class Columns {
	
	
	List<Map<String, String>> columns;

	public List<Map<String, String>> getColumns() {
		return columns;
	}

	public void setColumns(List<Map<String, String>> columns) {
		this.columns = columns;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(columns);
		
		return builder.toString();
	}

	

	

}
