
public class CircleCalc {

	public static void main(String[] args) {
		if (args.length == 1) {
			double r = 1d;
			try {
				if (args[0].substring(0, 3).equals("-r:")) {
					r = Double.parseDouble(args[0].substring(3));
				} else {
					System.err.println("Unknown paramater.");
					System.err.printf("Usage: java %s -r:Radius", CircleCalc.class.getName());
					System.exit(1);
				}
			} catch (NumberFormatException e) {
				System.err.println("Malformed input, check your radius.");
				System.err.printf("Usage: java %s -r:Radius", CircleCalc.class.getName());
				System.exit(1);
			}

			double a = r * r * Math.PI;
			double c = r * 2d * Math.PI;

			System.out.printf("Radius: %.2f\n", r);
			System.out.printf("Area: %.2f\n", a);
			System.out.printf("Circumference: %.2f\n", c);

		} else {
			System.err.println("Incorrect number of paramaters.");
			System.err.printf("Usage: java %s -r:Radius", CircleCalc.class.getName());
		}
	}

}
