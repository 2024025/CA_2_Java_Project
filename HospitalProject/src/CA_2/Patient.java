package CA_2;



/*This class is responsible for defining informations regarding the people who will use the hospital services.
It will contain information such as: admission date, discharge date, diagnosis, cost of the treatment, department
that the patient will be located, etc.
This classe extends the Person class, where it will retrieve the person's first and last name.
This class will be called in HospitalApplication class 
*/

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Patient extends Person {
//Private attributes to store the informations about the Patients
    private int age;
    private LocalDateTime admissionDate;
    private String diagnosis;
    private Department department;
    private String attendingDoctor;
    private int staydays;
    private double dailyRate;
    private LocalDateTime dischargeDate;

    
//    Constructor used to initializate all the attributes
    public Patient(String firstName, String lastName, int age, LocalDateTime admissionDate, String diagnosis,
            Department department, String attendingDoctor, int staydays,
            double dailyRate) { 

        super(firstName, lastName);
        this.age = age;
        this.admissionDate = admissionDate;
        this.diagnosis = diagnosis;
        this.department = department;
        this.attendingDoctor = attendingDoctor;
        this.staydays = staydays;
        this.dailyRate = dailyRate;
        this.dischargeDate = DischargeDateCalculator();
    }

    
//    Getters, used to access the attributes in other classes.
    public int getAge() {
        return age;
    }

    public LocalDateTime getAdmissionDate() {
        return admissionDate;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public Department getDepartment() {
        return department;
    }

    public String getAttendingDoctor() {
        return attendingDoctor;
    }

    public int getStaydays() {
        return staydays;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public LocalDateTime getDischargeDate() {
        return dischargeDate;
    }

//    Method created to calculate the cost of treatment for each patient.
    public double treatmentCostCalculator() {
        return staydays * dailyRate;
    }

//    Method created to calculate the period of days, that the patient was in the hospital until be discharged.
    public LocalDateTime DischargeDateCalculator() {
        return admissionDate.plusDays(staydays);
    }

    
//    toString() method used to print the report of each patient.
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return "\nPatient: " + getFirstName() + " " + getLastname()
                + "\nAge: " + age
                + "\nCondition: " + diagnosis
                + "\nDepartment: " + department
                + "\nAttending Doctor: " + attendingDoctor
                + "\nStay Days: " + staydays
                + "\nDischarge Date: " + dischargeDate.format(formatter)
                + "\nDaily Rate: € " + String.format("%.2f", dailyRate)
                + "\nTotal Cost: € " + String.format("%.2f",treatmentCostCalculator());
    }
}
