import java.lang.Class;

/**
 * Exercise 4.1
 * 
 * @author Björn Ros
 *
 */
public class ClassCreate {

	public static void main(String[] args) {
		try {
			Class c = Class.forName("A");
			A a =(A)c.newInstance();
			a.b();
			// Victorias lösning för metodanrop - hennes a är hennes class.
			//Method m = a.getMethod("a", new Class<?>[0]);
			//m.invoke(demo);
		} catch (ClassNotFoundException cnfe) {
			System.err.println(cnfe.getMessage());
			System.err.println(cnfe.getStackTrace());
			System.exit(1);
		}catch (InstantiationException ie) {
			System.err.println(ie.getMessage());
			System.err.println(ie.getStackTrace());
			System.exit(1);
		}catch (IllegalAccessException iae) {
			System.err.println(iae.getMessage());
			System.err.println(iae.getStackTrace());
			System.exit(1);
		}

	}

}
