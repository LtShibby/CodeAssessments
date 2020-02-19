package com.sk;

public class MissingPositiveNumber {

	public static void main(String[] args) {
		/**
		 * Input list of numbers to find the lowest missing positive number greater than
		 * 0
		 */
		int[] A = { 7, 6, 5, 4, 3, 2, -1, -2, -3, 11, -4, 10, -5, 9, -6, 8, -7, 1 };
		System.out.println("The lowest positive missing int is: " + findLowestMissingPositiveNumber(A, A.length));
	}

	/**
	 * First method that starts logic for finding the lowest missing positive number.
	 * 
	 * @param arr  - input array of int values. return lowest missing int value.
	 * @param size - size of input array
	 * @return
	 */
	static int findLowestMissingPositiveNumber(int arr[], int size) {

		/**
		 * -Separate positive and negative numbers.
		 * 
		 * -shift is the number of negative numbers stored at the beginning
		 * 
		 * -create a new array with only the positive elements from the original list
		 */
		int shift = segregate(arr, size);
		int arr2[] = new int[size - shift];
		int j = 0;

		/**
		 * take all positive elements from original array and store in a new array
		 */
		for (int i = shift; i < size; i++) {
			arr2[j] = arr[i];
			j++;
		}
		/**
		 * Call helper function to find the lowest missing positive value of the new
		 * array.
		 */
		return findMissingPositive(arr2, j);
	}

	/**
	 * Utility function that puts all non-positive (0 and negative) numbers on left
	 * side of arr[] and return count of such numbers
	 */
	static int segregate(int arr[], int size) {
		int j = 0, i;
		for (i = 0; i < size; i++) {
			if (arr[i] <= 0) {
				int temp;
				temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				j++;
			}
		}
		return j;
	}

	/*
	 * Find the smallest positive missing number in an array that contains all
	 * positive integers
	 */
	static int findMissingPositive(int arr[], int size) {
		int i;

		/**
		 * iterate through the list and make all values negative
		 */
		for (i = 0; i < size; i++) {
			int x = Math.abs(arr[i]);
			
			/**
			 * set current element to negative
			 */
			if (x - 1 < size && arr[x - 1] > 0)
				arr[x - 1] = -arr[x - 1];
		}

		/**
		 * iterate through each element of the list and find the first element that is positive.  
		 * 
		 * if no positive numbers exist, return size of the list + 1.
		 */
		for (i = 0; i < size; i++)
			if (arr[i] > 0)
				return i + 1; // 1 is added becuase indexes

		return size + 1;
	}

}
