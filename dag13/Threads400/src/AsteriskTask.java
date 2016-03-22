
public class AsteriskTask implements Runnable {

	private int id = 0;

	public AsteriskTask(int id) {
		this.id = id;
	}

	@Override
	public void run() {

		for (int i = 1; i <= 5; i++) {
			StringBuilder sb = new StringBuilder("Task ").append(id).append(": ");
			for (int j = 1; j<=i;j++) sb.append("*");
			System.out.println(sb.toString());

		}
	}

}
