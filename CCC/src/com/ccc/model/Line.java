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
	
	private int[] CouplingcolValues;
	private int[] Couplingsum;
	private int CouplingFinalValue;
	
	private int[] InheritancecolValues;
	private int[] Inheritancesum;
	private int InheritanceFinalValue;
	
	private int[] MethodcolValues;
	private int[] Methodsum;
	private int MethodFinalValue;
	
	public Line(int lineNumber, String lineContent) {
		this.lineNumber = lineNumber;
		this.lineContent = lineContent;
		
		this.colValues = new int[13];
		this.sum = new int[13];
		
		this.CouplingcolValues = new int[13];
		this.Couplingsum = new int[13];
		
		this.InheritancecolValues = new int[3];
		this.Inheritancesum = new int[4];
		
		this.MethodcolValues = new int[6];
		this.Methodsum = new int[5];
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
	
	public int getCouplingFinalValue() {
		return CouplingFinalValue;
	}

	public void setCouplingFinalValue() {
		int sum = 0;
		for(int x : this.CouplingcolValues) {
			sum += x;
		}
		this.CouplingFinalValue = sum;
	}

	public int[] getColValues() {
		return colValues;
	}

	public void setColValues(int index, int value) {
		this.colValues[index] = value;
	}
	
	public int[] getCouplingcolValues() {
		return CouplingcolValues;
	}

	public void setCouplingcolValues(int index, int value) {
		this.CouplingcolValues[index] = value;
	}

	public int[] getSum() {
		return sum;
	}

	public void setSum(int index, int value) {
		this.sum[index] = value;
	}
	
	public int[] getCouplingsum() {
		return Couplingsum;
	}

	public void setCouplingsum(int index, int value) {
		this.Couplingsum[index] = value;
	}

	public int[] getInheritancecolValues() {
		return InheritancecolValues;
	}

	public void setInheritancecolValues(int index, int value) {
		this.InheritancecolValues[index] = value;
	}

	public int[] getInheritancesum() {
		return Inheritancesum;
	}

	public void setInheritancesum(int index, int value) {
		this.Inheritancesum[index] = value;
	}

	public int getInheritanceFinalValue() {
		return InheritanceFinalValue;
	}

	public void setInheritanceFinalValue() {
		int sum = 0;
		for(int x : this.InheritancecolValues) {
			sum += x;
		}
		this.InheritanceFinalValue = sum;
	}

	public int[] getMethodcolValues() {
		return MethodcolValues;
	}

	public void setMethodcolValues(int index, int value) {
		this.MethodcolValues[index] = value;
	}

	public int[] getMethodsum() {
		return Methodsum;
	}

	public void setMethodsum(int index, int value) {
		this.Methodsum[index] = value;
	}

	public int getMethodFinalValue() {
		return MethodFinalValue;
	}

	public void setMethodFinalValue() {
		int sum = 0;
		for(int x : this.MethodcolValues) {
			sum += x;
		}
		this.MethodFinalValue = sum;
	}
	
}
