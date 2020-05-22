package com.ccc.services;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import com.ccc.model.CustomFile;
import com.ccc.model.FileRead;
import com.ccc.model.Line;

public class FileReadServiceImp implements FileReadService{

	//Code segmant for open files
	@Override
	public void openFile(FileRead file, CustomFile fileobj) {
		try{
			file.setScanner(new Scanner(new File(fileobj.getFilePath() + file.getFileName()))); 
        }catch(Exception e){
            System.out.println("Couldn't find the file.");
        }
	}

	//Code segmant for read files and store in a object
	@Override
	public void readFile(FileRead file, CustomFile fileobj) {
		int count=1;
		ArrayList<Line> lineSet = new ArrayList<Line>();
		while(file.getScanner().hasNext()){
			Line line = new Line(count, file.getScanner().nextLine());
			lineSet.add(line);
			count++;
        }
		fileobj.setLineSet(lineSet);
		fileobj.setLastIndex(lineSet.size());
	}

	//Code segmant for close opened files
	@Override
	public void closeFile(FileRead file) {
		file.getScanner().close();
	}
	
}
