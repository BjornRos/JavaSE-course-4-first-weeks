
public class ReverseNumber {

	public static void main(String[] args) {

		String number = "";
		
		if (args.length > 1) {
			System.err.println("Wrong number of arguments.");
			System.exit(1);
		}
		
		if (args.length == 0) {
			System.out.println("Please input an integer:");
			java.util.Scanner in = new java.util.Scanner(System.in);
			number = in.nextLine();
			in.close();
		} else
			number = args[0];
		
		if (!number.matches("\\d+")) {
			System.err.println("Not a number.");
			System.exit(1);
		}

		int length = number.length();

		for (int i = 0; i < length; i++)
			System.out.print(number.charAt(length - i - 1));
		
		System.out.println();

	}

}
