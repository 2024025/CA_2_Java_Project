package CA_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateRandomEmployee {

    private List<Employee> employees = new ArrayList<>();
    private List<Person> availableNames = new ArrayList<>();
    private Random random = new Random();

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setAvailableNames(List<Person> availableNames) {
        this.availableNames = availableNames;
    }

    public void generatingRandomEmployees(int quantityOfEmployees) {
        if (availableNames.isEmpty()) {
            System.out.println("Names or surnames coudn't be loaded from the file.");
            return;
        }
        employees.clear();
        for (int i = 0; i < quantityOfEmployees; i++) {
            Person person = availableNames.get(random.nextInt(availableNames.size()));

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

            Employee employee = new Employee(person.getFirstName(), person.getLastname(), randomManager, department);
            employees.add(employee);
        }
    }
}
