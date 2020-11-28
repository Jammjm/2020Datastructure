package hackerrank;

import java.util.*;

public class Queue_using_Two_Stacks {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Queue<Integer> q = new LinkedList<>();

		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();

		for (int i = 0; i < num; i++) {
			int order = sc.nextInt();
			switch (order) {
			case 1:
				int input = sc.nextInt();
				q.add(input);
				break;
			case 2:
				q.poll();
				break;
			case 3:
				System.out.println(q.peek());
				break;
			}

		}

	}

}
