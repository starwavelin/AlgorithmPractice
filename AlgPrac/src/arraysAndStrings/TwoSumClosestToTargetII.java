package arraysAndStrings;

import java.util.Scanner;

/**
 * Given a sorted integer array, 
 * find the indices of the two numbers whose sum is the closest one
 * to the target integer. If there are multiple solutions, 
 * just returning the first pair of indices you found is fine.
 * 
 * O(n) solution
 * 
 * @author Benjamin Lin
 *
 */

public class TwoSumClosestToTargetII {
	
	// input array: 2, 4, 8, 10, 12
	// target value: 17
	// result: indices 1, 4 or 2, 3; return one of them is sufficient.
	
	// input array: 2, 4, 8, 10, 12, 20
	// target value: 17
	// result: indices 1, 4 or 2, 3; return one of them is sufficient.

	public static int[] twoSumClosest(int[] input, int target) {
		if (input.length < 2 || input == null) {
			return null;
		}
		
		if (input.length == 2) {
			return new int[]{0, 1};
		}
		
		int start = 0;
		int end = input.length - 1;
		
		int diff = Integer.MAX_VALUE;
		int[] ret = new int[2];
		
		 			
		while (start + 1 <= end) {
			if (input[start] + input[end] < target) {
				if (Math.abs(input[start] + input[end] - target) < diff) {
					diff = Math.abs(input[start] + input[end] - target);
					ret[0] = start;
					ret[1] = end;					
				} 
				start++;
			}	else if (input[start] + input[end] > target) {
				if (Math.abs(input[start] + input[end] - target) < diff) {
					diff = Math.abs(input[start] + input[end] - target);
					ret[0] = start;
					ret[1] = end;					
				} 
				end--;
			}	else { // when input[start] + input[end] == target
				ret[0] = start; 
				ret[1] = end;
				return ret;
			}
		}
		
		return ret;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("*** Welcome to Ben's TwoSumClosestToTarget Test ***");
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Input your integer sorted array, \n" +
				"leave each number by space: ");
		String[] strs = sc.nextLine().split(" ");
		int[] testArray = new int[strs.length];
		for (int i = 0; i < strs.length; i++) {
			testArray[i] = Integer.parseInt(strs[i]);
		}
		
		System.out.print("Give your target number: ");
		int target = sc.nextInt();
		int[] result = twoSumClosest(testArray, target);
		if (result != null) {
			System.out.print("The two numbers that are closest to the target " 
				+ target + " have indices " + result[0] + " and " + result[1]);
		} else {
			System.out.print("Please check your input array!");
		}
	}
}