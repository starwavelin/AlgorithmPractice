package dataStructure;

import java.util.HashMap;
import java.util.Map;
/***************************************************************************
* Problem No. : 146
* Problem Name: LRU Cache
* Problem URL : https://leetcode.com/problems/lru-cache/description/
* Date        : Jan 31 2018
* Author      : Xian Lin
* Notes       : 
* 	Scenario: 
* 		Say I have a Least Recently Used Cache of capacity 5, and I already have the following 
* 		map.put(1, 10);
* 		map.put(2, 20);
* 		map.put(3, 30);
* 		map.put(4, 40);
* 		map.put(5, 50);
* 		cache is full now.
* 		Then I call
* 		map.get(3); // then <3, 30> is the recently used cache and <1, 10> becomes the least recently used cache
* 					// <3, 30> should be put to the end of the list
* 		map.put(6, 65); // should evict <1, 10> and append <6, 65> to the end.
* 	
* 	Assumption:
* 		For the get(int key) method, if the key doesn't exist, return Integer.MIN_VALUE;
* 	Input: 
* 	Output: 
* 
* 
* 	Data Structure and Alg:
* 		HashMap with LinkedList Node. 
* 		HashMap stores <key, value> pairs where the value would be wrapped in a node.
* 		Doubly Linked List keeps the insertion order and provides easy removal.
* 			Just used cache (recently used) should be removed from its current position and append to the end
* 			Head always stores the least recently used cache and when evicting is needed, remove head.
* 		We also used placeholder dummyHead and dummyTail, so the head and tail mentioned above are the actual head / tail of the cache data.
* 		
* 		
* Complexity  : 
* 	Time Complexity: get O(1); put O(1)
* 	Space Complexity: O(n) n--the capacity of the cache
* 
* meta        : tag-data-structure, tag-hash, tag-linked-list
***************************************************************************/
public class LruCache {

	class Node {
		int val;
		Node prev;
		Node next;
		public Node (int val) {
			this.val = val;
		}
	}
	
	private int capacity;
	private Node dummyHead;
	private Node dummyTail;
	private Map<Integer, Node> map;
	
	public LruCache(int capacity) {
		this.capacity = capacity;
		dummyHead = new Node(-1);
		dummyTail = new Node(-1);
		dummyHead.next = dummyTail;
		dummyTail.prev = dummyHead;
		map = new HashMap<>();
	}
	
	/**
	 * Note: after calling a get(), such node becomes a recently used one so 
	 * it needs to be removed from its current position in the linked list and append to the end.
	 */
	public int get(int key) {
		if (!map.containsKey(key)) {
			return Integer.MIN_VALUE;
		}
		Node cur = map.get(key);
		remove(cur);
		append(cur);
		return cur.val;
	}
	
	
	public void put(int key, int value) {
		/* Cases:
		 * 1. key already exists, update value
		 * 2. a new key
		 * 	2.1 capacity is 0, remove head then append
		 * 	2.2 capacity is greater than 0, just append
		 * Avoid book keeping, smart way to tracking capacity int cap = capacity - map.size(); 
		 * 
		 * */
		if (map.containsKey(key) ) {
			Node cur = map.get(key);
			remove(cur);
			append(cur);
			cur.val = value;
		} else {
			if (capacity - map.size() == 0) {
				remove(dummyHead.next);
			}
			Node node = new Node(value);
			map.put(key, node);
			append(node);
		}
		
	}
	
	private void remove(Node node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
	}
	
	private void append(Node node) {
		node.prev = dummyTail.prev;
		node.next = dummyTail;
		dummyTail.prev.next = node;
		dummyTail.prev = node;
	}
	
}