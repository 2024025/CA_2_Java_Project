package CA_2;

/*This class is responsible for representing and encapsulating through its methods
all the informations related to the hospital departments.
This class will be used in other classes such as: Employee, GenerateRandomEmployee,
in the main class in various methods, eg.: 'addEmployee', 'selectDepartment', etc.
Therefore, this class will display the necessary informations about the departments
as: Finance, Cardiology, H.R, I.T, etc.
*/


public class Department {

    private String departmentName;

    public Department(String departmentName) {//constructor used to initialize the department name.
        this.departmentName = departmentName;
    }

    public String getDepartmentName() {//Getter method used to get the department name in other classes
        return departmentName;
    }

    @Override
    public String toString() {//toString() method, used to print the department name.
        return "Department: " + departmentName;
    }
}
