package com.ccc.services;

import java.util.ArrayList;

import org.apache.commons.fileupload.FileItem;

import com.ccc.model.CustomFile;
import com.ccc.model.FileRead;
import com.ccc.model.Line;
import com.ccc.util.RemoveDuplicates;

public class Main {

	private boolean status;
	private ArrayList<CustomFile> fileList = new ArrayList<CustomFile>();
	private String fileType = "";
	public static String WEBCONTENTDIR = "git/CodeComplexityCalculator/CCC/WebContent/";
	//public static String WEBCONTENTDIR = "/opt/tomcat/webapps/ROOT/";

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

	public boolean run() {

		CouplingService couplingService = new CouplingServiceImp();

		for (CustomFile file : this.fileList) {

			if (!(file.getFileName().contains("java") || file.getFileName().contains("cpp"))) {
				System.out.println("Wrong file type");
			} else {
				try {
					if (file.getFileName().contains("java")) {
						fileType = "java";
					} else {
						fileType = "cpp";
					}
					file.setFileType(fileType);
					FileRead fileRead = new FileRead(file.getFileName());
					FileReadService fileReadService = new FileReadServiceImp();

					fileReadService.openFile(fileRead, file);
					fileReadService.readFile(fileRead, file);
					fileReadService.closeFile(fileRead);
					status = true;
				} catch (Exception e) {
					e.printStackTrace();
					status = false;
				}
			}
		}
		if (this.fileList.size() > 1) {
			try {
				couplingService.process2(this.fileList);
				status = true;
			} catch (Exception e) {
				status = false;
				e.printStackTrace();
			}
		}else {
			couplingService.process1(fileList.get(0));
		}
		couplingService.process3(this.fileList);
		return status;
	}

}
