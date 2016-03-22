/**
 * Exercise 4.6
 * 
 * @author Bj�rn Ros
 *
 */
public class AsteriskThreads implements Runnable {

	public static void main(String[] args) {
		Runnable r = new AsteriskThreads();
		Thread t1 = new Thread(r);
		t1.setName("F�rsta tr�den");
		Thread t2 = new Thread(r);
		t2.setName("Andra tr�den");
		t1.start();
		t2.start();
	}

	public void run() {
		for (int i = 1; i <= 7; i++)
			System.out.println(new StringBuilder().append(Thread.currentThread().getName()).append(": ")
					.append("*******".substring(7 - i)));
	}
}
