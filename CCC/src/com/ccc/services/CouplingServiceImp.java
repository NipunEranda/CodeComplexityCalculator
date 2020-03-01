package com.ccc.services;

import java.util.ArrayList;
import java.util.List;

import com.ccc.model.Coupling;
import com.ccc.model.CustomFile;
import com.ccc.model.FileRead;
import com.ccc.model.Line;
import com.ccc.util.LineArrayListToArray;

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
		file.setMethodList(methodSet);
	}

	@Override
	public void getCalledMethodSet(CustomFile file) {

		for (Line line : file.getLineSet()) {

			for (Line method : file.getMethodList()) {
				if (line.getLineContent().contains("." + method)) {
					System.out.println("Called Method List -> " + method);
					file.getCalledMethodList().add(method);
					file.getCalledMethodList().add(new Line(line.getLineNumber(), line.getLineContent()));
				}
			}
		}

	}

	// Senario 1
	@Override
	public void getRecursiveMethods(CustomFile file) {

		for (int i = 0; i < file.getMethodList().size(); i++) {

			if (i == file.getMethodList().size() - 1) {
				for (int j = file.getMethodList().get(i).getLineNumber() + 1; j < file.getLastIndex(); j++) {

					if (file.getLineSet().get(j).getLineContent()
							.contains(file.getMethodList().get(i).getLineContent())) {
						System.out.println(file.getMethodList().get(i).getLineNumber());
					}

				}
			} else {
 
				for (int j = file.getMethodList().get(i).getLineNumber() + 1; j < file.getMethodList().get(i + 1)
						.getLineNumber() - 1; j++) {

					if (file.getLineSet().get(j).getLineContent()
							.contains(file.getMethodList().get(i).getLineContent())) {
						System.out.println(file.getMethodList().get(i).getLineNumber());
					}

				}

			}

		}

	}

	@Override
	public ArrayList<List> getRegularMethods(CustomFile file) {

		for (Line alLine : file.getMethodList()) {

			for (Line recLine : file.getRecursiveMethods()) {
				if (alLine.getLineNumber() != recLine.getLineNumber()) {
					file.getRegularMethods().add(alLine);
				}
			}
		}
		return null;

	}

	// Senario 2
	@Override
	public ArrayList<List> getRegToReg(CustomFile file) {

		Line[] lineList = LineArrayListToArray.convert(file.getRegularMethods());

		for (int i = 0; i <= lineList.length - 1; i++) {
			if (i == lineList.length - 1) {
				for (int j = lineList[i].getLineNumber() + 1; j <= file.getLastIndex(); ++j) {

					if (file.getLineSet().get(j - 1).getLineContent()
							.contains(getMethodName(lineList[i].getLineContent()))) {
						file.getRegularToRegularMethods().add(lineList[i]);
						System.out.println("Recursive Method" + lineList[i].getLineNumber());
					}

				}
			} else {
				for (int j = lineList[i].getLineNumber() + 1; j < lineList[i + 1].getLineNumber(); ++j) {

					if (file.getLineSet().get(j - 1).getLineContent()
							.contains(getMethodName(lineList[i].getLineContent()))) {
						file.getRegularToRegularMethods().add(lineList[i]);
						System.out.println("Recursive Method" + lineList[i].getLineNumber());
					}

				}
			}

		}
		return null;

	}

}
