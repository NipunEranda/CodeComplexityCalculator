import java.util.Scanner;
public class Maths2{
	double num = 0.0;
	
	public static void main(String[] args){
		Maths2 m2 = new Maths2();
		Maths1 m1 = new Maths1();
		m2.answer();
	}
	
	public double getSqr(double num1){
		if(num1 == 0)
			return 0;
		else
			return getSqr(num1-1) + (2 * num1) - 1;
	}
	
	public double getCube(double num2){
		if(num2 == 0)
			return 0;
		else
			return getCube(num2-1) + 3 * (getSqr(num2)) - 3 * num2 + 1;
	}
	
	public void answer(){
		Maths2 m1 = new Maths();
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a number : ");
		num = input.nextInt();
	
	double n2 = m1.getSqr(num);
	
	System.out.println("Squared value of " + num + "is " + n2);
	System.out.println("Cube value of " + num + "is " + m1.getCube(num)); 
}

public double getlqr(double num1){
		answer();
		if(num1 == 0)
			return 0;
		else
			return getlqr(num1-1) + (2 * num1) - 1;
	}
