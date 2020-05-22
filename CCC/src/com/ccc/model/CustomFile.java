package com.ccc.model;

import java.util.ArrayList;

public class CustomFile {
	
	private String fileName;
	private String filePath;
	private String fileType;
	private boolean isRaw;
	private int lastIndex;
	private ArrayList<Line> lineSet;
	private Coupling coupling;
	private Inheritance inheritance;
	private ControlStructure controlStructure;
	private Size size;
	private Variables variables;
	private Method method;
	
	public CustomFile(String fileName) {
		this.fileName = fileName;
		this.lineSet = null;
		this.coupling = new Coupling(fileName);
		this.inheritance = new Inheritance(fileName);
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

	public Coupling getCoupling() {
		return coupling;
	}

	public void setCoupling(Coupling coupling) {
		this.coupling = coupling;
	}
	
	public Inheritance getInheritance() {
		return inheritance;
	}

	public void setInheritance(Inheritance inheritance) {
		this.inheritance = inheritance;
	}
	
	public ControlStructure getControlStructure() {
		return controlStructure;
	}
	
	public void setControlStructure(ControlStructure controlStructure) {
		this.controlStructure = controlStructure;
	}
	
	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public Variables getVariables() {
		return variables;
	}

	public void setVariables(Variables variables) {
		this.variables = variables;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}
	

}
