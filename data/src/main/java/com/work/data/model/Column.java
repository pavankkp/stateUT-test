package com.work.data.model;

public class Column {
	
	private String rawName;
	
	private String commonName;

	public String getRawName() {
		return rawName;
	}

	public void setRawName(String rawName) {
		this.rawName = rawName;
	}

	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Column [rawName=");
		builder.append(rawName);
		builder.append(", commonName=");
		builder.append(commonName);
		builder.append("]");
		return builder.toString();
	}
	
	

}
