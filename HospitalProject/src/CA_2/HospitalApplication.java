package CA_2;


/*
This class is the central application of this program, where all the methods for the program
to run efficiently will be located. In it, classes and their methods were imported to run the
system, starting from reading a .txt file to fill the lists of employees to Menus to interact
with the users, showing options to perform, like enter the employee or patient, sorting or search
for a specific person, etc.
 */
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class HospitalApplication {

//    private variables used to instantiate and initialize the classes that will be used in the program.
    private static List<Employee> employees = new ArrayList<>();//Used to store a list of employee object
    private static GenerateRandomEmployee randomEmployee = new GenerateRandomEmployee();//Used to instantiate and generate random employees, getting names, surnames, manager and departments
    private static DataFile dataFile;//Used to instantiate the DataFile class and its used to read and write data in and from txt file.
    private static Scanner myKb = new Scanner(System.in);//Insntantiate the class Scanner used to capture the user input
    private static List<Patient> patients = new ArrayList<>();//Used to instantiate and create a list of Patient object
    private static Sorter sorter = new Sorter();//Used to instantiate the class Sorter, will be used to sort the list of employees or patients
    private static Searcher searcher = new Searcher();//Used to instantiate the class Searcher, will be used to search the list of employees or patients

//It will be used to create a pattern of names entered by the users, using regex to avoid any other input, when the user has to insert a string. Making sure the input is adequate.
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z]+( [a-zA-Z]+)*$");

    public static void main(String[] args) {//Main method, where the program will be executed in this class.
        System.out.println("\n");
        System.out.println("===============================================");//Message to interact with the user
        System.out.println("||     WELCOME TO DUBLIN HOSPITAL SYSTEM     ||");
        System.out.println("||                                           ||");
        System.out.println("===============================================");
        System.out.println("\n");//adding an extra line

        if (!loadDataFromFile()) {//Conditions created to check if the data from a .txt file was loaded, that will be used to fill the lists with names and surnames.
            return;//If the condition is false, it will return, and terminate the program.
        }

        MainMenu optionMainMenu = null;//Initiating the variable as null, then the user can enter a number to choose the options in this menu

//Do-while loop, asking to the user to enter one of the options in the Main Menu. This loop will keep active until the user choose the option 3 - exiting the program.       
        do {
// Message to interact with the users
            System.out.println("\n");
            System.out.println("==========================================");
            System.out.println("||        WELCOME TO DUBLIN HOSPITAL    ||");
            System.out.println("||              MAIN MENU               ||");
            System.out.println("==========================================");
            System.out.println("|                                        |");
            System.out.println("|  1. EMPLOYEE INFORMATION               |");
            System.out.println("|  2. PATIENT INFORMATION                |");
            System.out.println("|  3. EXIT SYSTEM                        |");
            System.out.println("|                                        |");
            System.out.println("==========================================");
            System.out.print("PLEASE SELECT A VALID OPTION ENTERING A NUMBER: ");
            System.out.println("\n");
            int option = correctInput();//Using the method correctInput, to make sure the user entered a int number for the option.

//Condition created to compare the input from the user and matching it with the options, checking if the input is valid.            
            if (option >= 1 && option <= MainMenu.values().length) {//Its means, if the options is equals or bigger than 1 and lower than the size of the menus
                optionMainMenu = MainMenu.values()[option - 1];//Declaring options -1, because in the enum, it starts with zero, then if the user choose option 1, it will be acctualy the option 0 in the enum Menu.
                switch (optionMainMenu) {//Switch-case block to capture the input from the user and link it to the menu options
                    case EMPLOYEE_INFORMATIONS://Imported from MainMenu enum class
                        displayEmployeeMenu();//If the user choose the option 1, the menu will show up the employee Menu, calling the method displayEmployeeMenu()
                        break;//break to move back to the employee menu, and giving another chance to the user to choose another option.
                    case PATIENT_INFORMATIONS://Imported from MainMenu enum class
                        displayPatientMenu();//If the user choose the option 2, the menu will show up the Patients Menu, calling the method displayPatientMenu()
                        break;//break to move back to the employee menu, and giving another chance to the user to choose another option.
                    case EXIT_SYSTEM://Imported from the MainMenu enum class, ends the menu, and ends the program
                        System.out.println("Exiting the Dublin Hospital System. See You Soon!!");//Message to greeting the user.
                        return;
                }
            } else {//Else condition, in case the user enter a wrong option, that is not in the range from 1 to 3
                System.out.println("This is an invalid choice, please select a valid option from Menu!(1-3):");//Mesage to give instructions to the user
            }
        } while (optionMainMenu != MainMenu.EXIT_SYSTEM);//Ending the loop do-whle, where if the options is not 3(exit) the system keep running.
    }

//    From now on, there are methods created to run the program, and build all the structure of this system.
//    This method is used to make sure the input from the user is correct, its mean, if the user must enter a number and typed it wrongly, 
//    addin a letter or character as exemple, this method will return a new input to the user, to correct it.
    private static int correctInput() {
        while (!myKb.hasNextInt()) {//while loop created to keep asking the user to enter a correct input, only accepting numbers. when the input dont hasNextINt(integer) it will return.
            System.out.println("You Entered an invalid Input. Please enter a number: ");//Message to warning the user about the wrong input entered
            myKb.next();//Used to consume the line, avoiding an infinit loop.
        }
        int number = myKb.nextInt();//This variable will be used to read the next input from the user, in case the user has entered a wrong input before
        myKb.nextLine();//Used to consume the line, avoiding an infinit loop and clear the buffer.
        return number;//Returning a valid int to be used in the menu option.
    }

//This method is used to display the employee menu and execute the actions accordingly the user choice.    
//It allows some functionalities as sort, search, add, generate random employees, create report, check total expenses, etc.    
    private static void displayEmployeeMenu() {
        EmployeeMenu optionEmployeeMenu = null;//Initiating the variable optionemployeeMenu that will be choosen by the user.
        displayEmployees();//calling the method to display a list of 20 employees, generated randomly with names and surnames from the .txt file
        System.out.println();//adding an extra line

        do {//do-while block, used to show the options to the user, and make sure the user will enter a valid input

//Messages that will be displayed on the menu to give instructions to the user   
            System.out.println("\n");
            System.out.println("==========================================");
            System.out.println("||          EMPLOYEE INFORMATION         ||");
            System.out.println("==========================================");
            System.out.println("|                                        |");
            System.out.println("|  1. SORT EMPLOYEE LIST                 |");
            System.out.println("|  2. SEARCH FOR AN EMPLOYEE             |");
            System.out.println("|  3. ADD A NEW EMPLOYEE                 |");
            System.out.println("|  4. GENERATE A RANDOM EMPLOYEE         |");
            System.out.println("|  5. VIEW MONTHLY PAYROLL EXPENSES      |");
            System.out.println("|  6. EXPORT EMPLOYEE PAYROLL REPORT     |");
            System.out.println("|  7. EXIT TO MAIN MENU                  |");
            System.out.println("|                                        |");
            System.out.println("==========================================");
            System.out.println("CHOOSE ONE OF THE OPTIONS: ");

            int option = correctInput();//variable responsible to receive the user input, calling the method correctInput to make sure an int number will be entered.

//If-else condition used to guarantee that the option made by the user is in the range between 1-7, is this condition is true, the switch case will be activated. Otherwise the else condition will run!            
            if (option >= 1 && option <= EmployeeMenu.values().length) {
                optionEmployeeMenu = EmployeeMenu.values()[option - 1];//Setting up the index, because the enum class starts with 0,then I need to add -1 on it to match the options.
                switch (optionEmployeeMenu) {
                    case SORTING_EMPLOYEE_LIST://Calling the employeeMenu from MainMenu class
                        System.out.println("SORTING EMPLOYEE LIST...");
                        sortEmployee();//Calling the method responsible to sort the employee list.
                        break;
                    case SEARCHING_EMPLOYEE:
                        System.out.println("SEARCHING FOR AN EMPLOYEE...");
                        searchEmployee();//Calling the method responsible to search an employee on the list.
                        break;
                    case ADDING_NEW_EMPLOYEE:
                        System.out.println("ADDING A NEW EMPLOYEE...");
                        addEmployee();//Calling the method responsible to add an employee on the list.
                        break;
                    case EMPLOYEE_RANDOM_GENERATION:
                        System.out.println("GENERATING A RANDOM EMPLOYEE...");
                        generateRandomEmployee();//Calling the method responsible to generate an employee randomly in the list.
                        break;
                    case VIEW_MONTHLY_EXPENSES:
                        System.out.println("VIEWING MONTHLY PAYROLL EXPENSES...");
                        displayMonthlyExpenses();//Calling the method responsible to display the employee total of expenses, in the list.
                        break;
                    case EXPORT_EMPLOYEES_REPORT:
                        System.out.println("EXPORTING EMPLOYEE PAYROLL REPORT...");
                        exportEmployees();//Calling the method responsible to export the list.
                        break;
                    case EXIT_MENU:
                        System.out.println("RETURNING TO MAIN MENU...");
                        return;//Returning to the Main Menu.
                }
            } else {
                System.out.println("Please insert a válid option, only numbers allowed (1 - 7): ");//message to give instructions to the user, in case a number out of this range is entered.
            }
        } while (optionEmployeeMenu != EmployeeMenu.EXIT_MENU);//The loop keeps on, until the user choose the option Exit, that it's option 7 on the displayed menu.
    }

    /*This method is responsible to display the Patient menu, showing all the options to the user, such as: add new patient, sort the list, search a patient on the list
    view the total income received by the hospital, export a .txt file with all the reports from the list created, and exit.
     */
    private static void displayPatientMenu() {
        PatientMenu optionPatientMenu = null;//Initiate the variable as null, to get the option entered by the user.

        do {//do-while block, used to show the options to the user, and make sure the user will enter a valid input

//Messages that will be displayed on the menu to give instructions to the user  
            System.out.println("\n");
            System.out.println("==========================================");
            System.out.println("||           PATIENT INFORMATION         ||");
            System.out.println("==========================================");
            System.out.println("|                                        |");
            System.out.println("|  1. ADD A NEW PATIENT                  |");
            System.out.println("|  2. SORT PATIENT LIST                  |");
            System.out.println("|  3. SEARCH FOR A PATIENT               |");
            System.out.println("|  4. VIEW TOTAL TREATMENT INCOME        |");
            System.out.println("|  5. EXPORT PATIENT REPORT              |");
            System.out.println("|  6. EXIT TO MAIN MENU                  |");
            System.out.println("|                                        |");
            System.out.println("==========================================");
            System.out.println("CHOOSE ONE OF THE OPTIONS: ");

            int option = correctInput();//variable responsible to receive the user input, calling the method correctInput to make sure an int number will be entered.

//If-else condition used to guarantee that the option made by the user is in the range between 1-6, is this condition is true, the switch case will be activated. Otherwise the else condition will run!            
            if (option >= 1 & option <= PatientMenu.values().length) {
                optionPatientMenu = PatientMenu.values()[option - 1];//Setting up the index, because the enum class starts with 0,then I need to add -1 on it to match the options.

                switch (optionPatientMenu) {
                    case ADD_NEW_PATIENT:
                        System.out.println("ADDING A NEW PATIENT...");
                        addNewPatient();//Calling the methd responsible to add a new patient 
                        break;
                    case SORT_PATIENT_LIST:
                        System.out.println("SORTING PATIENT LIST...");
                        sortPatients();//Calling the method responsible to sort the list of patients added.
                        break;
                    case SEARCH_PATIENT:
                        System.out.println("SEARCHING FOR A PATIENT...");
                        searchPatient();//Calling the method in charge to search a patient on the list
                        break;
                    case VIEW_TREATMENT_INCOME:
                        System.out.println("VIEWING TOTAL TREATMENT INCOME...");
                        viewTotalTreatmentIncome();//Calling the method responsible to display the hospital total income for the patient treatments
                        break;
                    case EXPORT_PATIENT_REPORT:
                        System.out.println("EXPORTING PATIENT REPORT...");
                        exportPatientReport();//Calling the method responsible to export a .txt file with all the patient details list
                        break;
                    case EXIT_MENU:
                        System.out.println("RETURNING TO MAIN MENU...");
                        return;//Returning to the Main Menu.
                }

            } else {
                System.out.println("Please Select From All the Available Options! (1- 6)");//Message to give instructions to the user, in case a number out of this range is entered.
            }
        } while (optionPatientMenu != PatientMenu.EXIT_MENU);//The loop keeps on, until the user choose the option Exit, that it's option 6 on the displayed menu.
    }

    /*This method is responsible to load the .txt file and fill the list with names and surnames, generating 20 employees randomly, 
assigning them positions, departments, hours worked and total amount received.    
     */
    private static boolean loadDataFromFile() {
        boolean isLoaded = false; //Initiating the boolean variable as false
        do {//Do-while block, used to make sure the loop will keep runing in case the file isn't read.
            System.out.print("Enter a filename to be read: ");//Message to give instructions to the user.
            String fileName = myKb.nextLine().toLowerCase().trim();//fileName String variable, used to receive the user input. Doing the String treatment, put it on lowerCase and trim, to remove extras spaces before and after.

            try {//TRy-catch block used to treat exceptions.
                dataFile = new DataFile(fileName);//Instantiating the DataFile class with the fileName that will be entered by the user

//This if condition is used to check if the names and surnames were loaded, in case its null or empty, the error message will show up to the user, giving instructions about the error and the loop will run again
                if (dataFile.getName() == null || dataFile.getSurname() == null || dataFile.getName().length == 0 || dataFile.getSurname().length == 0) {
                    System.out.println("Error Loading the Names and Surnames from the file!");
                    continue;//If the file couldn't be loaded, it will keep looping, asking to the user a new input.
                }

//Setting the lists of name and surname for the randomEmployee Obj.
//The data loaded from the .txt file will be used to set up the employee name and surname in a list in the randomEmployee obj.
//Calling the setEmployee methods to store the data as a list.
                randomEmployee.setEmployeeName(Arrays.asList(dataFile.getName()));
                randomEmployee.setEmployeeSurname(Arrays.asList(dataFile.getSurname()));

                System.out.println("The file is completely loaded!");//Message to interact with the user
                randomEmployee.generatingRandomEmployees(20);//Responsible to generate a list with 20 employees with random data
                employees.addAll(randomEmployee.getEmployees());//Responsible to add all the employees randomly generated to the employee list
                isLoaded = true;//boolean conditionto confirm the file was loaded successfully, allowing then the loop to ends.
                System.out.println();//Adding an extra line
            } catch (Exception e) {//Try-catch block, used to capture an exception when reading the file
                System.out.println("An error occurred when reading the file: " + e.getMessage());//message to give instructions to the user, in case exists any issue when reading the file
            }
        } while (!isLoaded);//If the isLoaded variable is false, the loop will keep running
        return true;//in case its true, its means the file was loaded successfully
    }

    //method used to check if the name is valid, and doesnt has any number or character on it. using the regex "^[a-zA-Z]+( [a-zA-Z]+)*$" to check it. 
    private static boolean isValidName(String name) {
        return name != null && NAME_PATTERN.matcher(name).matches();
    }

//Method created to validate employees and patient names, using a while loop to repeat the action until the user enter a valid name.    
    private static String validateName() {
        String name;//Declaring name as a String variable
        while (true) {//While loop to check if the name is valid, if its not a valid name the loop will restart.
            name = myKb.nextLine();//Receiving the user input
            if (isValidName(name)) {//condition if-else to check the input entered, using the method isValidName to check if name has only letters allowed in the regex used,
                break;//the loop ends if the condition is true
            } else {//if the name is not a valid name, because contain some characters or numbers, the messages below will show up and the loop will restart
                System.out.print("This is a Invalid name! Only enters allowed (A-Z).");
                System.out.print("\nLet's try again, Enter the Name: ");
            }
        }
        return name;//if the while loop ends with a true condition and the name is validate, the method return the name validated.
    }

    //This method will works exactly the same way that the previous method, but in this case is allowed to use a prompt, with a message. 
    private static String validateName(String prompt) {
        String name;
        while (true) {
            System.out.println(prompt);
            name = myKb.nextLine().trim();
            if (isValidName(name)) {
                break;
            } else {
                System.out.println("Invalid Input! Please enter letters Only! (A-Z)");
            }
        }
        return name;
    }

//Method responsible to check if there is any duplicated data on the list. this method has three parameters, name, surname and Role.
    private static boolean isDataDuplicated(String name, String surname, Role manager) {
        for (Employee employee : employees) {//for-each loop used to iterate each employee, from the employees list. It will be used to run each employee and check if it already exists.
//conditions created to compare if the firstname and lastname are equals to name and surname, and if the title is equals to the title received by the employee
            if (employee.getFirstName().equalsIgnoreCase(name)
                    && employee.getLastname().equalsIgnoreCase(surname)
                    && employee.getManager().getPosition().getTitle().equalsIgnoreCase(manager.getPosition().getTitle())) {
                return true;// if the condition is true, its measn that there is a employee with the same name, surname and position was found on the list
            }
        }
        return false;//return false if no duplicated data was found.
    }

//This method, as the name already says, is used to add a new employee to the employee list. It will collect the input from the user as name, surname, manager and department
//after it, the method will check if this data is duplicated, if its not, another conditional will shows up to confirm the data to be entered, if all the data is true, the new employee is added to the list.    
    private static void addEmployee() {
//Declaring variables that will be used in the user inputs.        
        String name;
        String surname;
        Role manager = null;
        Department department = null;

        System.out.print("Employee First Name: ");//Message to give user instructions
        name = validateName();//receiving the input and calling the validateName method, to make sure the name has only letters

        System.out.print("Employee Last Name: ");
        surname = validateName();//receiving the input and calling the validateName method, to make sure the name has only letters

        manager = selectManager();//calling the method to select the manager
        department = selectDepartment(manager);//calling the method to select the department

        if (isDataDuplicated(name, surname, manager)) {//condition created to check if the employee is already on the list, avoiding duplicated data
            System.out.println("Employee with the same Name and Position already exists. Please try again!");//message to user instructions
            return;//exit the method, no adding the employee.
        }
//Showing the datas to be inserted, to user confirmation.
        System.out.println("\nPlease confirm the following details:");
        System.out.println("First Name: " + name);
        System.out.println("Last Name: " + surname);
        System.out.println("Position: " + manager.getPosition().getTitle());
        System.out.println("Department: " + department.getDepartmentName());

        System.out.println("Is this Data Correct? (YES/NO): ");//Message to get the user confirmation
        String confirmation = myKb.nextLine().trim().toLowerCase();//Variable to receive the user answer.

//If-Else condition to execute the addition, in this case if the user checked the data and decided to added the new employee, a 'yes' has to be entered, otherwise the program return to the method
        if (confirmation.equals("yes")) {
            Employee employee = new Employee(name, surname, manager, department);
            employees.add(employee);
            System.out.println("Employee added sucessfully: " + employee);
        } else {
            System.out.println("Let's try again..");
            addEmployee();
        }
    }

//this method is responsible to show a list of positions, and ask the user to select one of them to assign it to a employee that will be added.    
    private static Role selectManager() {
        while (true) {//while loop used to in case the user dont choose a valid option in the manager list.
            System.out.println("Which Position?");//message to interact with the user
            List<Role> managers = Role.getEmployeePosition();//calling the method getEmployeePosition from the class Role and getting its list
            for (int i = 0; i < managers.size(); i++) {//foor loop used to run all the list and show each avaiable position to the user choose
                System.out.println((i + 1) + ". " + managers.get(i));//printing the list enumerating each position, ex: 1. Department Head, 2. Supervisor, etc
            }
            String input = myKb.nextLine().trim();//Receiving the user input
            if (input.isEmpty()) {//condition in case the input is empty
                System.out.println("Input cannot be empty. Please select a valid option!");//message to guide the user to enter one of the options
                continue;//used to keep asking the user, returning to the loop
            }
            try {//try-catch block used to treat any exceptions that could appear.
                int managerChoice = Integer.parseInt(input);//converting the input in a integer, and assigning it to the managerChoice variable
                if (managerChoice >= 1 && managerChoice <= managers.size()) {//condition created to check if the option entered by the user is in the valid range, from 1 to the size of the list
                    return managers.get(managerChoice - 1);//returning the option selected by the user and using -1 to match the index of the list, because it starts with 0, then if the user chosse the option 1, it is actually the option 0 in the list
                } else {//else condition, used in case the user enter a number out of the range
                    System.out.println("Invalid Choice, please enter a number between 1 and " + managers.size());
                }
            } catch (NumberFormatException e) {//catch block used to capture the exception, in case the user enter any other thing that is not a number.
                System.out.println("Invalid Input! Please enter a number!");
            }
        }
    }

//This method is responsible to show a list of departments , and will ask the user to assign one of the departments in the list to the new person added to the list.    
    private static Department selectDepartment(Role manager) {
        List<String> allowedDepartments = manager.getPosition().getAllowedDepartment();//getting the list of allowed departments for the position choosed by the user
        List<Department> allDepartments = DepartmentEmployee.getEmployeeDepartments();//taking all existing departments
        List<Department> filteredDepartments = new ArrayList<>();//creating a new list of departments

        for (Department department : allDepartments) {//for each loop used to iterate through the entire list of existing departments and address them to department
//condition where allowedDepartments contains the names of the departments, and then adds them to the new filteredDepartment list            
            if (allowedDepartments.contains(department.getDepartmentName())) {
                filteredDepartments.add(department);
            }
        }
        System.out.println("Select the Department:");//Message to interact with the user
        //for loop created to print list of departments
        for (int i = 0; i < filteredDepartments.size(); i++) {
            System.out.println((i + 1) + ". " + filteredDepartments.get(i).getDepartmentName());
        }

        int choice = correctInput();//using the correctInput method to ensure that the user enters a number
        if (choice < 1 || choice > filteredDepartments.size()) {//condition created to ensure that the user enters a number that is within the list range
            System.out.println("Invalid Choice! Please select a valid Department!");//message displayed if the user enters a value outside the range
            return selectDepartment(manager);//returns to the method if the condition is false
        }
        return filteredDepartments.get(choice - 1);//returns the selected department if the condition is true and edits the value of the int, since the list starts with zero. so it needs to subtract a number to match the list with the user's answer
    }

//This method is responsible for generating a random employee using the generatingRandomEmployees method of the GenerateRandomEmployee class.    
    private static void generateRandomEmployee() {
        randomEmployee.generatingRandomEmployees(1);//determining the number of employees to be randomly generated.
        employees.addAll(randomEmployee.getEmployees());//adding the new randomly generated employee to the employees list
//for loop used to display the new employee information and the total number of employees in the list        
        for (Employee employee : randomEmployee.getEmployees()) {
            System.out.println("Employee: " + employee);
        }
        System.out.println("\nCurrent number of employees after this random generation: " + employees.size());
    }

//This method is responsible for performing a search for a person in the list of employees.    
    private static void searchEmployee() {
        System.out.println("Enter the Employee's First and Last Names to be searched: ");//Message to interact with the user
        String employeeName = myKb.nextLine().trim();//variable that will receive user input, with the string being treated with .trim, to remove spaces before and after the entered name

        searcher.searchByName(employees, employeeName);//searchByName method of the Searcher class being used to search the list for an employee, based on the name provided by the user.
    }

//This method is responsible for sorting the list of employees in alphabetical order.
//The method will use the mergeSortList method of the Sorter class to sort from index 0 to the final size of the list.    
    private static void sortEmployee() {
        sorter.mergeSortList(employees, 0, employees.size() - 1);
    }

//This method is responsible for displaying all employees in the employee list, it uses a for-each loop to go through all the elements in the list and then prints the list.    
    private static void displayEmployees() {
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

//This method is responsible for displaying the total salaries paid to employees, who are on the employees list, by the hospital. 
    private static void displayMonthlyExpenses() {
//totalExpense variable of type double, which will receive a stream so that it can add up the data for each employee's salary and receives the reference to the getMonthlyPayment method for each employee        
        double totalExpenses = employees.stream().mapToDouble(Employee::getMonthlyPayment).sum();
        System.out.printf("Monthly Expenses for Employees: €%.2f", totalExpenses);//print used to show the total amount spent on payments to employees who are on the list
        System.out.println();//adding an extra line
    }

//Method used to export employee list data to a text file, writing all the list data to this file, using the DataFile class and the writeEmployeeReport method.    
    private static void exportEmployees() {
        String fileName = "Employee_Report.txt"; //name of the file to be exported
        dataFile.writeEmployeeReport(fileName, employees);
    }

//This method is responsible for adding a new patient to the patient list.
    private static void addNewPatient() {
//validateName(prompt) method used so that the user can enter the patient's name        
        String firstName = validateName("Enter the Patient First Name: ");
        String lastName = validateName("Enter the Patient Last Name: ");

        System.out.print("Enter the Patient Age: ");
        int age = correctInput();//variable acts by calling the correctInput method to ensure that the input is an integer

        LocalDateTime admissionDate = LocalDateTime.now();//admission Date variables using the LocalDateTime method, to capture the day and time of the patient's admission to the hospital

        System.out.print("Enter the Patient Diagnosis: ");
        String diagnosis = myKb.nextLine().trim();//Receiving the input about the patient diagnosis 

        Department department = selectDepartmentForPatient();//Selecting the department where the patient will be directed in the hospital, using the selectDepartmentForPatient method

        System.out.print("Enter Attending Doctor Name: ");
        String attendinDoctor = myKb.nextLine().trim();//Receiving the Doctor name

        System.out.print("Enter Stay Days: ");
        int stayDays = myKb.nextInt();//Entry to inform how many days the patient will stay in the hospital / how long the treatment will last

        System.out.print("Enter Daily Rate: ");
        double dailyRate = myKb.nextDouble();//Determining the value of treatment
        myKb.nextLine();

//User confirmation message containing all patient data and their care at the hospital. The message follows an if-else condition in which if the user 
//enters the word yes, the if condition will be considered true and the patient will then be added to the list, otherwise the else condition, which will be false, will display a message to the user and return to the beginning of the method.        
        System.out.println("Please confirm the following Patient Details: ");
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Age: " + age);
        System.out.println("Admission Date: " + admissionDate);
        System.out.println("Diagnosis: " + diagnosis);
        System.out.println("Department: " + department.getDepartmentName());
        System.out.println("Attending Doctor: Dr. " + attendinDoctor);
        System.out.println("Stay Date: " + stayDays);
        System.out.println("Daily Rate: " + String.format("%.2f", dailyRate));
        System.out.print("Is all the Data Correct? (YES/NO): ");
        String confirmation = myKb.nextLine().trim().toLowerCase();

        if (confirmation.equals("yes")) {
            Patient newPatient = new Patient(firstName, lastName, age, admissionDate, diagnosis, department, attendinDoctor, stayDays, dailyRate);
            patients.add(newPatient);
            System.out.println("Patient added successfully: \n" + newPatient);
        } else {
            System.out.println("Patient addition cancelled. Let's try again!");
            addNewPatient();
        }
    }

//This method is responsible for showing a list of departments to which the patient will be referred in the hospital, which must be chosen by the user when the patient is being added to the list.    
    private static Department selectDepartmentForPatient() {
        List<Department> allDepartments = DepartmentPatient.getPatientDepartments();//variable created that will receive all departments of the departmentPatient class
        System.out.println("Select the Department for Patient Treatment:");

//for loop used to print the list of departments, where it will go through all the departments contained in this list allDepartments taking its index and associating a name through the getDepartmentName method, of the department class.
        for (int i = 0; i < allDepartments.size(); i++) {
            System.out.println((i + 1) + ". " + allDepartments.get(i).getDepartmentName());
        }
        int choice = correctInput();//variable calling the correctInput method to ensure that the user entered an integer
//conditional used to ensure that the user makes a choice that will be among the range of numbers in the list. If the user chooses something that is not in the range, a message will inform them of the error and return them to the beginning of the method.        
        if (choice < 1 || choice > allDepartments.size()) {
            System.out.println("This is an invalid choice, please select a valid Department!");
            return selectDepartmentForPatient();
        }
//If the condition is true, the method then returns the selected department, according to the user's choice, using the subtraction -1 to combine the user's choice with the list index, which starts with 0.
        return allDepartments.get(choice - 1);
    }

//This method is responsible for sorting the list of patients in alphabetical order.
//It will use the mergeSortList method of the sorter class, searching for patients from index 0 to the end of the list.    
    private static void sortPatients() {
        if(patients.isEmpty()){//condition created in case the list of patients is empty.
            System.out.println("No Patients avaiable to sort. Returning to Patient Menu...");//message will show up to instruct the user that theres no patients in the list.
            return;//returning to the patient menu
        }
        sorter.mergeSortList(patients, 0, patients.size() - 1);
        System.out.println();
    }

//This method is responsible for searching for a name in the patients list.
//It will use the searchByName method of the searcher class.
    private static void searchPatient() {
        System.out.print("Enter the Patient Full Name to be searched: ");
        String patientName = myKb.nextLine();//input entered by the user 
        searcher.searchByName(patients, patientName);
    }

//This method is responsible for calculating and displaying the total value of expenses for patient treatment.   
    private static void viewTotalTreatmentIncome() {
//totalIncome variable of type double, which will receive a stream so that it can add up the data for each patient's total treatment and receives the reference to the treatmentCostCalculator method for each patient                
        double totalIncome = patients.stream().mapToDouble(Patient::treatmentCostCalculator).sum();
        System.out.println("Total Treatment Income: € " + totalIncome);//print used to show the total amount spent 
    }

//This method is responsible for exporting the data contained in the patient list to a text file. To do this, it will use the writePatientReport method of the Datafile class.    
    private static void exportPatientReport() {
        String fileName = "Patient_Report.txt";
        dataFile.writePatientReport(fileName, patients);
    }

//This method is responsible for displaying all patients in the list.
//For this to happen, the method consists of a for-each loop that will go through all the patient in the patients list.    
    private static void displayPatient() {
        for (Patient patient : patients) {
            System.out.println(patient);;
            System.out.println();
        }
    }
}
