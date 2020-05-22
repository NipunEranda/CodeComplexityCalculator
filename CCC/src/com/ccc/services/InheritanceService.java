package com.ccc.services;

import com.ccc.model.CustomFile;

public interface InheritanceService {

	
	
	public void getDirectInheritance(CustomFile file);
	public void getInDirectInheritance(CustomFile file);
	
	public void getTotalInheritance(CustomFile file);
	
	public void process2();
	public void process1(CustomFile customFile);
	void getci(CustomFile file);

}