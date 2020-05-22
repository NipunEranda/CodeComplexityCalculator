package Service.inheritance_complexity;

import Model.CustomFile;

public interface InheritanceService {

	
	
	public void getDirectInheritance(CustomFile file);
	public void getInDirectInheritance(CustomFile file);
	
	public void getTotalInheritance(CustomFile file);
	public void getci(CustomFile file);
	
	public void process2();
	public void process1(CustomFile customFile);

}
