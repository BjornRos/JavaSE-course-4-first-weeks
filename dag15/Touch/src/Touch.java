import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Touch {
	/**
	 * @author Björn Ros
	 * @param args
	 * 
	 * To do:
	 * There seems to be a problem with the time zone identifiers, only ´Z´ works for now.
	 */
	public static void main(String[] args) {

		Path path = null;
		FileTime time = null;

		switch (args.length) {
		case 1:
			path = Paths.get(args[0]);
			time = FileTime.from(Instant.now());
			break;

		case 5:
				if (!args[0].equals("-d")) {
					System.err.println("Unknown parameter.");
					System.err.println("Usage: Touch [-d Timestamp] <File|Directory>.");
					System.exit(1);
				}

			try {
				String stime = args[1]+ " "+args[2]+" "+args[3];
				ZonedDateTime zdt = ZonedDateTime.of(LocalDateTime.parse(stime,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z")), ZoneId.of(args[3]));
				time = FileTime.from(zdt.toInstant());
				path = Paths.get(args[4]);
				
			} catch (DateTimeParseException e) {

				System.err.println("Malformed timestamp. Example: 2016-03-21 08:36:10 Z");
				System.err.println("Usage: Touch [-d Timestamp] <File|Directory>.");
				System.exit(1);
			}

			break;
		default:
			System.err.println("Wrong number of parameters.");
			System.err.println("Usage: Touch [-d Timestamp] <File|Directory>.");
			System.exit(1);
			break;

		}

		try {
			Files.setLastModifiedTime(path, time);
			System.out.println("Touch completed successfully.");
		} catch (IOException e) {
			System.err.println("File is not accessible.");
			System.err.println("Usage: Touch [-d Timestamp] <File|Directory>.");
			System.exit(1);
		}

	}

}
