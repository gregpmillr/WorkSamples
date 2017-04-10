import java.io.IOException;
import java.util.Random;

/**
 * Used to test the implementation of MinHeap and MaxHeap.
 * This class will create MinHeap and MaxHeap, print 
 * both in level order as well as descending, and ascending order. 
 * My analysis is given at the end of descending and ascending orders.
 * 
 * @author Greg Miller
 *
 */

public class testHeaps {

	/**
	 * Declare & initialize variables
	 */
	private static final int MAX_VALUES_TO_INSERT = 29;
	private static final int MAX_RANDOM_VALUE = 50;
	private static final int MIN_RANDOM_VALUE = 1;
	private static final int MAX_HEAP_SIZE = 30;

	public static void main(String[] args) throws IOException {
		
		/**
		 * Instantiate min and max heaps with max heap size of 30
		 */
		MaxHeap maxHeap = new MaxHeap(MAX_HEAP_SIZE);
		MinHeap minHeap = new MinHeap(MAX_HEAP_SIZE);
		
		/**
		 * Random number generator to generate numbers between 1-50
		 */
		Random rng = new Random();
		
		/**
		 * Add randomized values to the min and max heap
		 */
		for(int i = 0;i<MAX_VALUES_TO_INSERT;i++){
			int randomVal = rng.nextInt(MAX_RANDOM_VALUE) + MIN_RANDOM_VALUE;
			minHeap.add(randomVal);
			maxHeap.add(randomVal);
		}

		// print the MaxHeap in level order
		maxHeap.printLevelOrder();
		
		/**
		 * Level order of max heap: 
			1 47
			2 47
			2 40
			3 47
			3 46
			3 39
			3 37
			4 44
			4 46
			4 29
			4 35
			4 18
			4 35
			4 26
			4 32
			5 17
			5 33
			5 15
			5 33
			5 27
			5 8
			5 1
			5 29
			5 1
			5 17
			5 16
			5 6
			5 11
			5 13
		 */
		
		/**
		 *  Show the descending order of MaxHeap by iterating through
		 *  its maximum nodes
		 */
		System.out.println("\nDescending order using maxheap:");
		for(int i = 0;i<MAX_VALUES_TO_INSERT;i++){
			System.out.println(maxHeap.deleteMax());
		}
		
		/**
		 * Descending order using maxheap:
			47
			47
			47
			46
			46
			44
			40
			39
			37
			35
			35
			33
			33
			32
			29
			29
			27
			26
			18
			17
			17
			16
			15
			13
			11
			8
			6
			1
			1
		 */
		
		/**
		 * Analysis of MaxHeap level order vs descending order:
		 * 
		 * The root node is the maximum value of either orders,
		 * Each node per level is <= the greatest value node within the 
		 * level previous to the current. It's shown in the output that both orders 
		 * continue descending as expected, although due to the nature (implementation)
		 * of level-order, some numbers in level-order are higher than the 
		 * previous, which is expected. 
		 */
		
		// print the MinHeap in level order
		minHeap.printLevelOrder();
		
		/**
		 * Level order of min heap: 
			1 1
			2 1
			2 6
			3 33
			3 8
			3 11
			3 13
			4 44
			4 33
			4 27
			4 15
			4 17
			4 16
			4 18
			4 32
			5 47
			5 47
			5 46
			5 35
			5 46
			5 29
			5 47
			5 29
			5 17
			5 39
			5 37
			5 35
			5 40
			5 26
		 */
		
		/**
		 *  Show the ascending order of MinHeap by iterating through
		 *  its minimum nodes
		 */
		System.out.println("\nAscending order using min heap:");
		for(int i = 0;i<MAX_VALUES_TO_INSERT;i++){
			System.out.println(minHeap.deleteMin());
		}
		
		/**
		 * Ascending order using min heap:
			1
			1
			6
			8
			11
			13
			15
			16
			17
			17
			18
			26
			27
			29
			29
			32
			33
			33
			35
			35
			37
			39
			40
			44
			46
			46
			47
			47
			47
		 */
		
		
		/**
		 * Analysis of MinHeap level order vs ascending order:
		 * 
		 * The root node is the minimum value of either orders,
		 * Each node per level is >= the smallest value node within the 
		 * level previous to the current. It's shown in the output that both orders 
		 * continue ascending as expected, although due to the nature (implementation)
		 * of level-order, some numbers in level-order are higher than the 
		 * previous, which is expected. 
		 */
		
	}

}
