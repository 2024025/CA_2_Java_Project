package CA_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateRandomEmployee {

    private List<Employee> employees = new ArrayList<>();
    private List<String> employeeName = new ArrayList<>();
    private List<String> employeeSurname = new ArrayList<>();
    private Random random = new Random();

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<String> getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(List<String> employeeName) {
        this.employeeName = employeeName;
    }

    public List<String> getEmployeeSurname() {
        return employeeSurname;
    }

    public void setEmployeeSurname(List<String> employeeSurname) {
        this.employeeSurname = employeeSurname;
    }

    public void generatingRandomEmployees(int quantityOfEmployees) {
        if (employeeName.isEmpty() || employeeSurname.isEmpty()) {
            System.out.println("Names or surnames coudn't be loaded from the file.");
            return;
        }
        employees.clear();

        for (int i = 0; i < quantityOfEmployees; i++) {
            String name = employeeName.get(random.nextInt(employeeName.size()));
            String surname = employeeSurname.get(random.nextInt(employeeSurname.size()));
            List<Manager> managers = Manager.getEmployeePosition();

            Manager randomManager = managers.get(random.nextInt(managers.size()));
            Position position = randomManager.getPosition();

            List<String> allowedDepartments = position.getAllowedDepartment();
            Department department = null;

            List<Department> departments = DepartmentEmployee.getEmployeeDepartments();
            for (int j = 0; j < departments.size(); j++) {
                Department dep = departments.get(j);
                if (allowedDepartments.contains(dep.getDepartmentName())) {
                    department = dep;
                    break;
                }
            }
            if (department == null) {
                throw new RuntimeException("No suitable department was found for the position:" + position.getTitle());
            }

            Employee employee = new Employee(name, surname, randomManager, department);
            employees.add(employee);
        }
    }
}
