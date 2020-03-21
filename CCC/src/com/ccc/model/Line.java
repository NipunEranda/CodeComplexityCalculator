package com.ccc.model;

public class Line {
	
	private CustomFile file;
	private String fileName;
	private int lineNumber;
	private String lineContent;
	private int endLineNumber;
	private String objName;
	
	public Line(int lineNumber, String lineContent) {
		this.lineNumber = lineNumber;
		this.lineContent = lineContent;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public CustomFile getFile() {
		return file;
	}

	public void setFile(CustomFile file) {
		this.file = file;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getLineContent() {
		return lineContent;
	}

	public void setLineContent(String lineContent) {
		this.lineContent = lineContent;
	}

	public int getEndLineNumber() {
		return endLineNumber;
	}

	public void setEndLineNumber(int endLineNumber) {
		this.endLineNumber = endLineNumber;
	}

	public String getObjName() {
		return objName;
	}

	public void setObjName(String objName) {
		this.objName = objName;
	}
	

}
