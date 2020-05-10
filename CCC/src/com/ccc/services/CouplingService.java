package com.ccc.services;
import java.util.ArrayList;

import com.ccc.model.CustomFile;
import com.ccc.model.Line;

public interface CouplingService {

	public void getClassNames(CustomFile file);
	public void getObjectSet(CustomFile file);
	public void setEndLineNumber(CustomFile file);
	public int getEndLineNumber(CustomFile file, Line line);
	public void getMethodSet(CustomFile file);
	public void getCalledMethodSet_DF(CustomFile ifile);
	public void getGlobalVariableSet(CustomFile file);
	public void getGlobalVariableListInReg(CustomFile file);
	public void getGlobalVariableListInRec(CustomFile file);
	public void getSystemMethods(CustomFile file);
	
	public void getRecursiveMethods(CustomFile file);
	public void getRegularMethods(CustomFile file);
	public void getRegInReg(CustomFile file);
	public void getRecInReg(CustomFile file);
	public void getRecInRec(CustomFile file);
	public void getRegInRec(CustomFile file);
	
	public void getObjectSet_DF(CustomFile ifile);
	public void getSystemMethods_DF(CustomFile ifile);
	public void getMethods_DF();
	public void getRegInReg_DF();
	public void getRecInReg_DF();
	public void getRecInRec_DF();
	public void getRegInRec_DF();
	public void getGlobalVariableList_DF(CustomFile ifile);
	public void getGlobalVariableListInReg_DF();
	public void getGlobalVariableListInRec_DF();
	
	public void process1(CustomFile file);
	public void process2();

}
