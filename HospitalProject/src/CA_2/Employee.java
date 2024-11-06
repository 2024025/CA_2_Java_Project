package CA_2;

/*This class is responsible for storing all information related to employees, such as: department,
hours worked, monthly salary payment, etc.
This class is an extension of Person class, where it will absorb data from first and last names.
This class will be called in the GenerateRandomEmployee and HospitalApplication classes.
*/

public class Employee extends Person {
//Private attributes to store informations about the Employees.
    private Manager manager;
    private Department department;
    private int hoursWorked;
    private double monthlyPayment;

//    Constructor used to initialize all the attributes
    public Employee(String firstName, String lastName, Manager manager, Department department) {
        super(firstName, lastName);
        this.manager = manager;
        this.department = department;

        if (manager == null) {//Conditions created to checking if the manager is null
            throw new IllegalArgumentException("Manager can not be null!");//If this condition is true, a exception will be throw and a message will show up to warning the user
        }
        if (department == null) {//Conditions created to checking if the department is null
            throw new IllegalArgumentException("Department can not be null!");//If this condition is true, a exception will be throw and a message will show up to warning the user
        }
        
        
        //If the condition is false, the methods below will be called.
        
        setRandomHoursWorked();//calling the method, to generate a random amount of hours
        calculateSalary();//calling the method to calculate the salary.
    }

    
//    Getters and Setters, to allow other class import this attributes
    
    private void setRandomHoursWorked() {//Method created to define a random amount of hours worked by employees
        Salary salaryCalculator = new Salary();//Creating a salary object to calculate the salary.
        this.hoursWorked = salaryCalculator.generateRandomWorkedHours(80, 160);//Defining the amount of hours allowed to work, min 80 and max 160
    }

    
    public Manager getManager() {//Getter used to be accessible in other classes.
        return manager;
    }

    public double getMonthlyPayment() {//Getter used to be accessible in other classes.
        return monthlyPayment;
    }

    public Department getDepartment() {//Getter used to be accessible in other classes.
        return department;
    }

//    Method create to calculate the salary for each employee, accordingly to the managerType Position .
    private void calculateSalary() {
        Salary salaryCalculator = new Salary();//Creating a salary object to calculate the salary.
        this.monthlyPayment = salaryCalculator.calculateMonthlyPayment(manager.getPosition().getTitle(), hoursWorked);//Calculating the monthly payment accordingly to the manager type position and hours worked
    }

//    Method create in case there is any needs to enter the hours manually, not used in the HospitalApplication, yet.
    public void setHoursWorked(int hours) {
        this.hoursWorked = hours;
        calculateSalary();//Recalculating the salary, accordingly with the hours entered.
    }

    
//    toString() method, used to print the strings about the employee, its hours and salary.
    @Override
    public String toString() {
        return "Employee: " + getFirstName() + " " + getLastname() + " - "
                + manager.getPosition().getTitle() + " - " + department.getDepartmentName()
                + " - Worked Hours: " + hoursWorked + "h - Salary: € " + String.format("%.2f", monthlyPayment);
    }

}
