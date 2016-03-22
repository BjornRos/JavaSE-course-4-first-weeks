import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class Toraf {
	/**
	 * Exercise 7.4
	 * 
	 * @author Björn Ros
	 * @param args
	 * 
	 * Fungerar inte.
	 * Första raden verkar ha en keylängd 3 kortare än vad den borde.
	 * 
	 */
	public static void main(String[] args) {

		String dictionaryFile = "./facit.txt";
		int keylen=0;
		int reclen=0;
		String separator = ":";
		try {
			
			keylen = Files.lines(Paths.get(dictionaryFile)).flatMap(line -> Stream.of(line.split(separator)[0])).mapToInt(String::length).max().orElse(0);
			reclen = Files.lines(Paths.get(dictionaryFile)).flatMap(line -> Stream.of(line.split(separator)[1])).mapToInt(String::length).max().orElse(0);
		} catch (IOException e) {
			System.err.println("Failed to get length of the longest key and/or record.");
			e.printStackTrace();
			System.exit(1);
		}
			
			try (RandomAccessFile raf = new RandomAccessFile(Paths.get(dictionaryFile+".raf.txt").toFile(),"rw");){
				final int klen = keylen;
				final int rlen = reclen;
				
				
				
				Files.lines(Paths.get(dictionaryFile)).forEach((String line) -> {
					try {
						byte[] bkey = new byte[klen];
						byte[] brec = new byte[rlen];
						
						String[] record = line.trim().split(separator);
						
						bkey=record[0].getBytes(StandardCharsets.UTF_8);
						brec=record[1].getBytes(StandardCharsets.UTF_8);
						raf.write(Arrays.copyOf(bkey,klen));
						raf.write(Arrays.copyOf(brec,rlen));
					} catch (Exception e) {
						System.err.println("Could not write to file.");
						e.printStackTrace();
						System.exit(1);
					}
				});
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("File written:"+ dictionaryFile+".raf.txt");
			System.out.println("Key length:"+ keylen+"          Record length:"+reclen);
			
	}

}
