import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;



public class FileManager {

	public static String savedFilePath;

	public static Generation loadGenerationFromFile(String filepath) {

		int[][] pregen = new int[50][50];
		int width = -1;
		int height = -1;

		if (!(filepath.endsWith(".gen"))) {
			System.err.println("Nieprawid³owe rozszerzenie pliku!");
			return null;
		} else try {
			FileReader fr = new FileReader(filepath);
			BufferedReader br = new BufferedReader(fr);

			String line = null; 
			String[] arguments = null;
			int i = 0;
			while ( (line = br.readLine() ) != null) {
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
					
					arguments = line.split(" ");
					
					//for (int j=0; j<arguments.length; j++) System.out.println(arguments[j]);
 
					for (int x = 0; x < width; x++) {
						try {
							pregen[x][i - 1] = Integer.parseInt(arguments[x].toString());
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
		for (int x=0; x<width; x++) {
			for (int y = 0; y < height; y++) {
				if (pregen[x][y] == 0)
					gen.grid[x][y] = Generation.FieldState.FIELD_EMPTY;
				else if (pregen[x][y] == 1)
					gen.grid[x][y] = Generation.FieldState.FIELD_CONDUCTOR;
				else if (pregen[x][y] == 2)
					gen.grid[x][y] = Generation.FieldState.FIELD_HEAD;
				else if (pregen[x][y] == 3)
					gen.grid[x][y] = Generation.FieldState.FIELD_TAIL;
				else
					gen.grid[x][y] = Generation.FieldState.FIELD_EMPTY;
			}
		}

		System.out.println("Loaded the file correctly.");
		return gen;
	}

	// do zaimplementowania
	// + sprawdzaæ czy plik ma rozszerzenie .gen
	// do usuniêcia potem (dodane, ¿eby siê kompilowa³o)

	public static void saveGenerationToFile() {
		// do zaimplementowania
		// zapisywanie do pliku: savedFilePath + ".gen"
	}

}