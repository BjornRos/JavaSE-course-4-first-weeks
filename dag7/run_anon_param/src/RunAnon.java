/**
 * Exercise 3.9 & 3.10
 * 
 * (Need to specify that String printIt is final for java 7.
 * 
 * @author Björn Ros
 *
 */
public class RunAnon {

	public static void main(String[] args) {

		if (args.length > 0) {
			String printIt = args[0];

			new Thread(new Runnable() {
				public void run() {
					System.out.println("Message: "+printIt);
				}
			}).start();
		} else System.err.println("Please provide a commandline argument to be output.");
	}

}
