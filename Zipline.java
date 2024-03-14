//Sahithra Kesavan
//Period 4
import java.util.Scanner;

public class Zipline {
    public static void main(String[] args) 
  {
        Scanner scanner = new Scanner(System.in); 
        int testCases = scanner.nextInt(); //Taking in the input the user provides (test cases)

        while (testCases > 0) //Going through each test case
          {
            int ziplines = scanner.nextInt(); //Taking in number of ziplines the user gives
            int ziplineLength = scanner.nextInt(); //Taking in the length of the ziplines
            int[][][] intervals = new int[ziplines][][]; //Making a 3-d array that will store the duration/period for each ziplines
            
            for (int i = 0; i < ziplines; i++) //Going through the duration/intervals for each zipline
            {
                int numIntervals = scanner.nextInt(); //Taking in the number of intervals for the current zipline
                intervals[i] = new int[numIntervals][2]; //Making an array for the current zipline
                for (int j = 0; j < numIntervals; j++) 
                {
                    intervals[i][j][0] = scanner.nextInt(); //Start position for the interval
                    intervals[i][j][1] = scanner.nextInt(); //End position for the interval
                }
            }

            boolean canZipResult = canZip(intervals, 0, 1, ziplineLength); //Checking if Vinnies can escape by calling the method that will use the given intervals and ziplines to see if it's possible to zipline
            if (canZipResult) 
            { 
                System.out.println("YES"); //Returning yes if Vinnies can escape
            } else {
                System.out.println("NO"); //Returning No if vinnes cant escape
            }

            testCases--; //Moving on to the next test case
        }
        scanner.close();
    }

    private static boolean canZip(int[][][] intervals, int currentPosition, int time, int ziplineLength) //Recursive function that will check if Vinnes can escape by using the intervals and zipline information
  { 
        if (currentPosition >= ziplineLength) 
        {
            return true; //If vinnes has gone across the whole zipline and can escape -> return true
        }

        //Goign through each zipline's intervals
        for (int[][] intervalSet : intervals) 
        {
            for (int[] interval : intervalSet) 
            {
                int start = interval[0]; //Start pos of the interval
                int end = interval[1];//End pos of the interval

                int nextPosition = currentPosition + time; //Find the next pos after moving and if the next pos is inside the current interval recurisvely check for the next positions.
                if (nextPosition >= start && nextPosition <= end) //Checking if the next pos is in the current interval
                {
                    if (canZip(intervals, nextPosition, 1, ziplineLength) ||canZip(intervals, nextPosition, 2, ziplineLength)) //Figure out if you can move with time 1 or time 2 and if you can, return true meaning that there is a escape path
                    { 
                        return true;
                    }
                }
            }
        }

        return false; //return false if there is none
    }
}