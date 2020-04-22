import java.util.Scanner;
 class Years{
   public int getYear(){
	int year;
	String enteredYear;
	Scanner sc = new Scanner(System.in);
	System.out.println("Enter the year as a number :");
	enteredYear = sc.next();
	year = Integer.parseInt(enteredYear);
	return year;
   }
 }
//------------------------------------------------------------------------------------------------------------------------------------
 class Months extends Years{
   public int getMonth(){
	int month;
	String enteredMonthNumber;
	Scanner sc = new Scanner(System.in);
	System.out.println("Enter the month number :");
	enteredMonthNumber = sc.next();
	month = Integer.parseInt(enteredMonthNumber);
	return month;
   }
 }
//------------------------------------------------------------------------------------------------------------------------------------
class DaysPerMonth extends Months{ 
 static int numDays = 0;
 public static void main(String[] args) {
   int year;
   Months m = new Months();
   int month = m.getMonth();
   
   if((month < 1) || (month > 12)){ 
     System.out.println("Kindly enter a number between 0 to 13.");
   }
   else {
    switch (month) {
      case 1:
      case 3:
      case 5:
      case 7:
      case 8:
      case 10:
      case 12:
        numDays = 31;
        System.out.println("Month " + month + " consists of " + numDays + " days.");
        break;
      case 4:
      case 6:
      case 9:
      case 11:
       numDays = 30;
       System.out.println("Month " + month + " consists of " + numDays + " days.");
       break;
      case 2:
       year = m.getYear();
       if(year < 1) {
        System.out.println("Kindly enter a valid year.");
       }
       else{
        if(((year % 4 == 0) &&  !(year % 100 == 0)) || (year % 400 == 0)){
         numDays = 29;
		 if(year > 2020){
		  System.out.println("In year " + year + " month " + month + " will consist of " + numDays + " days.");
         }
         else{
          System.out.println("In year " + year + " month " + month + " has consisted of " + numDays + " days.");
         }
        }//if at line 61
        else{
         numDays = 28;
         if (year > 2020){
          System.out.println("In year " + year + " month " + month + " will consist of " + numDays + " days.");
         }
         else{
          System.out.println("In year " + year + " month " + month + " has consisted of " + numDays + " days.");
         }
         break;  
        }//else at line 70
       }//else at line 60
    }//switch at line 37
   }//else at line 36
  }//method
 }//class