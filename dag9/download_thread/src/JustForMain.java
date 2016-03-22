/**
 * Exercise 4.10
 * This solution is not the intended, it was meant to use join() and not producer-consumer-pattern.
 * 
 * @author Björn Ros
 *
 */
public class JustForMain {
	
	public static void main(String[] args) {
		
		SharedDrop<String> shareddrop = new SharedDrop<String>();
		Runnable r = new DownloadFeedback(shareddrop);
		Runnable rr = new Downloader(shareddrop);
		Thread t1 = new Thread(rr);
		t1.setName("Downloader");
		Thread t2 = new Thread(r);
		t2.setName("Feedback");
		t1.start();
		t2.start();
	}

}
