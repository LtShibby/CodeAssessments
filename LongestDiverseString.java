package com.sk;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class LongestDiverseString {

	/**
	 * Nested class Node used to store number of times each letter needs to appear
	 */
	static class Node {
		int val;
		char letter;

		public Node(int val, char c) {
			this.val = val;
			this.letter = c;
		}
	}

	public static void main(String[] args) {

		/**
		 * Set number of times each character A, B, and C should appear respectively.
		 */
		int A = 11, B = -8, C = 11;

		/**
		 * Check that the number of times each should appear is not negative or null.
		 */
		if (A < 0) {
			A = 0;
		}
		if (B < 0) {
			B = 0;
		}
		if (C < 0) {
			C = 0;
		}

		/**
		 * Put values of the characters and how many times each needs to appear into a
		 * HashMap to input as a parameter in the helper function.
		 * 
		 */
		Map<Character, Integer> map = new HashMap<>();
		map.put('a', A);
		map.put('b', B);
		map.put('c', C);

		/**
		 * Call helper function to return longest diverse string possible with the given
		 * parameters.
		 */
		System.out.println(findString(map));
	}

	/**
	 * helper method used to find the longest possible diverse string with the given
	 * parameters stored in map
	 * 
	 * @param map
	 * @return
	 */
	public static String findString(Map<Character, Integer> map) {

		/**
		 * Create a new PriorityQueue of Node objects
		 */
		Queue<Node> q = new PriorityQueue<>(new Comparator<Node>() {

			/**
			 * Override the compare method in Comparator to dictate that Priority in the
			 * PriorityQueue is based on which has the highest value in Node.val
			 */
			@Override
			public int compare(Node a, Node b) {
				return b.val - a.val;
			}
		});

		/**
		 * For each key/value set in the passed HashMap object, create a Node object
		 * representing the letter and value where value is the maximum number of times
		 * it needs to be in the returned string. Add to the PriorityQueue if the
		 * Node.val is not 0.
		 * 
		 * Any objects offered to the PriorityQueue will be sorted by Node.val
		 * descending order.
		 */
		for (Character l : map.keySet()) {
			if (map.get(l) != 0) {
				Node n = new Node(map.get(l), l);
				q.offer(n);
			}
		}

		/**
		 * Create a StringBuilder to create the string throughout the loop.
		 */
		StringBuilder res = new StringBuilder();

		/**
		 * While there are still objects in the PriorityQueue
		 */
		while (!q.isEmpty()) {

			/**
			 * Retrieve the head element of the PriorityQueue
			 */
			Node first = q.poll();

			/**
			 * The current length of the String being build is not 0 
			 * and 
			 * The last character in the string is equal to current Node.letter
			 */
			if (res.length() != 0 && res.charAt(res.length() - 1) == first.letter) {

				/**
				 * If the PriorityQueue is empty the string is as long as it can get.
				 * 
				 * return empty String as answer
				 */
				if (q.isEmpty()) {
					return res.toString();
				}
				/**
				 * -The PriorityQueue is not empty. 
				 * 	-Pull the head of PriorityQueue to a second Node Object. 
				 * 	-Add a letter from this node object once.
				 * 	-Offer the second Node back to the PriorityQueue
				 * 	-Offer the first Node back to the PriorityQueue
				 *	
				 *	The Nodes will be automatically sorted in descending order of Node.val
				 */
				else {
					Node second = q.poll();
					res.append(second.letter);
					second.val--;
					if (second.val != 0) {
						q.offer(second);
					}
					q.offer(first);
				}
			} 
			/**
			 * The String being built has either
			 * 	0 characters in it presently
			 * or
			 * 	the last character in the string is different from the current Node.letter
			 * 
			 * Set limit to be the smaller of either 2 or the number in Node.val
			 * 
			 * Append the character in Node.letter to the string based on the determined limit (either 1 or 2)
			 * 
			 * Offer the Node back to the PriorityQueue if Node.val is not 0
			 */
			else {
				int limit = Math.min(2, first.val);
				for (int i = 0; i < limit; i++) {
					res.append(first.letter);
					first.val--;
				}
				if (first.val != 0) {
					q.offer(first);
				}
			}
		}
		/**
		 * End of the method.  Return answer String
		 */
		return res.toString();
	}

}