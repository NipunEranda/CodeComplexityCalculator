package Model;

import java.util.Scanner;

public class FileRead {
	 private Scanner scanner;
	    private String fileName;
	    
	    public FileRead(String fName) {
			this.fileName = fName;
		}
	    
	    public Scanner getScanner() {
	    	return scanner;
	    }
	    
	    public void setScanner(Scanner scanner) {
	    	this.scanner = scanner;
	    }
	    
	    public String getFileName() {
	    	return fileName;
	    }
}
