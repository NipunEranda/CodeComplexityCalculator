package com.ccc.model;

import java.util.ArrayList;

public class Inheritance {

	private String fileName;
	public static int[] weights = new int[3];
	
	private Line line;
	private int[] lineWeight = new int[4];
	
	private int n_direct;
	private int n_indirect;
	
	private int n_total;
	
	private int tot_direct;
	private int tot_indirect;
	
	private int tot_total;
	
	private int[] sum;
	private int finalValue;
	
	private ArrayList<Line> Direct_I;
	private ArrayList<Line> Indirect_I;
	
	private ArrayList<Line> Total_I;
	
	public Inheritance(String fileName) {
		
		this.setFileName(fileName);
		this.Direct_I = null;
		this.Indirect_I = null;
		this.Total_I = null;
		this.sum = new int[3];
		
		if(!(weights[0] > 0)) {
			setDefaultWeights();
		}
	}

	//setting default values
	
	public static void setDefaultWeights() {
		
		weights[0] = 1;
		weights[1] = 1;
		weights[2] = 1;
		
		
	}
	//setting custom values
	
	public static void setCustomWeights(int W_direct, int W_indirect,int W_total) {
		
		weights[0] = W_direct;
		weights[1] = W_indirect;
		weights[2] = W_total;
	}
	
	
	
	public static int[] getWeights() {
		return weights;
	}

	public static void setWeights(int[] weights) {
		Inheritance.weights = weights;
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

	public int getN_direct() {
		return n_direct;
	}

	public void setN_direct(int n_direct) {
		this.n_direct = n_direct;
	}

	public int getN_indirect() {
		return n_indirect;
	}

	public void setN_indirect(int n_indirect) {
		this.n_indirect = n_indirect;
	}

	public int getTot_direct() {
		return tot_direct;
	}

	public void setTot_direct(int tot_direct) {
		this.tot_direct = tot_direct;
	}

	public int getTot_indirect() {
		return tot_indirect;
	}

	public void setTot_indirect(int tot_indirect) {
		this.tot_indirect = tot_indirect;
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

	public ArrayList<Line> getDirect_I() {
		return Direct_I;
	}

	public void setDirect_I(ArrayList<Line> direct_I) {
		this.Direct_I = direct_I;
	}

	public ArrayList<Line> getIndirect_I() {
		return Indirect_I;
	}

	public void setIndirect_I(ArrayList<Line> indirect_I) {
		this.Indirect_I = indirect_I;
	}

	public String getFileName() {
		return fileName;
	}

	private void setFileName(String fileName) {
		this.fileName = fileName;
		
	}

	public void setFinalValue() {
		
		//setting total direct inheritance value
		
		tot_direct = weights[0] * n_direct;
		setTot_direct(tot_direct);
		
		//setting total indirect inheritance value
		
		tot_indirect = weights[1]*n_indirect;
		setTot_indirect(tot_indirect);
		
		//total inheritance with direct and indirect inheritance
		
		tot_total = tot_direct + tot_indirect;
		setTot_total(tot_total);
		
		//final value calculations
		
		if(tot_total <=3) {
			finalValue = tot_total;
		}else {
			finalValue = 4;
		}
				
	}


	public int getN_total() {
		return n_total;
	}


	public void setN_total(int n_total) {
		this.n_total = n_total;
	}


	public int getTot_total() {
		return tot_total;
	}


	public void setTot_total(int tot_total) {
		this.tot_total = tot_total;
	}


	public ArrayList<Line> getTotal_I() {
		return Total_I;
	}


	public void setTotal_I(ArrayList<Line> total_I) {
		this.Total_I = total_I;
	}

}