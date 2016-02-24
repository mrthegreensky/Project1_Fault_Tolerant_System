public class InsertionThread extends Thread {
	
	private static int[] list = null;
	private static boolean finished = false;

	public InsertionThread(int[] list) {
		this.list = list;
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
        this.list = insertionSort.insertionSort(list);
        this.finished = true;
	}

}