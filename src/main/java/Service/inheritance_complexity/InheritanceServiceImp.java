package Service.inheritance_complexity;

import java.util.ArrayList;

import Model.CustomFile;
import Model.Inheritance;
import Model.Line;

public class InheritanceServiceImp extends InheritanceService {

	static ArrayList<CustomFile> fileList;
	
	public InheritanceServiceImp(ArrayList<CustomFile> fList) {
		// TODO Auto-generated constructor stub
		fileList = fList;
	}

	
//get direct inheritance patterns
	public void getDirectInheritance( CustomFile file) {
		
		ArrayList<Line> DirectInheritance = new ArrayList<Line>();
		
		
		for(Line line : file.getLineSet()) {
			boolean direct = false;
			if(line.getLineContent().contains("class") && line.getLineContent().contains("extends")) {
					 direct = true;
		
		}if(direct) {
			DirectInheritance.add(line);
		}
	}
		file.getInheritance().setDirect_I(DirectInheritance);
}
	
//get indirect inheritance patterns
public void getInDirectInheritance( CustomFile file) {
		
		ArrayList<Line> InDirectInheritance = new ArrayList<Line>();
		
		for(Line line : file.getLineSet()) {
			boolean indirect = false;
			if(line.getLineContent().contains("toString") ) {
					 indirect = true;
		
		}if(indirect) {
			InDirectInheritance.add(line);
		}
	}
		file.getInheritance().setIndirect_I(InDirectInheritance);
}



	public void process1(CustomFile file) {
		
		getDirectInheritance(file);
		getInDirectInheritance(file);
		
		file.getInheritance().setN_direct(file.getInheritance().getDirect_I().size());
		file.getInheritance().setN_indirect(file.getInheritance().getIndirect_I().size());
	}
	
	public void process2() {
		for(CustomFile file : fileList) {
			
			getDirect_I(file);
			getInDirect_I(file);
		}
		
		for(CustomFile file :fileList) {
			
			file.getInheritance().setN_direct(file.getInheritance().getDirect_I().size());
			file.getInheritance().setN_indirect(file.getInheritance().getIndirect_I().size());
		}
	}
	
	// Process for calculations to display results in the table
	public static void process3() {
		int count = 0;
		
		for (CustomFile file : fileList) {
			int[] sum = new int[4];
			for (Line line : file.getLineSet()) {
				
				//column1
				count = 0;
				for(Line regLine : file.getInheritance().getDirect_I()) {
					if(regLine.getLineNumber() == line.getLineNumber()) {
						count ++;
					}
				}
				
				line.setSum(0, count);
				file.getInheritance();
				line.setColValues(0, count * Inheritance.getWeights()[0]);
				
				

				//column2
				count = 0;
				for(Line regLine : file.getInheritance().getIndirect_I()) {
					if(regLine.getLineNumber() == line.getLineNumber()) {
						count ++;
					}
				}
				
				line.setSum(1, count);
				file.getInheritance();
				line.setColValues(1, count * Inheritance.getWeights()[1]);
			}
			
			file.getInheritance().setFinalValue();
			file.getInheritance().setSum(sum);
		}
		
	}
	}
