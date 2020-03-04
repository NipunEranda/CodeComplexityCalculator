package com.ccc.model;

import java.util.ArrayList;

public class CustomFile {
	
	private String fileName;
	private String filePath;
	private int lastIndex;
	private ArrayList<Line> lineSet;
	private Coupling coupling;
	
	public CustomFile(String fileName) {
		this.fileName = fileName;
		this.lineSet = null;
		this.coupling = new Coupling(fileName);
	}

	public String getFileName() {
		return fileName;
	}
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public int getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	public void setLineSet(ArrayList<Line> lineSet) {
		this.lineSet = new ArrayList<Line>(lineSet);
	}
	
	public ArrayList<Line> getLineSet(){
		return lineSet;
	}

	public Coupling getCoupling() {
		return coupling;
	}

	public void setCoupling(Coupling coupling) {
		this.coupling = coupling;
	}
	
	

}
