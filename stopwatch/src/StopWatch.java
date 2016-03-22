import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

/**
 * 
 * @author Björn Ros
 *
 * 
 */
public class StopWatch {

	/**
	 * 
	 * @param args
	 *            First argument may be -"repeat:XX" to run the test a number of
	 *            times. The rest are the argumentline to use at (Windows)
	 *            commandpromt.
	 * @return Statistics for the run(s) in milliseconds.
	 */
	public static long runOnce(String[] args) {
		ProcessBuilder proc = new ProcessBuilder();
		proc.command(args);
		proc.redirectOutput(ProcessBuilder.Redirect.INHERIT);
		proc.redirectInput(ProcessBuilder.Redirect.INHERIT);
		proc.redirectError(ProcessBuilder.Redirect.INHERIT);
		proc.directory(new File("."));

		Instant start = Instant.now();
		try {

			proc.start().waitFor();

		} catch (IOException ioe) {
			System.err.println("Unexecutable commandline.");
			System.err.println(ioe.getMessage());
			System.exit(2);
		} catch (InterruptedException ie) {
			System.err.println("Command Interrupted.");
			System.err.println(ie.getMessage());
			System.exit(3);
		}
		Instant stop = Instant.now();
		return Duration.between(start, stop).toMillis();
	}

	public static void exitWithInstructions() {
		System.err.println("No program to execute. Please pass a commandline as parameter.");
		System.err.println("On the offchance you want to execute a native command (on windows) that syntax would be:");
		System.err.println("\"cmd /C <command>\"");
		System.exit(1);

	}

	public static void main(String[] args) {

		if (args.length == 0)
			exitWithInstructions();

		if (args[0].matches("-repeat:(\\d+)")) {
			if (args.length == 1)
				exitWithInstructions();

			String[] newArgs = new String[args.length - 1];
			for (int i = 1; i < args.length; i++)
				newArgs[i - 1] = args[i];

			int repetitions = Integer.parseInt(args[0].substring(8));

			double[] timings = new double[repetitions];
			for (int i = 0; i < repetitions; i++) {
				timings[i] = runOnce(newArgs);
			}

			Arrays.sort(timings);
			double median = timings[(timings.length-1)/2];			
			double min = timings[0];
			double max = timings[timings.length-1];
			double mean =0;
			for (int i = 0;i<timings.length;i++)
				mean += timings[i];
			mean /= timings.length;
			
			double varianceSquaredSum = 0;
			for (int i = 0;i<timings.length;i++)
				if (timings[i]-mean!=0) varianceSquaredSum += Math.sqrt(Math.abs(timings[i]-mean));
			double stnd = Math.sqrt(varianceSquaredSum/(timings.length-1));
			
			//double mean = StatUtils.mean(timings);
			//double median = StatUtils.percentile(timings, 50d);
			//double min = StatUtils.min(timings);
			//double max = StatUtils.max(timings);
			//double stnd = FastMath.sqrt(StatUtils.variance(timings));

			System.out.println("-------=> Executiontime <=---------");
			System.out.printf(" Repetitions: %d\n", repetitions);
			System.out.printf(" Mean: %.2fms\n", mean);
			System.out.printf(" Median: %.0fms\n", median);
			System.out.printf(" Max: %.0fms\n", max);
			System.out.printf(" Min: %.0fms\n", min);

			System.out.printf(" Standard deviation: %.2fms\n", stnd);
			System.out.println("-----------------------------------");

		} else {
			System.out.println("-----------------------------------");
			System.out.printf(" Executiontime: %d\n", runOnce(args));

		}

	}

}
