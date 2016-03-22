import java.nio.channels.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Downloader implements Runnable {
	SharedDrop drop;

	public Downloader(SharedDrop sd) {
		drop = sd;
	}

	public void run() {
		FileOutputStream fos = null;
		URL website = null;
		ReadableByteChannel rbc = null;
		try {
			website = new URL("http://logonoid.com/images/java-logo.png");
		} catch (MalformedURLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		try {
			rbc = Channels.newChannel(website.openStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			fos = new FileOutputStream("Inversion_example.png");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		drop.drop(true);
	}
}
