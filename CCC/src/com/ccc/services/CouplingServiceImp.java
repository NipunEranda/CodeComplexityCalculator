package com.ccc.services;

import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.tomcat.jni.File;

import com.ccc.model.Coupling;
import com.ccc.model.CustomFile;
import com.ccc.model.FileRead;
import com.ccc.model.Line;
import com.ccc.util.LineArrayListToArray;
import com.ccc.util.RemoveDuplicates;

import sun.nio.ch.FileKey;

public class CouplingServiceImp implements CouplingService {

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
		file.getCoupling().setClassList(classList);
	}

	@Override
	public void getObjectSet(CustomFile file) {
		ArrayList<Line> classObjList = new ArrayList<Line>();

		for (Line line : file.getLineSet()) {

			if (line.getLineContent().contains(" new ")) {
				for (Line line_class : file.getCoupling().getClassList()) {
					if (line.getLineContent().contains(line_class.getLineContent())) {

						String[] sub = line.getLineContent().split("\\(");
						String x = sub[0].replace(line_class.getLineContent(), "").trim();
						classObjList.add(new Line(line.getLineNumber(), x.split(" ")[0]));
					}
				}
			}

		}

		file.getCoupling().setClassObjectList(classObjList);
	}

	@Override
	public void getObjectSet_DF(CustomFile ifile, ArrayList<CustomFile> fileList) {
		ArrayList<Line> classObjList_DF = new ArrayList<Line>();
		for (CustomFile file : fileList) {

			if (!file.getFileName().equalsIgnoreCase(ifile.getFileName())) {

				for (Line line : ifile.getLineSet()) {

					for (Line line_class : file.getCoupling().getClassList()) {

						if (line.getLineContent().contains(" new ")
								&& line.getLineContent().contains(line_class.getLineContent())) {
							String[] sub = line.getLineContent().split("\\(");
							String x = sub[0].replace(line_class.getLineContent(), "").trim();
							classObjList_DF.add(new Line(line.getLineNumber(), x.split(" ")[0]));
						}

					}

				}

			}
			ifile.getCoupling().setClassObjectList_DF(classObjList_DF);
		}

	}

	@Override
	public int getEndLineNumber(CustomFile file, Line line) {
		int endNumber = 0;
		for (Line line1 : file.getCoupling().getMethodList()) {

			if (line1.getLineNumber() == line.getLineNumber()) {
				endNumber = line1.getEndLineNumber();
				break;
			}

		}
		return endNumber;
	}

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
		file.getCoupling().setMethodList(methodSet);
	}

	@Override
	public void setEndLineNumber(CustomFile file) {

		for (int i = 0; i < file.getCoupling().getMethodList().size(); i++) {

			int opnBrkt = 0;
			int clsBrkt = 0;

			if (i == file.getCoupling().getMethodList().size() - 1) {

				for (int j = file.getCoupling().getMethodList().get(i).getLineNumber() - 1; j < file
						.getLastIndex(); ++j) {

					if (file.getLineSet().get(j).getLineContent().contains("{")
							&& file.getLineSet().get(j).getLineContent().contains("}")) {

					} else if (file.getLineSet().get(j).getLineContent().contains("{")) {
						++opnBrkt;
					} else if (file.getLineSet().get(j).getLineContent().contains("}")) {
						--opnBrkt;
					}

					if (opnBrkt == 0) {
						file.getCoupling().setEndLineNumber(i, ++j);
						break;
					} else {
						continue;
					}
				}

			} else {

				for (int j = file.getCoupling().getMethodList().get(i).getLineNumber() - 1; j < file.getCoupling()
						.getMethodList().get(i + 1).getLineNumber() - 1; ++j) {

					if (file.getLineSet().get(j).getLineContent().contains("{")
							&& file.getLineSet().get(j).getLineContent().contains("}")) {

					} else if (file.getLineSet().get(j).getLineContent().contains("{")) {
						++opnBrkt;
					} else if (file.getLineSet().get(j).getLineContent().contains("}")) {
						--opnBrkt;
					}

					if (opnBrkt == clsBrkt) {
						file.getCoupling().setEndLineNumber(i, ++j);
						break;
					} else {
						continue;
					}

				}

			}

		}

	}

	// Senario 1
	@Override
	public void getRecursiveMethods(CustomFile file) {

		ArrayList<Line> recursiveMethodSet = new ArrayList<Line>();
		ArrayList<Line> recursiveMethodCalls = new ArrayList<Line>();

		for (Line line : file.getCoupling().getMethodList()) {
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

		file.getCoupling().setRecursiveMethods(recursiveMethodSet);
		file.getCoupling().setRecursiveMethodCalls(recursiveMethodCalls);
	}

	@Override
	public void getRegularMethods(CustomFile file) {

		ArrayList<Line> regularMethodSet = new ArrayList<Line>();

		for (Line line : file.getCoupling().getMethodList()) {
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

		file.getCoupling().setRegularMethods(regularMethodSet);
	}

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

				for (Line line_method : file.getCoupling().getMethodList()) {

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

		file.getCoupling().setSystemMethods(systemMethodList);
	}

	// Senario 2
	@Override
	public void getRegInReg(CustomFile file) {

		ArrayList<Line> regInReg = new ArrayList<Line>();

		for (Line line : file.getCoupling().getRegularMethods()) {
			for (Line regLine : file.getCoupling().getRegularMethods()) {
				if (line.getLineNumber() != regLine.getLineNumber()) {
					for (int i = line.getLineNumber(); i < getEndLineNumber(file, line); i++) {

						if (file.getLineSet().get(i).getLineContent().contains(regLine.getLineContent())) {
							regInReg.add(file.getLineSet().get(i));
						}

					}
				}

			}
			for (Line line_sys : file.getCoupling().getSystemMethods()) {

				if (line_sys.getLineNumber() > line.getLineNumber()
						&& line_sys.getLineNumber() < getEndLineNumber(file, line)) {
					regInReg.add(line_sys);
				}

			}
		}
		file.getCoupling().setRegularInRegularMethods(regInReg);
	}

	// Senario 4
	@Override
	public void getRecInReg(CustomFile file) {

		ArrayList<Line> recInReg = new ArrayList<Line>();

		for (Line regLine : file.getCoupling().getRegularMethods()) {

			for (int i = regLine.getLineNumber(); i < getEndLineNumber(file, regLine); i++) {

				for (Line recLine : file.getCoupling().getRecursiveMethods()) {
					if (file.getLineSet().get(i).getLineContent().contains(recLine.getLineContent())) {
						recInReg.add(file.getLineSet().get(i));
					}
				}

			}

		}
		file.getCoupling().setRecursiveInRegularMethods(recInReg);
	}

	// Senario 6
	@Override
	public void getRecInRec(CustomFile file) {

		ArrayList<Line> recInRec = new ArrayList<Line>();

		for (Line line : file.getCoupling().getRecursiveMethods()) {
			for (Line recLine : file.getCoupling().getRecursiveMethods()) {
				if (line.getLineNumber() != recLine.getLineNumber()) {
					for (int i = line.getLineNumber(); i < getEndLineNumber(file, line); i++) {

						if (file.getLineSet().get(i).getLineContent().contains(recLine.getLineContent())) {
							recInRec.add(file.getLineSet().get(i));
						}

					}
				}

			}
		}

		file.getCoupling().setRecursiveInRecursiveMethods(recInRec);

	}

	// Senario 8
	@Override
	public void getRegInRec(CustomFile file) {

		ArrayList<Line> regInRec = new ArrayList<Line>();

		for (Line recLine : file.getCoupling().getRecursiveMethods()) {

			for (int i = recLine.getLineNumber(); i < getEndLineNumber(file, recLine); i++) {

				for (Line regLine : file.getCoupling().getRegularMethods()) {
					if (file.getLineSet().get(i).getLineContent().contains(regLine.getLineContent())) {
						regInRec.add(file.getLineSet().get(i));
					}
				}

			}

			for (Line line_sys : file.getCoupling().getSystemMethods()) {

				if (line_sys.getLineNumber() > recLine.getLineNumber()
						&& line_sys.getLineNumber() < getEndLineNumber(file, recLine)) {
					regInRec.add(line_sys);
				}

			}

		}

		file.getCoupling().setRegularInRecursiveMethods(regInRec);
	}

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

			for (Line line_meth : file.getCoupling().getMethodList()) {
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

		file.getCoupling().setGlobalVariableSet(variableSet);

	}

	// Senario 10
	@Override
	public void getGlobalVariableListInReg(CustomFile file) {

		ArrayList<Line> globalVariableSetInReg = new ArrayList<Line>();

		for (Line line : file.getCoupling().getGlobalVariableSet()) {

			for (Line line_reg : file.getCoupling().getRegularMethods()) {

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

		file.getCoupling().setGlobalVariableListInReg(globalVariableSetInReg);
	}

	// Senario 12
	@Override
	public void getGlobalVariableListInRec(CustomFile file) {

		ArrayList<Line> globalVariableSetInRec = new ArrayList<Line>();

		for (Line line : file.getCoupling().getGlobalVariableSet()) {

			for (Line methodLine : file.getCoupling().getRecursiveMethods()) {

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

		file.getCoupling().setGlobalVariableListInRec(globalVariableSetInRec);

	}

	@Override
	public void getSystemMethods_DF(CustomFile ifile, ArrayList<CustomFile> fileList) {

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

						for (Line line_method : ifile.getCoupling().getMethodList()) {

							if (method.contains(line_method.getLineContent())) {
								status = false;
								break;
							}

						}

						if (status == true) {
							for (Line line_meth : file.getCoupling().getMethodList()) {
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
			file.getCoupling().setSystemMethods(systemMethodList_DF);
		}
	}

	@Override
	public void getCalledMethodSet_DF(CustomFile ifile, ArrayList<CustomFile> fileList) {
		ArrayList<Line> objectList = new ArrayList<>();
		ArrayList<Line> calledMethodList_DF = new ArrayList<>();
		for (CustomFile file : fileList) {
			if (!file.getFileName().equalsIgnoreCase(ifile.getFileName())) {
				/*
				 * for (Line line : ifile.getLineSet()) { if
				 * (line.getLineContent().contains(file.getFileName().split("\\.")[0])) { if
				 * (line.getLineContent().contains("new")) {
				 * line.setFileName(line.getLineContent().split(" ")[0]);
				 * line.setObjName(line.getLineContent().split(" ")[1]); line.setFile(file);
				 * objectList.add(line); }
				 * 
				 * } ifile.getCoupling().setObjectList(objectList);
				 * 
				 * if (objectList.size() > 0) { for (Line obj : objectList) {
				 * 
				 * if (line.getLineContent().contains(obj.getLineContent().split(" ")[1]) &&
				 * line.getLineContent().contains(".") && line.getLineContent().contains(";")) {
				 * line.setFileName(obj.getLineContent().split(" ")[0]);
				 * line.setObjName(obj.getLineContent().split(" ")[1]); line.setFile(file);
				 * calledMethodList_DF.add(line); }
				 * 
				 * } }
				 * 
				 * }
				 */
				for (Line line : ifile.getLineSet()) {

					for (Line line_meth : file.getCoupling().getMethodList()) {

						if (line.getLineContent().contains("." + line_meth.getLineContent())) {
							line.setFileName(file.getFileName().split("\\.")[0]);
							calledMethodList_DF.add(line);
						}

					}

				}

				ifile.getCoupling().setCalledMethodList_DF(calledMethodList_DF);
			}
		}

	}

	@Override
	public void getMethods_DF(ArrayList<CustomFile> fileList) {

		for (CustomFile file : fileList) {
			ArrayList<Line> inReg = new ArrayList<>();
			ArrayList<Line> inRec = new ArrayList<>();
			/*
			 * for (Line line : file.getCoupling().getCalledMethodList_DF()) {
			 * 
			 * for (Line line_reg : file.getCoupling().getRegularMethods()) {
			 * 
			 * for (Line line_end : file.getCoupling().getMethodList()) {
			 * 
			 * if (line_end.getLineNumber() == line_reg.getLineNumber()) {
			 * 
			 * if (line.getLineNumber() > line_reg.getLineNumber() && line.getLineNumber() <
			 * line_end.getEndLineNumber()) { inReg.add(line); }
			 * 
			 * }
			 * 
			 * }
			 * 
			 * }
			 * 
			 * for (Line line_rec : file.getCoupling().getRecursiveMethods()) {
			 * 
			 * for (Line line_end : file.getCoupling().getMethodList()) {
			 * 
			 * if (line_end.getLineNumber() == line_rec.getLineNumber()) {
			 * 
			 * if (line.getLineNumber() > line_rec.getLineNumber() && line.getLineNumber() <
			 * line_end.getEndLineNumber()) { inRec.add(line); }
			 * 
			 * }
			 * 
			 * }
			 * 
			 * }
			 * 
			 * }
			 */

			for (Line line : file.getCoupling().getCalledMethodList_DF()) {

				for (CustomFile tFile : fileList) {

					if (tFile.getFileName().split("\\.")[0].equalsIgnoreCase(line.getFileName())) {

						for (Line line_meth_reg : tFile.getCoupling().getRegularMethods()) {
							if (line.getLineContent().contains(line_meth_reg.getLineContent())) {
								inReg.add(line);
							}
						}

						for (Line line_meth_rec : tFile.getCoupling().getRecursiveMethods()) {
							if (line.getLineContent().contains(line_meth_rec.getLineContent())) {
								inRec.add(line);
							}
						}

					}

				}

			}

			file.getCoupling().setInReg_DF(inReg);
			file.getCoupling().setInRec_DF(inRec);

		}

	}

	@Override
	public void getRegInReg_DF(ArrayList<CustomFile> fileList) {
		for (CustomFile file : fileList) {
			ArrayList<Line> regInReg_DF = new ArrayList<>();

			for (Line line : file.getCoupling().getInReg_DF()) {

				for (Line line_reg : file.getCoupling().getRegularMethods()) {

					if (line.getLineNumber() > line_reg.getLineNumber()
							&& line.getLineNumber() < line_reg.getEndLineNumber()) {
						regInReg_DF.add(line);
					}

				}

				for (Line line_sys : file.getCoupling().getSystemMethods()) {

					if (line_sys.getLineNumber() > line.getLineNumber()
							&& line_sys.getLineNumber() < getEndLineNumber(file, line)) {
						regInReg_DF.add(line_sys);
					}

				}

			}
			file.getCoupling().setRegularInRegularMethods_DF(regInReg_DF);
		}
	}

	@Override
	public void getRecInReg_DF(ArrayList<CustomFile> fileList) {
		for (CustomFile file : fileList) {
			ArrayList<Line> recInReg_DF = new ArrayList<>();
			for (Line line : file.getCoupling().getInRec_DF()) {

				for (Line line_reg : file.getCoupling().getRegularMethods()) {

					if (line.getLineNumber() > line_reg.getLineNumber()
							&& line.getLineNumber() < line_reg.getEndLineNumber()) {
						recInReg_DF.add(line);
					}

				}
			}
			file.getCoupling().setRecursiveInRegularMethods_DF(recInReg_DF);
		}
	}

	@Override
	public void getRecInRec_DF(ArrayList<CustomFile> fileList) {
		for (CustomFile file : fileList) {
			ArrayList<Line> recInRec_DF = new ArrayList<>();
			for (Line line : file.getCoupling().getInRec_DF()) {

				for (Line line_rec : file.getCoupling().getRecursiveMethods()) {

					if (line.getLineNumber() > line_rec.getLineNumber()
							&& line.getLineNumber() < line_rec.getEndLineNumber()) {
						recInRec_DF.add(line);
					}

				}
			}
			file.getCoupling().setRecursiveInRecursiveMethods_DF(recInRec_DF);
		}
	}

	@Override
	public void getRegInRec_DF(ArrayList<CustomFile> fileList) {
		for (CustomFile file : fileList) {
			ArrayList<Line> regInRec_DF = new ArrayList<>();
			for (Line line : file.getCoupling().getInReg_DF()) {

				for (Line line_rec : file.getCoupling().getRecursiveMethods()) {

					if (line.getLineNumber() > line_rec.getLineNumber()
							&& line.getLineNumber() < line_rec.getEndLineNumber()) {
						regInRec_DF.add(line);
					}

				}

				for (Line line_sys : file.getCoupling().getSystemMethods()) {

					if (line_sys.getLineNumber() > line.getLineNumber()
							&& line_sys.getLineNumber() < getEndLineNumber(file, line)) {
						regInRec_DF.add(line_sys);
					}

				}
			}
			file.getCoupling().setRegularInRecursiveMethods_DF(regInRec_DF);
		}
	}

	@Override
	public void getGlobalVariableList_DF(CustomFile ifile, ArrayList<CustomFile> fileList) {
		ArrayList<Line> globalVariableList_DF = new ArrayList<>();

		for (CustomFile file : fileList) {

			if (!ifile.getFileName().equalsIgnoreCase(file.getFileName())) {
				for (Line line : ifile.getLineSet()) {
					for (Line line_g : file.getCoupling().getGlobalVariableSet()) {
						if(line.getLineContent().contains("."+line_g.getLineContent())) {
							globalVariableList_DF.add(line);
						}
					}
				}
			}

		}

		ifile.getCoupling().setGlobalVariableList_DF(globalVariableList_DF);

	}

	@Override
	public void getGlobalVariableListInReg_DF(ArrayList<CustomFile> fileList) {
		for (CustomFile file : fileList) {
			ArrayList<Line> globalVariableListInReg_DF = new ArrayList<>();
			for (Line regLine : file.getCoupling().getRegularMethods()) {

				for (Line gVariable : file.getCoupling().getGlobalVariableList_DF()) {

					if (gVariable.getLineNumber() > regLine.getLineNumber()
							&& gVariable.getLineNumber() < getEndLineNumber(file, regLine)) {
						globalVariableListInReg_DF.add(gVariable);
					}

				}

			}

			file.getCoupling().setGlobalVariableListInReg_DF(globalVariableListInReg_DF);
		}
	}

	@Override
	public void getGlobalVariableListInRec_DF(ArrayList<CustomFile> fileList) {

		for (CustomFile file : fileList) {
			ArrayList<Line> globalVariableListInRec_DF = new ArrayList<>();
			for (Line recLine : file.getCoupling().getRecursiveMethods()) {

				for (Line gVariable : file.getCoupling().getGlobalVariableList_DF()) {

					if (gVariable.getLineNumber() > recLine.getLineNumber()
							&& gVariable.getLineNumber() < getEndLineNumber(file, recLine)) {
						globalVariableListInRec_DF.add(gVariable);
					}

				}

			}

			file.getCoupling().setGlobalVariableListInRec_DF(globalVariableListInRec_DF);
		}
	}

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

		file.getCoupling().setNr(file.getCoupling().getRecursiveMethodCalls().size());
		file.getCoupling().setNmcms(file.getCoupling().getRegularInRegularMethods().size());
		file.getCoupling().setNmcmd(0);
		file.getCoupling().setNmcrms(file.getCoupling().getRecursiveInRegularMethods().size());
		file.getCoupling().setNmcrmd(0);
		file.getCoupling().setNrmcrms(file.getCoupling().getRecursiveInRecursiveMethods().size());
		file.getCoupling().setNrmcrmd(0);
		file.getCoupling().setNrmcms(file.getCoupling().getRegularInRecursiveMethods().size());
		file.getCoupling().setNrmcmd(0);
		file.getCoupling().setNmrgvs(file.getCoupling().getGlobalVariableListInReg().size());
		file.getCoupling().setNmrgvd(0);
		file.getCoupling().setNrmrgvs(file.getCoupling().getGlobalVariableListInRec().size());
		file.getCoupling().setNrmrgvd(0);
		file.getCoupling().setFinalValue();

	}

	@Override
	public void process2(ArrayList<CustomFile> fileList) {

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
			getObjectSet_DF(file, fileList);
			getSystemMethods_DF(file, fileList);
			getGlobalVariableList_DF(file, fileList);
		}

		for (CustomFile file : fileList) {
			getCalledMethodSet_DF(file, fileList);
		}

		getMethods_DF(fileList);
		getRegInReg_DF(fileList);
		getRecInReg_DF(fileList);
		getRecInRec_DF(fileList);
		getRegInRec_DF(fileList);
		getGlobalVariableListInReg_DF(fileList);
		getGlobalVariableListInRec_DF(fileList);

		for (CustomFile file : fileList) {

			file.getCoupling().setNr(file.getCoupling().getRecursiveMethodCalls().size());
			file.getCoupling().setNmcms(file.getCoupling().getRegularInRegularMethods().size());
			file.getCoupling().setNmcmd(file.getCoupling().getRegularInRegularMethods_DF().size());
			file.getCoupling().setNmcrms(file.getCoupling().getRecursiveInRegularMethods().size());
			file.getCoupling().setNmcrmd(file.getCoupling().getRecursiveInRegularMethods_DF().size());
			file.getCoupling().setNrmcrms(file.getCoupling().getRecursiveInRecursiveMethods().size());
			file.getCoupling().setNrmcrmd(file.getCoupling().getRecursiveInRecursiveMethods_DF().size());
			file.getCoupling().setNrmcms(file.getCoupling().getRegularInRecursiveMethods().size());
			file.getCoupling().setNrmcmd(file.getCoupling().getRegularInRecursiveMethods_DF().size());
			file.getCoupling().setNmrgvs(file.getCoupling().getGlobalVariableListInReg().size());
			file.getCoupling().setNmrgvd(file.getCoupling().getGlobalVariableListInReg_DF().size());
			file.getCoupling().setNrmrgvs(file.getCoupling().getGlobalVariableListInRec().size());
			file.getCoupling().setNrmrgvd(file.getCoupling().getGlobalVariableListInRec_DF().size());
			file.getCoupling().setFinalValue();

		}

	}

	@Override
	public void process3(ArrayList<CustomFile> fileList) {

		int count = 0;
		CustomFile resultFile = new CustomFile("resultFile");
		ArrayList<Line> resultLineSet = new ArrayList<>();

		for (CustomFile file : fileList) {
			int[] sum = new int[13];
			for (Line line : file.getLineSet()) {

				// col1
				count = 0;
				for (Line regLine : file.getCoupling().getRecursiveMethodCalls()) {
					if (regLine.getLineNumber() == line.getLineNumber()) {
						count++;
					}
				}

				line.setSum(0, count);
				line.setColValues(0, count * file.getCoupling().getWr());

				// col2
				count = 0;
				for (Line regLine : file.getCoupling().getRegularInRegularMethods()) {
					if (regLine.getLineNumber() == line.getLineNumber()) {
						count++;
					}
				}

				line.setSum(1, count);
				line.setColValues(1, count * file.getCoupling().getWmcms());

				// col3
				if (fileList.size() > 1) {
					count = 0;
					for (Line regLine : file.getCoupling().getRegularInRegularMethods_DF()) {
						if (regLine.getLineNumber() == line.getLineNumber()) {
							count++;
						}
					}
					line.setSum(2, count);
					line.setColValues(2, count * file.getCoupling().getWmcmd());
				} else {
					line.setSum(2, 0);
					line.setColValues(2, 0);
				}

				// col4
				count = 0;
				for (Line regLine : file.getCoupling().getRecursiveInRegularMethods()) {
					if (regLine.getLineNumber() == line.getLineNumber()) {
						count++;
					}
				}
				line.setSum(3, count);
				line.setColValues(3, count * file.getCoupling().getWmcrms());

				// col5
				if (fileList.size() > 1) {
					count = 0;
					for (Line regLine : file.getCoupling().getRecursiveInRegularMethods_DF()) {
						if (regLine.getLineNumber() == line.getLineNumber()) {
							count++;
						}
					}
					line.setSum(4, count);
					line.setColValues(4, count * file.getCoupling().getWmcrmd());
				} else {
					line.setSum(2, 0);
					line.setColValues(2, 0);
				}

				// col6
				count = 0;
				for (Line regLine : file.getCoupling().getRecursiveInRecursiveMethods()) {
					if (regLine.getLineNumber() == line.getLineNumber()) {
						count++;
					}
				}
				line.setSum(5, count);
				line.setColValues(5, count * file.getCoupling().getWrmcrms());

				// col7
				if (fileList.size() > 1) {
					count = 0;
					for (Line regLine : file.getCoupling().getRecursiveInRecursiveMethods_DF()) {
						if (regLine.getLineNumber() == line.getLineNumber()) {
							count++;
						}
					}
					line.setSum(6, count);
					line.setColValues(6, count * file.getCoupling().getWrmcrmd());
				} else {
					line.setSum(2, 0);
					line.setColValues(2, 0);
				}

				// col8
				count = 0;
				for (Line regLine : file.getCoupling().getRegularInRecursiveMethods()) {
					if (regLine.getLineNumber() == line.getLineNumber()) {
						count++;
					}
				}
				line.setSum(7, count);
				line.setColValues(7, count * file.getCoupling().getWrmcms());

				// col9
				if (fileList.size() > 1) {
					count = 0;
					for (Line regLine : file.getCoupling().getRegularInRecursiveMethods_DF()) {
						if (regLine.getLineNumber() == line.getLineNumber()) {
							count++;
						}
					}
					line.setSum(8, count);
					line.setColValues(8, count * file.getCoupling().getWrmcmd());
				} else {
					line.setSum(2, 0);
					line.setColValues(2, 0);
				}

				// col10
				count = 0;
				for (Line regLine : file.getCoupling().getGlobalVariableListInReg()) {
					if (regLine.getLineNumber() == line.getLineNumber()) {
						count++;
					}
				}
				line.setSum(9, count);
				line.setColValues(9, count * file.getCoupling().getWmrgvs());

				// col11
				if (fileList.size() > 1) {
					count = 0;
					for (Line regLine : file.getCoupling().getGlobalVariableListInReg_DF()) {
						if (regLine.getLineNumber() == line.getLineNumber()) {
							count++;
						}
					}
					line.setSum(10, count);
					line.setColValues(10, count * file.getCoupling().getWmrgvd());
				} else {
					line.setSum(2, 0);
					line.setColValues(2, 0);
				}

				// col12
				count = 0;
				for (Line regLine : file.getCoupling().getGlobalVariableListInRec()) {
					if (regLine.getLineNumber() == line.getLineNumber()) {
						count++;
					}
				}
				line.setSum(11, count);
				line.setColValues(11, count * file.getCoupling().getWrmrgvs());

				// col13
				count = 0;
				if (fileList.size() > 1) {
					for (Line regLine : file.getCoupling().getGlobalVariableListInRec_DF()) {
						if (regLine.getLineNumber() == line.getLineNumber()) {
							count++;
						}
					}
					line.setSum(12, count);
					line.setColValues(12, count * file.getCoupling().getWrmrgvd());
				} else {
					line.setSum(2, 0);
					line.setColValues(2, 0);
				}
				line.setFinalValue();

			}

			for (Line line : file.getLineSet()) {

				for (int i = 0; i < line.getSum().length; i++) {
					sum[i] += line.getSum()[i];
				}

			}
			file.getCoupling().setSum(sum);
		}

	}

}
