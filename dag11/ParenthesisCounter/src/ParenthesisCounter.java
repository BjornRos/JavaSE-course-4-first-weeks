import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Stack;

public class ParenthesisCounter {

	public static void main(String[] args) {

		if (args.length != 1) {
			System.err.println("Usage: java ParenthesisCounter <FileName.Java>");
			System.exit(1);
		}

		Stack<Character> s = new Stack<Character>();
		Scanner scan = null;
		int rownumber = 0;
		try {
			scan = new Scanner(Paths.get(args[0]));

			while (scan.hasNextLine()) {

				rownumber++;
				String line = scan.nextLine();

				for (Character c : line.toCharArray()) {

					switch (c) {

					case '[':
						s.push(c);
						break;
					case '{':
						s.push(c);
						break;
					case '(':
						s.push(c);
						break;

					case ')':
						if (s.peek() != '(') {
							System.err.printf("\')\' without \'(\' on row %d.\nAborting.", rownumber);
							System.exit(1);
						} else
							s.pop();
						break;
					case '}':
						if (s.peek() != '{') {
							System.err.printf("\'}\' without \'{\' on row %d.\nAborting.", rownumber);
							System.exit(1);
						} else
							s.pop();
						break;
					case ']':
						if (s.peek() != '[') {
							System.err.printf("\']\' without \'[\' on row %d.\nAborting.", rownumber);
							System.exit(1);
						} else
							s.pop();
						break;
					default:
						break;
					}

				}

			}

		} catch (IOException e) {

			e.printStackTrace();
			System.exit(0);
		} finally {
			if (scan != null)
				scan.close();
		}

		if (s.empty())
			System.out.println("Successfully checked file for parenthesis.");
		else
			System.out.println("Error: \'" + s.peek() + "\' without beginning/closing partner present.");

	}

}
