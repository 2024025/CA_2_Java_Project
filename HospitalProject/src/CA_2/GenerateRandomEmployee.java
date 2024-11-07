package CA_2;

/*This class is responsible for generating employees randomly, defining their
names, surnames, managers and departments. This class will be important to populate
the system and perform tests on how the program will behave.
This class is being called in the main class HospitalApplication, in the method
responsible for the employee menu, giving to the user the option in the menu to 
create a employee randomly.
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateRandomEmployee {

    private List<Employee> employees = new ArrayList<>();//List used to store the employee as a object that will be generate randomly
    private List<String> employeeName = new ArrayList<>();//List used to store the employee name as a object that will be generate randomly
    private List<String> employeeSurname = new ArrayList<>();//List used to store the employee surname as a object that will be generate randomly
    private Random random = new Random();//Using the method random to create an object random, responsible to generate random values.

    public List<Employee> getEmployees() {//Getter to capture the employee
        return employees;
    }

    public void setEmployees(List<Employee> employees) {//Setter, used to define the Employee List
        this.employees = employees;
    }

    public List<String> getEmployeeName() {//Getter to capture the employee name
        return employeeName;
    }

    public void setEmployeeName(List<String> employeeName) {//Setter, used to define the Employee name List
        this.employeeName = employeeName;
    }

    public List<String> getEmployeeSurname() {//Getter to capture the employee surname
        return employeeSurname;
    }

    public void setEmployeeSurname(List<String> employeeSurname) {//Setter, used to define the Employee surname List
        this.employeeSurname = employeeSurname;
    }
    
//This method will be responsible to indeed generate the employee randomly 
//It has a parameter of int type to manage how many random employees must be generated.
    public void generatingRandomEmployees(int quantityOfEmployees) {
        if (employeeName.isEmpty() || employeeSurname.isEmpty()) {//condition to check if the name or surname are empty
            System.out.println("Names or surnames coudn't be loaded from the file.");//if the condition is true, the message will show up to guide the user.
            return;//Ends the method because couldn't find a name or surname 
        }
        employees.clear();//Used to clear the list before generate another employee

        for (int i = 0; i < quantityOfEmployees; i++) {//for loop to iterate and generate the employees
            String name = employeeName.get(random.nextInt(employeeName.size()));//Selecting the name from the list, randomly
            String surname = employeeSurname.get(random.nextInt(employeeSurname.size()));//Selecting the surname from the list, randomly
            
            List<Role> managers = Role.getEmployeePosition();//list calling the Role class and the method 'getemployeePosition' to be add it to the new random employee

            Role randomManager = managers.get(random.nextInt(managers.size()));//Selecting the managers roles randomly
            Position position = randomManager.getPosition();//getting the position and addressing it to the manager role.

            List<String> allowedDepartments = position.getAllowedDepartment();//List to get the departments allowed for a specific position
            Department department = null;

            List<Department> departments = DepartmentEmployee.getEmployeeDepartments();//Used to get a list of departments that will be avaiable
            for (int j = 0; j < departments.size(); j++) {//for loop used to find a allowed department for a position
                Department dep = departments.get(j);//Obtain the department in the index j in the list
                if (allowedDepartments.contains(dep.getDepartmentName())) {//condition created to check if the departmentName is in the allowedDepartmens list
                    department = dep;//If its true, the department is allowed to be used 
                    break; //ends the loop
                }
            }
            
//condition if, in case no allowed department is found, then it wil throw an exception for error handling and informing  the user that it wasnt possible to find a department for the position.
            if (department == null) {
                throw new RuntimeException("No suitable department was found for the position:" + position.getTitle());
            }

            
//Creating a new employee, defining its name, surname, a random Role(role) and the allowed department for its role            
            Employee employee = new Employee(name, surname, randomManager, department);
            employees.add(employee);// adding the new employee generated to the lsit of employees.
        }
    }
}
