import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CircleInfo {

	/**
	 * Exercise 7.3
	 * 
	 * @author Björn Ros
	 * @param args
	 * 
	 *            It’s often convenient to read lines of text from standard
	 *            input, and the InputStreamReader and BufferedReader classes
	 *            make this task possible. Create an application named
	 *            CircleInfo that, after obtaining a BufferedReader instance
	 *            that’s chained to standard input, enters a loop that prompts
	 *            the user to enter a radius, parses the entered radius into a
	 *            double value, and outputs a pair of messages that report the
	 *            circle’s circumference and area based on this radius (Friessen
	 *            2011, s. 580).
	 */
	public static void main(String[] args) {

		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);

		try {
			while (true) {
				System.out.println("Please enter a circle´s radius:");
				String s = br.readLine();
				if (s.toLowerCase().equals("quit"))
					System.exit(0);
				try {
					double radius = Double.parseDouble(s);

					System.out.printf("The circumference: %.2f\n", radius * 2 * Math.PI);
					System.out.printf("The area: %.2f\n", radius * radius * Math.PI);
				} catch (NumberFormatException e) {
					System.out.println("Type a number to calculate a circle´s properties.");
					System.out.println("Type \"quit\" to exit the program.");
				}
			}
		} catch (IOException e) {
			System.err.println("Can not read the keyboard (system.in) properly.");
			System.err.println("Usage: java CircleInfo");
			System.exit(1);
		}

	}

}
