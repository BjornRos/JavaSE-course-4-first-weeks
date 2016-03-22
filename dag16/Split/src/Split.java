
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Split {
/**
 * Exercise 7.2
 * @author Björn Ros
 * @param filename to split
 * 
 * Create an application named Split for splitting a
large file into a number of smaller partx files (where x
starts at 0 and increments; for example, part0, part1,
part2, and so on). Each partx file (except possibly the
last partx file, which holds the remaining bytes) will
have the same size. This application has the following
usage syntax: java Split pathname. (To
recombine the part files on a Windows platform, use
the copy command and its /B binary option.) (Friessen
2011, s. 580.)
 */
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int BYTECHUNK = 6400000;

		if (args.length != 1) {
			System.err.println("Wrong number of parameters");
			System.err.println("Usage: java Split <path>");
			System.exit(1);
		}

		Path path = Paths.get(args[0]);
		File file = path.toFile();

		if (!file.exists()) {
			System.err.println("File does not exist.");
			System.err.println("Usage: java Split <path>");
			System.exit(1);
		}

		int partnr = 0;
		int size = 0;
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			System.err.println("File does not exist.");
			System.err.println("Usage: java Split <path>");
			System.exit(1);
		}
		
		try {
			size = (BYTECHUNK > fis.available()) ? fis.available() : BYTECHUNK;
		} catch (IOException e1) {
			System.err.println("Could not open file.");
			System.err.println("Usage: java Split <path>");
			System.exit(1);

		}
		try {
			while (fis.available() > 0) {
				partnr++;
				byte[] buffer = new byte[size];
				FileOutputStream fos = new FileOutputStream(
						Files.createFile(Paths.get(args[0] + ".part" + partnr)).toFile());
				fis.read(buffer);
				fos.write(buffer);
				fos.close();
				size = (BYTECHUNK > fis.available()) ? fis.available() : BYTECHUNK;
			}

		} catch (FileNotFoundException e) {
			System.err.println("Could not open file.");
			System.err.println("Usage: java Split <path>");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Could not access file.");
			System.err.println("Usage: java Split <path>");
			System.exit(1);
		} finally {
			if (fis != null)
				try {
					fis.close();
				} catch (IOException e) {
					System.err.println("Could not close outputfile.");
					System.err.println("Usage: java Split <path>");
					System.exit(1);
				}
		}
		System.out.println("Split successfully split " + args[0] + " into " + partnr + " parts.");

	}

}
