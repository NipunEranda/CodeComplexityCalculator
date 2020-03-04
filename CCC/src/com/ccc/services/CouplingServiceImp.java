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

	// Senario 1
	@Override
	public void getRecursiveMethods(CustomFile file) {

		ArrayList<Line> recursiveMethodSet = new ArrayList<Line>();

		for (int i = 0; i < file.getCoupling().getMethodList().size(); i++) {

			if (i == file.getCoupling().getMethodList().size() - 1) {
				for (int j = file.getCoupling().getMethodList().get(i).getLineNumber() + 1; j < file.getLastIndex(); j++) {

					if (file.getLineSet().get(j).getLineContent()
							.contains(file.getCoupling().getMethodList().get(i).getLineContent())) {
						recursiveMethodSet.add(new Line(file.getCoupling().getMethodList().get(i).getLineNumber(),
								file.getCoupling().getMethodList().get(i).getLineContent()));
					}

				}
			} else {

				for (int j = file.getCoupling().getMethodList().get(i).getLineNumber() + 1; j < file.getCoupling().getMethodList().get(i + 1)
						.getLineNumber() - 1; j++) {

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
				for (int j = file.getCoupling().getMethodList().get(i).getLineNumber() + 1; j < file.getLastIndex(); j++) {

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
				for (int j = file.getCoupling().getMethodList().get(i).getLineNumber() + 1; j < file.getCoupling().getMethodList().get(i + 1)
						.getLineNumber() - 1; j++) {

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
				for (int j = file.getCoupling().getMethodList().get(i).getLineNumber() + 1; j < file.getLastIndex(); j++) {
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
				for (int j = file.getCoupling().getMethodList().get(i).getLineNumber() + 1; j < file.getCoupling().getMethodList().get(i + 1)
						.getLineNumber() - 1; j++) {
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

		
		
	}

}
