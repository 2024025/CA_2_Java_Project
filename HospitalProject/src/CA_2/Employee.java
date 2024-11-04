package CA_2;

public class Employee extends Person {

    private Manager manager;
    private Department department;
    private int hoursWorked;
    private double monthlyPayment;

    public Employee(String firstName, String lastName, Manager manager, Department department) {
        super(firstName, lastName);
        this.manager = manager;
        this.department = department;

        if (manager == null) {
            throw new IllegalArgumentException("Manager can not be null!");
        }
        if (department == null) {
            throw new IllegalArgumentException("Department can not be null!");
        }
        setRandomHoursWorked();
        calculateSalary();
    }

    private void setRandomHoursWorked() {
        Salary salaryCalculator = new Salary();
        this.hoursWorked = salaryCalculator.generateRandomWorkedHours(80, 160);
    }

    public Manager getManager() {
        return manager;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public Department getDepartment() {
        return department;
    }

    private void calculateSalary() {
        Salary salaryCalculator = new Salary();
        this.monthlyPayment = salaryCalculator.calculateMonthlyPayment(manager.getPosition().getTitle(), hoursWorked);
    }

    public void setHoursWorked(int hours) {
        this.hoursWorked = hours;
        calculateSalary();
    }

    @Override
    public String toString() {
        return "Employee: " + getFirstName() + " " + getLastname() + " - "
                + manager.getPosition().getTitle() + " - " + department.getDepartmentName()
                + " - Worked Hours: " + hoursWorked + "h - Salary: € " + String.format("%.2f", monthlyPayment);
    }

}
