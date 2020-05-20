package Service.inheritance_complexity;

import Model.CustomFile;
import Model.FileRead;

public interface FileReadService {
	
	public void openFile(FileRead file, CustomFile fileobj);
	public void readFile(FileRead file, CustomFile fileobj);
	public void closeFile(FileRead file);
	
}