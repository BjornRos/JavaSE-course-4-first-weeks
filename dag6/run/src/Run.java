/**
 * 
 * @author Björn Ros
 *
 */
public class Run {


	public static void main(String[] args) {

		class Go implements Runnable {
				public void run() {
					System.out.println("Hello!");
				}
			}
		
		new Thread(new Go()).start();
		
	}

}
