package CA_2;

/*This Role class is responsible for associating a position or role to a specific sector
of the company. This class will be of great importance in this program because it will prevent
that when generating an employee randomly, this employee will be related to a non-corresponding
sector, for example: Doctor in the It sector, or Nurse in the Human Resources.
This class will be used in other classes as Employee, GenerateRandomEmployee and Main Class.
*/


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Role {

    private Position position;

    public Role(Position position) {//class constructor, which will be used to initialize position
        
//Condition used to check if position is null, if this condition is true, will throw an exception to make 
//sure that always will have an valid position for a Role        
        if (position == null) {
            throw new IllegalArgumentException("Manager type can not be null!");
        }
        this.position = position;
    }

    public Position getPosition() {//Getter method used to be accessible in other classes
        return position;
    }

    @Override
    public String toString() {
        return position.toString();
    }

//This list is constant and unmodifiable, being immutable to protect and avoid changes about the relation between roles and departments.
//It will represent all avaiable managers type in this hospital and will link it to their respective departments
//It is used when the user is adding a new employee, and a new menu will show up, to choose a position and a department.    
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

    
/*This metodh will be responsible for creating a list of Role objects, where each one will be associated with a position
    A list called 'managers' will be created initially and then a for each loop will be used to 'run' each position in the 'POSITIONS'
    list. After this, the Role Object with each existing position in the 'POSITIONS' list will be added to the managers list.
    Finally, the new managers list will be returned containing all the roles and their positions.
*/    
    public static List<Role> getEmployeePosition() {
        List<Role> managers = new ArrayList<>();
        for (Position pos : POSITIONS) {
            managers.add(new Role(pos));
        }
        return managers;
    }

}
