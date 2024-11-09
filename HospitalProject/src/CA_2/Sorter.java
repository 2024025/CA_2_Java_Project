package CA_2;

/*
This class is responsible for sorting a list of people (employees) in ascending order.
To do this, this class will use the MergeSort Algorithm. This algorithm works by dividing
the list into sublists until the individuality of these sublists is reached. After these
subdivisions, the merge is done again, ordering item by item, placing them in their proper
positions, so that the ascending order is maintained. Due to this process, this algorithm receives
the characteristic of 'divide and conquer', that is, it performs several divisions and solves
them one by one unitl it achieves the final objective, which is to recombine all the sublists
to sort the list as a whole. I chose to use this algorithm because it has a time complexity of
O(n log n), and is extremely efficient, and for the hospital project where over the years the
amount of information to be stored in the database will grow exponentially, the Merge algorithm 
will continue to be efficient because it can work efficiently even with large amount of elements.
 */
import java.util.ArrayList;
import java.util.List;

public class Sorter { //Declared as public to be acessible in other classes

    //Declaring mergeSortList as a public method. Method used to sort the list, with the parameters people, name and the indexes left and right.
    public void mergeSortList(List<? extends Person> person, int left, int right) {

        //Conditional if: If the left side is lower than the right side, its mean if this condition is true
        //There are more than one element in the subList. Then the there are more elements to be divided.
        if (left < right) {
            int middle = (left + right) / 2; //Getting the middle of the list to divide it in two.
            mergeSortList(person, left, middle); //Recursive call responsible to divide the list from left part up to middle.
            mergeSortList(person, middle + 1, right);//Recursive call responsible to divide the list from middle +1 to the right

            //After dividing the list into two halves, now the method called is mergeSortedSubLists, that will be used to merge the sorted subLists
            mergeSortedSubLists(person, left, middle, right);
        }
    }

    
    //Method created to merge/combine two subLists, allowing the algorithm to complete its function to sort the List
        private void mergeSortedSubLists(List<? extends Person> list, int left, int middle, int right) {
        
        //A new variable leftlist that will be used to store a Person list from the left index up to the middle index.
        List<Person> leftList = new ArrayList<>(list.subList(left, middle + 1));

        //A new variable rightList that will be used to store a Person list from the middle index up to the right index.
        List<Person> rightList = new ArrayList<>(list.subList(middle + 1, right + 1));
        
        int i = 0; //index that will go through the leftList
        int j = 0;//index that will go through the rightList
        int k = left;// index that will be used to place the elements back to the original list.

        //Loop used to keep running until there is any element in both sublists, it is, while there's index i lower than the size of
        //leftList, and the index j still lower than the size of rightList, the loop will be active.
        while (i < leftList.size() && j < rightList.size()) {
            //Concatenating the first and lastname to create the fullName
            String fullNameLeft = (leftList.get(i).getFirstName() + " " + leftList.get(i).getLastname()).toLowerCase();
            String fullNameRight = (rightList.get(j).getFirstName() + " " + rightList.get(j).getLastname()).toLowerCase();

            //Condition used to compare the fullName from the left part with the fullName in the right part
            //In this case, if the fullnameLeft, compared with the fullNameRight, is lower or equal to zero, this name will be placed
            //first in the final list.
            if (fullNameLeft.compareTo(fullNameRight) <= 0) {
                ((List<Person>) list).set(k, leftList.get(i));//Adding the leftList element back to the list in the 'k' index position.
                i++; //move to the next element on the leftList, to check it again.
            
            //If the if condition is false, then the rightList is added to the list and the index j is incremented.
            } else {
                ((List<Person>) list).set(k, rightList.get(j));
                j++;
            }
            k++; //this index is used to move to the next position in the final and sorted list.
        }
        
        //Loop used to add remaing elements from leftList to the original list.
        while (i < leftList.size()) {
            ((List<Person>) list).set(k++, leftList.get(i++));//Adds the current element to the leftList and increments i and k
        }
        //Loop used to add remaing elements from the rightList to the original list
        while (j < rightList.size()) {
            ((List<Person>) list).set(k++, rightList.get(j++));//Adds the current element to the rightList and increments i and k
        }
    }
}
