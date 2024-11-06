package CA_2;

/*This class is responsible for representing the administrative departments in the hospital,
where the employees will be located. This class extends the DepartmentPatient class, thus
making it a broad and complete class, with all departments into administrative and clinical.
I will be able then to have a better organization of the system, when I link an employee to a
department, without running the risk of randomly creating an employee, who will take over a clinical
department, for example: A doctor would not be likely to work in Human Resource Dpt.
This class will be called in the HospitalApplication class.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DepartmentEmployee extends DepartmentPatient {
//Constant list of departments for administratives employees

    private static final List<Department> employeeDepartments = Arrays.asList(
            new Department("Executive"),
            new Department("Finance"),
            new Department("Human Resources (H.R)"),
            new Department("Procurement and Supply"),
            new Department("Information Technology (I.T)"),
            new Department("Patient Support Services"),
            new Department("Quality Assurance"),
            new Department("Health and Safety"),
            new Department("Facility Management"),
            new Department("Medical Records"),
            new Department("Billing and Insurance")
    );

    public DepartmentEmployee(String name) {//Constructor created to call the superclass constructor DepartmentPatient
        super(name);
    }

//    Method creted to combine the list of departments of patients and employees
    public static List<Department> getEmployeeDepartments() {
        List<Department> allDepartments = new ArrayList<>(DepartmentPatient.getPatientDepartments());//creating a new list that handle the patient Departments
        allDepartments.addAll(employeeDepartments);//Adding the list of employee departments 
        return allDepartments; //returning the full list, combined between both departments.
    }
}
