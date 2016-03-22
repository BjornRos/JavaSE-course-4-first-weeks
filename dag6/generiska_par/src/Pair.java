/**
 * Exercise 3.7
 * 
 * @author Björn Ros
 *
 */
public class Pair<T> {

	private T value1, value2;

	public Pair(T v1, T v2) {
		value1 = v1;
		value2 = v2;
	}

	public T first() {
		return value1;
	}

	public T second() {
		return value2;
	}

	public static void main(String[] args) {

		Pair<String> p = new Pair<>("Hello","World!");
		System.out.printf("%s %s\n",p.first(),p.second());
	}

}
