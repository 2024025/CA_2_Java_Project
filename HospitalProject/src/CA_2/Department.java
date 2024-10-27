package CA_2;

import java.util.Arrays;
import java.util.List;

public class Department {

    private String departmentName;

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return departmentName;
    }

    
    public static List<Department> getDepartments(){
        return Arrays.asList(
            new Department("Cardiology"),
            new Department("Emergency"),
            new Department("Radiology"),
            new Department("Executive"),
            new Department("Finance"),
            new Department("Orthopaedics"),
            new Department("Oncology"),
            new Department("Nutrition"),
            new Department("Anaesthesia"),
            new Department("Laboratory")           
            );
    }
}
