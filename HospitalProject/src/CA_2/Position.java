package CA_2;
/*
This class is responsible for defining the hospital positions. It will
associate a position with a existent department, eg.: Doctor, Nurse, Director
which will be linked to a department, eg.: Emergency, Cardiology, Finance, etc.

*/
import java.util.List;

public class Position {

    private String title;//Determining title as a private String
    
//Determining 'allowedDepartment' as a String List, where it will be called to store
//all departments allowed for each position. This will avoid mistakes when generating
//employees randomly, eg.: Doctors being related to the H.R department.    
    private List<String> allowedDepartment;//

//Constructor of the Class. It will be used to initialize the title and the list of allowed departments.    
    public Position(String title, List<String> allowedDepartment) {
        this.title = title;
        this.allowedDepartment = allowedDepartment;
    }

    
//Getter used to be accessed in the main class, that will need to search for the position
//title to add a new employee and check if the addition is also duplicated, through the 'isDataDuplicated' method.
    public String getTitle() {
        return title;
    }

    
//This getter method is also applied in the main class, where it will have the function of returning the list of departments
//that will be allowed for the departments that will be selected to each position    
    public List<String> getAllowedDepartment() {
        return allowedDepartment;
    }

    @Override
    public String toString() {
        return title;
    }
}
