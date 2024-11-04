package CA_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DepartmentEmployee extends DepartmentPatient {

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

    public DepartmentEmployee(String name) {
        super(name);
    }

    public static List<Department> getEmployeeDepartments() {
        List<Department> allDepartments = new ArrayList<>(DepartmentPatient.getPatientDepartments());
        allDepartments.addAll(employeeDepartments);
        return allDepartments;
    }
}
