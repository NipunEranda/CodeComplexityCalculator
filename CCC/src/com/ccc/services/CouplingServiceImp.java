package com.ccc.services;

import java.util.ArrayList;

import com.ccc.model.Coupling;
import com.ccc.model.FileRead;
import com.ccc.model.Line;
import com.ccc.util.LineArrayListToArray;

public class CouplingServiceImp implements CouplingService {

	@Override
	public String getMethodName(String line) {
		if (line.contains("main") || line.contains("class") || line.contains("if") || line.contains("switch")
				|| line.contains("catch") || line.contains("return") || line.contains(";")) {

		} else if (line.contains("public")) {
			String[] sub = line.split("\\(");
			String x = sub[0].replace("public", "").replace("private", "").replace("protected", "")
					.replace("static", "").replace("final", "").trim();
			if (x.split(" ").length > 1) {
				return x.split(" ")[1];
			} else {
				return x;
			}
		}
		return null;
	}

	@Override
	public ArrayList<String> getMethodSet() {

		ArrayList<String> methodList = new ArrayList<>();

		int count = 1;

		for (String line : Main.programLineList) {

			if (line.contains("main") || line.contains("class") || line.contains("if") || line.contains("switch")
					|| line.contains("catch") || line.contains("return") || line.contains(";")) {

			} else if (line.contains("public")) {
				String[] sub = line.split("\\(");
				String x = sub[0].replace("public", "").replace("private", "").replace("protected", "")
						.replace("static", "").replace("final", "").trim();
				if (x.split(" ").length > 1) {
					System.out.println(x.split(" ")[1]);
					Main.functionList.add(new Line(count, line));
					methodList.add(x.split(" ")[1]);
				} else {
					System.out.println(x);
					Main.functionList.add(new Line(count, line));
					methodList.add(x);
				}
			}
			count++;
		}
		return methodList;
	}

	@Override
	public ArrayList<String> getCalledMethodSet() {

		ArrayList<String> calledMethodList = new ArrayList<>();
		int count = 1;
		for (String line : Main.programLineList) {

			for (String method : Main.methodList) {
				if (line.contains("." + method)) {
					System.out.println("Called Method List -> " + method);
					calledMethodList.add(method);
					Main.methodCallList.add(new Line(count, line));
				}
			}
			count++;
		}
		return calledMethodList;
	}

	
	@Override
	public void getRegularMethods() {
		
		for(Line alLine : Main.functionList) {
			
			for(Line recLine : Main.recursiveMethodList) {
				if(alLine.getLineNumber() != recLine.getLineNumber()) {
					Main.regularMethodList.add(alLine);
				}
			}
		}
		
	}

	// Senario 1
	@Override
	public void getRecursiveMethods() {

		Line[] lineList = LineArrayListToArray.convert(Main.functionList);

		for (int i = 0; i <= lineList.length - 1; i++) {
			if (i == lineList.length-1) {
				for (int j = lineList[i].getLineNumber()+1; j <= Main.lastLineNumber; ++j) {
					
					if(Main.programLineList.get(j-1).contains(getMethodName(lineList[i].getLineContent()))) {
						Main.recursiveMethodList.add(lineList[i]);
						System.out.println("Recursive Method" + lineList[i].getLineNumber());
					}
					
				}
			} else {
				for (int j = lineList[i].getLineNumber()+1; j < lineList[i + 1].getLineNumber(); ++j) {
					
					if(Main.programLineList.get(j).contains(getMethodName(lineList[i].getLineContent()))) {
						Main.recursiveMethodList.add(lineList[i]);
						System.out.println("Recursive Method" + lineList[i].getLineNumber());
					}
					
				}
			}
			
		}

	}
	
	

	//Senario 2
	@Override
	public void getRegToReg() {
		
		Line[] lineList = LineArrayListToArray.convert(Main.regularMethodList);
		
		for (int i = 0; i <= lineList.length - 1; i++) {
			if (i == lineList.length-1) {
				for (int j = lineList[i].getLineNumber()+1; j <= Main.lastLineNumber; ++j) {
					
					if(Main.programLineList.get(j-1).contains(getMethodName(lineList[i].getLineContent()))) {
						Main.regToReg.add(lineList[i]);
						System.out.println("Recursive Method" + lineList[i].getLineNumber());
					}
					
				}
			} else {
				for (int j = lineList[i].getLineNumber()+1; j < lineList[i + 1].getLineNumber(); ++j) {
					
					if(Main.programLineList.get(j).contains(getMethodName(lineList[i].getLineContent()))) {
						Main.regToReg.add(lineList[i]);
						System.out.println("Recursive Method" + lineList[i].getLineNumber());
					}
					
				}
			}
			
		}
		
	}

	


}
