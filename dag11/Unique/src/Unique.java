import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Unique {
/**
 * @author Björn Ros
 * @param args
 * 
 * Funger i kommandotolken.
 */
	public static void main(String[] args) {
	
		
		Set<String> rows = new LinkedHashSet<String>();
		
		Scanner scan = new Scanner(System.in);
		
		while (scan.hasNextLine())
		{
				
			rows.add(scan.nextLine());
		}
		scan.close();
	    rows.forEach( s -> System.out.println(s));
	    
	    
		
		
		
	}

}
