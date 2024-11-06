package CA_2;

/*
this class is an inheritance from the Department class, being an extension of it.
This class is responsible for determining the departments which hospital patients
can be treated, such as: ICU, Surgery, Laboratory, etc.
This class has a static list 'patientDepartments' where will display all the departments
in the hospital and has a method 'getPatientDepartments' returning the list, and being
accessible in other classes, as example: GenerateRandomemployee, main class HospitalApplication.
*/


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

    public DepartmentPatient(String name) {//Constructor used to call the superclass Department, and get the department name.
        super(name);
    }

    public static List<Department> getPatientDepartments() {
        return patientDepartments;
    }
}
