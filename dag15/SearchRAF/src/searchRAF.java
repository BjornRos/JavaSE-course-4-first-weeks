import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.StringReader;
import java.nio.file.Paths;

public class searchRAF {
	/**
	 * Exercise 7.5
	 * 
	 * @author Björn Ros
	 * @param args
	 *            Skapa ett program som använder direktfilen från övning 7.4 för
	 *            att hitta synonymer med binärsökning. ”Binärsökning är en
	 *            algoritm för att avgöra om en mängd innehåller ett givet
	 *            element. Sökningen utförs i flera steg och i varje steg skall
	 *            man utesluta att halva den kvarvarande mängden innehåller
	 *            elementet och därmed kunna koncentrera sig på den andra
	 *            halvan” (http://sv.wikipedia.org/wiki/Binärsökning).
	 */
	public static void main(String[] args) {

		// ENGLISH Key length:40 Record length:13516
		// SWEDISH Key length:28 Record length:275
		// Facit Key field size : 28 bytes
		// Value field size: 248 bytes
		final int KEYLEN = 28;
		final int RECLEN = 248;
		final int TOTLEN = KEYLEN + RECLEN;

		String dictionaryFile = "./facit.txt";

		try (RandomAccessFile raf = new RandomAccessFile(Paths.get(dictionaryFile + ".raf.txt").toFile(), "r")) {
			long records = raf.length() / TOTLEN;
			long halfseek = 0;
			long nextseek = (records / 2) * TOTLEN;
			long nextstep = (records / 2) * TOTLEN;
			byte[] key = new byte[KEYLEN];
			byte[] record = new byte[RECLEN];
			String skey = "";
			boolean quit = false;
			while (!quit) {
				raf.seek(nextseek);
				raf.readFully(key);
				skey = new String(key, "UTF-8").replace((char) 0, ' ');
				System.out.println("Searching.. found " + skey + " at " + nextseek);
				if (skey.trim().equals(args[0])) {
					raf.readFully(record);
					System.out.println("Funna synonymer:");
					System.out.println(new String(record, "UTF-8").trim());
					quit = true;
				} else {
					//nextstep keeps track of the size of the next step, calculations is to implode and explode between record length and byte size.
					nextstep = ((nextstep / TOTLEN) / 2) * TOTLEN;
					halfseek = nextseek;
					// depending upon alphabetical order of found key and searchterm, go up or down the file.
					if (skey.compareTo(args[0]) < 0)
						nextseek += nextstep;
					else
						nextseek -= nextstep;

				}
				if (halfseek == nextseek)
					quit = true;

			}

		} catch (IOException e) {

		}

	}

}
