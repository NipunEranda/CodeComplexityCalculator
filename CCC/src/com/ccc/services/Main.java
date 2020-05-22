package com.ccc.services;

import java.util.ArrayList;

import org.apache.commons.fileupload.FileItem;

import com.ccc.model.CustomFile;
import com.ccc.model.FileRead;
import com.ccc.model.Line;
import com.ccc.model.SizeService;
import com.ccc.model.SizeServiceImp;
import com.ccc.model.VariablesServiceImp;
import com.ccc.model.VariablesServices;

public class Main {

	private boolean status;
	private ArrayList<CustomFile> fileList = new ArrayList<CustomFile>();
	private String fileType = "";
	public static String WEBCONTENTDIR = "git/CodeComplexityCalculator/CCC/WebContent/";
	// Folder path inside the server
	// public static String WEBCONTENTDIR ="/opt/tomcat/webapps/ROOT/";

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

		CouplingService couplingService = new CouplingServiceImp(fileList);
		ControlStructureService controlStructureService = new ControlStructureServiceImp(fileList);
		InheritanceService inheritanceService = new InheritanceServiceImp(fileList);
		SizeService sizeService = new SizeServiceImp(fileList);
		VariablesServices variablesServices = new VariablesServiceImp(fileList);

		// For Multi File Upload
		if (this.fileList.size() > 1) {
			try {
				couplingService.process2();
				controlStructureService.process2();
				inheritanceService.process2();
				//sizeService.process2();
				//variablesServices.process2();
				this.status = true;
			} catch (Exception e) {
				this.status = false;
				e.printStackTrace();
			}
		} // Single File Upload
		else {
			couplingService.process1(fileList.get(0));
			controlStructureService.process1(fileList.get(0));
			inheritanceService.process1(fileList.get(0));
			//sizeService.process1(fileList.get(0));
			//variablesServices.process1(fileList.get(0));
			this.status = true;
		}
		CouplingServiceImp.process3();
		InheritanceServiceImp.process3();
		//SizeServiceImp.process3();
		//VariablesServiceImp.process3();
		return this.status;
	}

}
