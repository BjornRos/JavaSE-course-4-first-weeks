/**
 * Exercise 4.8
 * 
 * @author Björn Ros
 *
 */
public class AsteriskThreadsDelayed implements Runnable {
	
	public static void main(String[] args) {
		AsteriskThreadsDelayed r1 = new AsteriskThreadsDelayed();
		AsteriskThreadsDelayed r2 = new AsteriskThreadsDelayed();
		Thread t1 = new Thread(r1);
		t1.setName("Första tråden");
		Thread t2 = new Thread(r2);
		t2.setName("Andra tråden");
		t1.start();
		t2.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		System.out.println("Stopping All Threads!");
		t1.interrupt();
		t2.interrupt();
		
	}

	public void run() {
		for (int i = 1; i <= 7; i++)
		{ 
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
//				break;
			}
			System.out.println(new StringBuilder().append(Thread.currentThread().getName()).append(": ")
					.append("*******".substring(7 - i)));
			if (Thread.interrupted()) break;
		}
	}
}
