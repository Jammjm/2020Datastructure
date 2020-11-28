package hackerrank;

import java.io.*;
import java.util.*;

public class Computing_the_GCD {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();

		if (a > b)
			a = a - b;

		int res = GCD(a, b);
		System.out.println(res);

	}

	public static int GCD(int a, int b) {

		int q = b / a;
		int r = b % a;

		if (r == 0)
			return a;
		else {
			return GCD(r, a);
		}
	}

}
