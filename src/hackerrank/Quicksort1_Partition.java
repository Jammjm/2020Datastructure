package hackerrank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Quicksort1_Partition {

    // Complete the quickSort function below.
    static void quickSort(int[] arr) {
    	int pivot = arr[0];
    	ArrayList<Integer> prev = new ArrayList<>();
    	ArrayList<Integer> next = new ArrayList<>();
    	for(int i = 1; i<arr.length; i++) {
    		if(arr[i]<pivot) {
    			prev.add(arr[i]); 
    		}else {
    			next.add(arr[i]);
    		}
    	}
    	prev.add(pivot);
    	ArrayList<Integer> result = new ArrayList<>();
    	result.addAll(prev);
    	result.addAll(next);
    	
    	
    	
    	for(int n: result){
            System.out.print(n+" ");
        }
        System.out.println("");
        
    	
    	
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
    	BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        quickSort(arr);

     
    }
}
