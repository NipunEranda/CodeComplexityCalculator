import java.util.Scanner;
public class Maths2{
	double num = 0.0;
	
	public static void main(String[] args){
		Maths2 m2 = new Maths2();
		m2.answer();
	}
	
	public double getSqr(double num1){
		Maths1 m5 = new Maths1();
		m5.getCube();
		m5.answer();
		m5.num = 0;
		if(num1 == 0)
			return 0;
		else
			return getSqr(num1-1) + (2 * num1) - 1;
	}
	
	public double getCube(double num2){
		Maths1 m3 = new Maths1();
		m3.answer();
		m3.getSqr();
		m3.num = 0;
		if(num2 == 0)
			return 0;
		else
			return getCube(num2-1) + 3 * (getSqr(num2)) - 3 * num2 + 1;
	}
	
	public void answer(){
		Maths1 m4 = new Maths1();
		Maths2 m1 = new Maths2();
		m4.getCube();
		m4.printSomething1();
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a number : ");
		num = input.nextInt();
	
	double n2 = m1.getSqr(num);
	
	System.out.println("Squared value of " + num + "is " + n2);
	System.out.println("Cube value of " + num + "is " + m1.getCube(num)); 
}

	public void printSomething2(){
		System.out.println("Hello");
	}

public double getlqr(double num1){
		answer();
		if(num1 == 0)
			return 0;
		else
			return getlqr(num1-1) + (2 * num1) - 1;
	}
