package com.ccc.services;

import java.util.ArrayList;

import com.ccc.model.FileRead;
import com.ccc.model.Line;

public class Main {
	
	public static ArrayList<Line> regToReg = new ArrayList<>();
	
	public static ArrayList<Line> recursiveMethodList = new ArrayList<>();
	public static ArrayList<Line> regularMethodList = new ArrayList<>();
	
	public static String fileType = "";
	public static int lastLineNumber = 0;
	public static ArrayList<String> programLineList = new ArrayList<>();
	public static ArrayList<String> methodList;
	public static ArrayList<Line> functionList = new ArrayList<Line>();
	public static ArrayList<Line> methodCallList = new ArrayList<Line>();
	public static ArrayList<String> calledMethodList;
	public static String WEBCONTENTDIR = "git/CodeComplexityCalculator/CCC/WebContent/";
	
	public static void run(String fName) {
		
		if(!(fName.contains("java") || fName.contains("cpp"))) {
			System.out.println("Wrong file type");
		}else{
			
			if(fName.contains("java")) {
				fileType = "java";
			}else {
				fileType = "cpp";
			}
			
			FileRead fileRead = new FileRead(fName);
			FileReadService fileReadService = new FileReadServiceImp();
			
			fileReadService.openFile(fileRead);
			fileReadService.readFile(fileRead);

			CouplingService couplingService = new CouplingServiceImp();
			methodList = (ArrayList<String>)couplingService.getMethodSet().clone();
			calledMethodList = (ArrayList<String>)couplingService.getCalledMethodSet().clone();
			
			for(Line line : functionList) {
				System.out.print("Line no : " + line.getLineNumber());
				System.out.println(", Line content : " + line.getLineContent());;
			}
			
			for(Line line : methodCallList) {
				System.out.print("Line no : " + line.getLineNumber());
				System.out.println(", Line content : " + line.getLineContent());;
			}	
			couplingService.getRecursiveMethods();
			
			fileReadService.closeFile(fileRead);
		}
	}
    
}
