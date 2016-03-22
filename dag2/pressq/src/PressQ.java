/**
 * 
 * @author Björn Ros
 *
 */
public class PressQ {

	public static void main(String[] args) throws java.io.IOException {
		String s = null;
		java.util.Scanner in = new java.util.Scanner(System.in);
		do {
			s = in.nextLine();
		} while (!s.equalsIgnoreCase("q"));
		in.close();
	}

}
