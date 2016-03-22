/**
 * 
 * @author Björn Ros
 *
 *         I used scanner, which does not cast an IOException, so I can not
 *         finish the exercise exactly as intended. But I can do roughly the
 *         same.
 */
public class PressQException {

	public static void main(String[] args) throws NotGonnaHappenException {

		String s = null;
		try {
			try (java.util.Scanner in = new java.util.Scanner(System.in)) {
				do {
					s = in.nextLine();
				} while (!s.equalsIgnoreCase("q"));
			} catch (Exception e) {
				throw new NotGonnaHappenException("I did not think that could happen here!", e);
			}
		} catch (NotGonnaHappenException e) {
			System.err.printf("Original Error: %s\n", e.getException().getClass().getName());
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}

}
