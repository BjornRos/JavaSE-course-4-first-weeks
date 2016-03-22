import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Downloader implements Runnable {
	SharedDrop<String> drop;

	public Downloader(SharedDrop<String> sd) {
		drop = sd;
	}

	public void run() {
		String name="";
		Path file=null;
		String adress="";
				try {
					adress = "http://www.microsoft.com";
					final URL url = new URL(adress);
					final URLConnection conn = url.openConnection();
					final BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
					final String pageText = reader.lines().collect(Collectors.joining("\n"));
					
					
					file = Paths.get(url.getFile());
//					Files.createDirectories(file);
//					Files.createFile(file);
					name = file.getFileName().toString();
					
					if (name.equals("")) name = Paths.get(url.getHost()).toString();
					name += ".html";
					Files.write(Paths.get(name), pageText.getBytes(Charset.forName("UTF-8")));
					
					//System.out.println(pageText);
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
					System.err.println("IOException!");
					System.err.println(e.getMessage());
				}
		
		drop.drop(new StringBuilder().append(adress).append(" -> ").append(name).toString());
	}
}
