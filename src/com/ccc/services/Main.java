package com.ccc.services;

import java.util.ArrayList;

import org.apache.commons.fileupload.FileItem;

import com.ccc.model.CustomFile;
import com.ccc.model.FileRead;
import com.ccc.model.Line;

public class Main {

	private boolean status;
	private ArrayList<CustomFile> fileList = new ArrayList<CustomFile>();
	private String fileType = "";
	public static String WEBCONTENTDIR = "C://Users/pasan/Desktop/ITPM/test/CodeComplexityCalculator-master/CCC/WebContent/";

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

		ControlStructureService controlStructureService = new ControlStructureServiceImp(fileList);

		for (CustomFile file : this.fileList) {
			if (file.getIsRaw() != true) {
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
						this.status = true;
					} catch (Exception e) {
						e.printStackTrace();
						this.status = false;
					}
				}
			}
		}

		// For Multi File Upload
		if (this.fileList.size() > 1) {
			try {
				controlStructureService.process2();
				this.status = true;
			} catch (Exception e) {
				this.status = false;
				e.printStackTrace();
			}
		} // Single File Upload
		else {
			controlStructureService.process1(fileList.get(0));
			this.status = true;
		}
	//	ControlStructureServiceImp.process3();
		return this.status;
	}

}