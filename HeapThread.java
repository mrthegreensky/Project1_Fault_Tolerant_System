import java.util.Random;

public class HeapThread extends Thread {
	
	private static int[] list = null;
	private static boolean finished = false;
	private static double min = 0.5;
	private static double hazard = 0;
	
	public HeapThread(int[] list, double hazard) {
		this.list = list;
		this.hazard = hazard;
	}

	public int[] getList() {
		return this.list;
	}

	public boolean getStatus() {
		return this.finished;
	}

	public void run() {

		HeapSort hsort = new HeapSort();
        this.list = hsort.HeapSort(list);
        Random random = new Random();
        double temp = random.nextDouble();
        if((temp <= min) && (temp >= (min+(hazard*hsort.getNumAccesses())))) {
        	System.out.println("temp is: " + temp + " upper bound is: " + (min+(hazard*hsort.getNumAccesses())));
        	this.finished = true;
        } 
	}

}