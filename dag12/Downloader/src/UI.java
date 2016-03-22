import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;

public class UI implements Runnable {

	ArrayBlockingQueue<String> queue = null;
	CountDownLatch countDown = null;

	public UI(ArrayBlockingQueue<String> q) {
		queue = q;
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		while (!Thread.interrupted()){
			String s;
			try {
				s = queue.take();
				System.out.println("Downloaded: " + s);
			} catch (InterruptedException e) {
				return;
			}
			
		}
	}
}
