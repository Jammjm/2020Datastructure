package hackerrank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class A_6 {

	// Complete the isBalanced function below.
	static String isBalanced(String s) {
		String[] arr = s.split("");
		Stack<String> stack = new Stack<>();
		int addnum = 0;
		int popnum = 0;
		for (int i = 0; i < arr.length; i++) {
			String str = arr[i];

			if (str.equals("{") || str.equals("(") || str.equals("[")) {
				stack.add(str);
				addnum++;
			} else {
				if (stack.isEmpty() == false) {
					popnum++;
					String pop = stack.pop();
					switch (pop) {
					case "(":
						if (!str.equals(")"))
							return "NO";
						break;
					case "{":
						if (!str.equals("}"))
							return "NO";
						break;
					case "[":
						if (!str.equals("]"))
							return "NO";
						break;
					}
				}else return "NO";
			}
		}
		if(popnum == addnum) return "YES";
		else return "NO";
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

		int t = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int tItr = 0; tItr < t; tItr++) {
			String s = scanner.nextLine();

			String result = isBalanced(s);

			bufferedWriter.write(result);
			bufferedWriter.newLine();
		}

		bufferedWriter.close();

		scanner.close();
	}
}
