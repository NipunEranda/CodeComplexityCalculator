package com.ccc.model;

import java.util.ArrayList;

public class ControlStructure {
	
	private String fileName;
	public static int[] weights = new int[4];
	
	private Line line;
	private int[] lineWeight = new int[4];
	
	private int wtcs;
	private int nc;
	private int ccspps;
	private int cs;
	
	private int[] sum;
	private int finalValue;
	
	private ArrayList<Integer> wtcsList;
	private ArrayList<Integer> ncList;
	private ArrayList<Integer> ccsppsList;
	private ArrayList<Integer> csList;
	private ArrayList<String> statement;
	

	public ControlStructure(String filename) {
		// TODO Auto-generated constructor stub
		this.setFileName(fileName);
		this.wtcsList = null;
		this.ncList = null;
		this.ccsppsList = null;
		this.sum = new int[3];
		
		if(!(weights[0] > 0)) {
			setDefaultWeights();
		}
		
	}

	public static void setDefaultWeights() {
		// TODO Auto-generated method stub
		weights[0] = 2;
		weights[1] = 3;
		weights[2] = 2;
		weights[3] = 1;
		
	}
	
	public static void setCustomWeight(int wCondition,int wIteration,int wSwitch,int wCase) {
		// TODO Auto-generated method stub
		weights[0] = wCondition;
		weights[1] = wIteration;
		weights[2] = wSwitch;
		weights[3] = wCase;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public static int[] getWeights() {
		return weights;
	}

	public static void setWeights(int[] weights) {
		ControlStructure.weights = weights;
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	public int[] getLineWeight() {
		return lineWeight;
	}

	public void setLineWeight(int[] lineWeight) {
		this.lineWeight = lineWeight;
	}

	public int getWtcs() {
		return wtcs;
	}

	public void setWtcs(int wtcs) {
		this.wtcs = wtcs;
	}

	public int getNc() {
		return nc;
	}

	public void setNc(int nc) {
		this.nc = nc;
	}

	public int getCcspps() {
		return ccspps;
	}

	public void setCcspps(int ccspps) {
		this.ccspps = ccspps;
	}
	
	public int getCs() {
		return cs;
	}

	public void setCs(int cs) {
		this.cs = cs;
	}

	public int[] getSum() {
		return sum;
	}

	public void setSum(int[] sum) {
		this.sum = sum;
	}

	public int getFinalValue() {
		return finalValue;
	}

	public void setFinalValue(int finalValue) {
		this.finalValue = finalValue;
	}
	
	public void setFinalValue() {
		this.finalValue = (wtcs * nc) + ccspps;
		
	}

	public ArrayList<Integer> getWtcsList() {
		return wtcsList;
	}

	public void setWtcsList(ArrayList<Integer> wtcsList) {
		this.wtcsList = wtcsList;
	}

	public ArrayList<Integer> getNcList() {
		return ncList;
	}

	public void setNcList(ArrayList<Integer> ncList) {
		this.ncList = ncList;
	}

	public ArrayList<Integer> getCcsppsList() {
		return ccsppsList;
	}

	public void setCcsppsList(ArrayList<Integer> ccsppsList) {
		this.ccsppsList = ccsppsList;
	}

	public ArrayList<Integer> getCsList() {
		return csList;
	}

	public void setCsList(ArrayList<Integer> csList) {
		this.csList = csList;
	}

	public ArrayList<String> getStatement() {
		return statement;
	}

	public void setStatement(ArrayList<String> statement) {
		this.statement = statement;
	}


	
	

}