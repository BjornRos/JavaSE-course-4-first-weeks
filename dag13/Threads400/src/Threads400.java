import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class Threads400 {
/**
 * Exercise 6.2
 * 
 * Seems like processor 2,3 and 4 need some setup time.
 * 
 * @author Björn Ros
 * @param args
 */
	public static void main(String[] args) {
		
		int processors = Runtime.getRuntime().availableProcessors()-1;
		if (processors < 1) processors = 1;
		
		ExecutorService xs = Executors.newFixedThreadPool(4);
		
		for (int i = 0; i<9;i++) {
			xs.submit(new AsteriskTask(i));
		
		}
		
		
		
	}

}
