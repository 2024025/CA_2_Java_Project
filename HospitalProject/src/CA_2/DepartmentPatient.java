package CA_2;

import java.util.Arrays;
import java.util.List;

public class DepartmentPatient extends Department {

    private static final List<Department> patientDepartments = Arrays.asList(
            new Department("Surgery"),
            new Department("Intensive Care Unit (ICU)"),
            new Department("Critical Care Unit (CCU)"),
            new Department("General Care"),
            new Department("Emergency"),
            new Department("Cardiology"),
            new Department("Radiology and Imaging"),
            new Department("Pharmacy"),
            new Department("Orthopaedics"),
            new Department("Physiotherapy"),
            new Department("Oncology"),
            new Department("Nutrition"),
            new Department("Anaesthesia"),
            new Department("Laboratory")
    );

    public DepartmentPatient(String name) {
        super(name);
    }

    public static List<Department> getPatientDepartments() {
        return patientDepartments;
    }
}
