package CA_2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Patient extends Person {

    private int age;
    private LocalDateTime admissionDate;
    private String diagnosis;
    private Department department;
    private String attendingDoctor;
    private int staydays;
    private double dailyRate;
    private LocalDateTime dischargeDate;

    public Patient(String firstName, String lastName, int age, LocalDateTime admissionDate, String diagnosis,
            Department department, String attendingDoctor, int stayday,
            double dailyRat) { //, LocalDateTime dischargeDate

        super(firstName, lastName);
        this.age = age;
        this.admissionDate = admissionDate;
        this.diagnosis = diagnosis;
        this.department = department;
        this.attendingDoctor = attendingDoctor;
        this.staydays = staydays;
        this.dailyRate = dailyRate;
        this.dischargeDate = dischargeDate;
    }

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

    public double treatmentCostCalculator() {
        return staydays * dailyRate;
    }

    public LocalDateTime DischargeDateCalculator() {
        return admissionDate.plusDays(staydays);
    }

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
                + "\nDaily Rate: € " + dailyRate
                + "\nTotal Cost: € " + treatmentCostCalculator();
    }
}
