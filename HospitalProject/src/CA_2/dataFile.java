package CA_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class dataFile {

    private String[] names;
    private String[] surnames;

    public String[] getNames() {
        return names;
    }

    public String[] getSurnames() {
        return surnames;
    }

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

    public dataFile(String fileName) {
        int countLines = linesInFile(fileName);
        if (countLines == 0) {
            System.out.println("The file is empty!");
            return;
        }
        names = new String[countLines];
        surnames = new String[countLines];
        readFile(fileName);
    }

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

}
