package CA_2;


import java.util.Scanner;

public class HospitalProject {
  
    private Scanner myKb = new Scanner(System.in);         
    
    private Manager[] manager = {
        new Manager("Director"),
        new Manager("Operations Manager"),
        new Manager("Supervisor"),
        new Manager("Department Head"),
        new Manager("Research Coordinator"),
        new Manager("Ward Supervisor"),
        new Manager("Doctor"),
        new Manager("Surgeon"),
        new Manager("General Practitioner"),
        new Manager("Nurse"),
        new Manager("Nursing Assistant"),
        new Manager("Medical Technician"),
    };
    
    private Department[] department = {
        new Department("Executive"),
        new Department("Finances"),
        new Department("Critical Care Unit - CTI"),
        new Department("Intensive Care Unit - ICU"),
        new Department("Emergency"),
        new Department("Primary Care"),
        new Department("Oncology"),
        new Department("Laboratory"),
        new Department("Cardiology"),
        new Department("Radiology"),
        new Department("Anaesthetist"),
        new Department("Surgery"),
        new Department("Orthopaedic"),
    };

   
}
