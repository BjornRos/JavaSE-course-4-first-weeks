/**
 * 
 * @author Björn Ros
 *
 */
public class Car {

	int doors;
	String model;
	String manufacturer;
	
	public static void main(String[] args){
		Car car = new Car(2,"RX-8","Mazda");
	}
	
	
	public Car (int doors, String model, String manufacturer)
	{
		this.doors=doors;
		this.model=model;
		this.manufacturer=manufacturer;
		
		System.out.println(manufacturer+" "+model+", "+doors+" dörrar");
		
	}
}
