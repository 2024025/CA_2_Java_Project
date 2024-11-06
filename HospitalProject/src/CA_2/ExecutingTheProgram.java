package CA_2;

import java.util.Scanner;


public class ExecutingTheProgram {

  
    public static void main(String[] args) {
       authenticatingUser();
       HospitalApplication.main(args);
    }
    
    private static boolean authenticatingUser(){
        Scanner myKb = new Scanner(System.in);
        int maxAttempts = 5;
        int attempts = 0;
        while(attempts < maxAttempts){
            System.out.print("Enter your Username: " );
            String userName = myKb.nextLine();
            System.out.print("Enter your Password: ");
            String password = myKb.nextLine();
            
            if("admin".equals(userName) && "admin".equals(password)){
                System.out.println("Login Authorized!");
                return true;
            }else{
                attempts++;
                System.out.println("Invalid username or password. Attempts remaining: " +(maxAttempts - attempts));
                if(attempts < maxAttempts){
                    System.out.println("Please Try again\n");
                }
            }
        }
        System.out.println("Your account has been blocked!");
        return false;
    }
}
