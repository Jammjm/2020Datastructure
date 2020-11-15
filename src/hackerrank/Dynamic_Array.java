package hackerrank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'dynamicArray' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. 2D_INTEGER_ARRAY queries
     */
	

    public static List<Integer> dynamicArray(int n, List<List<Integer>> queries) {
    // Write your code here
    	int lastanswer = 0;
    	List<Integer> result = new ArrayList<>();
    	List<List<Integer>> Sol = new ArrayList<>();
    	for(int i = 0; i<n; i++) {
    		List<Integer> s = new ArrayList<>();
    		Sol.add(s);
    	}
    	
    	for(int i = 0; i<queries.size(); i++) {
    		List<Integer> temp = queries.get(i);
    		if(temp.get(0) == 1) {
    			int x = temp.get(1);
    			int y = temp.get(2);
    			int seqnum = ((x^lastanswer)%n);
    			Sol.get(seqnum).add(y);
    		}else if(temp.get(0) == 2) {
    			int x = temp.get(1);
    			int y = temp.get(2);
    			int seqnum = ((x^lastanswer)%n);
    			int seqsize = Sol.get(seqnum).size();
    			lastanswer = Sol.get(seqnum).get(y%seqsize);
    			result.add(lastanswer);
  
    		}
    	}
    	
    	
    	return  result;
    }

}

public class Dynamic_Array {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int q = Integer.parseInt(firstMultipleInput[1]);

        List<List<Integer>> queries = new ArrayList<>();

        for (int i = 0; i < q; i++) {
            String[] queriesRowTempItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            List<Integer> queriesRowItems = new ArrayList<>();

            for (int j = 0; j < 3; j++) {
                int queriesItem = Integer.parseInt(queriesRowTempItems[j]);
                queriesRowItems.add(queriesItem);
            }

            queries.add(queriesRowItems);
        }

        List<Integer> result = Result.dynamicArray(n, queries);

        for (int i = 0; i < result.size(); i++) {
            bufferedWriter.write(String.valueOf(result.get(i)));

            if (i != result.size() - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

