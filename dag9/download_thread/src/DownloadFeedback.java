
public class DownloadFeedback implements Runnable {

	SharedDrop<String> drop;
	

	public DownloadFeedback(SharedDrop<String> sd) {
		drop = sd;
		
	}

	public void run() {

		while (!Thread.interrupted()) {
	
			String s = drop.take();
		
		if (!s.equals(""))
			System.out.println(s);
		}
	}
}
