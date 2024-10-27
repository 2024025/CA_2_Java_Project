package CA_2;

import java.util.Arrays;
import java.util.List;

public class Manager {

    private String managerType;

    public Manager(String managerType) {
        this.managerType = managerType;
    }

    public String getManagerType() {
        return managerType;
    }

    public void setManagerType(String managerType) {
        this.managerType = managerType;
    }

    @Override
    public String toString() {
        return managerType;
    }
    
    
    public static List<Manager> getEmployeePosition(){
        return Arrays.asList(
                new Manager("Department Head"),
                new Manager("Nursing Supervisor"),
                new Manager("Research Coordinator"),
                new Manager("Director"),
                new Manager("Nurse"),
                new Manager("Assistant")
        );
    }
}
