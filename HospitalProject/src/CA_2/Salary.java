package CA_2;

import java.util.Random;

public class Salary {
/*This Salary class was implemented with the aim of assigning a salary based on the position and hours worked by each employee.
    This class will have some methods to make the class more efficient, the first method is 'generateRandomWorkedHours', as the
    name suggests, this method is responsible for creating a min and max value of hours randomly. The idea of having this method
    is that in a hospital environment the amount of hours worked can vary, especially because there is a high risk of contracting
    viruses and diseases, where employees are constantly affected and need less hours working to recover themselves.
    
    The second method 'calculateMonthlyPayment' which will use a switch case to address each position to its respective hourly value.
    The last method 'validateHoursWorked' is intended to check if the hours worked are within a range of 80 to 160 hours per month,
    returning true if the condition is within this range and false if its below or above.This private method with a conditional check if the
    hours are within the established standard, returning 0.0 if they are not in the defined range.
    */

//Method to generate random hours between the min and max amount of hours. This method will be used in the Employee class, to set the random Hours worked by a employee
    public int generateRandomWorkedHours(int min, int max) { 
        Random random = new Random();
        return min + random.nextInt(max - min + 1);
    }

    public double calculateMonthlyPayment(String managerType, int hoursWorked) {//Used to set the €/hour for each position.
        double hourlyRate;
        switch (managerType) {
            case "Department Head":
                hourlyRate = 50.0;
                break;
            case "Supervisor":
                hourlyRate = 45.0;
                break;
            case "Coordinator":
                hourlyRate = 40.0;
                break;
            case "Director":
                hourlyRate = 60.0;
                break;
            case "Doctor":
                hourlyRate = 55.0;
                break;
            case "Nurse":
                hourlyRate = 35.0;
                break;
            case "Nurse Technician":
                hourlyRate = 25.0;
                break;
            case "Administrative Assistant":
                hourlyRate = 16.0;
                break;
            default:
                System.out.println("Position choosen is not Valid!");
                return 0.0;
        }
        if (!validateHoursWorked(hoursWorked)) {
            return 0.0;
        }
        return hourlyRate * hoursWorked;//Returning the total to receive by the employee.
    }

    private boolean validateHoursWorked(int hoursWorked) {//Defining a range of min and max hours, to validate the method above.
        return hoursWorked >= 80 && hoursWorked <= 160;
    }

}
