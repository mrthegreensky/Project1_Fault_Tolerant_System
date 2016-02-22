import java.io.*;
import java.lang.*;
import java.util.ArrayList;


public class Driver {

	/* variables */
    private static int[] list = null;
    private static BufferedReader reader = null;
    private static PrintWriter writer = null;
    private static int maxLines = 0;

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


    /*LineNumberReader modified from: http://stackoverflow.com/questions/453018/number-of-lines-in-a-file-in-java*/
    public static void readData() {
        try {
            LineNumberReader lineReader = new LineNumberReader(new FileReader(input));
            lineReader.skip(Long.MAX_VALUE);
            maxLines = lineReader.getLineNumber();
            lineReader.close();

            list = new int[maxLines];

        	reader = new BufferedReader(new FileReader(input));
        	String line = null;
            
            int iter = 0;
        	while((line = reader.readLine()) != null) {
                	list[iter] = Integer.parseInt(line);
                    iter++;
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

    public static void writeData(int[] list) {
        try {
            writer = new PrintWriter(output);
        } catch (FileNotFoundException e) {
            System.err.println("Caught FileNotFoundException: " + e.getMessage());
            System.exit(1);
        }
        
        int count = 0;
        int length = list.length;
        
        while (count < length) {
            writer.println(list[count]);
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

        int[] sortedList = new int[maxLines];
        HeapSort hsort = new HeapSort();
        sortedList = hsort.HeapSort(list);
        
        
        int[] sortedList2 = new int[maxLines];
        MyInsertionSort insertionSort = new MyInsertionSort();
        System.loadLibrary("insertionsort");
        sortedList2 = insertionSort.insertionSort(list);
        
        writeData(sortedList2);
		
	}
}
