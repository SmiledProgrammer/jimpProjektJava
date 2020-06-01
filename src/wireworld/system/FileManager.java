package wireworld.system;

import wireworld.system.Generation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {

	public static String savedFilePath;
	public static String openedFilePath;

	public static Generation loadGenerationFromFile(String filepath) {

		int width = -1;
		int height = -1;
		Generation gen = null;

		if (!(filepath.endsWith(".gen"))) {
			System.err.println("Wrong file format!");
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
						System.err.println("Wrong file format!");
						e.printStackTrace();
					}
					i++;
					gen = new Generation(width, height);
				} else {
					arguments = line.split(" ");
					for (int x = 0; x < width; x++) {
						try {
							if (Integer.parseInt(arguments[x]) == 0)
								gen.setCell(Generation.FieldState.FIELD_EMPTY, x, i-1);
							else if (Integer.parseInt(arguments[x]) == 1)
								gen.setCell(Generation.FieldState.FIELD_CONDUCTOR, x, i-1);
							else if (Integer.parseInt(arguments[x]) == 2)
								gen.setCell(Generation.FieldState.FIELD_HEAD, x, i-1);
							else if (Integer.parseInt(arguments[x]) == 3)
								gen.setCell(Generation.FieldState.FIELD_TAIL, x, i-1);
							else
								gen.setCell(Generation.FieldState.FIELD_EMPTY, x, i-1);
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

		System.out.println("Loaded the file correctly.");
		return gen;
	}

	public static void saveGenerationToFile(Generation gen, String filename) {
		if (filename != null) {
			try {
				FileWriter fw = new FileWriter(filename);
				fw.write(gen.width + " " + gen.height);
				fw.write("\n");

				for (int i = 0; i < gen.height; i++) {
					for (int j = 0; j < gen.width; j++) {
						if (gen.getCell(j, i) == Generation.FieldState.FIELD_EMPTY) {
							fw.write(0 + " ");
						} else if (gen.getCell(j, i) == Generation.FieldState.FIELD_CONDUCTOR) {
							fw.write(1 + " ");
						} else if (gen.getCell(j, i) == Generation.FieldState.FIELD_HEAD) {
							fw.write(2 + " ");
						} else if (gen.getCell(j, i) == Generation.FieldState.FIELD_TAIL) {
							fw.write(3 + " ");
						}
					}
					fw.write("\n"); //nowa linia
				}
				fw.close();
				System.out.println("File successfully saved (" + filename + ").");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Couldn't save the file!");
			}
			System.out.println("Generation has been saved.");
		}
	}

}