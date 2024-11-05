package CA_2;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class HospitalApplication {

    private static List<Employee> employees = new ArrayList<>();
    private static GenerateRandomEmployee randomEmployee = new GenerateRandomEmployee();
    private static DataFile dataFile;
    private static Scanner myKb = new Scanner(System.in);
    private static List<Patient> patients = new ArrayList<>();
    private static Sorter sorter = new Sorter();
    private static Searcher searcher = new Searcher();
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z]+( [a-zA-Z]+)*$");

    public static void main(String[] args) {
        System.out.println("||||-- WELCOME TO DUBLIN HOSPITAL SYSTEM - DHS --||||");
        System.out.println();
       
        if (!loadDataFromFile()){
            return;
        }

        MainMenu optionMainMenu = null;
        do {
            System.out.println("--- MAIN MENU - DHS ---");
            System.out.println("1. EMPLOYEE INFORMATION");
            System.out.println("2. PATIENT INFORMATION");
            System.out.println("3. EXIT SYSTEM");
            int option = correctInput();

            if (option >= 1 && option <= MainMenu.values().length) {
                optionMainMenu = MainMenu.values()[option - 1];
                switch (optionMainMenu) {
                    case EMPLOYEE_INFORMATIONS:
                        displayEmployeeMenu();
                        break;
                    case PATIENT_INFORMATIONS:
                        displayPatientMenu();
                        break;
                    case EXIT_SYSTEM:
                        System.out.println("Exiting the Dublin Hospital System. See You Soon!!");
                        return;
                }
            } else {
                System.out.println("This is an invalid choice, please select a valid option from Menu!(1-3):");
            }
        } while (optionMainMenu != MainMenu.EXIT_SYSTEM);
    }

    private static int correctInput() {
        while (!myKb.hasNextInt()) {
            System.out.println("You Entered an invalid Input. Please enter a number: ");
            myKb.next();
        }
        int number = myKb.nextInt();
        myKb.nextLine();
        return number;
    }

    private static void displayEmployeeMenu() {
        EmployeeMenu optionEmployeeMenu = null;
        displayEmployees();
        System.out.println();
        do {
            System.out.println("\n--- EMPLOYEE MENU - DHS ---");
            System.out.println("1. SORT EMPLOYEE LIST");
            System.out.println("2. SEARCH FOR AN EMPLOYEE");
            System.out.println("3. ADD A NEW EMPLOYEE");
            System.out.println("4. GENERATE A RANDOM EMPLOYEE");
            System.out.println("5. VIEW MONTHLY PAYROL EXPENSES");
            System.out.println("6. EXPORT EMPLOYEE PAYROLL REPORT");
            System.out.println("7. EXIT EMPLOYEE MENU");

            System.out.println("CHOOSE ONE OF THE OPTIONS: ");
            int option = correctInput();

            if (option >= 1 && option <= EmployeeMenu.values().length) {
                optionEmployeeMenu = EmployeeMenu.values()[option - 1];
                switch (optionEmployeeMenu) {
                    case SORTING_EMPLOYEE_LIST:
                        System.out.println("SORTING EMPLOYEE LIST...");
                        sortEmployee();
                        break;
                    case SEARCHING_EMPLOYEE:
                        System.out.println("SEARCHING FOR AN EMPLOYEE...");
                        searchEmployee();
                        break;
                    case ADDING_NEW_EMPLOYEE:
                        System.out.println("ADDING A NEW EMPLOYEE...");
                        addEmployee();
                        break;
                    case EMPLOYEE_RANDOM_GENERATION:
                        System.out.println("GENERATING A RANDOM EMPLOYEE...");
                        generateRandomEmployee();
                        break;
                    case VIEW_MONTHLY_EXPENSES:
                        System.out.println("VIEWING MONTHLY PAYROLL EXPENSES...");
                        displayMonthlyExpenses();
                        break;
                    case EXPORT_EMPLOYEES_REPORT:
                        System.out.println("EXPORTING EMPLOYEE PAYROLL REPORT...");
                        exportEmployees();
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

    private static void displayPatientMenu() {
        PatientMenu optionPatientMenu = null;

        do {
            System.out.println("\n--- PATIENT MENU - DHS ---");
            System.out.println("1. ADD A NEW PATIENT");
            System.out.println("2. SORT PATIENT LIST");
            System.out.println("3. SEARCH PATIENT");
            System.out.println("4. VIEW TREATMENT INCOME");
            System.out.println("5. EXPORT PATIENT REPORT");
            System.out.println("6. EXIT PATIENT MENU");

            System.out.println("CHOOSE ONE OF THE OPTIONS: ");
            int option = correctInput();

            if (option >= 1 & option <= PatientMenu.values().length) {
                optionPatientMenu = PatientMenu.values()[option - 1];

                switch (optionPatientMenu) {
                    case ADD_NEW_PATIENT:
                        System.out.println("ADDING A NEW PATIENT...");
                        addNewPatient();
                        displayPatient();
                        break;
                    case SORT_PATIENT_LIST:
                        System.out.println("SORTING PATIENT LIST...");
                        displayPatient();
                        sortPatients();
                        break;
                    case SEARCH_PATIENT:
                        System.out.println("SEARCHING FOR A PATIENT...");
                        searchPatient();
                        break;
                    case VIEW_TREATMENT_INCOME:
                        System.out.println("VIEWING TOTAL TREATMENT INCOME...");
                        viewTotalTreatmentIncome();
                        break;
                    case EXPORT_PATIENT_REPORT:
                        System.out.println("EXPORTING PATIENT REPORT...");
                        exportPatientReport();
                        break;
                    case EXIT_MENU:
                        System.out.println("RETURNING TO MAIN MENU...");
                        return;
                }

            } else {
                System.out.println("Please Select From All the Available Options! (1- 6)");
            }
        } while (optionPatientMenu != PatientMenu.EXIT_MENU);
    }

    private static boolean loadDataFromFile() {
        boolean isLoaded = false;
        do {
            System.out.print("Enter a filename to be read: ");
            String fileName = myKb.nextLine().toLowerCase().trim();

            try {
                dataFile = new DataFile(fileName);
                if (dataFile.getName() == null || dataFile.getSurname() == null || dataFile.getName().length == 0 || dataFile.getSurname().length == 0) {
                    System.out.println("Error Loading the Names and Surnames from the file!");
                    continue;
                }

                randomEmployee.setEmployeeName(Arrays.asList(dataFile.getName()));
                randomEmployee.setEmployeeSurname(Arrays.asList(dataFile.getSurname()));
                System.out.println("The file is completely loaded!");
                randomEmployee.generatingRandomEmployees(20);
                employees.addAll(randomEmployee.getEmployees());
                isLoaded = true;
                System.out.println();
            } catch (Exception e) {
                System.out.println("An error occurred when reading the file: " + e.getMessage());
            }
        } while (!isLoaded);
        return true;
    }
    

    private static boolean isValidName(String name) {
        return name != null && NAME_PATTERN.matcher(name).matches();
    }

    private static String validateName() {
        String name;
        while (true) {
            name = myKb.nextLine();
            if (isValidName(name)) {
                break;
            } else {
                System.out.print("This is a Invalid name! Only enters allowed (A-Z)."); 
                System.out.print("\nLet's try again, Enter the Name: ");
            }
        }
        return name;
    }

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

    private static boolean isDataDuplicated(String name, String surname, Manager manager) {
        for (Employee employee : employees) {
            if (employee.getFirstName().equalsIgnoreCase(name)
                    && employee.getLastname().equalsIgnoreCase(surname)
                    && employee.getManager().getPosition().getTitle().equalsIgnoreCase(manager.getPosition().getTitle())) {
                return true;
            }
        }
        return false;
    }

    private static void addEmployee() {
        String name;
        String surname;
        Manager manager = null;
        Department department = null;
        boolean correctInput;

        System.out.print("Employee First Name: ");
        name = validateName();

        System.out.print("Employee Last Name: ");
        surname = validateName();

        manager = selectManager();
        department = selectDepartment(manager);

        if (isDataDuplicated(name, surname, manager)) {
            System.out.println("Employee with the same Name and Position already exists. Please try again!");
            return;
        }

        System.out.println("\nPlease confirm the following details:");
        System.out.println("First Name: " + name);
        System.out.println("Last Name: " + surname);
        System.out.println("Position: " + manager.getPosition().getTitle());
        System.out.println("Department: " + department.getDepartmentName());

        System.out.println("Is this Data Correct? (YES/NO): ");
        String confirmation = myKb.nextLine().trim().toLowerCase();

        if (confirmation.equals("yes")) {
            Employee employee = new Employee(name, surname, manager, department);
            employees.add(employee);
            System.out.println("Employee added sucessfully: " + employee);
        } else {
            System.out.println("Let's try again..");
            addEmployee();
        }
    }

    private static Manager selectManager() {
        while (true) {
            System.out.println("Which Position?");
            List<Manager> managers = Manager.getEmployeePosition();
            for (int i = 0; i < managers.size(); i++) {
                System.out.println((i + 1) + ". " + managers.get(i));
            }
            String input = myKb.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please select a valid option!");
                continue;
            }
            try {
                int managerChoice = Integer.parseInt(input);
                if (managerChoice >= 1 && managerChoice <= managers.size()) {
                    return managers.get(managerChoice - 1);
                } else {
                    System.out.println("Invalid Choice, please enter a number between 1 and " + managers.size());
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid INput! Please enter a number!");
            }
        }
    }

    private static Department selectDepartment(Manager manager) {
        List<String> allowedDepartments = manager.getPosition().getAllowedDepartment();
        List<Department> allDepartments = DepartmentEmployee.getEmployeeDepartments();
        List<Department> filteredDepartments = new ArrayList<>();

        for (Department department : allDepartments) {
            if (allowedDepartments.contains(department.getDepartmentName())) {
                filteredDepartments.add(department);
            }
        }
        System.out.println("Select the Department:");
        for (int i = 0; i < filteredDepartments.size(); i++) {
            System.out.println((i + 1) + ". " + filteredDepartments.get(i).getDepartmentName());
        }

        int choice = correctInput();
        if (choice < 1 || choice > filteredDepartments.size()) {
            System.out.println("Invalid Choice! Please select a valid Department!");
            return selectDepartment(manager);
        }
        return filteredDepartments.get(choice - 1);
    }

    private static void generateRandomEmployee() {
        randomEmployee.generatingRandomEmployees(1);
        employees.addAll(randomEmployee.getEmployees());
        for (Employee employee : randomEmployee.getEmployees()) {
            System.out.println("Employee: " + employee);
        }
        System.out.println("\nCurrent number of employees after this random generation: " + employees.size());
    }

    private static void searchEmployee() {
        System.out.println("Enter the Employee's First and Last Names to be searched: ");
        String employeeName = myKb.nextLine().trim();
    
        searcher.searchByName(employees, employeeName);
    }

    private static void sortEmployee() {
        sorter.mergeSortList(employees, 0, employees.size() - 1); 
    }

    private static void displayEmployees() {
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    private static void displayMonthlyExpenses() {
        double totalExpenses = employees.stream().mapToDouble(Employee::getMonthlyPayment).sum();
        System.out.printf("Monthly Expenses for Employees: €%.2f", totalExpenses);
        System.out.println();
    }

    private static void exportEmployees() {
        String fileName = "Employee_Report.txt"; 
        dataFile.writeEmployeeReport(fileName, employees);
    }

    private static void addNewPatient() {
        String firstName = validateName("Enter the Patient First Name: ");
        String lastName = validateName("Enter the Patient Last Name: ");

        System.out.print("Enter the Patient Age: ");
        int age = correctInput();

        LocalDateTime admissionDate = LocalDateTime.now();

        System.out.print("Enter the Patient Diagnosis: ");
        String diagnosis = myKb.nextLine().trim();

        Department department = selectDepartmentForPatient();

        System.out.print("Enter Attending Doctor Name: ");
        String attendinDoctor = myKb.nextLine().trim();

        System.out.print("Enter Stay Days: ");
        int stayDays = myKb.nextInt();

        System.out.print("Enter Daily Rate: ");
        double dailyRate = myKb.nextDouble();
        myKb.nextLine();

        System.out.println("Please confirm the following Patient Details: ");
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Age: " + age);
        System.out.println("Admission Date: " + admissionDate);
        System.out.println("Diagnosis: " + diagnosis);
        System.out.println("Department: " + department.getDepartmentName());
        System.out.println("Attending Doctor: Dr. " + attendinDoctor);
        System.out.println("Stay Date: " +  stayDays);
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

    private static Department selectDepartmentForPatient() {
        List<Department> allDepartments = DepartmentPatient.getPatientDepartments();
        System.out.println("Select the Department for Patient Treatment:");
        for (int i = 0; i < allDepartments.size(); i++) {
            System.out.println((i + 1) + ". " + allDepartments.get(i).getDepartmentName());
        }
        int choice = correctInput();
        if (choice < 1 || choice > allDepartments.size()) {
            System.out.println("This is an invalid choice, please select a valid Department!");
            return selectDepartmentForPatient();
        }
        return allDepartments.get(choice - 1);
    }

    private static void sortPatients() {
        System.out.println("Sorting Patients...");
        sorter.mergeSortList(patients, 0, patients.size() - 1);
        System.out.println();
    }

    private static void searchPatient() {
        System.out.print("Enter the Patient Full Name to be searched: ");
        String patientName = myKb.nextLine();
        searcher.searchByName(patients, patientName);
    }

    private static void viewTotalTreatmentIncome() {
        double totalIncome = patients.stream().mapToDouble(Patient::treatmentCostCalculator).sum();
        System.out.println("Total Treatment Income: € " + totalIncome);
    }

    private static void exportPatientReport() {
        String fileName = "Patient_Report.txt";
        dataFile.writePatientReport(fileName, patients);
    }

    private static void displayPatient() {
        for (Patient patient : patients) {
            System.out.println(patient);;
            System.out.println();
        }
    }
}
