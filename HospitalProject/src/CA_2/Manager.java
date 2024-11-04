package CA_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Manager {

    private Position position;

    public Manager(Position position) {
        if (position == null) {
            throw new IllegalArgumentException("Manager type can not be null!");
        }
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return position.toString();
    }

    private static final List<Position> POSITIONS = Collections.unmodifiableList(Arrays.asList(
            new Position("Department Head", Arrays.asList("Quality Assurance", "Health and Safety", "Finance", "Medical Report", "Billing and Insurance", "Patient Support Services")),
            new Position("Supervisor", Arrays.asList("General Care", "Finance", "Intensive Care Unit (ICU)", "Emergency", "Pharmacy", "Laboratory", "Information Technology (I.T)", "Procurement and Supply")),
            new Position("Coordinator", Arrays.asList("Radiology and Imaging", "Finance", "Physiotherapy", "Oncology", "Laboratory", "Information Technology (I.T)", "Facility Management")),
            new Position("Director", Arrays.asList("Executive", "Finance", "Human Resources (H.R)", "Information Technology (I.T)")),
            new Position("Doctor", Arrays.asList("Cardiology", "Surgery", "Emergency", "General Care", "Intensive Care Unit (I.C.U)", "Physiotherapy", "Oncology", "Critical Care Unit (C.C.U)", "Orthopaedics", "Nutrition", "Anaesthesia")),
            new Position("Nurse", Arrays.asList("General Care", "Intensive Care Unit (I.C.U)", "Critical Care Unit (C.C.U)", "Emergency", "Pharmacy", "Oncology", "Nutrition")),
            new Position("Nurse Technician", Arrays.asList("Pharmacy", "Oncology", "Laboratory", "General Care", "Intensive Care Unit (I.C.U)", "Critical Care Unit (C.C.U)", "Emergency", "Nutrition")),
            new Position("Administrative Assistant", Arrays.asList("Finance", "Medical Records", "Billing and Insurance", "Human Resources (H.R)", "Procurement and Supply", "Information Technology (I.T)", "Facility Management", "Patient Support Services"))
    ));

    public static List<Manager> getEmployeePosition() {
        List<Manager> managers = new ArrayList<>();
        for (Position pos : POSITIONS) {
            managers.add(new Manager(pos));
        }
        return managers;
    }

}
