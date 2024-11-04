/*
This enum class will be used to generate the program menus.
This class is divided into 3 different enums, the first (MainMenu) will show the program's main menu, 
giving the user the chance to choose which sector of the program will be used (Employee or Patient).

The second enum was generated to show a detailed menu, if the user chooses option 2 of the MainMenu. 
In it, a list of 20 employees can be viewed, sorted by list, searched for an employee from the list, 
added new employees, generated a random employee, exported a report with the data of the employees 
from the generated list and finally, exited the menu, returning to the initial menu (MainMenu).

Lastly, the PatientMenu enum. Where it can be accessed by the user when choosing 
option 2 of the MainMenu and will be used to add new patients, sort the list of manually entered 
patients, check the amount to be received for the treatment of this patient, export a report
with the entered patients and finally exit the menu, returning to the MainMenu.

*/
package CA_2;

public enum MainMenu {
    EMPLOYEE_INFORMATIONS,
    PATIENT_INFORMATIONS,
    EXIT_SYSTEM;
}

enum EmployeeMenu{
    SORTING_EMPLOYEE_LIST,
    SEARCHING_EMPLOYEE,
    ADDING_NEW_EMPLOYEE,
    EMPLOYEE_RANDOM_GENERATION,
    VIEW_MONTHLY_EXPENSES,
    EXPORT_EMPLOYEES_REPORT,
    EXIT_MENU;
}

enum PatientMenu{
    ADD_NEW_PATIENT,
    SORT_PATIENT_LIST,
    VIEW_TREATMENT_INCOME,
    EXPORT_PATIENT_REPORT,
    EXIT_MENU;
}
