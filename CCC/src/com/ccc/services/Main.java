package com.ccc.services;

import com.ccc.model.FileRead;

public class Main {
	
	public static String WEBCONTENTDIR = "git/CodeComplexityCalculator/CCC/WebContent/";
    
    /*public static void main(String args[]){
        CouplingCheck c = new CouplingCheck();
        c.process();
    }*/
	
	public static void run(String fName) {
		FileRead fileRead = new FileRead(fName);
		FileReadService fileReadService = new FileReadServiceImp();
		
		fileReadService.openFile(fileRead);
		fileReadService.readFile(fileRead);
		fileReadService.closeFile(fileRead);
	}
    
}
