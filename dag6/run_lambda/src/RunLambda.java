/**
 * 
 * @author Björn Ros
 *
 */
public class RunLambda {

	public static void main(String[] args) {

		new Thread(() -> System.out.println("Hello!")).start();

	}

}
