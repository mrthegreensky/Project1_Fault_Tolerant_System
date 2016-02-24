public class HeapThread extends Thread {
	
	private static int[] list = null;
	private static boolean finished = false;
	public HeapThread(int[] list) {
		this.list = list;
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
        this.finished = true;
	}

}