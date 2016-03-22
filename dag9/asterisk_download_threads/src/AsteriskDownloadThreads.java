/**
 * Exercise 4.9
 * This solution is not the intended, it was meant to use join() and not producer-consumer-pattern.
 * 
 * @author Björn Ros
 *
 */
public class AsteriskDownloadThreads implements Runnable {
	
	SharedDrop drop;
	public static void main(String[] args) {
		SharedDrop shareddrop = new SharedDrop();
		Runnable r = new AsteriskDownloadThreads(shareddrop);
		Runnable rr = new Downloader(shareddrop);
		Thread t1 = new Thread(rr);
		t1.setName("Downloader");
		Thread t2 = new Thread(r);
		t2.setName("Asterisker");
		t1.start();
		t2.start();
	}

	public AsteriskDownloadThreads(SharedDrop sd) {
		drop = sd;
	}

	public void run() {
		for (int i = 1; i <= 7; i++) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			if (drop.take()) break;
			
			System.out.println(new StringBuilder().append(Thread.currentThread().getName()).append(": ")
					.append("*******".substring(7 - i)));
		}
	}
}
