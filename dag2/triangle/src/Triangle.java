

public class Triangle {

	public static void main(String[] args) {
		final int HEIGHT = 10;
		final int WIDTH = HEIGHT*2+1;
		final int HALF_WIDTH = WIDTH / 2;
		for (int y = HEIGHT; y>=1; y--)
		{
			for (int x = 1; x<=WIDTH;x++)
				if (x>=y && x-HALF_WIDTH<=HALF_WIDTH-y) System.out.print('*'); 
					else System.out.print(' ');
			System.out.println();

		}
	}
}
