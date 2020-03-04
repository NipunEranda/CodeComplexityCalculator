package com.ccc.services;

import java.util.ArrayList;
import java.util.List;

import com.ccc.model.Coupling;
import com.ccc.model.CustomFile;
import com.ccc.model.FileRead;
import com.ccc.model.Line;

public interface CouplingService {

	public String getMethodName(String line);
	public void getMethodSet(CustomFile file);
	public void getCalledMethodSet(CustomFile file);
	
	public void getRecursiveMethods(CustomFile file);
	public void getRegularMethods(CustomFile file);
	public void getRegInReg(CustomFile file);
	public void getRecInReg(CustomFile file);

}
