package com.ccc.services;

import java.util.ArrayList;
import java.util.List;

import com.ccc.model.ControlStructure;
import com.ccc.model.CustomFile;
import com.ccc.model.Line;

public class ControlStructureServiceImp implements ControlStructureService {
	
	static ArrayList<CustomFile> fileList;
	
	public ControlStructureServiceImp(ArrayList<CustomFile> fList) {
		fileList = fList;
	}
	
	// Control Structure Calculation
	public static void Calculation(CustomFile file) {
		
		int wCondition	= file.getControlStructure().getWeights()[0];
		int wIteration	= file.getControlStructure().getWeights()[1];
		int wSwitch		= file.getControlStructure().getWeights()[2];
		int wCase		= file.getControlStructure().getWeights()[3];
		
		
		ArrayList<Integer> ccs_column = new ArrayList<Integer>();
		ArrayList<Integer> wtcs_column = new ArrayList<Integer>();
		ArrayList<Integer> nc_column = new ArrayList<Integer>();
		ArrayList<Integer> ccspps_column = new ArrayList<Integer>();
		ArrayList<String> statement = new ArrayList<String>();
		
		
		// create inner list
		List<Integer> nested = new ArrayList<Integer>();
		nested.add(0);
		int temp = 0;
		
		// check scope is nested or not
		boolean nesting = false;
		
		for(Line l : file.getLineSet()) {
			
			int wtcs = 0;
			int nc = 0;
			int cs = 0;
			int ccspps = 0;
			boolean isComment = false;
			
			//get current statement from code and save to string
			String line = l.getLineContent();
						
						//ignore comments
						if(line.startsWith("//")) {
							isComment = true;
						}
						if(line.contains("//") && !isComment) {
							String[] ln = line.split("//");
							//System.out.print("# without comment :"+ln[0]);
							//System.out.println(" # comment :"+ln[1]);
							
							line = ln[0];	
						}
						isComment = false;
						
						
			
						// if contains any ITERATION in current statement assign weights and process formula
						if(line.contains("for") || line.contains("while") || line.contains("do{")) {
							//System.out.println("iteration detected");
							wtcs = wIteration;
							nc = 1;
							cs = (wtcs * nc) + temp;
							ccspps = temp;
							//System.out.println("for cs: "+cs);

						}
						
						// if current statement contains any CONDITONAL CS assign weights and process formula
						if(line.contains("if") || line.contains("else if") ) {
							//System.out.println("condition detected");
							wtcs = wCondition;
				
							// check if there many conditions in conditional statement and count it
							if(line.contains("&&") || line.contains("||")) {
								nc= 1;
								int andcondtions = line.split("&&").length - 1;
								int orcondtions = line.split("\\|\\|").length - 1;
							
								nc += andcondtions;
								nc += orcondtions;
								
							}else {
								// otherwise set condition to 1
								nc = 1;
							}
							
							//System.out.println("if Condtion : "+nc);
							
							temp = nested.get(nested.size()-1);
							
							cs = (wtcs * nc) + temp;
							ccspps = temp;
						}
						
						// if current statement includes any SWITCH, assign weights and calculate complexity
						if(line.contains("switch")) {
							//System.out.println("switch detected");
							wtcs = wSwitch;
							nc = 1;
							cs = (wtcs * nc) + temp;
							ccspps = temp;
						}
						
						// if current statement include a CASE, assign weights and calculate complexity
						if(line.contains("case")) {	
							//System.out.println("case detected");
							wtcs = wCase;
							nc = 1;
							cs = (wtcs * nc) + temp;
							ccspps = temp;
						
						}
						
						// set inner ccspps values as a list
						if(line.contains("{") && line.contains("if") || line.contains("switch") || line.contains("for") || line.contains("while") || line.contains("do")) {
							//System.out.println("nested line "+l.getLineNumber()+" ccspps: "+cs);
							nested.add(cs);
							nesting = true;
							temp = cs;
						}else if(line.contains("}")) {
							if(nesting) {
								//System.out.println("line end : "+l.getLineNumber());
								nested.remove(nested.size()-1);
								nesting = false;
							}
							
							//System.out.println(nested.toString());
						
						}
					
						// add current statement weights to column list
						wtcs_column.add(wtcs);
						nc_column.add(nc);
						ccspps_column.add(ccspps);
						ccs_column.add(cs);
						statement.add(line);
									
			
		}
		
		file.getControlStructure().setWtcsList(wtcs_column);
		file.getControlStructure().setNcList(nc_column);
		file.getControlStructure().setCcsppsList(ccspps_column);
		file.getControlStructure().setCsList(ccs_column);
		file.getControlStructure().setStatement(statement);
		
	}
	
	// calculate control structure complexity for single file
	public void process1(CustomFile file) {
		// TODO Auto-generated method stub
		
		Calculation(file);
		
	}

	// calculate control structure complexity for multiple file
	@Override
	public void process2() {
		// TODO Auto-generated method stub
			for (CustomFile file : fileList) {
				Calculation(file);
			}
		
	}
	
	// Recalculate complexity after changing weights for single file
	public static void process3() {
		
			if(fileList.size()> 1) {
				
				for(CustomFile file : fileList) {
					Calculation(file);
				}
				
			}else {
				
				//System.out.println("Weight Updated");
				Calculation(fileList.get(0));
			}
			
	}
	
	

}