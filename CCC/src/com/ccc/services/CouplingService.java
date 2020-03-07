package com.ccc.services;
import com.ccc.model.CustomFile;
import com.ccc.model.Line;

public interface CouplingService {

	public boolean isAMethod(Line line);
	public String getMethodName(String line);
	public void getMethodSet(CustomFile file);
	public void getCalledMethodSet(CustomFile file);
	public void getMethodListFull(CustomFile file);
	
	public void getRecursiveMethods(CustomFile file);
	public void getRegularMethods(CustomFile file);
	public void getRegInReg(CustomFile file);
	public void getRecInReg(CustomFile file);
	public void getRecInRec(CustomFile file);

}
