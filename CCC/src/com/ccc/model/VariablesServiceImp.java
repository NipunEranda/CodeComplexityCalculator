package com.ccc.model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.ccc.model.Variables;
import com.ccc.model.CustomFile;
import com.ccc.model.Line;


public class VariablesServiceImp implements VariablesServices {
	
	static ArrayList<CustomFile> fileList;
	
	//Setting filelist for operations
	public VariablesServiceImp(ArrayList<CustomFile> fList) {
		fileList = fList;
	}

	//get method name from a line
	@Override
	public String getMethodName(String line) {
		if (line.contains("main") || line.contains("class") || line.contains("if") || line.contains("switch")
				|| line.contains("catch") || line.contains("return") || line.contains(";")) {

		} else if (line.contains("public")) {
			String[] sub = line.split("\\(");
			String x = sub[0].replace("public", "").replace("private", "").replace("protected", "")
					.replace("static", "").replace("final", "").trim();
			if (x.split(" ").length > 1) {
				return x.split(" ")[1];
			} else {
				return x;
			}
		}
		return null;
	}

	//get class names in the file
	@Override
	public void getClassNames(CustomFile file) {
		ArrayList<Line> classList = new ArrayList<Line>();

		for (Line line : file.getLineSet()) {

			if (line.getLineContent().contains("class") && line.getLineContent().contains("{")) {
				String[] sub = line.getLineContent().split("\\(");
				String x = sub[0].replace("public", "").replace("class", "").replace("{", "").trim();

				classList.add(new Line(line.getLineNumber(), x.split(" ")[0]));
			}

		}
		
		file.getVariables().setClassList(classList);
	}

	//get objects created in the file
	@Override
	public void getObjectSet(CustomFile file) {
		ArrayList<Line> classObjList = new ArrayList<Line>();

		for (Line line : file.getLineSet()) {

			if (line.getLineContent().contains(" new ")) {
				for (Line line_class : file.getVariables().getClassList()) {
					if (line.getLineContent().contains(line_class.getLineContent())) {

						String[] sub = line.getLineContent().split("\\(");
						String x = sub[0].replace(line_class.getLineContent(), "").trim();
						classObjList.add(new Line(line.getLineNumber(), x.split(" ")[0]));
					}
				}
			}

		}

		file.getVariables().setClassObjectList(classObjList);
	}

	//get objects created using other filess
	@Override
	public void getObjectSet_DF(CustomFile ifile) {
		ArrayList<Line> classObjList_DF = new ArrayList<Line>();
		for (CustomFile file : fileList) {

			if (!file.getFileName().equalsIgnoreCase(ifile.getFileName())) {

				for (Line line : ifile.getLineSet()) {

					for (Line line_class : file.getVariables().getClassList()) {

						if (line.getLineContent().contains(" new ")
								&& line.getLineContent().contains(line_class.getLineContent())) {
							String[] sub = line.getLineContent().split("\\(");
							String x = sub[0].replace(line_class.getLineContent(), "").trim();
							classObjList_DF.add(new Line(line.getLineNumber(), x.split(" ")[0]));
						}

					}

				}

			}
			ifile.getVariables().setClassObjectList_DF(classObjList_DF);
		}

	}

	//get end line numbers to identify method scope
	@Override
	public int getEndLineNumber(CustomFile file, Line line) {
		int endNumber = 0;
		for (Line line1 : file.getVariables().getMethodList()) {

			if (line1.getLineNumber() == line.getLineNumber()) {
				endNumber = line1.getEndLineNumber();
				break;
			}

		}
		return endNumber;
	}

	//get user defined method set in the file
	@Override
	public void getMethodSet(CustomFile file) {

		ArrayList<Line> methodSet = new ArrayList<Line>();

		for (Line line : file.getLineSet()) {
			if (line.getLineContent().contains("class") || line.getLineContent().contains("if")
					|| line.getLineContent().contains("switch") || line.getLineContent().contains("catch")
					|| line.getLineContent().contains("return") || line.getLineContent().contains(";")) {

			} else if (line.getLineContent().contains("public")) {
				String[] sub = line.getLineContent().split("\\(");
				String x = sub[0].replace("public", "").replace("private", "").replace("protected", "")
						.replace("static", "").replace("final", "").trim();
				if (x.split(" ").length > 1) {
					methodSet.add(new Line(line.getLineNumber(), x.split(" ")[1]));
				} else {
					methodSet.add(new Line(line.getLineNumber(), x));
				}
			}
		}
		file.getVariables().setMethodList(methodSet);
	}

	//set end line numbers
	@Override
	public void setEndLineNumber(CustomFile file) {

		for (int i = 0; i < file.getVariables().getMethodList().size(); i++) {

			int opnBrkt = 0;
			int clsBrkt = 0;

			if (i == file.getVariables().getMethodList().size() - 1) {

				for (int j = file.getVariables().getMethodList().get(i).getLineNumber() - 1; j < file
						.getLastIndex(); ++j) {

					if (file.getLineSet().get(j).getLineContent().contains("{")
							&& file.getLineSet().get(j).getLineContent().contains("}")) {

					} else if (file.getLineSet().get(j).getLineContent().contains("{")) {
						++opnBrkt;
					} else if (file.getLineSet().get(j).getLineContent().contains("}")) {
						--opnBrkt;
					}

					if (opnBrkt == 0) {
						file.getVariables().setEndLineNumber(i, ++j);
						break;
					} else {
						continue;
					}
				}

			} else {

				for (int j = file.getVariables().getMethodList().get(i).getLineNumber() - 1; j < file.getVariables()
						.getMethodList().get(i + 1).getLineNumber() - 1; ++j) {

					if (file.getLineSet().get(j).getLineContent().contains("{")
							&& file.getLineSet().get(j).getLineContent().contains("}")) {

					} else if (file.getLineSet().get(j).getLineContent().contains("{")) {
						++opnBrkt;
					} else if (file.getLineSet().get(j).getLineContent().contains("}")) {
						--opnBrkt;
					}

					if (opnBrkt == clsBrkt) {
						file.getVariables().setEndLineNumber(i, ++j);
						break;
					} else {
						continue;
					}

				}

			}

		}

	}

	//Get recursive method calls
	// Senario 1
	@Override
	public void getRecursiveMethods(CustomFile file) {

		ArrayList<Line> recursiveMethodSet = new ArrayList<Line>();
		ArrayList<Line> recursiveMethodCalls = new ArrayList<Line>();

		for (Line line : file.getVariables().getMethodList()) {
			boolean no_recursive = true;

			for (int i = line.getLineNumber(); i < line.getEndLineNumber(); i++) {

				if (file.getLineSet().get(i).getLineContent().contains(line.getLineContent())) {
					no_recursive = false;
					recursiveMethodCalls.add(file.getLineSet().get(i));
				}
			}
			if (no_recursive == false) {
				recursiveMethodSet.add(line);
			}
		}

		file.getVariables().setRecursiveMethods(recursiveMethodSet);
		file.getVariables().setRecursiveMethodCalls(recursiveMethodCalls);
	}

	//get regular methods
	@Override
	public void getRegularMethods(CustomFile file) {

		ArrayList<Line> regularMethodSet = new ArrayList<Line>();

		for (Line line : file.getVariables().getMethodList()) {
			boolean no_recursive = true;

			for (int i = line.getLineNumber(); i < line.getEndLineNumber(); i++) {

				if (file.getLineSet().get(i).getLineContent().contains(line.getLineContent())) {
					no_recursive = false;
				}
			}
			if (no_recursive) {
				regularMethodSet.add(line);
			}
		}

		file.getVariables().setRegularMethods(regularMethodSet);
	}

	//get system methods
	@Override
	public void getSystemMethods(CustomFile file) {

		ArrayList<Line> systemMethodList = new ArrayList<>();

		for (Line line : file.getLineSet()) {
			if (line.getLineContent().contains("main") || line.getLineContent().contains("class")
					|| line.getLineContent().contains("if") || line.getLineContent().contains("switch")
					|| line.getLineContent().contains("catch") || line.getLineContent().contains("return")
					|| line.getLineContent().contains("new")) {

			} else if (line.getLineContent().contains("(") && line.getLineContent().contains(")")
					&& line.getLineContent().contains(";")) {
				boolean status = true;
				String[] sub = line.getLineContent().split("\\(");
				String method = sub[0].trim();

				for (Line line_method : file.getVariables().getMethodList()) {

					if (method.contains(line_method.getLineContent())) {
						status = false;
						break;
					}

				}

				if (status == true) {
					systemMethodList.add(line);
				}
			}
		}

		file.getVariables().setSystemMethods(systemMethodList);
	}

	//get regular methods, which called by another regular method
	// Senario 2
	@Override
	public void getRegInReg(CustomFile file) {

		ArrayList<Line> regInReg = new ArrayList<Line>();

		for (Line line : file.getVariables().getRegularMethods()) {
			for (Line regLine : file.getVariables().getRegularMethods()) {
				if (line.getLineNumber() != regLine.getLineNumber()) {
					for (int i = line.getLineNumber(); i < getEndLineNumber(file, line); i++) {

						if (file.getLineSet().get(i).getLineContent().contains(regLine.getLineContent())) {
							regInReg.add(file.getLineSet().get(i));
						}

					}
				}

			}
			for (Line line_sys : file.getVariables().getSystemMethods()) {

				if (line_sys.getLineNumber() > line.getLineNumber()
						&& line_sys.getLineNumber() < getEndLineNumber(file, line)) {
					regInReg.add(line_sys);
				}

			}
		}
		file.getVariables().setRegularInRegularMethods(regInReg);
	}

	//get recursive methods, which called by another regular method
	// Senario 4
	@Override
	public void getRecInReg(CustomFile file) {

		ArrayList<Line> recInReg = new ArrayList<Line>();

		for (Line regLine : file.getVariables().getRegularMethods()) {

			for (int i = regLine.getLineNumber(); i < getEndLineNumber(file, regLine); i++) {

				for (Line recLine : file.getVariables().getRecursiveMethods()) {
					if (file.getLineSet().get(i).getLineContent().contains(recLine.getLineContent())) {
						recInReg.add(file.getLineSet().get(i));
					}
				}

			}

		}
		file.getVariables().setRecursiveInRegularMethods(recInReg);
	}

	//get recursive methods, which called by another recursive method
	// Senario 6
	@Override
	public void getRecInRec(CustomFile file) {

		ArrayList<Line> recInRec = new ArrayList<Line>();

		for (Line line : file.getVariables().getRecursiveMethods()) {
			for (Line recLine : file.getVariables().getRecursiveMethods()) {
				if (line.getLineNumber() != recLine.getLineNumber()) {
					for (int i = line.getLineNumber(); i < getEndLineNumber(file, line); i++) {

						if (file.getLineSet().get(i).getLineContent().contains(recLine.getLineContent())) {
							recInRec.add(file.getLineSet().get(i));
						}

					}
				}

			}
		}

		file.getVariables().setRecursiveInRecursiveMethods(recInRec);

	}

	//get regular methods, which called by another recursive method
	// Senario 8
	@Override
	public void getRegInRec(CustomFile file) {

		ArrayList<Line> regInRec = new ArrayList<Line>();

		for (Line recLine : file.getVariables().getRecursiveMethods()) {

			for (int i = recLine.getLineNumber(); i < getEndLineNumber(file, recLine); i++) {

				for (Line regLine : file.getVariables().getRegularMethods()) {
					if (file.getLineSet().get(i).getLineContent().contains(regLine.getLineContent())) {
						regInRec.add(file.getLineSet().get(i));
					}
				}

			}

			for (Line line_sys : file.getVariables().getSystemMethods()) {

				if (line_sys.getLineNumber() > recLine.getLineNumber()
						&& line_sys.getLineNumber() < getEndLineNumber(file, recLine)) {
					regInRec.add(line_sys);
				}

			}

		}

		file.getVariables().setRegularInRecursiveMethods(regInRec);
	}

	//get global variable set in the file
	@Override
	public void getGlobalVariableSet(CustomFile file) {

		ArrayList<Line> variableSet = new ArrayList<Line>();

		for (Line line : file.getLineSet()) {

			// Check more details on global variables
			if (line.getLineContent().contains("=") && line.getLineContent().contains(";")) {
				if (line.getLineContent().contains("if ") || line.getLineContent().contains("switch ")
						|| line.getLineContent().contains("catch ") || line.getLineContent().contains("return ")
						|| line.getLineContent().contains("private") || line.getLineContent().contains("protected")) {

				} else if (line.getLineContent().contains("byte") || line.getLineContent().contains("short")
						|| line.getLineContent().contains("int") || line.getLineContent().contains("long")
						|| line.getLineContent().contains("float") || line.getLineContent().contains("double")
						|| line.getLineContent().contains("char") || line.getLineContent().contains("boolean")) {

					String value = line.getLineContent().replace("static", "").replace("public", "").trim();
					String[] sub = value.split(" ");
					variableSet.add(new Line(line.getLineNumber(), sub[1]));
				}
			}
		}

		ArrayList<Integer> removableNumbers = new ArrayList<>();
		for (Line line : variableSet) {

			for (Line line_meth : file.getVariables().getMethodList()) {
				if (line.getLineNumber() > line_meth.getLineNumber()
						&& line.getLineNumber() < line_meth.getEndLineNumber()) {
					removableNumbers.add(line.getLineNumber());
				}
			}

		}
		for (int x : removableNumbers) {

			for (int i = 0; i < variableSet.size(); i++) {

				if (variableSet.get(i).getLineNumber() == x) {
					variableSet.remove(i);
				}

			}

		}

		file.getVariables().setGlobalVariableSet(variableSet);

	}

	//get global variables referenced by regular methods
	// Senario 10
	@Override
	public void getGlobalVariableListInReg(CustomFile file) {

		ArrayList<Line> globalVariableSetInReg = new ArrayList<Line>();

		for (Line line : file.getVariables().getGlobalVariableSet()) {

			for (Line line_reg : file.getVariables().getRegularMethods()) {

				for (int i = line_reg.getLineNumber(); i < getEndLineNumber(file, line_reg); i++) {
					if (file.getLineSet().get(i).getLineContent().contains(line.getLineContent())
							&& !file.getLineSet().get(i).getLineContent().contains("." + line.getLineContent())) {

						String text = file.getLineSet().get(i).getLineContent();

						String regex = "\\b" + line.getLineContent() + "\\b";

						Pattern pattern = Pattern.compile(regex);
						Matcher matcher = pattern.matcher(text);

						while (matcher.find()) {
							globalVariableSetInReg.add(file.getLineSet().get(i));
						}
					}
				}

			}

		}

		file.getVariables().setGlobalVariableListInReg(globalVariableSetInReg);
	}

	//get global variables referenced by recursive methods
	// Senario 12
	@Override
	public void getGlobalVariableListInRec(CustomFile file) {

		ArrayList<Line> globalVariableSetInRec = new ArrayList<Line>();

		for (Line line : file.getVariables().getGlobalVariableSet()) {

			for (Line methodLine : file.getVariables().getRecursiveMethods()) {

				for (int i = methodLine.getLineNumber(); i < getEndLineNumber(file, methodLine); i++) {

					if (file.getLineSet().get(i).getLineContent().contains(line.getLineContent())
							&& !file.getLineSet().get(i).getLineContent().contains("." + line.getLineContent())) {

						String text = file.getLineSet().get(i).getLineContent();

						String regex = "\\b" + line.getLineContent() + "\\b";

						Pattern pattern = Pattern.compile(regex);
						Matcher matcher = pattern.matcher(text);

						while (matcher.find()) {
							globalVariableSetInRec.add(file.getLineSet().get(i));
						}

					}

				}

			}

		}

		file.getVariables().setGlobalVariableListInRec(globalVariableSetInRec);

	}

	//get system methods without other methods from another files
	@Override
	public void getSystemMethods_DF(CustomFile ifile) {

		ArrayList<Line> systemMethodList_DF = new ArrayList<>();

		for (CustomFile file : fileList) {
			if (ifile.getFileName() != file.getFileName()) {
				for (Line line : ifile.getLineSet()) {
					if (line.getLineContent().contains("main") || line.getLineContent().contains("class")
							|| line.getLineContent().contains("if") || line.getLineContent().contains("switch")
							|| line.getLineContent().contains("catch") || line.getLineContent().contains("return")
							|| line.getLineContent().contains("new")) {

					} else if (line.getLineContent().contains("(") && line.getLineContent().contains(")")
							&& line.getLineContent().contains(";")) {
						boolean status = true;
						String[] sub = line.getLineContent().split("\\(");
						String method = sub[0].trim();

						for (Line line_method : ifile.getVariables().getMethodList()) {

							if (method.contains(line_method.getLineContent())) {
								status = false;
								break;
							}

						}

						if (status == true) {
							for (Line line_meth : file.getVariables().getMethodList()) {
								if (method.contains(line_meth.getLineContent())) {
									status = false;
									break;
								}
							}

						}

						if (status == true) {
							systemMethodList_DF.add(line);
						}

					}
				}
			}
			file.getVariables().setSystemMethods(systemMethodList_DF);
		}
	}

	//get called method set which belong to another file
	@Override
	public void getCalledMethodSet_DF(CustomFile ifile) {
		ArrayList<Line> calledMethodList_DF = new ArrayList<>();
		for (CustomFile file : fileList) {
			if (!file.getFileName().equalsIgnoreCase(ifile.getFileName())) {

				for (Line line : ifile.getLineSet()) {

					for (Line line_meth : file.getVariables().getMethodList()) {

						if (line.getLineContent().contains("." + line_meth.getLineContent())) {
							line.setFileName(file.getFileName().split("\\.")[0]);
							calledMethodList_DF.add(line);
						}

					}

				}

				ifile.getVariables().setCalledMethodList_DF(calledMethodList_DF);
			}
		}

	}

	//get method set which belong to another file
	@Override
	public void getMethods_DF() {

		for (CustomFile file : fileList) {
			ArrayList<Line> inReg = new ArrayList<>();
			ArrayList<Line> inRec = new ArrayList<>();

			for (Line line : file.getVariables().getCalledMethodList_DF()) {

				for (CustomFile tFile : fileList) {

					if (tFile.getFileName().split("\\.")[0].equalsIgnoreCase(line.getFileName())) {

						for (Line line_meth_reg : tFile.getVariables().getRegularMethods()) {
							if (line.getLineContent().contains(line_meth_reg.getLineContent())) {
								inReg.add(line);
							}
						}

						for (Line line_meth_rec : tFile.getVariables().getRecursiveMethods()) {
							if (line.getLineContent().contains(line_meth_rec.getLineContent())) {
								inRec.add(line);
							}
						}

					}

				}

			}

			file.getVariables().setInReg_DF(inReg);
			file.getVariables().setInRec_DF(inRec);

		}

	}

	//A regular method calling another regular method in a different file
	@Override
	public void getRegInReg_DF() {
		for (CustomFile file : fileList) {
			ArrayList<Line> regInReg_DF = new ArrayList<>();

			for (Line line : file.getVariables().getInReg_DF()) {

				for (Line line_reg : file.getVariables().getRegularMethods()) {

					if (line.getLineNumber() > line_reg.getLineNumber()
							&& line.getLineNumber() < line_reg.getEndLineNumber()) {
						regInReg_DF.add(line);
					}

				}

				for (Line line_sys : file.getVariables().getSystemMethods()) {

					if (line_sys.getLineNumber() > line.getLineNumber()
							&& line_sys.getLineNumber() < getEndLineNumber(file, line)) {
						regInReg_DF.add(line_sys);
					}

				}

			}
			file.getVariables().setRegularInRegularMethods_DF(regInReg_DF);
		}
	}

	//A regular method calling a recursive method in a different file
	@Override
	public void getRecInReg_DF() {
		for (CustomFile file : fileList) {
			ArrayList<Line> recInReg_DF = new ArrayList<>();
			for (Line line : file.getVariables().getInRec_DF()) {

				for (Line line_reg : file.getVariables().getRegularMethods()) {

					if (line.getLineNumber() > line_reg.getLineNumber()
							&& line.getLineNumber() < line_reg.getEndLineNumber()) {
						recInReg_DF.add(line);
					}

				}
			}
			file.getVariables().setRecursiveInRegularMethods_DF(recInReg_DF);
		}
	}

	//A recursive method calling another recursive method in a different file
	@Override
	public void getRecInRec_DF() {
		for (CustomFile file : fileList) {
			ArrayList<Line> recInRec_DF = new ArrayList<>();
			for (Line line : file.getVariables().getInRec_DF()) {

				for (Line line_rec : file.getVariables().getRecursiveMethods()) {

					if (line.getLineNumber() > line_rec.getLineNumber()
							&& line.getLineNumber() < line_rec.getEndLineNumber()) {
						recInRec_DF.add(line);
					}

				}
			}
			file.getVariables().setRecursiveInRecursiveMethods_DF(recInRec_DF);
		}
	}

	//A recursive method calling a regular method in a different file
	@Override
	public void getRegInRec_DF() {
		for (CustomFile file : fileList) {
			ArrayList<Line> regInRec_DF = new ArrayList<>();
			for (Line line : file.getVariables().getInReg_DF()) {

				for (Line line_rec : file.getVariables().getRecursiveMethods()) {

					if (line.getLineNumber() > line_rec.getLineNumber()
							&& line.getLineNumber() < line_rec.getEndLineNumber()) {
						regInRec_DF.add(line);
					}

				}

				for (Line line_sys : file.getVariables().getSystemMethods()) {

					if (line_sys.getLineNumber() > line.getLineNumber()
							&& line_sys.getLineNumber() < getEndLineNumber(file, line)) {
						regInRec_DF.add(line_sys);
					}

				}
			}
			file.getVariables().setRegularInRecursiveMethods_DF(regInRec_DF);
		}
	}

	//get global variable set which are belong to another file
	@Override
	public void getGlobalVariableList_DF(CustomFile ifile) {
		ArrayList<Line> globalVariableList_DF = new ArrayList<>();

		for (CustomFile file : fileList) {

			if (!ifile.getFileName().equalsIgnoreCase(file.getFileName())) {
				for (Line line : ifile.getLineSet()) {
					for (Line line_g : file.getVariables().getGlobalVariableSet()) {
						if(line.getLineContent().contains("."+line_g.getLineContent())) {
							globalVariableList_DF.add(line);
						}
					}
				}
			}

		}

		ifile.getVariables().setGlobalVariableList_DF(globalVariableList_DF);

	}

	//A regular method referencing a global variable in a different file 
	@Override
	public void getGlobalVariableListInReg_DF() {
		for (CustomFile file : fileList) {
			ArrayList<Line> globalVariableListInReg_DF = new ArrayList<>();
			for (Line regLine : file.getVariables().getRegularMethods()) {

				for (Line gVariable : file.getVariables().getGlobalVariableList_DF()) {

					if (gVariable.getLineNumber() > regLine.getLineNumber()
							&& gVariable.getLineNumber() < getEndLineNumber(file, regLine)) {
						globalVariableListInReg_DF.add(gVariable);
					}

				}

			}

			file.getVariables().setGlobalVariableListInReg_DF(globalVariableListInReg_DF);
		}
	}

	//A recursive method referencing a global variable in a different file
	@Override
	public void getGlobalVariableListInRec_DF() {

		for (CustomFile file : fileList) {
			ArrayList<Line> globalVariableListInRec_DF = new ArrayList<>();
			for (Line recLine : file.getVariables().getRecursiveMethods()) {

				for (Line gVariable : file.getVariables().getGlobalVariableList_DF()) {

					if (gVariable.getLineNumber() > recLine.getLineNumber()
							&& gVariable.getLineNumber() < getEndLineNumber(file, recLine)) {
						globalVariableListInRec_DF.add(gVariable);
					}

				}

			}

			file.getVariables().setGlobalVariableListInRec_DF(globalVariableListInRec_DF);
		}
	}
	
	
	
	
	
	
	
	
	


	//process 1 stared
	@Override
	public void process1(CustomFile file) {

		getClassNames(file);
		getObjectSet(file);
		getMethodSet(file);
		getSystemMethods(file);
		setEndLineNumber(file);
		getGlobalVariableSet(file);
		getRecursiveMethods(file);
		getRegularMethods(file);
		getRegInReg(file);
		getRecInReg(file);
		getRecInRec(file);
		getRegInRec(file);
		getGlobalVariableListInReg(file);
		getGlobalVariableListInRec(file);

		
		file.getVariables().setWvs(file.getVariables().getGlobalVariableListInReg().size());
		file.getVariables().setWvs(0);
		file.getVariables().setNpdtv(file.getVariables().getGlobalVariableListInReg().size());
		file.getVariables().setNpdtv(0);
		file.getVariables().setNcdtv(file.getVariables().getGlobalVariableListInReg().size());
		file.getVariables().setNcdtv(0);
		
	
		file.getVariables().setFinalValue();

	}

	//Process for a multi file upload
	@Override
	public void process2() {

		for (CustomFile file : fileList) {
			getClassNames(file);
			getObjectSet(file);
			getMethodSet(file);
			getSystemMethods(file);
			setEndLineNumber(file);
			getGlobalVariableSet(file);
			getRecursiveMethods(file);
			getRegularMethods(file);
			getRegInReg(file);
			getRecInReg(file);
			getRecInRec(file);
			getRegInRec(file);
			getGlobalVariableListInReg(file);
			getGlobalVariableListInRec(file);
		}

		for (CustomFile file : fileList) {
			getObjectSet_DF(file);
			getSystemMethods_DF(file);
			getGlobalVariableList_DF(file);
		}

		for (CustomFile file : fileList) {
			getCalledMethodSet_DF(file);
		}

		getMethods_DF();
		getRegInReg_DF();
		getRecInReg_DF();
		getRecInRec_DF();
		getRegInRec_DF();
		getGlobalVariableListInReg_DF();
		getGlobalVariableListInRec_DF();

		for (CustomFile file : fileList) {
			
			

			file.getVariables().setWvs(file.getVariables().getRecursiveMethodCalls().size());
			file.getVariables().setNpdtv(file.getVariables().getRegularInRegularMethods().size());
			file.getVariables().setNcdtv(file.getVariables().getRegularInRegularMethods_DF().size());
			
			
			file.getVariables().setFinalValue();

		}

	}

	//Process for calculations to display results in the table
	public static void process3() {
		
		int count = 0;

		for (CustomFile file : fileList) {
			int[] sum = new int[3];
			for (Line line : file.getLineSet()) {

				// col1
				count = 0;
				for (Line regLine : file.getVariables().getRecursiveMethodCalls()) {
					if (regLine.getLineNumber() == line.getLineNumber()) {
						count++;
					}
				}

				line.setSum(0, count);
				file.getVariables();
				line.setColValues(0, count * Variables.getWeights()[0]);

				// col2
				count = 0;
				for (Line regLine : file.getVariables().getRegularInRegularMethods()) {
					if (regLine.getLineNumber() == line.getLineNumber()) {
						count++;
					}
				}

				line.setSum(1, count);
				file.getVariables();
				line.setColValues(1, count * Variables.getWeights()[1]);

				// col3
				if (fileList.size() > 1) {
					count = 0;
					for (Line regLine : file.getVariables().getRegularInRegularMethods_DF()) {
						if (regLine.getLineNumber() == line.getLineNumber()) {
							count++;
						}
					}
					line.setSum(2, count);
					line.setColValues(2, count * Variables.getWeights()[2]);
				} else {
					line.setSum(2, 0);
					line.setColValues(2, 0);
				}

				// col4
				count = 0;
				for (Line regLine : file.getVariables().getRecursiveInRegularMethods()) {
					if (regLine.getLineNumber() == line.getLineNumber()) {
						count++;
					}
				}
				line.setSum(3, count);
				line.setColValues(3, count * Variables.getWeights()[3]);

				// col5
				if (fileList.size() > 1) {
					count = 0;
					for (Line regLine : file.getVariables().getRecursiveInRegularMethods_DF()) {
						if (regLine.getLineNumber() == line.getLineNumber()) {
							count++;
						}
					}
					line.setSum(4, count);
					line.setColValues(4, count * Variables.getWeights()[4]);
				} else {
					line.setSum(2, 0);
					line.setColValues(2, 0);
				}

				

			}

			for (Line line : file.getLineSet()) {

				for (int i = 0; i < line.getSum().length; i++) {
					sum[i] += line.getSum()[i];
				}

			}
			file.getVariables().setFinalValue();
			file.getVariables().setSum(sum);
		}

	}



}