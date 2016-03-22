
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
/**
 * Exercise 6.3
 * 
 * @author Björn Ros
 *  *
 */
public class FutureWebpage implements Callable<String> {

	private String page;

	public FutureWebpage(String page) {
		this.page = page;
	}

	public static void main(String[] args) {

		ExecutorService xs = Executors.newFixedThreadPool(1);
		
		
		try {
			System.out.println(xs.submit(new FutureWebpage("http://www.google.se")).get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public String call() {

		// try {
		URL url;
		try {
			url = new URL(page);
			final URLConnection conn = url.openConnection();
			final BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			final String pageText = reader.lines().collect(Collectors.joining("\n"));
			reader.close();
			return pageText;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}