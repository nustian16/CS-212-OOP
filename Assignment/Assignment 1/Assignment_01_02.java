/*
   Name :              Muhammad Rizwan Khalid
   Class:              BSCS-6A
   Reg.no:             180459
   Instructor :        Dr. Anis ur Rehman
   CS-212 Assignment : 01
*/

package assignment_02;
import java.util.Scanner;

public class Assignment_02 {
    public static void main(String[] args) {
        
        // Creating the scanner object
        Scanner input = new Scanner(System.in);
        
        //Dclaring the variables
        int number, count = 0, newNum = 0;
        int temp = 1000, temp1, temp2; 
        int[] numberArray = new int[4];
        
        //Getting input from the user
        System.out.print("Enter four digit Encrypted Number: ");
        number = input.nextInt();
        
        //Calling the function to count number of digits.
        count = getDigitsOfNumber(number);
        
        //While noumber of digits are not four prompt an error
        while(count != 4){
            System.err.println("You have not entered the four digit Number!!");
            System.out.print("Enter four digit Encrypted Number: ");
            number = input.nextInt();
            count = getDigitsOfNumber(number);
        }
        
        //Accessing individual digits of a number
        for(int i = 0; i < numberArray.length; i++){
            numberArray[numberArray.length - i - 1] = number % 10;   
            number = number / 10;
        }
        
        //Exchanging the position of first and third digit.
        temp1 = numberArray[0];
        numberArray[0] = numberArray[2];
        numberArray[2] = temp1;
        
        //Exchanging second and last digit
        temp2 = numberArray[1];
        numberArray[1] = numberArray[3];
        numberArray[3] = temp2;
        
        //Adding 3 to individual number.
        for(int i = 0; i < numberArray.length; i++){
            numberArray[i] = numberArray[i] + 3;
            //if adding exceed 9, take a mod
            if(numberArray[i] > 9){
                numberArray[i] = numberArray[i] % 10;
            }
        }
        
        //generating the decrypted number.
        for(int i = 0; i < numberArray.length; i++){
            numberArray[i] = numberArray[i] * temp;
            newNum = newNum + numberArray[i];
            temp /= 10;
        }
        System.out.println("The Decrypted Integer is "+newNum);
    }
    
    //Function definition.
    static int getDigitsOfNumber(int number){
        int count = 0;
        while(number != 0){
            // while number is not zero divide by 10.
            number = number /10;
            count++;
        }
        //return total number of digits
        return count;
    }
}
