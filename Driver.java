import java.io.*;
import java.lang.*;
import java.util.ArrayList;


public class Driver {

	/* variables */
    private static ArrayList<Integer> list = new ArrayList<Integer>();
    private static BufferedReader reader = null;
    private static PrintWriter writer = null;

	private static File input = null;
    private static File output = null;
    private static double probability = 0;
    private static int timeLimit = 0;


    /* methods */

	public static void InitInputs(String[] args) {
        input = new File(args[0]); //input
        output = new File(args[1]); //output
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

        HeapSort hsort = new HeapSort();
        list = hsort.HeapSort(list);
        
		//HeapSort.HeapSort(list);

        writeData();
		
	}
}
