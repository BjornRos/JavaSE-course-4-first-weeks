import java.io.*;

public class ManOrWoman {

	/**
	 * @uthor Björn Ros
	 * 
	 * @param args Programparameters.&nbspThere shouldn´t be any.
	 * @throws IOException IOEXception from using java.lang.scanner.
	 */

	public static void main(String[] args) throws IOException {

		String s = null;
		java.util.Scanner in = new java.util.Scanner(System.in);
		do {

			s = in.nextLine();
			if (s.matches("\\d{6,8}-\\d{4}")) {
				System.out.println(
						((Integer.parseInt(s.substring(s.length() - 2, s.length() - 1)) % 2) == 1) ? "Man" : "Kvinna");
			} else
				if (!s.equals("")) System.out.println("Please provide a valid personnummer (dddddd-dddd).");
		} while (!s.equals(""));
		in.close();
		System.out.println("Goodbye.");
	}

}
