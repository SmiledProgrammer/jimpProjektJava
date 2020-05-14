import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileManager {

	public static String savedFilePath;

	public static Generation loadGenerationFromFile(String filepath) {

		int[][] pregen = null;
		int width = -1;
		int height = -1;

		try {
			FileReader fr = new FileReader(filepath);
			BufferedReader br = new BufferedReader(fr);

			String line = null;
			String[] arguments = null;
			int i = 0;
			while ((line = br.readLine()) != null) {
				if (i == 0) {
					try {
						arguments = line.split(" ");
						width = Integer.parseInt(arguments[0]);
						height = Integer.parseInt(arguments[1]);

					} catch (Exception e) {
						System.out.println("Wrong file format!");
						e.printStackTrace();
					}
					i++;
				} else {
					arguments = null;
					arguments = line.split(" ");

					for (int x = 0; x < width; x++) {
						try {
							pregen[x][i - 1] = Integer.parseInt(arguments[x]);
						} catch (Exception e) {
							System.out.println("Wrong file format!");
							e.printStackTrace();
						}
					}
					i++;
				}
			}
			try {
				fr.close();
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException i) {
			i.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Generation gen = new Generation(width, height);
		return gen;

	}

	// do zaimplementowania
	// + sprawdzać czy plik ma rozszerzenie .gen
	// do usunięcia potem (dodane, żeby się kompilowało)

	public static void saveGenerationToFile() {
		// do zaimplementowania
		// zapisywanie do pliku: savedFilePath + ".gen"
	}

}