public class InsertionThread extends Thread {
	
	private static int[] list = null;

	public InsertionThread(int[] list) {
		this.list = list;
	}

	public int[] getList() {
		return this.list;
	}

	public void run() {

        MyInsertionSort insertionSort = new MyInsertionSort();
        System.loadLibrary("insertionsort");
        this.list = insertionSort.insertionSort(list);

	}

}