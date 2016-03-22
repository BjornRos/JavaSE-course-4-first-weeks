import java.util.*;
import java.text.*;

public class Birthday {
	/**
	 * @author Björn Ros
	 * @param args
	 *            Programparametrar.&nsbpBör vara dagens datum och ett
	 *            personnummer, t ex 2016-03-02 och
	 *            1986-03-02-3332.&nsbpAlternativt kan enbart ett personnummer
	 *            användas.
	 */

	public static void TerminalError(String message) {
		System.err.println(message);
		System.err.printf("Usage: java %s [<Dagens datum> <Personnummer> || <Personnummer>]\n",
				Birthday.class.getName());
		System.exit(1);
	}

	public static void main(String[] args) {

		String today = null, birth = null;

		switch (args.length) {
		case 2:
			if (args[0].length() != 10)
				TerminalError("Felaktigt dagsdatum!");
			if (args[1].length() != 13)
				TerminalError("Felaktigt personnummer!");
			today = args[0].substring(5, 7) + args[0].substring(8, 10);
			birth = args[1].substring(4, 8);
			break;
		case 1:
			if (args[0].length() != 13)
				TerminalError("Felaktigt persnummer!");
			today = new SimpleDateFormat("MMdd").format(new Date());
			birth = args[0].substring(4, 8);
			break;
		}

		if (today.equals(birth))
			System.out.println("Grattis");

	}

}
