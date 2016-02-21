import java.util.ArrayList;


/*Class to perform HeapSort
 Originally written for ECE 325 Fall 2014 and adapted from that version
 */
public class HeapSort {

    
	public static ArrayList<Integer> HeapSort(ArrayList<Integer> Array) {
		
		int count = Array.size();
		
		heapify(Array, count);
		
		int end = count - 1;
		
		while (end > 0) {
			int temp = Array.get(end);
			Array.set(end, Array.get(0));
			Array.set(0, temp);
			
			end = end - 1;
			
			MoveDown(Array, 0, end);
		}
        
        return Array;
	}	
	
    
	public static void heapify(ArrayList<Integer> Array, int count) {
		int start = ((count-2)/2);
		
		while (start >= 0) {
			MoveDown(Array, start, count-1);
			start = start -1;
		}
        
	}
	
    
	public static void MoveDown(ArrayList<Integer> Array, int start, int end) {
		
		int root = start;
		
		while (root*2 + 1 <= end) {
			int child = root*2 + 1;
			int swap = root;
			if (Array.get(swap) < Array.get(child)) {
				swap = child; 
				
			}
			if (child+1 <= end) {
				if (Array.get(swap) < Array.get(child+1)) {
				swap = child + 1;
				}
			}
			if (swap != root) {
				int temp = Array.get(root);
				Array.set(root, Array.get(swap));
				Array.set(swap, temp);
				
				root = swap;
				
			} else {
				return;
            }
		}
	}
    
}
