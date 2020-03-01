package com.ccc.services;

import com.ccc.model.CustomFile;
import com.ccc.model.FileRead;

public interface FileReadService {
	
	public void openFile(FileRead file);
	public void readFile(FileRead file, CustomFile fileobj);
	public void closeFile(FileRead file);
	
}
