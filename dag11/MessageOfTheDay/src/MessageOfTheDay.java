import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MessageOfTheDay {
	/**
	 * @author Björn Ros
	 * @param args
	 */

	
	/**
	 * Gets some statistics from a Corpus sample file about length of sentences.
	 * 
	 * Sentence detection is weak however, but a rather large problem to solve. Too large for one day. Right now it has false positives which in turn
	 * increases the number of presumed sentences in a file, which in turn pads the allocation of the ArrayList. Other calculations are however more
	 * plausible.
	 * 
	 * @param file
	 * @return
	 */
	public static double AverageCharactersPerSentence(String file) {
		long sentenceCount = 0;
		long charCount=0;
		Scanner scan = null;
		try {

			scan = new Scanner(Paths.get(file));
			scan.useDelimiter("");
			while (scan.hasNext()) {
				String s = scan.next();
				charCount++;
				if (s.matches("\\p{Punct}")) sentenceCount++; // \\p{Punct}
			}
		} catch (IOException e) {

			e.printStackTrace();
		} finally {

			if (scan != null)
				scan.close();
		}
		return (charCount/sentenceCount);
	}

	public static void main(String[] args) {

		
		String QUOTESFILE = "QUOTES.TXT";

		
		double avgCharsPerSentence = MessageOfTheDay.AverageCharactersPerSentence("Sample.txt");
		// Add some padding for the "- Author´s Name"
		avgCharsPerSentence += 15.0f;
		// Account for UTF8 encoding
		double bytesPerAvgSentence = 2*avgCharsPerSentence;
		
		double assumedQuotes = new File(QUOTESFILE).length() / bytesPerAvgSentence;
		
		// I have not been able to find a sample that matches quotes, so I add 100% extra for the longwinded, information crammed and 
		// subsentenized characteristics of many quotes.
		// The 100% also accounts for abbreviations, picture texts, headlines, punctuation in names and other artifacts in the sample text.
		// After that I add 30% extra for security
		double modifiedQuotes = assumedQuotes * (2.0f * 1.30f);
		
		
		List<String> quotes = new ArrayList<String>((int)modifiedQuotes);

		Scanner scan = null;
		try {

			scan = new Scanner(Paths.get(QUOTESFILE));
			while (scan.hasNextLine())
				quotes.add(scan.nextLine());

		} catch (IOException e) {

			e.printStackTrace();
		} finally {

			if (scan != null)
				scan.close();
		}

		System.out.println(quotes.get(new Random().nextInt(quotes.size())));

	}

}
