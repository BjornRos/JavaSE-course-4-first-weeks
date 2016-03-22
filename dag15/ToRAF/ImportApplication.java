import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public final class ImportApplication {
	private static final Charset CHARSET = Charset.forName("UTF-8");

	private static class Stats {
		private final int key;
		private final int value;

		Stats() {
			this(0, 0);
		}

		Stats(int key, int value) {
			this.key = key;
			this.value = value;
		}

		public int getKey() {
			return key;
		}

		public int getValue() {
			return value;
		}

		public Stats max(Stats other) {
			return new Stats(Math.max(key, other.getKey()), Math.max(value, other.getValue()));
		}
	}

	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.printf("Usage: java %s <input file> <output file>%n", ImportApplication.class.getName());
			System.exit(1);
		}

		final Path in = Paths.get(args[0]);
		final Path out = Paths.get(args[1]);

		try (final RandomAccessFile raf = new RandomAccessFile(out.toFile(), "rw")) {
			final Stats stats = statistics(in);
			System.out.printf("Key field size  : %3d bytes%nValue field size: %3d bytes%n", stats.getKey(),
					stats.getValue());

			Files.lines(in, CHARSET).forEach(l -> writeRecord(raf, l, stats));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	private static void writeRecord(RandomAccessFile raf, String line, Stats stats) {
		String[] values = line.split(":");
		writeField(raf, values[0], stats.getKey());
		writeField(raf, values[1], stats.getValue());
	}

	private static void writeField(RandomAccessFile raf, String contents, int length) {
		byte[] field = Arrays.copyOf(contents.getBytes(CHARSET), length);

		try {
			raf.write(field);
		} catch (IOException e) {
			throw new RuntimeException("Unable to write.", e);
		}
	}

	private static Stats statistics(final Path in) throws IOException {
		return Files.lines(in, Charset.forName("UTF-8")).map((s) -> {
			String[] fields = s.split(":");
			return new Stats(fields[0].getBytes().length, fields[1].getBytes().length);
		}).reduce(new Stats(), Stats::max);
	}
}
