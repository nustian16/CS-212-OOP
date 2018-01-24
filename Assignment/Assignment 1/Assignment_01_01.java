/*
   Name :              Muhammad Rizwan Khalid
   Class:              BSCS-6A
   Reg.no:             180459
   Instructor :        Dr. Anis ur Rehman
   CS-212 Assignment : 01
*/

package assignment_01;
import java.util.Scanner;

public class Assignment_01 {
    public static void main(String[] args) {
       
        //Creating Scanner object.
        Scanner input = new Scanner(System.in);
        
        //Declaring Variables
        int number, newNum = 0, count = 0;
        int temp = 1000, temp1, temp2; 
        int [] numberArray = new int [4];
        
        //Getting input from the user.
        System.out.print("Enter the four digit no. to Encrypt: ");
        number = input.nextInt();
        
        //Calling Function to count number of digit.
        count = getDigitsOfNumber(number);
        while(count != 4){
            //While user does not enter the four digit number. It will prompt an error
            System.err.println("You have not entered the four digit Number!!");
            System.out.print("Enter the four digit no. to Encrypt: ");
            number = input.nextInt();
            count = getDigitsOfNumber(number);
        }
        
        //Accesing individual digits of a number
        for(int i = 0; i < numberArray.length; i++){
            numberArray[numberArray.length - i - 1] = number % 10;   
            number = number / 10;
        }
        
        //Adding 7 to the individual digit and taking mod.
        for(int i = 0; i < numberArray.length; i++){
            numberArray[i] = (numberArray[i] + 7) % 10;
        }
        
        //Exchanging the first and third digit.
        temp1 = numberArray[0];
        numberArray[0] = numberArray[2];
        numberArray[2] = temp1;
        
        //Exchanging the second and last digit.
        temp2 = numberArray[1];
        numberArray[1] = numberArray[3];
        numberArray[3] = temp2;
        
        //Generating the new encrypted number.
        for(int i = 0; i < numberArray.length; i++){
            numberArray[i] = numberArray[i] * temp;
            newNum = newNum + numberArray[i];
            temp /= 10;
        }
        
        //Calling fucntion to check the number of digits
        count =  getDigitsOfNumber(newNum);
        if(count == 3){
//In case the third integer is zero then encrypted integer will become 3 digit number after exchanig the position of fist and third digit of the integer.    
            System.out.println("The Encrypted Integer is "+0+newNum);
        }
        else{
            //Printing the encrypted number.
            System.out.println("The Encrypted Integer is "+newNum);
        }
    }
    
    //Function defintition
    static int getDigitsOfNumber(int number){
        int count = 0;
        //While number is not zero divide by 1o
        while(number != 0){
            number = number /10;
            count++;
        }
        //It returns the number of digits.
        return count;
    }
}
