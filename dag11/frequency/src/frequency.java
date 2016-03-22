import java.util.ArrayList;
import java.util.List;

import java.util.Collections;

public class frequency {
	/**
	 * @author Björn Ros
	 * @param args
	 */

	public static void main(String[] args) {

		if (args.length < 2) {
			System.err.println("Please provide at least 2 arguments.");
			System.exit(1);
		}

		List<String> list = new ArrayList<String>();
		for (int i = 0; i < args.length - 1; i++)
			list.add(args[i]);

		System.out.printf("Frequency of %s: %d\n", args[args.length - 1],
				Collections.frequency(list, args[args.length - 1]));

	}

}
