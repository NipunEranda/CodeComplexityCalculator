package com.ccc.model;

import java.util.ArrayList;

public class CustomFile {
	
	private String fileName;
	private int lastIndex;
	private ArrayList<Line> lineSet;
	private ArrayList<Line> MethodList;
	private ArrayList<Line> calledMethodList;
	private ArrayList<Line> regularMethods;
	private ArrayList<Line> recursiveMethods;
	private ArrayList<Line> regularToRegularMethods;
	
	public CustomFile(String fileName) {
		this.fileName = fileName;
		this.lineSet = null;
		this.MethodList = null;
		this.calledMethodList = null;
		this.regularMethods = null;
		this.recursiveMethods = null;
		this.regularToRegularMethods = null;
	}

	public String getFileName() {
		return fileName;
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

	public void setMethodList(ArrayList<Line> methodList) {
		this.MethodList = new ArrayList<Line>(methodList);
	}

	public void setCalledMethodList(ArrayList<Line> calledMethodList) {
		this.calledMethodList = new ArrayList<Line>(calledMethodList);
	}

	public void setRegularMethods(ArrayList<Line> regularMethods) {
		this.regularMethods = new ArrayList<Line>(regularMethods);
	}

	public void setRecursiveMethods(ArrayList<Line> recursiveMethods) {
		this.recursiveMethods = new ArrayList<Line>(recursiveMethods);
	}

	public void setRegularToRegularMethods(ArrayList<Line> regularToRegularMethods) {
		this.regularToRegularMethods = new ArrayList<Line>(regularToRegularMethods);
	}

	public ArrayList<Line> getLineSet(){
		return lineSet;
	}
	
	public ArrayList<Line> getMethodList(){
		return MethodList;
	}

	public ArrayList<Line> getCalledMethodList(){
		return calledMethodList;
	}

	public ArrayList<Line> getRegularMethods(){
		return regularMethods;
	}
	
	public ArrayList<Line> getRecursiveMethods(){
		return recursiveMethods;
	}
	
	public ArrayList<Line> getRegularToRegularMethods(){
		return regularToRegularMethods;
	}

}
