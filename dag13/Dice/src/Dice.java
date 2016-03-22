import java.util.Random;

/**
 * Exercise 6.4
 * @author Björn Ros
 *
 */
public class Dice {

	public static void main (String[] args) {
		
		System.out.println("Long Live the King: "+new Dice().throwDice());
	}
	
	public int throwDice () {return new Random().nextInt(6)+1;}
	
}
