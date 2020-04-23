package com.ccc.model;

import java.util.ArrayList;

public class Coupling {

	private String fileName;

	private int Wr = 2;
	private int Wmcms = 2;
	private int Wmcmd = 3;
	private int Wmcrms = 3;
	private int Wmcrmd = 4;
	private int Wrmcrms = 4;
	private int Wrmcrmd = 5;
	private int Wrmcms = 3;
	private int Wrmcmd = 4;
	private int Wmrgvs = 1;
	private int Wmrgvd = 2;
	private int Wrmrgvs = 1;
	private int Wrmrgvd = 2;

	private Line line;
	private int[] lineWeight = new int[14];
	private int Nr;
	private int Nmcms;
	private int Nmcmd;
	private int Nmcrms;
	private int Nmcrmd;
	private int Nrmcrms;
	private int Nrmcrmd;
	private int Nrmcms;
	private int Nrmcmd;
	private int Nmrgvs;
	private int Nmrgvd;
	private int Nrmrgvs;
	private int Nrmrgvd;
	
	private int totr;
	private int totmcms;
	private int totmcmd;
	private int totmcrms;
	private int totmcrmd;
	private int totrmcrms;
	private int totrmcrmd;
	private int totrmcms;
	private int totrmcmd;
	private int totmrgvs;
	private int totmrgvd;
	private int totrmrgvs;
	private int totrmrgvd;
	private int[] sum;
	private int finalValue;
	
	private ArrayList<Line> MethodList;
	private ArrayList<Line> methodSetFull;
	private ArrayList<Line> calledMethodList;
	private ArrayList<Line> calledMethodList_DF;
	private ArrayList<Line> globalVariableSet;
	private ArrayList<Line> globalVariableListInReg;
	private ArrayList<Line> globalVariableListInRec;
	private ArrayList<Line> regularMethods;
	private ArrayList<Line> systemMethods;
	private ArrayList<Line> recursiveMethods;
	private ArrayList<Line> recursiveMethodCalls;
	private ArrayList<Line> regularInRegularMethods;
	private ArrayList<Line> recursiveInRegularMethods;
	private ArrayList<Line> recursiveInRecursiveMethods;
	private ArrayList<Line> regularInRecursiveMethods;

	private ArrayList<Line> objectList;
	private ArrayList<Line> inReg_DF;
	private ArrayList<Line> inRec_DF;
	private ArrayList<Line> regularInRegularMethods_DF;
	private ArrayList<Line> recursiveInRegularMethods_DF;
	private ArrayList<Line> recursiveInRecursiveMethods_DF;
	private ArrayList<Line> regularInRecursiveMethods_DF;
	private ArrayList<Line> globalVariableList_DF;
	private ArrayList<Line> globalVariableListInReg_DF;
	private ArrayList<Line> globalVariableListInRec_DF;

	public Coupling(String fileName) {
		this.setFileName(fileName);
		this.MethodList = null;
		this.calledMethodList = null;
		this.regularMethods = null;
		this.recursiveMethods = null;
		this.regularInRegularMethods = null;
		this.methodSetFull = null;
		this.sum = new int[13];
	}

	public Line getLine() {
		return line;
	}

	public int[] getLineWeight() {
		return lineWeight;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	public void setLineWeight(int[] lineWeight) {
		this.lineWeight = lineWeight;
	}

	public int getWr() {
		return Wr;
	}

	public int getWmcms() {
		return Wmcms;
	}

	public int getWmcmd() {
		return Wmcmd;
	}

	public int getWmcrms() {
		return Wmcrms;
	}

	public int getWmcrmd() {
		return Wmcrmd;
	}

	public int getWrmcrms() {
		return Wrmcrms;
	}

	public int getWrmcrmd() {
		return Wrmcrmd;
	}

	public int getWrmcms() {
		return Wrmcms;
	}

	public int getWrmcmd() {
		return Wrmcmd;
	}

	public int getWmrgvs() {
		return Wmrgvs;
	}

	public int getWmrgvd() {
		return Wmrgvd;
	}

	public int getWrmrgvs() {
		return Wrmrgvs;
	}

	public int getWrmrgvd() {
		return Wrmrgvd;
	}

	public void setWr(int wr) {
		Wr = wr;
	}

	public void setWmcms(int wmcms) {
		Wmcms = wmcms;
	}

	public void setWmcmd(int wmcmd) {
		Wmcmd = wmcmd;
	}

	public void setWmcrms(int wmcrms) {
		Wmcrms = wmcrms;
	}

	public void setWmcrmd(int wmcrmd) {
		Wmcrmd = wmcrmd;
	}

	public void setWrmcrms(int wrmcrms) {
		Wrmcrms = wrmcrms;
	}

	public void setWrmcrmd(int wrmcrmd) {
		Wrmcrmd = wrmcrmd;
	}

	public void setWrmcms(int wrmcms) {
		Wrmcms = wrmcms;
	}

	public void setWrmcmd(int wrmcmd) {
		Wrmcmd = wrmcmd;
	}

	public void setWmrgvs(int wmrgvs) {
		Wmrgvs = wmrgvs;
	}

	public void setWmrgvd(int wmrgvd) {
		Wmrgvd = wmrgvd;
	}

	public void setWrmrgvs(int wrmrgvs) {
		Wrmrgvs = wrmrgvs;
	}

	public void setWrmrgvd(int wrmrgvd) {
		Wrmrgvd = wrmrgvd;
	}

	public int getNr() {
		return Nr;
	}

	public void setNr(int nr) {
		Nr = nr;
	}

	public int getNmcms() {
		return Nmcms;
	}

	public void setNmcms(int nmcms) {
		Nmcms = nmcms;
	}

	public int getNmcmd() {
		return Nmcmd;
	}

	public void setNmcmd(int nmcmd) {
		Nmcmd = nmcmd;
	}

	public int getNmcrms() {
		return Nmcrms;
	}

	public void setNmcrms(int nmcrms) {
		Nmcrms = nmcrms;
	}

	public int getNmcrmd() {
		return Nmcrmd;
	}

	public void setNmcrmd(int nmcrmd) {
		Nmcrmd = nmcrmd;
	}

	public int getNrmcrms() {
		return Nrmcrms;
	}

	public void setNrmcrms(int nrmcrms) {
		Nrmcrms = nrmcrms;
	}

	public int getNrmcrmd() {
		return Nrmcrmd;
	}

	public void setNrmcrmd(int nrmcrmd) {
		Nrmcrmd = nrmcrmd;
	}

	public int getNrmcms() {
		return Nrmcms;
	}

	public void setNrmcms(int nrmcms) {
		Nrmcms = nrmcms;
	}

	public int getNrmcmd() {
		return Nrmcmd;
	}

	public void setNrmcmd(int nrmcmd) {
		Nrmcmd = nrmcmd;
	}

	public int getNmrgvs() {
		return Nmrgvs;
	}

	public void setNmrgvs(int nmrgvs) {
		Nmrgvs = nmrgvs;
	}

	public int getNmrgvd() {
		return Nmrgvd;
	}

	public void setNmrgvd(int nmrgvd) {
		Nmrgvd = nmrgvd;
	}

	public int getNrmrgvs() {
		return Nrmrgvs;
	}

	public void setNrmrgvs(int nrmrgvs) {
		Nrmrgvs = nrmrgvs;
	}

	public int getNrmrgvd() {
		return Nrmrgvd;
	}

	public void setNrmrgvd(int nrmrgvd) {
		Nrmrgvd = nrmrgvd;
	}

	public int getTotr() {
		return totr;
	}

	public void setTotr(int totr) {
		this.totr = totr;
	}

	public int getTotmcms() {
		return totmcms;
	}

	public void setTotmcms(int totmcms) {
		this.totmcms = totmcms;
	}

	public int getTotmcmd() {
		return totmcmd;
	}

	public void setTotmcmd(int totmcmd) {
		this.totmcmd = totmcmd;
	}

	public int getTotmcrms() {
		return totmcrms;
	}

	public void setTotmcrms(int totmcrms) {
		this.totmcrms = totmcrms;
	}

	public int getTotmcrmd() {
		return totmcrmd;
	}

	public void setTotmcrmd(int totmcrmd) {
		this.totmcrmd = totmcrmd;
	}

	public int getTotrmcrms() {
		return totrmcrms;
	}

	public void setTotrmcrms(int totrmcrms) {
		this.totrmcrms = totrmcrms;
	}

	public int getTotrmcrmd() {
		return totrmcrmd;
	}

	public void setTotrmcrmd(int totrmcrmd) {
		this.totrmcrmd = totrmcrmd;
	}

	public int getTotrmcms() {
		return totrmcms;
	}

	public void setTotrmcms(int totrmcms) {
		this.totrmcms = totrmcms;
	}

	public int getTotrmcmd() {
		return totrmcmd;
	}

	public void setTotrmcmd(int totrmcmd) {
		this.totrmcmd = totrmcmd;
	}

	public int getTotmrgvs() {
		return totmrgvs;
	}

	public void setTotmrgvs(int totmrgvs) {
		this.totmrgvs = totmrgvs;
	}

	public int getTotmrgvd() {
		return totmrgvd;
	}

	public void setTotmrgvd(int totmrgvd) {
		this.totmrgvd = totmrgvd;
	}

	public int getTotrmrgvs() {
		return totrmrgvs;
	}

	public void setTotrmrgvs(int totrmrgvs) {
		this.totrmrgvs = totrmrgvs;
	}

	public int getTotrmrgvd() {
		return totrmrgvd;
	}

	public void setTotrmrgvd(int totrmrgvd) {
		this.totrmrgvd = totrmrgvd;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setEndLineNumber(int lineNumber, int number) {
		this.MethodList.get(lineNumber).setEndLineNumber(number);
	}

	public void setMethodList(ArrayList<Line> methodList) {
		this.MethodList = new ArrayList<Line>(methodList);
	}

	public void setCalledMethodList(ArrayList<Line> calledMethodList) {
		this.calledMethodList = new ArrayList<Line>(calledMethodList);
	}

	public ArrayList<Line> getGlobalVariableSet() {
		return globalVariableSet;
	}

	public void setGlobalVariableSet(ArrayList<Line> globalVariableSet) {
		this.globalVariableSet = globalVariableSet;
	}

	public ArrayList<Line> getGlobalVariableListInReg() {
		return globalVariableListInReg;
	}

	public void setGlobalVariableListInReg(ArrayList<Line> globalVariableList) {
		this.globalVariableListInReg = globalVariableList;
	}

	public ArrayList<Line> getGlobalVariableListInRec() {
		return globalVariableListInRec;
	}

	public void setGlobalVariableListInRec(ArrayList<Line> globalVariableListInRec) {
		this.globalVariableListInRec = globalVariableListInRec;
	}

	public ArrayList<Line> getSystemMethods() {
		return systemMethods;
	}

	public void setSystemMethods(ArrayList<Line> systemMethods) {
		this.systemMethods = systemMethods;
	}

	public void setRegularMethods(ArrayList<Line> regularMethods) {
		this.regularMethods = new ArrayList<Line>(regularMethods);
	}

	public void setRecursiveMethods(ArrayList<Line> recursiveMethods) {
		this.recursiveMethods = new ArrayList<Line>(recursiveMethods);
	}

	public void setRecursiveMethodCalls(ArrayList<Line> recursiveMethodCalls) {
		this.recursiveMethodCalls = recursiveMethodCalls;
	}

	public void setRegularInRegularMethods(ArrayList<Line> regularToRegularMethods) {
		this.regularInRegularMethods = new ArrayList<Line>(regularToRegularMethods);
	}

	public void setRecursiveInRegularMethods(ArrayList<Line> recursiveInRegularMethods) {
		this.recursiveInRegularMethods = recursiveInRegularMethods;
	}

	public ArrayList<Line> getMethodList() {
		return MethodList;
	}

	public ArrayList<Line> getCalledMethodList() {
		return calledMethodList;
	}

	public ArrayList<Line> getRegularMethods() {
		return regularMethods;
	}

	public ArrayList<Line> getRecursiveMethods() {
		return recursiveMethods;
	}

	public ArrayList<Line> getRecursiveMethodCalls() {
		return recursiveMethodCalls;
	}

	public ArrayList<Line> getRegularInRegularMethods() {
		return regularInRegularMethods;
	}

	public ArrayList<Line> getRecursiveInRegularMethods() {
		return recursiveInRegularMethods;
	}

	public ArrayList<Line> getMethodSetFull() {
		return methodSetFull;
	}

	public void setMethodSetFull(ArrayList<Line> methodSetFull) {
		this.methodSetFull = methodSetFull;
	}

	public ArrayList<Line> getRecursiveInRecursiveMethods() {
		return recursiveInRecursiveMethods;
	}

	public void setRecursiveInRecursiveMethods(ArrayList<Line> recursiveInRecursiveMethods) {
		this.recursiveInRecursiveMethods = recursiveInRecursiveMethods;
	}

	public ArrayList<Line> getRegularInRecursiveMethods() {
		return regularInRecursiveMethods;
	}

	public void setRegularInRecursiveMethods(ArrayList<Line> regularInRecursiveMethods) {
		this.regularInRecursiveMethods = regularInRecursiveMethods;
	}

	public ArrayList<Line> getCalledMethodList_DF() {
		return calledMethodList_DF;
	}

	public void setCalledMethodList_DF(ArrayList<Line> calledMethodList_DF) {
		this.calledMethodList_DF = calledMethodList_DF;
	}

	public ArrayList<Line> getRegularInRegularMethods_DF() {
		return regularInRegularMethods_DF;
	}

	public void setRegularInRegularMethods_DF(ArrayList<Line> regularInRegularMethods_DF) {
		this.regularInRegularMethods_DF = regularInRegularMethods_DF;
	}

	public ArrayList<Line> getRecursiveInRegularMethods_DF() {
		return recursiveInRegularMethods_DF;
	}

	public void setRecursiveInRegularMethods_DF(ArrayList<Line> recursiveInRegularMethods_DF) {
		this.recursiveInRegularMethods_DF = recursiveInRegularMethods_DF;
	}

	public ArrayList<Line> getRecursiveInRecursiveMethods_DF() {
		return recursiveInRecursiveMethods_DF;
	}

	public void setRecursiveInRecursiveMethods_DF(ArrayList<Line> recursiveInRecursiveMethods_DF) {
		this.recursiveInRecursiveMethods_DF = recursiveInRecursiveMethods_DF;
	}

	public ArrayList<Line> getRegularInRecursiveMethods_DF() {
		return regularInRecursiveMethods_DF;
	}

	public void setRegularInRecursiveMethods_DF(ArrayList<Line> regularInRecursiveMethods_DF) {
		this.regularInRecursiveMethods_DF = regularInRecursiveMethods_DF;
	}

	public ArrayList<Line> getObjectList() {
		return objectList;
	}

	public void setObjectList(ArrayList<Line> objectList) {
		this.objectList = objectList;
	}

	public ArrayList<Line> getInReg_DF() {
		return inReg_DF;
	}

	public void setInReg_DF(ArrayList<Line> inReg_DF) {
		this.inReg_DF = inReg_DF;
	}

	public ArrayList<Line> getInRec_DF() {
		return inRec_DF;
	}

	public void setInRec_DF(ArrayList<Line> inRec_DF) {
		this.inRec_DF = inRec_DF;
	}

	public ArrayList<Line> getGlobalVariableList_DF() {
		return globalVariableList_DF;
	}

	public void setGlobalVariableList_DF(ArrayList<Line> globalVariableList_DF) {
		this.globalVariableList_DF = globalVariableList_DF;
	}

	public ArrayList<Line> getGlobalVariableListInReg_DF() {
		return globalVariableListInReg_DF;
	}

	public void setGlobalVariableListInReg_DF(ArrayList<Line> globalVariableListInReg_DF) {
		this.globalVariableListInReg_DF = globalVariableListInReg_DF;
	}

	public ArrayList<Line> getGlobalVariableListInRec_DF() {
		return globalVariableListInRec_DF;
	}

	public void setGlobalVariableListInRec_DF(ArrayList<Line> globalVariableListInRec_DF) {
		this.globalVariableListInRec_DF = globalVariableListInRec_DF;
	}
	
	public void setFinalValue() {
		
		totr = Wr * Nr;
		setTotr(totr);
		totmcms = Wmcms * Nmcms;
		setTotmcms(totmcms);
		totmcmd = Wmcmd * Nmcmd;
		setTotmcmd(totmcmd);
		totmcrms = Wmcrms * Nmcrms;
		setTotmcrms(totmcrms);
		totmcrmd = Wmcrmd * Nmcrmd;
		setTotmcrmd(totmcrmd);
		totrmcrms = Wrmcrms * Nrmcrms;
		setTotrmcrms(totrmcrms);
		totrmcrmd = Wrmcrmd * Nrmcrmd;
		setTotrmcrmd(totrmcrmd);
		totrmcms = Wrmcms * Nrmcms;
		setTotrmcms(totrmcms);
		totrmcmd = Wrmcmd * Nrmcmd;
		setTotrmcmd(totrmcmd);
		totmrgvs = Wmrgvs * Nmrgvs;
		setTotmrgvs(totmrgvs);
		totmrgvd = Wmrgvd * Nmrgvd;
		setTotmrgvd(totmrgvd);
		totrmrgvs = Wrmrgvs * Nrmrgvs;
		setTotmrgvs(totmrgvs);
		totrmrgvd = Wrmrgvd * Nrmrgvd;
		setTotrmrgvd(totrmrgvd);
		
		finalValue = totr + totmcms + totmcmd + totmcrms + totmcrmd + totrmcrms + totrmcrmd
				 + totrmcms + totrmcmd + totmrgvs + totmrgvd + totrmrgvs + totrmrgvd;

	}

	public int getFinalValue() {
		return finalValue;
	}
	
	public void setSum(int[] sum) {
		this.sum = sum;
	}
	
	public int[] getSum() {
		return this.sum;
	}
}
