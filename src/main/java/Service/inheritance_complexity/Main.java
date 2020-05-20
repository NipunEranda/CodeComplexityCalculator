package Service.inheritance_complexity;
import java.util.ArrayList;

import Model.CustomFile;
import Model.FileRead;
import Model.Inheritance;



public class Main {
	private boolean status;
	private ArrayList<CustomFile> fileList = new ArrayList<CustomFile>();
	private String fileType = "";
	public static String WEBCONTENTDIR = "git/CodeComplexityCalculator/CCC/WebContent/";
	// Folder path inside the server( public static String WEBCONTENTDIR =
	// "/opt/tomcat/webapps/ROOT/"; )

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

		InheritanceService couplingService = new InheritanceServiceImp(fileList);

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
						FileReadService fileReadService = (FileReadService) new FileReadServiceImp();

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
				couplingService.process2();
				this.status = true;
			} catch (Exception e) {
				this.status = false;
				e.printStackTrace();
			}
		} // Single File Upload
		else {
			couplingService.process1(fileList.get(0));
			this.status = true;
		}
		InheritanceServiceImp.process3();
		return this.status;
	}
}
