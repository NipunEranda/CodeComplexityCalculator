package com.ccc.services;

import java.io.File;
import java.util.Scanner;

import com.ccc.model.FileRead;

public class FileReadServiceImp implements FileReadService{

	@Override
	public void openFile(FileRead file) {
		try{
			file.setScanner(new Scanner(new File(Main.WEBCONTENTDIR + "uploads/" + file.getFileName()))); 
        }catch(Exception e){
            System.out.println("Couldn't find the file.");
        }
	}

	@Override
	public void readFile(FileRead file) {
		while(file.getScanner().hasNext()){
            System.out.println(file.getScanner().nextLine());
        }
	}

	@Override
	public void closeFile(FileRead file) {
		file.getScanner().close();
	}
	
}
