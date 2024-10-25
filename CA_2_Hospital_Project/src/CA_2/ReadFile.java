package CA_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {

    public static NamesOnFile readFile(String fileName) {
        int countLines = sizeOfFile(fileName);
        if (countLines == 0) {
            System.out.println("The file is Empty");;
            return null;
        }

        String[] employeeName = new String[countLines];
        String[] employeeSurname = new String[countLines];

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String lines;
            int index = 0;
            while ((lines = br.readLine()) != null) {
                int lastCommaIndex = lines.lastIndexOf(",");
                if (lastCommaIndex == -1) {
                    System.out.println("This line couldn't be parsed: " + lines);
                    continue;
                }

                String name = lines.substring(0, lastCommaIndex).trim();
                String surname = lines.substring(lastCommaIndex + 1).trim();

                employeeName[index] = name;
                employeeSurname[index] = surname;
                index++;
            }
            br.close();

        } catch (IOException e) {
            System.out.println("Error: Invalid Name/Surname" + e.getMessage());
        }
        return new NamesOnFile(employeeName, employeeSurname);
    }

    private static int sizeOfFile(String fileName) {
        int countingLines = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            while (br.readLine() != null) {
                countingLines++;
            }
        } catch (IOException e) {
            System.out.println("Error: The line counts couldn't be processed!");
        }
        return countingLines;
    }
}
