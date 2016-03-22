/**
 * 
 * @author Björn Ros
 *
 */
public class RunAnon {

	public static void main(String[] args) {

		new Thread(new Runnable() {
			public void run() {
				System.out.println("Hello!");
			}
		}).start();

	}

}
