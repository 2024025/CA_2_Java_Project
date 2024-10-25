package CA_2;

public class Employee {
    private String name;
    private Manager managerPosition;
    private Department department;

    public Employee(String name, Manager managerPosition, Department department) {
        this.name = name;
        this.managerPosition = managerPosition;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public Manager getManagerPosition() {
        return managerPosition;
    }

    public Department getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return name + " - " + managerPosition + " - " + department;
    }
    
    
    
    
}
