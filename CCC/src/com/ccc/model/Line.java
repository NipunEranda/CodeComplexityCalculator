package com.ccc.model;

public class Line {
	
	private int lineNumber;
	private String lineContent;
	private int endLineNumber;
	
	public Line(int lineNumber, String lineContent) {
		this.lineNumber = lineNumber;
		this.lineContent = lineContent;
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
	

}
