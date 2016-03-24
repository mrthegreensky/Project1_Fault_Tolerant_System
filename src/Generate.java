import java.io.*;
import java.util.Random;

public class Generate {

	
	public static void writeData(File file, int numvar) {
		PrintWriter writer = null;
		
		int max = numvar+1000;
		
		try {
			writer = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			System.err.println("Caught FileNotfoundException: " + e.getMessage());
			System.exit(1);
		} finally {
			Random random = new Random();
			
			for(int iter = 0; iter < numvar; iter++) {
				writer.println(random.nextInt(max));
			}
			
			writer.close();
		}    }
	
	/* maybe add try finally block*/
	public static void main(String[] args) {

		if(args.length != 2) {
			System.out.println("arg1, arg2: filename, integer values to be generated");
			System.exit(1);
		}

		String filename = args[0];
		int numvar = 0;
		try {
			 numvar = Integer.parseInt(args[1]);
		} catch (NumberFormatException e) {
			System.err.println("Argument" + args[1] + "must be an integer.");
			System.exit(1);
		}

		File file = new File(filename);

		writeData(file, numvar);
		
	}

}
