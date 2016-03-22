
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

public class Download implements Runnable {

	ArrayBlockingQueue<String> queue = null;
	CountDownLatch countDown=null;
	
	String[] files;

	public Download(String[] files, ArrayBlockingQueue<String> q, CountDownLatch countDown) {
		queue = q;
		this.files = files;
		this.countDown=countDown;
	}

	@Override
	public void run() {

		for (int i = 0; i < files.length; i++) {
			
			try {
				final URL url = new URL(files[i]);
				final URLConnection conn = url.openConnection();
				final BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
				final String pageText = reader.lines().collect(Collectors.joining("\n"));
				
				
				Path file = Paths.get(url.getHost()+url.getPath()+".TXT");
				Files.write(file, pageText.getBytes(Charset.forName("UTF-8")));
				
				//System.out.println(pageText);
				reader.close();
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
			try {
				queue.put(files[i].toString());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}countDown.countDown();
	}
}
