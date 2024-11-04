package CA_2;

import java.util.Random;

public class Salary {

    public int generateRandomWorkedHours(int min, int max) {
        Random random = new Random();
        return min + random.nextInt(max - min + 1);
    }

    public double calculateMonthlyPayment(String managerType, int hoursWorked) {
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
        return hourlyRate * hoursWorked;
    }

    private boolean validateHoursWorked(int hoursWorked) {
        return hoursWorked >= 80 && hoursWorked <= 160;
    }

}
