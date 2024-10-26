package CA_2;

public enum Menu {
    ADDING_NEW_EMPLOYEE("1 - ADD A NEW EMPLOYEE"),
    EMPLOYEE_RANDOM_GENERATION("2 - GENERATE A RANDOM EMPLOYEE"),
    SEARCHING_EMPLOYEE("3 - SEARCH AN EMPLOYEE"),
    SORTING_EMPLOYEE_LIST("4 - SORT THE EMPLOYEE LIST"),
    EXPORT_EMPLOYEES_REPORT("5 - EXPORT THE EMPLOYEE REPORT"),
    EXIT_MENU("6 - EXIT THE PROGRAM");
    
    private final String showMenu;

    private Menu(String showMenu) {
        this.showMenu = showMenu;
    }

    public String getShowMenu() {
        return showMenu;
    }         
}
