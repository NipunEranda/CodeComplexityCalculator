import java.util.Scanner;
public class Maths1{
	double num = 0.0;
	
	public static void main(String[] args){
		Maths1 m2 = new Maths1();
		m2.answer();
	}
	
	public double getSqr(double num1){
		Maths2 m5 = new Maths2();
		m5.getCube();
		m5.answer();
		if(num1 == 0)
			return 0;
		else
			return getSqr(num1-1) + (2 * num1) - 1;
	}
	
	public double getCube(double num2){
		Maths2 m3 = new Maths2();
		m3.answer();
		m3.getSqr();
		m3.num = 0;
		if(num2 == 0)
			return 0;
		else
			return getCube(num2-1) + 3 * (getSqr(num2)) - 3 * num2 + 1;
	}
	
	public void answer(){
		Maths1 m1 = new Maths1();
		Maths2 m4 = new Maths2();
		m4.getSqr();
		m4.printSomething2();
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a number : ");
		num = input.nextInt();
		m4.num = 0;
	
	double n2 = m1.getSqr(num);
	
	System.out.println("Squared value of " + num + "is " + n2);
	System.out.println("Cube value of " + num + "is " + m1.getCube(num)); 
}

	public void printSomething1(){
		System.out.println("Hello");
	}


public double getlqr(double num1){
		answer();
		if(num1 == 0)
			return 0;
		else
			return getlqr(num1-1) + (2 * num1) - 1;
	}
