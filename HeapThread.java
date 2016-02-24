public class HeapThread extends Thread {
	
	private static int[] list = null;

	public HeapThread(int[] list) {
		this.list = list;
	}

	public int[] getList() {
		return this.list;
	}

	public void run() {

		HeapSort hsort = new HeapSort();
        this.list = hsort.HeapSort(list);

	}

}