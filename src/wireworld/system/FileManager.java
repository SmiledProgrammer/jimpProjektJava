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

		int[][] pregen = new int[50][50];
		int width = -1;
		int height = -1;

		if (!(filepath.endsWith(".gen"))) {
			System.err.println("Nieprawid�owe rozszerzenie pliku!");
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
					gen.setCell(Generation.FieldState.FIELD_EMPTY, x, y);
				else if (pregen[x][y] == 1)
					gen.setCell(Generation.FieldState.FIELD_CONDUCTOR, x, y);
				else if (pregen[x][y] == 2)
					gen.setCell(Generation.FieldState.FIELD_HEAD, x, y);
				else if (pregen[x][y] == 3)
					gen.setCell(Generation.FieldState.FIELD_TAIL, x, y);
				else
					gen.setCell(Generation.FieldState.FIELD_EMPTY, x, y);
			}
		}

		System.out.println("Loaded the file correctly.");
		return gen;
	}


	public static void saveGenerationToFile(Generation gen) {
		
		if(!savedFilePath.endsWith(".gen")) {
			System.out.println("The filename doesn't end with .gen. Adding .gen");
			savedFilePath = savedFilePath.concat(".gen");

		}

		try {
			FileWriter fw = new FileWriter(savedFilePath);
			
			fw.write(gen.width + " " + gen.height);
			fw.write("\n");
			
			for (int i=0; i<gen.height; i++) {
				for (int j=0; j<gen.width; j++) {
	    			if (gen.getCell(j, i) == Generation.FieldState.FIELD_EMPTY) {
	    				fw.write( 0 + " ");
	    			} else if (gen.getCell(j, i) == Generation.FieldState.FIELD_CONDUCTOR) {
	    				fw.write( 1 + " ");
	    			} else if (gen.getCell(j, i) == Generation.FieldState.FIELD_HEAD) {
	    				fw.write( 2 + " ");
	    			} else if (gen.getCell(j, i) == Generation.FieldState.FIELD_TAIL) {
	    				fw.write( 3 + " ");
	    			}
	    		}
				fw.write("\n"); //nowa linia
			}

			fw.close();
			System.out.println("File successfully saved (" + savedFilePath +").");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Couldn't save the file!");
		}


	}

}