package CA_2;

/*
This class will be responsible for searching for a person through the user's input
with the person's first and last name to be located in the list. For this to happen,
I needed to create two methods in this class:

1 - binarySearchByName: This method is used to perform the search trhought the binary
search algorithm, in a recursive format, keeping the code more organized and simple.
Binary Search works by dividing the list of person object in two parts in every iteration,
when executed, it will search for the index of a person in the list, where the full name
will be provided by the user when inserting the input.

2- searchByName: This method was used only to perform a processing of the String sent by
the user throught the input. When entering the name to be searched, this method will process the string,
removing extras spaces and transforming letters into lowercase, to make the search more accurate.

The choice for a binary search algorithm was due to the efficiency of this type of algorithm,
as it has a time complexity classified as O(log n), that is, its execution time increases
logarithmically according to the increase in elements, which becomes much more efficient than
other search algorithms, such as the Linear.

Because it has the possibility of increasing the size of the list, with possible expansion of
the hospital, and consequently an increase in the database, the binary search algorithm becomes
more scalable than the Liner.

Reference:
GeeksforGeeks (2019). Binary Search - GeeksforGeeks. [online] GeeksforGeeks. 
Available at: https://www.geeksforgeeks.org/binary-search/.
*/

import java.util.List;

public class Searcher { //Defining Searcher as a public class, allowing it to be accessed by other classes.

    
    // Method created to search the person in the list, with the parameters people, name and the indexes left and right.
    public int binarySearchByName(List<? extends Person> person, String name, int left, int right) { 
    
    //In case the left index is greater than the right index, the method will return an int -1. It is the condition to end
    //the search and indicate to the user that the person wasn't found.
        if (left > right) {
            return -1;
        }
        
    //This line determines the middle index, performing the division between the left and right parts. To prevent the overflow error
    //at the time of division, when adding left and right, I decided to use the formula-> left + (right - left) /2, this way if the
    //list is increased exponencially in the future, there will be no problems in finding the middle, thus avoiding the risk of integer overflow.
    //Bharat, Stack Overflow. “Calculating Mid in Binary Search.” Stack Overflow, 18 July 2011,
    //stackoverflow.com/questions/6735259/calculating-mid-in-binary-search.
        int middle = left + (right - left) / 2;
        
    //Getting the first and lastname to build a 'fullName', treating this String using trim to remove extra spaces and lowercase.
        String fullName = (person.get(middle).getFirstName() + " " + person.get(middle).getLastname()).toLowerCase().trim();
        
    //If the fullName is equal to the name entered by the user, it will return the middle. Its means that the person was found!
        if (fullName.equalsIgnoreCase(name)) {
            return middle;
            
    //Condition else if used to compare the fullName with the name entered by the user, if the fullName is lower than the name being searched
    //The function will return the method, to run another searcher but starting from the right side and discarding the left part of the list.
    //And middle will be adjusted to the middle + 1, moving one spot to the right.
        } else if (fullName.compareTo(name) < 0) {
            return binarySearchByName(person, name, middle + 1, right);
            
    //In this else condition, it will run exactly the oposite way of the else if, the search will restart discarding the right part of the list.
    //And middle will be adjusted to the middle - 1, moving one spot to the left.
        } else {
            return binarySearchByName(person, name, left, middle - 1);
        }
    }
    
    
    //This method will be used to capture the input from the user and will use the binarySearchByName to locate the person being searched
    //This method is public and will be used in the main class.
    public void searchByName(List<? extends Person> person, String name){
        String inputName = name.trim().toLowerCase(); //Treating the String, removing spaces and keep the names on lowercase
        int index = binarySearchByName(person, inputName, 0, person.size() - 1); //Calling the binarySearchByName Method
        if(index >= 0){ //this condition is used to determine if the algorithm found the person, if the index >= 0 its means the person was found.
            System.out.println(person.get(index)); //Printing the person details.
        }else{// This conditions is used to show the message to the user that the person wasnt found on the list.
            System.out.println("Person wasn't found!");
        }
    }

}
