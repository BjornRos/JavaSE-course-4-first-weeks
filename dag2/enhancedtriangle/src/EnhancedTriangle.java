
public class EnhancedTriangle {

		public static void main(String[] args) {
			int height = 0;
			if (args.length==1) { 
				try {
					height = Integer.parseInt(args[0]);
				} catch (NumberFormatException e) 
				{	
					System.err.println("Please use a number as a parameter - e.g. 9");
					System.exit(1);
				}
				
				int width = height*2+1;
				int halfWidth = width / 2;
				System.out.println("Triangle Height:" + height);
				for (int y = height; y>=1; y--)
				{
					for (int x = 1; x<=width;x++)
						if (x>=y && x-halfWidth<=halfWidth-y) System.out.print('*'); 
							else System.out.print(' ');
					System.out.println();
	
				}
			} else System.err.println("Please give (only) a heightparamater - e.g. EnhancedTriangle 9");
		}
}
