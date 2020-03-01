package com.ccc.services;

import java.util.ArrayList;

import com.ccc.model.Coupling;
import com.ccc.model.FileRead;
import com.ccc.model.Line;

public interface CouplingService {

	public String getMethodName(String line);
	public ArrayList<String> getMethodSet();
	public ArrayList<String> getCalledMethodSet();
	
	public void getRecursiveMethods();
	public void getRegularMethods();
	public void getRegToReg();

}
