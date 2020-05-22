package com.ccc.model;

import java.util.ArrayList;

public class CustomFile {
	
	private String fileName;
	private String filePath;
	private String fileType;
	private boolean isRaw;
	private int lastIndex;
	private ArrayList<Line> lineSet;
	private ControlStructure controlStructure;
	
	public CustomFile(String fileName) {
		this.fileName = fileName;
		this.lineSet = null;
		this.controlStructure = new ControlStructure(fileName);
		this.isRaw=false;
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

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public boolean getIsRaw() {
		return isRaw;
	}
	
	public void setIsRaw(boolean isRaw) {
		this.isRaw = isRaw;
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


	public ControlStructure getControlStructure() {
		return controlStructure;
	}


	public void setControlStructure(ControlStructure controlStructure) {
		this.controlStructure = controlStructure;
	}


	

}
