package CA_2;

import java.util.List;

public class Position {

    private String title;
    private List<String> allowedDepartment;

    public Position(String title, List<String> allowedDepartment) {
        this.title = title;
        this.allowedDepartment = allowedDepartment;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getAllowedDepartment() {
        return allowedDepartment;
    }

    @Override
    public String toString() {
        return title;
    }
}
