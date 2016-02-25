import java.io.*;
import java.lang.*;
import java.util.Timer;


public class Driver {

	/* variables */
    private static int[] list = null;
    private static BufferedReader reader = null;
    private static PrintWriter writer = null;
    private static int maxLines = 0;
    private static int sum = 0;

	private static File input = null;
    private static File output = null;
    private static double pPrimary = 0;
    private static double pSecondary = 0;
    private static int timeLimit = 0;


    /* methods */
	public static void InitInputs(String[] args) {
        input = new File(args[0]); //input
        output = new File(args[1]); //output
        try {
            pPrimary = Double.parseDouble(args[2]);
            pSecondary = Double.parseDouble(args[3]);
            timeLimit = Integer.parseInt(args[4]);
        } catch (NumberFormatException e) {
            System.err.println("Arguments " + args[2] + ", " + args[3] + ", " + args[4] + "must be an double, double, integer respectively");
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
                    sum += list[iter];
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

    public static boolean checkSum(int[] list) {
        int tempSum =0;

        for(int i : list) {
            tempSum += i;
        }
        if(tempSum == sum) {
            return true;
        }
        return false;
    }


    public static boolean performHeapThread() {
        int[] heapList = new int[maxLines];
        HeapThread heapThread = new HeapThread(list, pPrimary);

        Timer timer = new Timer();
        WatchDog watchDog = new WatchDog(heapThread);

        timer.schedule(watchDog, timeLimit*1000);
        heapThread.start();
        
        try{
            heapThread.join();
            timer.cancel();
        } catch (InterruptedException e) {
            System.err.println("Caught InterruptedException: " + e.getMessage());
            return false;
        }

        if(heapThread.getStatus()) {
            heapList = heapThread.getList();
            if(checkSum(heapList)) {
                writeData(heapList);
            } else {
                System.out.println("Checksum for HeapSort has failed.");
                return false;
            }
        }

        return true;
    }


    public static boolean performInsertionThread() {
        int[] insList = new int[maxLines];
        InsertionThread insertionThread = new InsertionThread(list, pSecondary);

        Timer timer = new Timer();
        WatchDog watchDog = new WatchDog(insertionThread);

        timer.schedule(watchDog, timeLimit*1000);
        insertionThread.start();

        try {
            insertionThread.join();
            timer.cancel();
        } catch (InterruptedException e) {
            System.err.println("Caught InterruptedException: " + e.getMessage());
            return false;
        }

        if(insertionThread.getStatus()) {
            insList = insertionThread.getList();
            if(checkSum(insList)) {
                writeData(insList);
            } else {
                System.out.println("Checksum for InsertionSort has failed.");
                return false;
            }
        }
        return true;
    }



	public static void main(String[] args) {
		// TODO Auto-generated method stub

		if(args.length != 5) {
			System.err.println("Accepts 5 arguments. Input file name, output file name, failure probability for primary, failure probability for secondary and time limit (in seconds)");
			System.exit(1);
		}

        InitInputs(args);

        readData();
        
        boolean didNotWork = false;
        boolean heapSortStatus = performHeapThread();
        if(heapSortStatus == didNotWork) {
            performInsertionThread();
        }
	}


}
