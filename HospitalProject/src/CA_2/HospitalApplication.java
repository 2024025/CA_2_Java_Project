package CA_2;

import java.util.Scanner;

public class HospitalApplication {

    private static Scanner myKb = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("||||-- WELCOME TO DUBLIN HOSPITAL SYSTEM - DHS --||||");
        System.out.println();

        MainMenu optionMainMenu = null;
        do {
            System.out.println("--- MAIN MENU - DHS ---");
            System.out.println("1. EMPLOYEE INFORMATION");
            System.out.println("2. PATIENT INFORMATION");
            System.out.println("3. EXIT SYSTEM");
            int option = correctInput();
            
            if(option >= 1 && option <= MainMenu.values().length){
                optionMainMenu = MainMenu.values()[option - 1];
                switch(optionMainMenu){
                    case EMPLOYEE_INFORMATIONS:
                        displayEmployeeMenu();
                        break;
                    case PATIENT_INFORMATIONS:
                        displayPatientMenu();
                        break;
                    case EXIT_SYSTEM:
                        System.out.println("EXITING THE DUBLIN HOSPITAL SYSTEM. SEE YOU SOON!");
                        return;
                }
            }else{
                System.out.println("This is an invalid choice, please select a valid option from Menu!");
            }
        }while(optionMainMenu != MainMenu.EXIT_SYSTEM);
    }

    private static int correctInput(){
        while(!myKb.hasNextInt()){
            System.out.println("You Entered an invalid Input. Please enter a number: ");
            myKb.next();            
        }
        int number = myKb.nextInt();
        myKb.nextLine();
        return number;
    }

    private static void displayEmployeeMenu() {
        EmployeeMenu optionEmployeeMenu = null;

        do {
            System.out.println("--- EMPLOYEE MENU - DHS ---");
            System.out.println("1. SORT EMPLOYEE LIST");
            System.out.println("2. SEARCH FOR AN EMPLOYEE");
            System.out.println("3. ADD A NEW EMPLOYEE");
            System.out.println("4. GENERATE A RANDOM EMPLOYEE");
            System.out.println("5. VIEW MONTHLY PAYROL EXPENSES");
            System.out.println("6. EXPORT EMPLOYEE PAYROLL REPORT");
            System.out.println("7. EXIT EMPLOYEE MENU");

            System.out.println("CHOOSE ONE OF THE OPTIONS:");
            int option = correctInput();

            if (option >= 1 && option <= EmployeeMenu.values().length) {
                optionEmployeeMenu = EmployeeMenu.values()[option - 1];
                switch (optionEmployeeMenu) {
                    case SORTING_EMPLOYEE_LIST:
                        System.out.println("SORTING EMPLOYEE LIST...");
                        break;
                    case SEARCHING_EMPLOYEE:
                        System.out.println("SEARCHING FOR AN EMPLOYEE...");
                        break;
                    case ADDING_NEW_EMPLOYEE:
                        System.out.println("ADDING A NEW EMPLOYEE...");
                        break;
                    case EMPLOYEE_RANDOM_GENERATION:
                        System.out.println("GENERATING A RANDOM EMPLOYEE...");
                        break;
                    case VIEW_MONTHLY_EXPENSES:
                        System.out.println("VIEWING MONTHLY PAYROLL EXPENSES...");
                        break;
                    case EXPORT_EMPLOYEES_REPORT:
                        System.out.println("EXPORTING EMPLOYEE PAYROLL REPORT...");
                        break;
                    case EXIT_MENU:
                        System.out.println("RETURNING TO MAIN MENU...");
                        return;
                }
            } else {
                System.out.println("Please insert a válid option, only numbers allowed (1 - 7): ");
            }
        } while (optionEmployeeMenu != EmployeeMenu.EXIT_MENU);
    }


    private static void displayPatientMenu(){
        PatientMenu optionPatientMenu = null;
        
        do{
            System.out.println("--- PATIENT MENU - DHS ---");
            System.out.println("1. ADD A NEW PATIENT");
            System.out.println("2. SORT PATIENT LIST");
            System.out.println("3. SEARCH PATIENT");
            System.out.println("4. VIEW TREATMENT INCOME");
            System.out.println("5. EXPORT PATIENT REPORT");
            System.out.println("6. EXIT PATIENT MENU");
            
            System.out.println("CHOOSE ONE OF THE OPTIONS:");
            int option = correctInput();
            
            if(option >= 1 & option <= PatientMenu.values().length){
                optionPatientMenu = PatientMenu.values()[option - 1];
                
                switch(optionPatientMenu){
                    case ADD_NEW_PATIENT:
                        System.out.println("ADDING A NEW PATIENT...");
                        break;
                    case SORT_PATIENT_LIST:
                        System.out.println("SORTING PATIENT LIST...");
                        break;
                    case SEARCH_PATIENT:
                        System.out.println("SEARCHING FOR A PATIENT...");
                        break;
                    case VIEW_TREATMENT_INCOME:
                        System.out.println("VIEWING TOTAL TREATMENT INCOME...");
                        break;
                    case EXPORT_PATIENT_REPORT:
                        System.out.println("EXPORTING PATIENT REPORT...");
                        break;
                    case EXIT_MENU:
                        System.out.println("RETURNING TO MAIN MENU...");
                        return;
                }
                
            }else{
                System.out.println("PLEASE SELECT FROM ALL THE AVAILABLE OPTIONS!");
            }
        }while(optionPatientMenu != PatientMenu.EXIT_MENU);
    }
    
}
