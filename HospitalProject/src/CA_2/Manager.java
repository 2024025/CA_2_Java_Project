package CA_2;

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
}
