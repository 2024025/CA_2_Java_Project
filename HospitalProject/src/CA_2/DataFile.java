package CA_2;

/*
This class is responsible for reading data from a .txt file, where each line
will have a first name and a surname, separated by comma. When reading this file,
line by line, the data will be stored in two arrays(name and surname). These names
and surnames can be accessed in other classes due to the getter methods (getName
and getSurname)
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataFile { //Public class, accessible from others classes/packages

    private String[] name; // Declaring name as a String Array
    private String[] surname;// Declaring surname as a String Array

    public String[] getName() { //Getter method for name, let the array accessible in other classes/package
        return name;
    }

    public String[] getSurname() { //Getter method for name, let the array accessible in other classes/package
        return surname;
    }

    private void readFile(String fileName) { //Method readFile, responsible to read the file 'fileName' and update the arrays(name/surname)
        //Here I created two List, that will be used to store names and surnames temporarily
        //I choose use list, because the file size could be increased, and due this the ArraysList are more appropriated when we dont know
        // exactly the size of the array.
        List<String> listName = new ArrayList<>();
        List<String> listSurname = new ArrayList<>();

        try { //Try catch block to check and treat exceptions when reading the file.

            BufferedReader br = new BufferedReader(new FileReader(fileName)); //BufferedRead used to read the file 'fileName'
            String lines;
            while ((lines = br.readLine()) != null) { //This while block is used to read line by line, until reach the last line. When the line is null, the loop ends.
                int lastComma = lines.lastIndexOf(","); //int variable 'lastComma' used here to get the comma location , that will separate the names from surnames.
                if (lastComma == -1) { //If condition was created in case there's no comma in the read line.
                    System.out.println("This line couldn't be parsed: " + lines);//Message to inform the user that the line couldn't be processed and parsed.
                    continue;
                }
                //For each line read, the names (all words before the comma), will be added to the List
                listName.add(lines.substring(0, lastComma).trim());//Collecting the names located at the 0 index up to the last comma in the line, trim added to remove spaces before and after the content.

                //For each line read, the surnames (all words after the comma), will be added to the List
                listSurname.add(lines.substring(lastComma + 1).trim()); //Collecting the names located at after the last comma + 1, its means that will take the surname after the coma and add one extra index, reaching all the contents after the comma up to the end of the line, trim added to remove spaces before and after the content.
            }
            System.out.println("\nThe file " + fileName + " was read successfully!"); //Messasge to show the user that the file was read correctly.

        } catch (IOException e) {//Catch bblock, used to find any exception when reading the file and show a message to the user, explaing what the error was.
            System.out.println("This file couldn't be read! " + e.getMessage());
        }

        //Transforming the lists listName and listSurname into array.
        name = listName.toArray(new String[0]);
        surname = listSurname.toArray(new String[0]);
    }

    public DataFile(String fileName) { //The DataFile constructor takes the fileName and calls the method to read the file.
        readFile(fileName);
    }
    
    
    
    
    //This method was created to write an employee report in a text file, called: "Employees_Form_Report.txt"
    public void writeEmployeeReport(String fileName, List<Employee> employees){//The method is public, and can be called in the main class so that it can be executed.
        if(employees == null || employees.isEmpty()){ //This condition will be used to check if the employee list is empty, If it is, it will return, ending the execution of this method.
            System.out.println("\nThere is no employees to write in the report!");//Message to guide and give instructions to the user.
            return;
        }
        double totalExpenses = 0.0;//Initializing the variable that will calculate the total cost of hospital staff expenses
        fileName = "Employees_Form_Report.txt";// Defining the name of the file to be exported.
        try{ // Try-Catch block used to handle exceptions in case of problems when writing the file
            
//Using the bufferedWriter to write the text file and using the 'false' mode, so that the file is overwritten. In case need to keep the previous records, the true option would be recommended.
            BufferedWriter wr = new BufferedWriter(new FileWriter(fileName, false));
            wr.write("\nEmployee Report: "); //Message to interact with Users.
            wr.newLine();//Adding an extra line to the text file
            wr.newLine();
            for(Employee employee : employees){//for each loop used to iterate through each employee in the list
                wr.write(employee.toString());//Using the toString() method to write the employee details to the text file
                wr.newLine();//Adding an extra line to the text file
                
                totalExpenses += employee.getMonthlyPayment();//Used to calculate the total payment made to the hospital employees
            }
            wr.write(String.format("Hospital Total Monthly Expenses: €%.2f", totalExpenses)); // Printing the total amount paid for employees.
            wr.newLine();//Adding a new Line
            System.out.println("\nEMployee Report Successfully Created in: " + fileName);//Message to interact with users, and showing that the writer was successful
            wr.close();
        }catch(IOException e){//Catch used to capture any exceptions in the I/O
            System.out.println("\nError when writing the file: " +e.getMessage());//Message to guide the users, explaining the issue reasons.
        }
    }
    
    
//  Very similar method, used to write a report about Patients, following the same intentions of the method above.
    public void writePatientReport(String fileName, List<Patient> patients){
        if(patients == null || patients.isEmpty()){
            System.out.println("\nThere is no Patients to write in the Report!");
            return;
        }
        double totalIncome = 0.0;
        fileName = "Patients_Form_Report.txt";

        try {
            BufferedWriter wr = new BufferedWriter(new FileWriter(fileName, false));
            wr.write("\nPatient Report: ");
            wr.newLine();
            wr.newLine();
            for (Patient patient : patients) {
                wr.write(patient.toString());
                wr.newLine();
                wr.newLine();
                totalIncome += patient.treatmentCostCalculator();
            }
            wr.write(String.format("Hospital Total Income: €%.2f", totalIncome));
            wr.newLine();
            System.out.println("\nPatients Report Successfully Created in: " + fileName);
            wr.close();
        } catch (IOException e) {
            System.out.println("\nError when writing the file: " + e.getMessage());
        }
    }
}
