
public class SharedDrop<T> {

	T download;
	boolean empty = true;

	
	
	public synchronized void drop(T d) {
		
			
			while (!empty) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			empty=false;
			download = d;
			notifyAll();
		
	}

	public synchronized T take() {
		
		while (empty) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		empty=true;
		notifyAll();
		
		return download;
	}

}
