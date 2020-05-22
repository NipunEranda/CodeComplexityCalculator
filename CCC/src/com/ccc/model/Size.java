package com.ccc.model;

import java.util.ArrayList;

public class Size {

	private String fileName;
	
	public static int[] weights = new int[5];

	private Line line;
	private int[] lineWeight = new int[6];
	private int Nkw;
	private int Nid;
	private int Nop;
	private int Nnv;
	private int Nsl;
	
	
	
	private int tot_Nkw;
	private int tot_Nid;
	private int tot_Nop;
	private int tot_Nnv;
	private int tot_Nsl;
	
	
	
	private int[] sum;
	private int finalValue;
	
	private ArrayList<Line> classList;
	private ArrayList<Line> classObjectList;
	private ArrayList<Line> classObjectList_DF;
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
	
	private ArrayList<Line> keywords;
	
	

	

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

	public Size(String fileName) {
		this.setFileName(fileName);
		this.MethodList = null;
		this.calledMethodList = null;
		this.regularMethods = null;
		this.recursiveMethods = null;
		this.regularInRegularMethods = null;
		this.methodSetFull = null;
		this.sum = new int[5];

		if(!(weights[0] > 0)) {
			setDefaultWeights();
		}
	}
	
	public static void setDefaultWeights() {
		weights[0] = 1;
		weights[1] = 1;
		weights[2] = 1;
		weights[3] = 1;
		weights[4] = 1;
		
	}

	
	
	public static void setCustomWeights(int Nkw, int Nid, int Nop, int Nnv, int Nsl) {
		weights[0] = Nkw;
		weights[1] = Nid;
		weights[2] = Nop;
		weights[3] = Nnv;
		weights[4] = Nsl;
		
				
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

	public static int[] getWeights() {
		return weights;
	}

	public static void setWeights(int[] weights) {
		Size.weights = weights;
	}

	

	public int getNkw() {
		return Nkw;
	}

	public void setNkw(int nkw) {
		Nkw = nkw;
	}

	public int getNid() {
		return Nid;
	}

	public void setNid(int nid) {
		Nid = nid;
	}

	public int getNop() {
		return Nop;
	}

	public void setNop(int nop) {
		Nop = nop;
	}

	public int getNnv() {
		return Nnv;
	}

	public void setNnv(int nnv) {
		Nnv = nnv;
	}

	public int getNsl() {
		return Nsl;
	}

	public void setNsl(int nsl) {
		Nsl = nsl;
	}

	public int getTot_Nkw() {
		return tot_Nkw;
	}

	public void setTot_Nkw(int tot_Nkw) {
		this.tot_Nkw = tot_Nkw;
	}

	public int getTot_Nid() {
		return tot_Nid;
	}

	public void setTot_Nid(int tot_Nid) {
		this.tot_Nid = tot_Nid;
	}

	public int getTot_Nop() {
		return tot_Nop;
	}

	public void setTot_Nop(int tot_Nop) {
		this.tot_Nop = tot_Nop;
	}

	public int getTot_Nnv() {
		return tot_Nnv;
	}

	public void setTot_Nnv(int tot_Nnv) {
		this.tot_Nnv = tot_Nnv;
	}

	public int getTot_Nsl() {
		return tot_Nsl;
	}

	public void setTot_Nsl(int tot_Nsl) {
		this.tot_Nsl = tot_Nsl;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public ArrayList<Line> getClassList() {
		return classList;
	}

	public void setClassList(ArrayList<Line> classList) {
		this.classList = classList;
	}

	public ArrayList<Line> getClassObjectList() {
		return classObjectList;
	}

	public void setClassObjectList(ArrayList<Line> classObjectList) {
		this.classObjectList = classObjectList;
	}

	public ArrayList<Line> getClassObjectList_DF() {
		return classObjectList_DF;
	}

	public void setClassObjectList_DF(ArrayList<Line> classObjectList_DF) {
		this.classObjectList_DF = classObjectList_DF;
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
	
	
	public ArrayList<Line> getKeywords() {
		return keywords;
	}

	public void setKeywords(ArrayList<Line> keywords) {
		this.keywords = keywords;
	}
	
	
	/*
	 
	 private int Nkw;
	private int Nid;
	private int Nop;
	private int Nnv;
	private int Nsl;
	
	private int tot_Nkw;
	private int tot_Nid;
	private int tot_Nop;
	private int tot_Nnv;
	private int tot_Nsl;
	 
	 */
	
	public void setFinalValue() {
		
		
		
		tot_Nkw = weights[0] * Nkw;
		setNkw(tot_Nkw);
		tot_Nid = weights[1] * Nid;
		setNid(tot_Nid);
		tot_Nop = weights[2] * Nop;
		setNop(tot_Nop);
		tot_Nnv = weights[3] * Nnv;
		setNnv(tot_Nnv);
		tot_Nsl = weights[4] * Nsl;
		setNsl(tot_Nsl);
		
		
		
		
		
		
		finalValue = tot_Nkw + tot_Nid + tot_Nop + tot_Nnv + tot_Nsl ;

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