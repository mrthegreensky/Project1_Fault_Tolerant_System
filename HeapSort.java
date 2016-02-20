import java.io.*;
import java.util.ArrayList;


public class HeapSort {

    
    /* variables */
    private static ArrayList<Integer> list = new ArrayList<Integer>();
    private static BufferedReader reader = null;
    private static PrintWriter writer = null;
    private static File input = null;
    private static File output = null;
    
    
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
    
    
    public static void InitInputs(String[] args) {
        input = new File(args[0]); //input
        output = new File(args[1]); //output
        double probability = 0; //assigned default
        int timeLimit = 0; //assigned default
        try {
            probability = Double.parseDouble(args[2]);
            timeLimit = Integer.parseInt(args[3]);
        } catch (NumberFormatException e) {
            System.err.println("Arguments " + args[2] + ", " + args[3] + "must be an double, integer respectively");
            System.exit(1);
        }
    }
    
    
    public static void readData() {
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
	} catch (IOException e) {
		System.err.println("Caught IOExeception: " + e.getMessage());
		System.exit(1);
	}
    }
    
    
    public static void writeData() {
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

    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		if(args.length != 4) {
			System.err.println("Accepts 4 arguments. Input file name, output file name, failure probabilities and time limit");
			System.exit(1);
		}

        InitInputs(args);

        readData();
        
		HeapSort.HeapSort(list);

        writeData();
		
	}
}
