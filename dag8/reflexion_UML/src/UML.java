import java.lang.Class;
import java.lang.reflect.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.FileAlreadyExistsException;

/**
 * Exercise 4.3 & 4.5 (using StringBuilder)
 * 
 * @author Björn Ros
 *
 */
public class UML {
	private Class activeClass;
	
	private static Class parseClass(String s) {

		try {
			return (Class) Class.forName(s);
		} catch (ClassNotFoundException cnfe) {
			System.err.println(cnfe.getMessage());
			System.err.println(cnfe.getStackTrace());
			System.exit(1); }
		return null;
	}

	public static void main(String[] args) {
		Class c = null;

		

		if (args.length != 1) {
			System.err.println("Usage: UML Class");
			System.exit(1);
		}
		c = parseClass(args[0]);
		UML uml = new UML(c);
		if (uml.ToPlantUML(c.getName() + ".plantuml"))
			System.out.printf("%s%s has been created successfully.",c.getName() , ".plantuml");
		else System.err.println("Please try and rectify the problem.");
	}

	public UML(Class c) {
		activeClass = c;

	}

	public boolean ToPlantUML(String UMLFile) {

		Path path = Paths.get(UMLFile);
		try {
			Files.createFile(path);
		} catch (FileAlreadyExistsException e) {
			System.err.println("File already exists.");
			return false;
		} catch (IOException e) {
			System.err.println("File unaccessible.");
			
			return false;
		}

		try (PrintWriter writer = new PrintWriter(UMLFile)) {
			writer.println("@startuml");
			writer.println("");
			writer.println("class " + activeClass.getName() + " {");

			String[] methods = getMethodsAsPlantUML();
			String[] fields = getFieldsAsPlantUML();
			for (int i = 0; i < fields.length; i++)
				writer.println(fields[i]);

			for (int i = 0; i < methods.length; i++)
				writer.println(methods[i]);
			writer.println("}");
			writer.println("");
			writer.println("@enduml");

		} catch (FileNotFoundException e) {
			System.err.println("File unaccessible.");
			return false;
		}

		return true;
	}

	public String getMethodAsPlantUML(Method m) {
		String Access = "";
		String Name = m.getName();
		if ((m.getModifiers() & Modifier.PRIVATE) == Modifier.PRIVATE)
			Access = "-";
		if ((m.getModifiers() & Modifier.PROTECTED) == Modifier.PROTECTED)
			Access = "#";
		if ((m.getModifiers() & Modifier.PUBLIC) == Modifier.PUBLIC)
			Access = "+";
		if (Access.equals(""))	Access = "~"; // No accessmodifier
		String returnType = m.getReturnType().getSimpleName();
		return new StringBuilder().append(Access).append(returnType).append(" ").append(Name).append("()").toString();
	}

	public String getFieldAsPlantUML(Field f) {
		String Access = "";
		String Name = f.getName();
		if ((f.getModifiers() & Modifier.PRIVATE) == Modifier.PRIVATE)
			Access = "-";
		if ((f.getModifiers() & Modifier.PROTECTED) == Modifier.PROTECTED)
			Access = "#";
		if ((f.getModifiers() & Modifier.PUBLIC) == Modifier.PUBLIC)
			Access = "+";
		if (Access.equals(""))	Access = "~"; // No accessmodifier
		String fieldType = f.getType().getSimpleName();
		return new StringBuilder().append(Access).append(fieldType).append(' ').append(Name).toString();
	}

	public String[] getMethodsAsPlantUML() {

		Method[] methods = activeClass.getDeclaredMethods();
		String[] strings = new String[methods.length];

		for (int i = 0; i < methods.length; i++) {
			strings[i] = getMethodAsPlantUML(methods[i]);
		}
		return strings;
	}

	public String[] getFieldsAsPlantUML() {

		Field[] fields = activeClass.getDeclaredFields();
		String[] strings = new String[fields.length];

		for (int i = 0; i < fields.length; i++) {
			strings[i] = getFieldAsPlantUML(fields[i]);
		}
		return strings;
	}

}
