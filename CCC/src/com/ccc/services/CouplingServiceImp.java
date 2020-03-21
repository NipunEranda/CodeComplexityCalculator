package com.ccc.services;

import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.List;

import com.ccc.model.Coupling;
import com.ccc.model.CustomFile;
import com.ccc.model.FileRead;
import com.ccc.model.Line;
import com.ccc.util.LineArrayListToArray;

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
	public void getMethodListFull(CustomFile file) {

		ArrayList<Line> methodSetFull = new ArrayList<Line>();

		for (Line line : file.getLineSet()) {
			if (line.getLineContent().contains("main") || line.getLineContent().contains("class")
					|| line.getLineContent().contains("if") || line.getLineContent().contains("switch")
					|| line.getLineContent().contains("catch") || line.getLineContent().contains("return")
					|| line.getLineContent().contains(";")) {

			} else if (line.getLineContent().contains("{") && (line.getLineContent().contains("public")
					|| line.getLineContent().contains("private") || line.getLineContent().contains("protected"))) {
				methodSetFull.add(new Line(line.getLineNumber(), line.getLineContent()));
			}
		}
		file.getCoupling().setMethodSetFull(methodSetFull);

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
	public boolean isAMethod(Line line) {

		if (line.getLineContent().contains("main") || line.getLineContent().contains("class")
				|| line.getLineContent().contains("if") || line.getLineContent().contains("switch")
				|| line.getLineContent().contains("catch") || line.getLineContent().contains("return")
				|| line.getLineContent().contains(";")) {
			return false;
		} else if (line.getLineContent().contains("{") && (line.getLineContent().contains("public")
				|| line.getLineContent().contains("private") || line.getLineContent().contains("protected"))) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public void getMethodSet(CustomFile file) {

		ArrayList<Line> methodSet = new ArrayList<Line>();

		for (Line line : file.getLineSet()) {
			if (line.getLineContent().contains("main") || line.getLineContent().contains("class")
					|| line.getLineContent().contains("if") || line.getLineContent().contains("switch")
					|| line.getLineContent().contains("catch") || line.getLineContent().contains("return")
					|| line.getLineContent().contains(";")) {

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
	public void getCalledMethodSet(CustomFile file) {

		ArrayList<Line> calledMethodSet = new ArrayList<Line>();

		for (Line line : file.getLineSet()) {

			for (Line method : file.getCoupling().getMethodList()) {
				if (line.getLineContent().contains("." + method.getLineContent())) {
					calledMethodSet.add(method);
				}
			}

		}
		file.getCoupling().setCalledMethodList(calledMethodSet);
	}

	@Override
	public void setEndLineNumber(CustomFile file) {

		for (int i = 0; i < file.getCoupling().getMethodSetFull().size(); i++) {

			int opnBrkt = 0;
			int clsBrkt = 0;

			if (i == file.getCoupling().getMethodSetFull().size() - 1) {

				for (int j = file.getCoupling().getMethodSetFull().get(i).getLineNumber() - 1; j < file
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

				for (int j = file.getCoupling().getMethodSetFull().get(i).getLineNumber() - 1; j < file.getCoupling()
						.getMethodSetFull().get(i + 1).getLineNumber() - 1; ++j) {

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

		for (int i = 0; i < file.getCoupling().getMethodList().size(); i++) {

			if (i == file.getCoupling().getMethodList().size() - 1) {
				for (int j = file.getCoupling().getMethodList().get(i).getLineNumber() + 1; j < file
						.getLastIndex(); j++) {

					if (file.getLineSet().get(j).getLineContent()
							.contains(file.getCoupling().getMethodList().get(i).getLineContent())) {
						recursiveMethodSet.add(new Line(file.getCoupling().getMethodList().get(i).getLineNumber(),
								file.getCoupling().getMethodList().get(i).getLineContent()));
					}

				}
			} else {

				for (int j = file.getCoupling().getMethodList().get(i).getLineNumber() + 1; j < file.getCoupling()
						.getMethodList().get(i + 1).getLineNumber() - 1; j++) {

					if (file.getLineSet().get(j).getLineContent()
							.contains(file.getCoupling().getMethodList().get(i).getLineContent())) {
						recursiveMethodSet.add(new Line(file.getCoupling().getMethodList().get(i).getLineNumber(),
								file.getCoupling().getMethodList().get(i).getLineContent()));
					}

				}

			}

		}
		file.getCoupling().setRecursiveMethods(recursiveMethodSet);
	}

	@Override
	public void getRegularMethods(CustomFile file) {

		ArrayList<Line> regularMethodSet = new ArrayList<Line>();

		for (int i = 0; i < file.getCoupling().getMethodList().size(); i++) {
			if (i == file.getCoupling().getMethodList().size() - 1) {
				boolean no_recursive = true;
				for (int j = file.getCoupling().getMethodList().get(i).getLineNumber() + 1; j < file
						.getLastIndex(); j++) {

					if (file.getLineSet().get(j).getLineContent()
							.contains(file.getCoupling().getMethodList().get(i).getLineContent())) {
						no_recursive = false;
					}

				}

				if (no_recursive) {
					regularMethodSet.add(new Line(file.getCoupling().getMethodList().get(i).getLineNumber(),
							file.getCoupling().getMethodList().get(i).getLineContent()));
				}

			} else {
				boolean no_recursive = true;
				for (int j = file.getCoupling().getMethodList().get(i).getLineNumber() + 1; j < file.getCoupling()
						.getMethodList().get(i + 1).getLineNumber() - 1; j++) {

					if (file.getLineSet().get(j).getLineContent()
							.contains(file.getCoupling().getMethodList().get(i).getLineContent())) {
						no_recursive = false;
					}

				}

				if (no_recursive) {
					regularMethodSet.add(new Line(file.getCoupling().getMethodList().get(i).getLineNumber(),
							file.getCoupling().getMethodList().get(i).getLineContent()));
				}

			}
		}
		file.getCoupling().setRegularMethods(regularMethodSet);

	}

	// Senario 2
	@Override
	public void getRegInReg(CustomFile file) {

		ArrayList<Line> regInReg = new ArrayList<Line>();

		for (int i = 0; i < file.getCoupling().getMethodList().size(); i++) {
			if (i == file.getCoupling().getMethodList().size() - 1) {
				boolean is_recursive = false;
				for (int j = file.getCoupling().getMethodList().get(i).getLineNumber() + 1; j < file
						.getLastIndex(); j++) {
					if (file.getLineSet().get(j).getLineContent()
							.contains(file.getCoupling().getMethodList().get(i).getLineContent())) {
						is_recursive = true;
					}
				}
				if (is_recursive == true) {
					continue;
				} else {
					regInReg.add(file.getCoupling().getMethodList().get(i));
				}
				is_recursive = false;
			} else {
				boolean is_recursive = false;
				for (int j = file.getCoupling().getMethodList().get(i).getLineNumber() + 1; j < file.getCoupling()
						.getMethodList().get(i + 1).getLineNumber() - 1; j++) {
					if (file.getLineSet().get(j).getLineContent()
							.contains(file.getCoupling().getMethodList().get(i).getLineContent())) {
						is_recursive = true;
					}
				}
				if (is_recursive == true) {
					continue;
				} else {
					regInReg.add(file.getCoupling().getMethodList().get(i));
				}
				is_recursive = false;
			}
		}
		file.getCoupling().setRegularInRegularMethods(regInReg);

	}

	// Senario 4
	@Override
	public void getRecInReg(CustomFile file) {

		ArrayList<Line> recInReg = new ArrayList<Line>();

		for (int i = 0; i < file.getCoupling().getRegularMethods().size(); i++) {

			if (i == file.getCoupling().getRegularMethods().size() - 1) {
				boolean isAMethod = false;
				for (int j = file.getCoupling().getRegularMethods().get(i).getLineNumber(); j < file
						.getLastIndex(); j++) {

					if (isAMethod(file.getLineSet().get(j))) {
						break;
					} else {

						for (Line line : file.getCoupling().getRecursiveMethods()) {
							if (file.getLineSet().get(j).getLineContent().contains(line.getLineContent())) {
								recInReg.add(line);
							}
						}

					}

				}

			} else {

				for (int j = file.getCoupling().getRegularMethods().get(i).getLineNumber(); j < file.getCoupling()
						.getRegularMethods().get(i + 1).getLineNumber() - 1; j++) {

					for (Line line : file.getCoupling().getRecursiveMethods()) {
						if (file.getLineSet().get(j).getLineContent().contains(line.getLineContent())) {
							recInReg.add(line);
						}
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

		for (int i = 0; i < file.getCoupling().getRecursiveMethods().size(); i++) {

			if (i == file.getCoupling().getRecursiveMethods().size() - 1) {

				for (int k = file.getCoupling().getRecursiveMethods().get(i).getLineNumber(); k < file
						.getLastIndex(); k++) {

					boolean isAMethod = false;
					if (isAMethod(file.getLineSet().get(k))) {
						break;
					} else {
						if (file.getLineSet().get(k).getLineContent()
								.contains(file.getCoupling().getRecursiveMethods().get(i).getLineContent())) {
							for (Line line : file.getCoupling().getRecursiveMethods()) {
								if (!line.getLineContent()
										.contains(file.getCoupling().getRecursiveMethods().get(i).getLineContent())) {
									if (file.getLineSet().get(k).getLineContent().contains(line.getLineContent())) {
										recInRec.add(file.getCoupling().getRecursiveMethods().get(i));
									}
								}
							}
						}
						if (!file.getLineSet().get(k).getLineContent()
								.contains(file.getCoupling().getRecursiveMethods().get(i).getLineContent())) {
							for (Line line : file.getCoupling().getRecursiveMethods()) {
								if (file.getLineSet().get(k).getLineContent().contains(line.getLineContent())) {
									recInRec.add(file.getCoupling().getRecursiveMethods().get(i));
								}
							}
						}
					}

				}

			} else {
				for (int k = file.getCoupling().getRecursiveMethods().get(i).getLineNumber(); k < file.getCoupling()
						.getRecursiveMethods().get(i + 1).getLineNumber() - 1; k++) {

					boolean isAMethod = false;
					if (isAMethod(file.getLineSet().get(k))) {
						break;
					} else {
						if (file.getLineSet().get(k).getLineContent()
								.contains(file.getCoupling().getRecursiveMethods().get(i).getLineContent())) {
							for (Line line : file.getCoupling().getRecursiveMethods()) {
								if (!line.getLineContent()
										.contains(file.getCoupling().getRecursiveMethods().get(i).getLineContent())) {
									if (file.getLineSet().get(k).getLineContent().contains(line.getLineContent())) {
										recInRec.add(file.getCoupling().getRecursiveMethods().get(i));
									}
								}
							}
						}
						if (!file.getLineSet().get(k).getLineContent()
								.contains(file.getCoupling().getRecursiveMethods().get(i).getLineContent())) {
							for (Line line : file.getCoupling().getRecursiveMethods()) {
								if (file.getLineSet().get(k).getLineContent().contains(line.getLineContent())) {
									recInRec.add(file.getCoupling().getRecursiveMethods().get(i));
								}
							}
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

		for (int i = 0; i < file.getCoupling().getRecursiveMethods().size(); i++) {

			if (i == file.getCoupling().getRecursiveMethods().size() - 1) {

				for (int j = file.getCoupling().getRecursiveMethods().get(i).getLineNumber(); j < file
						.getLastIndex(); j++) {

					boolean isAMethod = false;
					if (isAMethod(file.getLineSet().get(j))) {
						break;
					} else {

						for (Line line : file.getCoupling().getRegularMethods()) {
							if (file.getLineSet().get(j).getLineContent().contains(line.getLineContent())) {
								regInRec.add(file.getCoupling().getRecursiveMethods().get(i));
							}
						}

					}

				}

			} else {

				for (int j = file.getCoupling().getRecursiveMethods().get(i).getLineNumber(); j < file.getCoupling()
						.getRecursiveMethods().get(i + 1).getLineNumber() - 1; j++) {

					boolean isAMethod = false;
					if (isAMethod(file.getLineSet().get(j))) {
						break;
					} else {

						for (Line line : file.getCoupling().getRegularMethods()) {

							if (file.getLineSet().get(j).getLineContent().contains(line.getLineContent())) {
								regInRec.add(file.getCoupling().getRecursiveMethods().get(i));
							}

						}

					}

				}

			}

		}
		file.getCoupling().setRegularInRecursiveMethods(regInRec);
	}

	@Override
	public void getGlobalVariableSet(CustomFile file) {

		ArrayList<Line> globalVariableSet = new ArrayList<Line>();
		ArrayList<Line> finalSet = new ArrayList<Line>();

		for (Line line : file.getLineSet()) {

			// Check more details on global variables
			if (line.getLineContent().contains("=") && line.getLineContent().contains(";")) {
				if (line.getLineContent().contains("main ") || line.getLineContent().contains("class ")
						|| line.getLineContent().contains("if ") || line.getLineContent().contains("switch ")
						|| line.getLineContent().contains("catch ") || line.getLineContent().contains("return ")
						|| line.getLineContent().contains("private") || line.getLineContent().contains("protected")) {

				} else if (line.getLineContent().contains("byte") || line.getLineContent().contains("short")
						|| line.getLineContent().contains("int") || line.getLineContent().contains("long")
						|| line.getLineContent().contains("float") || line.getLineContent().contains("double")
						|| line.getLineContent().contains("char") || line.getLineContent().contains("boolean")) {
					String[] sub = line.getLineContent().split(" ");

					if (line.getLineContent().contains("public ") && line.getLineContent().contains("static ")) {
						globalVariableSet.add(new Line(line.getLineNumber(), sub[3]));
					} else if (line.getLineContent().contains("public ")) {
						globalVariableSet.add(new Line(line.getLineNumber(), sub[2]));
					} else {
						globalVariableSet.add(new Line(line.getLineNumber(), sub[1]));
					}
				}
			}
		}

		for (Line line : globalVariableSet) {

			if (line.getLineNumber() < file.getCoupling().getMethodList().get(0).getLineNumber()) {
				finalSet.add(line);
			} else {

				for (int i = 1; i < file.getCoupling().getMethodList().size(); i++) {

					if (line.getLineNumber() > file.getCoupling().getMethodList().get(i).getEndLineNumber()
							&& line.getLineNumber() < file.getCoupling().getMethodList().get(i + 1).getLineNumber()) {
						finalSet.add(line);
					}

				}

			}
		}
		file.getCoupling().setGlobalVariableSet(finalSet);

	}

	// Senario 10
	@Override
	public void getGlobalVariableListInReg(CustomFile file) {

		ArrayList<Line> globalVariableSetInReg = new ArrayList<Line>();

		for (Line line : file.getCoupling().getGlobalVariableSet()) {

			for (Line methodLine : file.getCoupling().getRegularMethods()) {

				for (int i = methodLine.getLineNumber(); i < getEndLineNumber(file, methodLine); i++) {

					if (file.getLineSet().get(i).getLineContent().contains(line.getLineContent() + " = ")
							|| file.getLineSet().get(i).getLineContent().contains(line.getLineContent() + "=")) {
						globalVariableSetInReg.add(file.getLineSet().get(i));
					}

					ArrayList<Line> globalVariableSet = new ArrayList<Line>();
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

					if (file.getLineSet().get(i).getLineContent().contains(line.getLineContent() + " = ")
							|| file.getLineSet().get(i).getLineContent().contains(line.getLineContent() + "=")) {
						globalVariableSetInRec.add(file.getLineSet().get(i));
					}

				}

			}
		}
		file.getCoupling().setGlobalVariableListInRec(globalVariableSetInRec);

	}

	@Override
	public void getCalledMethodSet_DF(CustomFile ifile, ArrayList<CustomFile> fileList) {
		ArrayList<Line> objectList = new ArrayList<>();
		ArrayList<Line> calledMethodList_DF = new ArrayList<>();
		for (CustomFile file : fileList) {
			if (!file.getFileName().equalsIgnoreCase(ifile.getFileName())) {
				for (Line line : ifile.getLineSet()) {
					if (line.getLineContent().contains(file.getFileName().split("\\.")[0])) {

						if (line.getLineContent().contains("new")) {
							objectList.add(line);
						}

					}

					if (objectList.size() > 0) {
						for (Line obj : objectList) {

							if (line.getLineContent().contains(obj.getLineContent().split(" ")[1])
									&& line.getLineContent().contains(".") && line.getLineContent().contains(";")) {
								line.setFileName(obj.getLineContent().split(" ")[0]);
								line.setObjName(obj.getLineContent().split(" ")[1]);
								line.setFile(file);
								calledMethodList_DF.add(line);
							}

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
			for (Line line : file.getCoupling().getCalledMethodList_DF()) {

				for (Line line_reg : file.getCoupling().getRegularMethods()) {

					for (Line line_end : file.getCoupling().getMethodList()) {

						if (line_end.getLineNumber() == line_reg.getLineNumber()) {

							if (line.getLineNumber() > line_reg.getLineNumber()
									&& line.getLineNumber() < line_end.getEndLineNumber()) {
								inReg.add(line);
							}

						}

					}

				}

				for (Line line_rec : file.getCoupling().getRecursiveMethods()) {

					for (Line line_end : file.getCoupling().getMethodList()) {

						if (line_end.getLineNumber() == line_rec.getLineNumber()) {

							if (line.getLineNumber() > line_rec.getLineNumber()
									&& line.getLineNumber() < line_end.getEndLineNumber()) {
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

				for (Line line_reg : line.getFile().getCoupling().getRegularMethods()) {

					if (line.getLineContent().contains(line_reg.getLineContent())) {
						regInReg_DF.add(line);
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
			for (Line line : file.getCoupling().getInReg_DF()) {

				for (Line line_rec : line.getFile().getCoupling().getRecursiveMethods()) {

					if (line.getLineContent().contains(line_rec.getLineContent())) {
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

				for (Line line_rec : line.getFile().getCoupling().getRecursiveMethods()) {

					if (line.getLineContent().contains(line_rec.getLineContent())) {
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
			for (Line line : file.getCoupling().getInRec_DF()) {

				for (Line line_reg : line.getFile().getCoupling().getRegularMethods()) {

					if (line.getLineContent().contains(line_reg.getLineContent())) {
						regInRec_DF.add(line);
					}

				}
			}
			file.getCoupling().setRegularInRecursiveMethods_DF(regInRec_DF);
		}
	}

	@Override
	public void process1(CustomFile file) {
		getMethodSet(file);
		getMethodListFull(file);
		setEndLineNumber(file);
		getCalledMethodSet(file);
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

	@Override
	public void process2(ArrayList<CustomFile> fileList) {

		for (CustomFile file : fileList) {
			getCalledMethodSet_DF(file, fileList);
		}

		getMethods_DF(fileList);
		getRegInReg_DF(fileList);
		getRecInReg_DF(fileList);
		getRecInRec_DF(fileList);
		getRegInRec_DF(fileList);

		
	}

}
