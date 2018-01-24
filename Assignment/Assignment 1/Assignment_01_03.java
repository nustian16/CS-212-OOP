/*
   Name :              Muhammad Rizwan Khalid
   Class:              BSCS-6A
   Reg.no:             180459
   Instructor :        Dr. Anis ur Rehman
   CS-212 Assignment : 01
*/

package assignment_03;
public class Assignment_03 {
    public static void main(String[] args) {
        
        //Declaring the variables
        boolean [] primeArray = new boolean[1000];
        boolean check;
        
        //Initializing the array to true
        for (int i = 0; i < primeArray.length; i++) {
            primeArray[i] = true;
        }
        
        //Initializing the non-prime index to false
        for(int i = 0; i < primeArray.length; i++){
            //Calling the function to check prime
            check = ifPrime(i);
            if (check == true){
                // do nothing
            }
            else{
                primeArray[i] = false;
            }
        }
        
        //Printing the prime indicies of the boolean array
        for(int i = 0; i < primeArray.length; i++){
            if (primeArray[i] == true){
                    System.out.println(i);
            }
        }
    }
    
    //Function definition of ifPrime to check whether number is prime or not.
    static boolean ifPrime(int number){
        if ((number == 0) || (number == 1)){
            return false;
        }
        else if (number == 2){
            return true;
        }
        else {
            for (int i = 2; i < number; i++){
                if (number % i == 0){
                    return false;
                }
            }
        }
        return true;
    }
}
