public class InsertionThread extends Thread {
	
	private static int[] list = null;
	private static boolean finished = false;
	private static double hazard = 0;

	public InsertionThread(int[] list, double hazard) {
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

		MyInsertionSort insertionSort = new MyInsertionSort();
		System.loadLibrary("insertionsort");
		this.list = insertionSort.insertionSort(list, hazard);
		this.finished = true;
	}

}