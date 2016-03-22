
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Downloader {
/**
 * Exercise 6.1
 * 
 * @author Björn Ros
 * @param args
 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ExecutorService xs = Executors.newFixedThreadPool(2);
		
		String[] scrapeList = new String[] {"http://www.bing.se","http://www.microsoft.com","http://www.msn.com","http://www.stackoverflow.com"};
		
		CountDownLatch countDown = new CountDownLatch(1);
		ArrayBlockingQueue<String> que = new ArrayBlockingQueue<String>(1);
		
		xs.submit(new Download(scrapeList, que,countDown));
		xs.submit(new UI(que));
		

		try {
			countDown.await();
			
			xs.shutdownNow();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
