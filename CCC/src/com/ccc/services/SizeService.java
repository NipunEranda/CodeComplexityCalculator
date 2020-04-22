package com.ccc.services;

import java.util.ArrayList;

import com.ccc.model.CustomFile;
import com.ccc.model.Line;

public interface SizeService {

	public boolean isAMethod(Line line);
	public String getMethodName(String line);
	public void setEndLineNumber(CustomFile file);
	public int getEndLineNumber(CustomFile file, Line line);
	public void getMethodSet(CustomFile file);
	public void getCalledMethodSet(CustomFile file);
	public void getCalledMethodSet_DF(CustomFile ifile, ArrayList<CustomFile> fileList);
	public void getMethodListFull(CustomFile file);
	public void getGlobalVariableSet(CustomFile file);
	public void getGlobalVariableListInReg(CustomFile file);
	public void getGlobalVariableListInRec(CustomFile file);
	
	
	public void getRecursiveMethods(CustomFile file);
	public void getRegularMethods(CustomFile file);
	public void getRegInReg(CustomFile file);
	public void getRecInReg(CustomFile file);
	public void getRecInRec(CustomFile file);
	public void getRegInRec(CustomFile file);
	
	
	public void getMethods_DF(ArrayList<CustomFile> fileList);
	public void getRegInReg_DF(ArrayList<CustomFile> fileList);
	public void getRecInReg_DF(ArrayList<CustomFile> fileList);
	public void getRecInRec_DF(ArrayList<CustomFile> fileList);
	public void getRegInRec_DF(ArrayList<CustomFile> fileList);
	public void getGlobalVariableList_DF(CustomFile ifile, ArrayList<CustomFile> fileList);
	public void getGlobalVariableListInReg_DF(ArrayList<CustomFile> fileList);
	public void getGlobalVariableListInRec_DF(ArrayList<CustomFile> fileList);
	
	public void process1(CustomFile file);
	public void process2(ArrayList<CustomFile> fileList);
	public void process3(ArrayList<CustomFile> fileList);
	
}
