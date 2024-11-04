package CA_2;

public class Person {

    private String firstName;
    private String lastname;

    public Person(String firstName, String lastname) {
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
        return firstName + " " + lastname;
    }

}
