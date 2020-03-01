package com.ccc.services;

import java.util.ArrayList;

import org.apache.commons.fileupload.FileItem;

import com.ccc.model.CustomFile;
import com.ccc.model.FileRead;
import com.ccc.model.Line;

public class Main {

	private ArrayList<CustomFile> fileList = new ArrayList<CustomFile>();
	
	//public static ArrayList<Line> regToReg = new ArrayList<>();
	//public static ArrayList<Line> recursiveMethodList;
	//public static ArrayList<Line> regularMethodList;

	private String fileType = "";
	//public static ArrayList<String> programLineList;
	//public static ArrayList<Line> functionList;
	//public static ArrayList<Line> methodCallList;
	//public static ArrayList<String> calledMethodList;
	public static String WEBCONTENTDIR = "git/CodeComplexityCalculator/CCC/WebContent/";
	
	

	public ArrayList<CustomFile> getFileList() {
		return fileList;
	}



	public void setFileList(ArrayList<CustomFile> fileList) {
		this.fileList = fileList;
	}



	public String getFileType() {
		return fileType;
	}



	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public void run() {

		for (CustomFile file : this.fileList) {

			if (!(file.getFileName().contains("java") || file.getFileName().contains("cpp"))) {
				System.out.println("Wrong file type");
			} else {

				if (file.getFileName().contains("java")) {
					fileType = "java";
				} else {
					fileType = "cpp";
				}

				
				FileRead fileRead = new FileRead(file.getFileName());
				FileReadService fileReadService = new FileReadServiceImp();

				fileReadService.openFile(fileRead);
				fileReadService.readFile(fileRead, file);

				CouplingService couplingService = new CouplingServiceImp();
				
				couplingService.getMethodSet(file);
				couplingService.getCalledMethodSet(file);
				couplingService.getRecursiveMethods(file);

				fileReadService.closeFile(fileRead);
			}
		}
	}

}
