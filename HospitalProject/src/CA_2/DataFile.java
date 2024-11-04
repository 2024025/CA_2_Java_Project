package CA_2;

/*
This class is responsible for reading data from a .txt file, where each line
will have a first name and a surname, separated by comma. When reading this file,
line by line, the data will be stored in two arrays(name and surname). These names
and surnames can be accessed in other classes due to the getter methods (getName
and getSurname)
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataFile { //Public class, accessible from others classes/packages

    private String[] name; // Declaring name as a String Array
    private String[] surname;// Declaring surname as a String Array

    public String[] getName() { //Getter method for name, let the array accessible in other classes/package
        return name;
    }

    public String[] getSurname() { //Getter method for name, let the array accessible in other classes/package
        return surname;
    }

    private void readFile(String fileName) { //Method readFile, responsible to read the file 'fileName' and update the arrays(name/surname)
        //Here I created two List, that will be used to store names and surnames temporarily
        //I choose use list, because the file size could be increased, and due this the ArraysList are more appropriated when we dont know
        // exactly the size of the array.
        List<String> listName = new ArrayList<>();
        List<String> listSurname = new ArrayList<>();

        try { //Try catch block to check and treat exceptions when reading the file.

            BufferedReader br = new BufferedReader(new FileReader(fileName)); //BufferedRead used to read the file 'fileName'
            String lines;
            while ((lines = br.readLine()) != null) { //This while block is used to read line by line, until reach the last line. When the line is null, the loop ends.
                int lastComma = lines.lastIndexOf(","); //int variable 'lastComma' used here to get the comma location , that will separate the names from surnames.
                if (lastComma == -1) { //If condition was created in case there's no comma in the read line.
                    System.out.println("This line couldn't be parsed: " + lines);//Message to inform the user that the line couldn't be processed and parsed.
                    continue;
                }
                //For each line read, the names (all words before the comma), will be added to the List
                listName.add(lines.substring(0, lastComma).trim());//Collecting the names located at the 0 index up to the last comma in the line, trim added to remove spaces before and after the content.

                //For each line read, the surnames (all words after the comma), will be added to the List
                listSurname.add(lines.substring(lastComma + 1).trim()); //Collecting the names located at after the last comma + 1, its means that will take the surname after the coma and add one extra index, reaching all the contents after the comma up to the end of the line, trim added to remove spaces before and after the content.
            }
            System.out.println("The file " + fileName + " was read successfully!"); //Messasge to show the user that the file was read correctly.

        } catch (IOException e) {//Catch bblock, used to find any exception when reading the file and show a message to the user, explaing what the error was.
            System.out.println("This file couldn't be read!" + e.getMessage());
        }

        //Transforming the lists listName and listSurname into array.
        name = listName.toArray(new String[0]);
        surname = listSurname.toArray(new String[0]);
    }

    public DataFile(String fileName) { //The DataFile constructor takes the fileName and calls the method to read the file.
        readFile(fileName);
    }

}
