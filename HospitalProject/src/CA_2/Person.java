package CA_2;

/*This class is responsible for defining attributes and methods for any other class
that needs to add a person to this program. It is the Parent class and will be extended
to the subclasses Patients and Employees.
The idea behind creating this class is to avoid duplication and redundancy in the program
classes.
The Person class can be accessed by other classes through the getter methods(firstName and lastName).
*/

public class Person {

    private String firstName;
    private String lastname;

    public Person(String firstName, String lastname) {// constructor used to initialize firstName and lastName
        this.firstName = firstName;
        this.lastname = lastname;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastname() {
        return lastname;
    }

    @Override
    public String toString() {
        return firstName + " " + lastname;//toString() method used to print the firstName and lastName
    }

}
