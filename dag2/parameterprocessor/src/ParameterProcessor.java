public class ParameterProcessor {

	public static void main(String[] args) {

		boolean validCommand = false;

		if (args.length != 0) {
			for (int c = 0; c < args.length; c++) {
				switch (args[c]) {
				case "-V":
				case "-v": {
					System.out.println("Version 1.00.00");
					validCommand = true;
				}
				default:
				}
				if (validCommand)
					break;
			}
		}
		if (!validCommand)
			System.err.printf("Usage: java %s -[v|V]\n", ParameterProcessor.class.getName());
	}
}
