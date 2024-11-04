package CA_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DataFile {
    private String[] name;
    private String[] surname;

    public String[] getName() {
        return name;
    }

    public String[] getSurname() {
        return surname;
    }
    
    
    private void readFile(String fileName){
        List<String> listName = new ArrayList<>();
        List<String> listSurname = new ArrayList<>();
        
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String lines;
            while((lines = br.readLine()) != null){
                int lastComma = lines.lastIndexOf(",");
                if(lastComma == -1){
                    System.out.println("This line couldn't be parsed: " + lines);
                    continue;
                }
                listName.add(lines.substring(0, lastComma).trim());
                listSurname.add(lines.substring(lastComma + 1).trim());
            }
            System.out.println("The file " + fileName + " was read successfully!");
            
        }catch(IOException e){
            System.out.println("This file couldn't be read!" + e.getMessage());
        }
        name = listName.toArray(new String[0]);
        surname = listSurname.toArray(new String[0]);
    }
    
    public DataFile(String fileName){
        readFile(fileName);
    }
        
}
