package CA_2;

public class Manager {

    private String managerPosition;

    public Manager(String managerPosition) {
        this.managerPosition = managerPosition;
    }

    public String getManagerPosition() {
        return managerPosition;
    }

    @Override
    public String toString() {
        return managerPosition;
    }
}
