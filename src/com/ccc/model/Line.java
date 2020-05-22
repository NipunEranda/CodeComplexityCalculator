package com.ccc.model;

public class Line {
	
	private CustomFile file;
	private String fileName;
	private int lineNumber;
	private String lineContent;
	private int endLineNumber;
	private String objName;
	private int[] colValues;
	private int[] sum;
	private int finalValue;
	
	public Line(int lineNumber, String lineContent) {
		this.lineNumber = lineNumber;
		this.lineContent = lineContent;
		this.colValues = new int[13];
		this.sum = new int[13];
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

	public int getFinalValue() {
		return finalValue;
	}

	public void setFinalValue() {
		int sum = 0;
		for(int x : this.colValues) {
			sum += x;
		}
		this.finalValue = sum;
	}

	public int[] getColValues() {
		return colValues;
	}

	public void setColValues(int index, int value) {
		this.colValues[index] = value;
	}

	public int[] getSum() {
		return sum;
	}

	public void setSum(int index, int value) {
		this.sum[index] = value;
	}
	
}
