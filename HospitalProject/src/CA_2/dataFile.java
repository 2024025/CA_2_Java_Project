package CA_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DataFile {

    private String[] names;
    private String[] surnames;

    public String[] getNames() {
        return names;
    }

    public String[] getSurnames() {
        return surnames;
    }

    
//    Method responsible to read the file.
    private void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String lines;
            int index = 0;

            while ((lines = br.readLine()) != null) {
                int lastComma = lines.lastIndexOf(",");
                if (lastComma == -1) {
                    System.out.println("This line couldn't be parsed: " + lines);
                    continue;
                }
                names[index] = lines.substring(0, lastComma).trim();
                surnames[index] = lines.substring(lastComma + 1).trim();
                index++;
            }
            br.close();
        } catch (IOException e) {
            System.out.println("This file couldn't be read: " + e.getMessage());
        }
    }

//Constructor resposible to count the lines and initiate the arrays   
    public DataFile(String fileName) {
        int countLines = linesInFile(fileName);
        if (countLines == 0) {
            System.out.println("The file is empty!");
            return;
        }
        names = new String[countLines];
        surnames = new String[countLines];
        readFile(fileName);
    }

    
//Method to count line by line in the file    
    private int linesInFile(String fileName) {
        int lineCount = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            while (br.readLine() != null) {
                lineCount++;
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error! Lines couldn't be counted in this file: " + e.getMessage());
        }
        return lineCount;
    }
    
    
//Method responsible to write in a new file the names, surnames in a new file.    
    public void writeEmployeeReport(String fileName, List<Employee> employees){
        try{
            BufferedWriter wr = new BufferedWriter(new FileWriter(fileName, true));
            for(Employee employee: employees){
                wr.write(employee.toString());
                wr.newLine();
            }
            wr.close();
            System.out.println("Employee Report Successfully Created in: " +fileName);
        }catch(IOException e){
            System.out.println("Error when writing the file: " + e.getMessage());
        }
    }
}
