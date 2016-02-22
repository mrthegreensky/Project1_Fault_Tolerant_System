import java.util.ArrayList;


/*Class to perform HeapSort
 Originally written for ECE 325 Fall 2014 and adapted from that version
 */
public class HeapSort {

    
	public static int[] HeapSort(int[] Array) {
		
		int count = Array.length;
		
		heapify(Array, count);
		
		int end = count - 1;
		
		while (end > 0) {
			int temp = Array[end];
			Array[end] = Array[0];
			Array[0] = temp;
			
			end = end - 1;
			
			MoveDown(Array, 0, end);
		}
        
        return Array;
	}	
	
    
	public static void heapify(int[] Array, int count) {
		int start = ((count-2)/2);
		
		while (start >= 0) {
			MoveDown(Array, start, count-1);
			start = start -1;
		}
        
	}
	
    
	public static void MoveDown(int[] Array, int start, int end) {
		
		int root = start;
		
		while (root*2 + 1 <= end) {
			int child = root*2 + 1;
			int swap = root;
			if (Array[swap] < Array[child]) {
				swap = child; 
				
			}
			if (child+1 <= end) {
				if (Array[swap] < Array[child+1]) {
				swap = child + 1;
				}
			}
			if (swap != root) {
				int temp = Array[root];
				Array[root] = Array[swap];
				Array[swap] = temp;
				
				root = swap;
				
			} else {
				return;
            }
		}
	}
    
}
