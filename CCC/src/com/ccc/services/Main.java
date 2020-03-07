package com.ccc.services;

import java.util.ArrayList;

import org.apache.commons.fileupload.FileItem;

import com.ccc.model.CustomFile;
import com.ccc.model.FileRead;
import com.ccc.model.Line;
import com.ccc.util.RemoveDuplicateMethods;

public class Main {

	private ArrayList<CustomFile> fileList = new ArrayList<CustomFile>();
	private String fileType = "";
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

				fileReadService.openFile(fileRead, file);
				fileReadService.readFile(fileRead, file);

				CouplingService couplingService = new CouplingServiceImp();
				
				couplingService.getMethodSet(file);
				couplingService.getMethodListFull(file);
				couplingService.getCalledMethodSet(file);
				couplingService.getRecursiveMethods(file);
				couplingService.getRegularMethods(file);
				couplingService.getRegInReg(file);
				couplingService.getRecInReg(file);
				couplingService.getRecInRec(file);
				couplingService.getRegInRec(file);
				
				for(Line line : file.getCoupling().getRegularInRecursiveMethods()) {
					System.out.println(line.getLineNumber() + ", " + line.getLineContent());
				}

				fileReadService.closeFile(fileRead);
			}
		}
	}

}
