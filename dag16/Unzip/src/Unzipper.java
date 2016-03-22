import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import sun.launcher.resources.launcher;

public class Unzipper {
	/**
	 * Exercise 7.6
	 * 
	 * @author Björn Ros
	 * @param args
	 *            none
	 * 
	 *            Unzip the src.zip file from the JDK. Using Files.walk, find
	 *            all Java files that contain the keywords transient and
	 *            volatile (Horstmann 2014, s. 175) }
	 */
	public static void main(String[] args) {

		ZipInputStream zis = null;
		ZipEntry ze = null;
		boolean quit = false;
		Path dir = null;

		try {
			zis = new ZipInputStream(new FileInputStream(Paths.get("src.zip").toFile()));
		} catch (FileNotFoundException e) {
			System.err.println("Could not find src.zip.");
			System.exit(1);
		}

		try {

			dir = Files.createTempDirectory(null);
			dir.toFile().deleteOnExit();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Loop for unpacking
		while (!quit) {

			try {
				ze = zis.getNextEntry();
				byte[] buffer = new byte[(int) ze.getSize()];
				zis.read(buffer);
				System.err.println(dir.resolve(Paths.get(ze.getName())));
				Path filepath = Files.createFile(dir.resolve(Paths.get(ze.getName())));
				Files.write(filepath, buffer);
				filepath.toFile().deleteOnExit();
				if (zis.available() == 0)
					quit = true;
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("Error unpacking src.zip");
				System.exit(1);
			}

		}
		quit = false;
		Set<String> fileset = new HashSet<String>();
		try {
			// For each file....
			Files.walk(dir, 99, null).forEach(filepath -> {
				try {
					// ... check if any lines contains a keyword, and if so add
					// the filename to a set.
					Files.lines(filepath).filter(l -> (l.contains("transient") || l.contains("volatile")))
							.forEach(f -> fileset.add(filepath.toString()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (String s : fileset)
			System.out.println(s);
	}
}
