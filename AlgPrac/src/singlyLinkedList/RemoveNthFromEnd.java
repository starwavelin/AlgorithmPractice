package singlyLinkedList;

import java.util.Scanner;

/**
 * Remove the Nth node from the end of the list.
 * http://www.lintcode.com/en/problem/remove-nth-node-from-end-of-list/
 * @author Guru
 *
 */
public class RemoveNthFromEnd {
	
	public static void main(String[] args) {
		System.out.println("*** Welcome to Ben's "
				+ "Remove Nth from End of List Driver ***");
		
		LinkedList ll = new LinkedList();
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Input your integer array, \n"
				+ "leave each number by space: ");
		String[] strs = sc.nextLine().split(" ");
		int[] testArray = new int[strs.length];
		for (int i = 0; i < strs.length; i++) {
			testArray[i] = Integer.parseInt(strs[i]);
			
			ll.insertLast(testArray[i]);
		}		
		ll.displayLinkedList();
		
		System.out.print("Input an integer represent the Nth position from the end: ");
		int num = sc.nextInt();
		
		Node head = ll.getHead();
		
		System.out.print("The given linked list after remove Nth is: ");
		Node newHead = removeNthFromEnd(head, num);
		ll.displayLinkedList(newHead);
	}
	
	public static Node removeNthFromEnd(Node head, int n) {
		if (n <= 0) {
			return null;
		}
		
		Node dummy = new Node(-1);
		dummy.next = head;
		Node preDel = dummy;
		for (int i = 0; i < n; i++) {
			if (head == null) {
				return null;
			}
			head = head.next;
		}
		while (head != null) {
			preDel = preDel.next;
			head = head.next;
		}
		preDel.next = preDel.next.next;
		return dummy.next;
	}
}