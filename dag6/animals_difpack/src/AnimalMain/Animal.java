package AnimalMain;
import AnimalSubtypes.Cow;
import AnimalSubtypes.Crocodile;
import AnimalSubtypes.Crow;
import AnimalSubtypes.Lamb;
import AnimalSubtypes.Lion;

/**
 * 
 * @author Björn Ros
 *
 */
public abstract class Animal {

	public static void main(String[] args) {

		Animal[] Animals = new Animal[5];
		Animals[0] = new Lamb();
		Animals[1] = new Cow();
		Animals[2] = new Crocodile();
		Animals[3] = new Lion();
		Animals[4] = new Crow();

		for (int i = 0; i < 5; i++)
			System.out.println(Animals[i].makeSound());
	}

	public abstract String makeSound();

}
