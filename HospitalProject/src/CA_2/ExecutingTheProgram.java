package CA_2;

/*This class was created to run the system.
Being more clean and calling the class HospitalApplication.

I added a method named authenticatingUser, that will be an security method 
where the user can only access the system if the username and password is 
entered, avoiding non authorized people to use the system.


As security always comes first, I created a unique and impossible to hack password and username
as follows:

  ********************
    username: admin
    password: admin
  ********************
*/

import java.util.Scanner;


public class ExecutingTheProgram {

  
    public static void main(String[] args) {
       if(authenticatingUser()){//Condition created to login
            HospitalApplication.main(args);//if the condition is true, the username and password was enterd correctly, then the access is permited.
       }else{//if the condition is false, and the user entered the credentials wrongly 5 times, the system will terminate and the account will be blocked.
           System.exit(0);//ending the system.
       }
    }
    
    private static boolean authenticatingUser(){//Method to create a login in 
        Scanner myKb = new Scanner(System.in);
        int maxAttempts = 5;//variable to define the max attempts possible to login
        int attempts = 0;//attempts initialized
        while(attempts < maxAttempts){//loop to iterate the attempts, in case the user enter the credentials wrongly
            System.out.print("Enter your Username: " );//message to interact and reques the user to enter the input
            String userName = myKb.nextLine();//defining the variable as a string, and receiving the user input
            System.out.print("Enter your Password: ");//message to interact and reques the user to enter the input
            String password = myKb.nextLine();//defining the variable as a string, and receiving the user input
            
            if("admin".equals(userName) && "admin".equals(password)){//condition created to match the username and password
                System.out.println("Login Authorized!");//when the condition is true, the login is authorized
                return true;//return true to confirm the access
            }else{//when the condition is false
                attempts++;//counting the attempts to login in each iteration
                System.out.println("Invalid username or password. Attempts remaining: " +(maxAttempts - attempts));//Message to interact with the user and warning abou how many attemps there are left.
                if(attempts < maxAttempts){//condition created to keep the loop running
                    System.out.println("Please Try again\n");
                }
            }
        }
        System.out.println("Your account has been blocked!");//In case the user has exceeded the limit of attempts to login
        return false;
    }
}
