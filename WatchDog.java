import java.util.TimerTask;

/*
 * Created on Aug 6, 2004
 * Watchdog timer
 
 */

/**
 * @author dick
 * Modified by Andrew Zhong
 
 */
public class WatchDog extends TimerTask {

Thread watched;

	public WatchDog (Thread target){
		// Constructor sets the class variable 'watched'
		watched = target;
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		
		watched.stop();
		System.out.println("Time limit reached. WatchDog has stopped running thread.");
	}

}
