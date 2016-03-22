import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/**
 * Excerise 4.4
 * 
 * @author Björn Ros
 *
 */
public class DriverDemo {

	Driver driver;

	public static void main(String[] args) {

		java.lang.Class activeClass = null;
		Driver driver = null;

		try {
			activeClass = Class.forName("Driver");
			driver = (Driver) activeClass.newInstance();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			if (activeClass != null) {
				Method m = activeClass.getMethod("getCapabilitiesEx", null);
				System.out.println(m.invoke(driver) + " detected!");
			}

		} catch (NoSuchMethodException e) {
			try {
				if (activeClass != null) {
					Method m = activeClass.getMethod("getCapabilities", null);
					System.out.println(m.invoke(driver) + " detected!");
				}
			} catch (NoSuchMethodException ee) {
				System.out.println("There is no capability present! Aborting! Aborting!");
				System.exit(1);

			} catch (SecurityException ee) {
				e.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
