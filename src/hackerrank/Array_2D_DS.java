package hackerrank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Array_2D_DS {

    // Complete the hourglassSum function below.
    static int hourglassSum(int[][] arr) {

    	int max = 0;
    	int testcase = 0;
    	for(int row_idx = 0; row_idx < 4; row_idx++) {
    		for(int col_idx = 0; col_idx < 4; col_idx++) {
    			int sum = arr[row_idx][col_idx] + arr[row_idx][col_idx+1] + arr[row_idx][col_idx+2] +
    					arr[row_idx+2][col_idx] + arr[row_idx+2][col_idx+1] + arr[row_idx+2][col_idx+2] + 
    					arr[row_idx+1][col_idx+1];
    			
    			if (testcase == 0) max = sum;
    			else if(sum>max) max = sum;
    			testcase ++;
    		}
    	}
    	return max;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int[][] arr = new int[6][6];

        for (int i = 0; i < 6; i++) {
            String[] arrRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 6; j++) {
                int arrItem = Integer.parseInt(arrRowItems[j]);
                arr[i][j] = arrItem;
            }
        }

        int result = hourglassSum(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

