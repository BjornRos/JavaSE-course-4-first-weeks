/**
 * 
 * @author Björn Ros
 *
 * Create jar-file with this command in /bin:
 * jar cfe Animal.jar Animal Animal.class Lamb.class Cow.class Crocodile.class Lion.class Crow.class
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

class Lamb extends Animal {
	// @Override
	public String makeSound() {
		return ("Bääääääääää..");
	}

}

class Cow extends Animal {
	// @Override
	public String makeSound() {
		return ("Muuuuuuuuuu..");
	}
}

class Crocodile extends Animal {
	// @Override
	public String makeSound() {
		return ("Chow-chow-chow.");
	}
}

class Crow extends Animal {
	// @Override
	public String makeSound() {
		return ("Kraa-kraa, kraa-kraa.");
	}
}

class Lion extends Animal {
	// @Override
	public String makeSound() {
		return ("RRRAAAAAAWWWWWRRRRR.");
	}
}
