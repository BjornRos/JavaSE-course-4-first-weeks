
public class MainClass {

	public MainClass(Animal a) {
		System.out.println("MainClass instanciated. It makes a sound:");
		a.makeSound();
	}

	public static void main(String[] args) {

		if (args.length != 1) {
			System.err.println("Usage: MainClass [Lion|Cow]");
			System.exit(1);
		}
		if (args[0].equals("Lion") || args[0].equals("Cow")) {
			try {
				java.lang.Class c = Class.forName(args[0]);
				Animal a = (Animal) c.newInstance();
				MainClass mc = new MainClass(a);
			} catch (ClassNotFoundException cnfe) {
				System.err.println(cnfe.getMessage());
				System.err.println(cnfe.getStackTrace());
				System.exit(1);
			} catch (InstantiationException ie) {
				System.err.println(ie.getMessage());
				System.err.println(ie.getStackTrace());
				System.exit(1);
			} catch (IllegalAccessException iae) {
				System.err.println(iae.getMessage());
				System.err.println(iae.getStackTrace());
				System.exit(1);
			}
		} else {
			System.err.println("Usage: MainClass [Lion|Cow]");
			System.exit(1);
		}

	}

}
