package wireworld.system;

import wireworld.system.WireComponent.Orientation;

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

        if ( !(filepath.endsWith(".gen")) ) {
            System.err.println("Wrong file format!");
            return null;
        } else try {
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);
            String line;
            String[] arguments;
            int i = 0;
            while ((line = br.readLine()) != null) {
                arguments = line.split(" ");
                if ( i == 0 ) { //WCZYTYWANIE WYMIAROW
                    try {
                        width = Integer.parseInt(arguments[0]);
                        height = Integer.parseInt(arguments[1]);
                    } catch (Exception e) {
                        System.err.println("Wrong file format!");
                        e.printStackTrace();
                    }
                    i++;
                    gen = new Generation(width, height);
                } else if ( i < (height + 1) ) { //WCZYTYWANIE PLANSZY
                    for (int x = 0; x < width; x++) {
                        try {
                            if ( Integer.parseInt(arguments[x]) == 0 )
                                gen.setCell(Generation.FieldState.FIELD_EMPTY, x, i - 1);
                            else if ( Integer.parseInt(arguments[x]) == 1 )
                                gen.setCell(Generation.FieldState.FIELD_CONDUCTOR, x, i - 1);
                            else if ( Integer.parseInt(arguments[x]) == 2 )
                                gen.setCell(Generation.FieldState.FIELD_HEAD, x, i - 1);
                            else if ( Integer.parseInt(arguments[x]) == 3 )
                                gen.setCell(Generation.FieldState.FIELD_TAIL, x, i - 1);
                            else
                                gen.setCell(Generation.FieldState.FIELD_EMPTY, x, i - 1);
                        } catch (Exception e) {
                            System.out.println("Wrong file format!");
                            e.printStackTrace();
                        }
                    }
                    i++;
                } else if ( (arguments.length == 5) && (arguments[0].equalsIgnoreCase("OR") || arguments[0].equalsIgnoreCase("XOR") || arguments[0].equalsIgnoreCase("AND")) ) { //WCZYTYWANIE BRAMEK
                    WireComponentLibrary wireComponentLibrary = new WireComponentLibrary();
                    Orientation orientation = (arguments[3].equalsIgnoreCase("vertical")) ? Orientation.VERTICAL : Orientation.HORIZONTAL;
                    boolean flipped = (arguments[4].equalsIgnoreCase("true"));
                    int x = 0;
                    int y = 0;
					x = Integer.parseInt(arguments[1]);
					y = Integer.parseInt(arguments[2]);

                    if ( x >= 0 && y >= 0 && x < width && y < height ) {
                        if ( arguments[0].equalsIgnoreCase("OR") ) {
							wireComponentLibrary.placeComponent(gen, x, y, WireComponentLibrary.Type.OR_GATE, orientation, flipped);
                        } else if ( arguments[0].equalsIgnoreCase("XOR") ) {
							wireComponentLibrary.placeComponent(gen, x, y, WireComponentLibrary.Type.XOR_GATE, orientation, flipped);
                        } else if ( arguments[0].equalsIgnoreCase("AND") ) {
							wireComponentLibrary.placeComponent(gen, x, y, WireComponentLibrary.Type.AND_GATE, orientation, flipped);
                        }
                    }
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
		}
	}

}