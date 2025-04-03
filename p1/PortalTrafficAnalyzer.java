package p1; // do not delete; you may get a 0 if you do so

/*
Speedups
n = 1000, Avg. speedup: 47X
n = 2000, Avg. speedup: 456X
n = 3000, Avg. speedup: 1177X
n = 4000, Avg. speedup: 1629X
n = 5000, Avg. speedup: 1589X
n = 6000, Avg. speedup: 2065X
n = 7000, Avg. speedup: 2271X
n = 8000, Avg. speedup: 2565X
n = 9000, Avg. speedup: 2443X
n = 10000, Avg. speedup: 3106X

Process finished with exit code 0

 */


public class PortalTrafficAnalyzer {
    // A nested class for handling the results returned by the implementations
	// Do not touch this class. 
    public static class Result {
        public final int sum, leftIndex, rightIndex;

        public Result(int first, int second, int third){
            this.sum = first;
            this.leftIndex = second;
            this.rightIndex = third;
        }

        public String toString() {
            return "<" + sum + ", " + leftIndex + ", " + rightIndex + ">";
        }
    }
	
	// Returns the maximum sum, left index of the max. sub array, and the right index of the sub array
    public static Result enCubedImplementation(int[] A){
        int maxSum = 0, leftIndex = 0, rightIndex = 0; // dummy values; feel free to change the values

        for(int i = 0; i < A.length-1; i++) {
            for (int j = i + 1; j < A.length; j++) {
                int subSetSum = 0;
                for (int k = i; k <= j; k++) {
                    subSetSum += A[k];
                }

                if (subSetSum > maxSum) {
                    maxSum = subSetSum;
                    leftIndex = i;
                    rightIndex = j-1;
                }

            }
        }
        if(maxSum<0)
            maxSum = 0;
        return new Result(maxSum,leftIndex,rightIndex);
    }

	// Returns the maximum sum, left index of the max. subarray, and the right index of the subarray
    public static Result enSquaredImplementation(int[] A){
        int maxSum = 0, leftIndex = 0, rightIndex = 0; // dummy values; feel free to change the values
        //arrays.sort runs in nlogn
        //a double loop can be implemented to achieve n^2
        //if checkers necessary
        for(int i = 0;i < A.length;i++){
            int runSum = 0;
            for(int j = i ; j < A.length; j++) {
                runSum += A[j];

                if (runSum > maxSum) {
                    maxSum = runSum;
                    leftIndex = i;
                    rightIndex = j;
                }
            }
        }
        if(maxSum<0)
            maxSum = 0;

       
        return new Result(maxSum,leftIndex,rightIndex);
    }
}
