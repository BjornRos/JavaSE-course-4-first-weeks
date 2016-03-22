
public class SharedDrop {
	
	boolean flag = false;
	
	public synchronized void drop(boolean b) {flag =b; notifyAll();}
	
	public synchronized boolean take() {return flag;} 
	
}
