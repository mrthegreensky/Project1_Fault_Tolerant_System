import java.io.*;
import java.util.ArrayList;
//Andrew Zhong
//Lab 1

public class HeapSort {
	
	public static void HeapSort(ArrayList<Integer> Array) {
		
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
	
    /*
	public static ArrayList Swap(int First, int Second) {
		int temp = First;
		First = Second;
		Second = temp;
	}	
	*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//int[] Numbers = {6,5,4,8,3,2,7,1,9};	

		ArrayList<Integer> list = new ArrayList<Integer>();


		if(args.length != 4) {
			System.err.println("Accepts 4 arguments. Input file name, output file name, failure probabilities and time limit");
			System.exit(1);
		}
		
		File input = new File(args[0]); //input
		File output = new File(args[1]); //output
		double probability = 0; //assigned default
		int timeLimit = 0; //assigned default
		try {
			probability = Double.parseDouble(args[2]);
			timeLimit = Integer.parseInt(args[3]);
		} catch (NumberFormatException e) {
			System.err.println("Arguments " + args[2] + ", " + args[3] + "must be an double, integer respectively");
			System.exit(1);
		}

		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(input));
			String line = null;

			while((line = reader.readLine()) != null) {
				list.add(Integer.parseInt(line));
			}

		} catch (FileNotFoundException e) {
			System.err.println("Caught FileNotFoundException: " + e.getMessage());
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Caught IOException: " + e.getMessage());
			System.exit(1);
		}
 
		try {
			reader.close();
		} catch (FileNotFoundException e) {
			System.err.println("caught FileNotFoundException: " + e.getMessage());
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Caught IOException: " + e.getMessage());
		}

		HeapSort.HeapSort(list);

		PrintWriter writer = null;
		try {		
			writer = new PrintWriter(output);
		} catch (FileNotFoundException e) {
			System.err.println("Caught FileNotFoundException: " + e.getMessage());			
			System.exit(1);
		}			

		int count = 0;
		int length = list.size();
		while (count < length) {
			writer.println(list.get(count));
			count = count + 1;
		}
		writer.close();
		
	}
	
}
