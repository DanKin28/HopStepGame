import java.util.*;

public class HopStepGame
{
    public int minCost(int[] arr, int squares)
    {
       //Base Case: Reached last or second to last square
       if(squares == 0 || squares == 1)
       {
           return arr[squares];
       }
       
       //Recursively calculate values of first and second jump
       int first = arr[squares] + minCost(arr, squares - 1);
       int second = arr[squares] + minCost(arr, squares - 2);
       
       //Return the lowest value for caluclation
       return first < second ? first : second;
    }
    
    public int minCostMemoization(int[] arr, int squares, int[] memo)
    {
        //Base Case: Reached last or second to last square
        if(squares == 0 || squares == 1)
        {
            return arr[squares];
        }
        
        //If solution is already stored, no recalculation is neccesary
        if(memo[squares] != 0)
        {
            return memo[squares];
        }
       
       //Recursively calculate values of first and second jump
       int first = arr[squares] + minCostMemoization(arr, squares - 1, memo);
       int second = arr[squares] + minCostMemoization(arr, squares - 2, memo);
       
       //Add the lowest value to the memo array and return
       memo[squares] = first < second ? first : second;
       return memo[squares];
    }
    
    public int minCostTabulation(int[] arr)
    {
       //If the length of the array is 1, return the value
       if(arr.length == 1)
       {
           return arr[0];
       }
       
       //If the length of the array is 2, return the lowest value
       if(arr.length == 2)
       {
           return arr[0] < arr[1] ? arr[0] : arr[1];
       }
       
       //Create a temp array and store the first 2 values
       int len = arr.length;
       int[] tempArr = new int[len];
       tempArr[0] = arr[0];
       tempArr[1] = arr[1];
       
       //Loop through the array and store the new values in the temp array
       for(int i = 2; i < len; i++)
       {
          //Find the smallest value and add the new sum to the temp array
          int lowestVal = tempArr[i - 1] < tempArr[i - 2] ? tempArr[i - 1] : tempArr[i - 2];
          tempArr[i] = arr[i] + lowestVal; 
       }
       
       //Return smallest value of the last 2 elements in the temp array
       return tempArr[len - 1] < tempArr[len - 2] ? tempArr[len - 1] : tempArr[len - 2];
    }
}