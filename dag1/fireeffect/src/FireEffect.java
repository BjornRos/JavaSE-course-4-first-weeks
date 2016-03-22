
import java.io.DataInputStream;
import java.util.Random;
public class FireEffect {

	public static void main(String[] args) throws java.io.IOException, InterruptedException {
		
		int X_SIZE = 60;
		int Y_SIZE = 20;
		char[] c = {' ','░','▒','▓','█'};
		byte[][] b = new byte[X_SIZE][Y_SIZE];
		
		DataInputStream dis = new DataInputStream(System.in) ;
		try {
			while(dis.available() == 0) {
				// Seed
				for (int seed=0;seed<60;seed++)
				{
					Random r=new Random();
					b[seed][Y_SIZE-1]=(byte)r.nextInt(250);
				}
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				for (int y = Y_SIZE-1;y>0;y--)
				{
					System.out.println();
					for (int x = 0;x<X_SIZE;x++)
					{
						System.out.print(c[(b[x][y]&0xFF)/50]);
					}
				}
			}
		}
		catch (java.io.IOException e) { System.out.println("Det blev fel");}
		finally {}
		
	}

}
