import java.text.NumberFormat;

public class InputData {

    public static void processArguments(String[] args) {
        boolean fileToOpenSpecified = false;
        boolean newFileSpecified = false;
        int width = 0;
        int height = 0;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--open")) {
                if (i + 1 < args.length) {
                    fileToOpenSpecified = true;
                } else {
                    System.err.println("Filepath to file to open wasn't specified after \"--open\".");
                }
            } else if (args[i].equals("--save")) {
                if (i + 1 < args.length) {
                    FileManager.savedFilePath = args[++i];
                    System.out.println("WireWorld will be saved in filepath \"" + args[i] + "\".");
                } else {
                    System.err.println("Filepath to save file in wasn't specified after \"--save\". Generation will not be saved.");
                }
            } else if (args[i].equals("--generate")) {
                if (i + 1 < args.length) {
                    int number = -1;
                    try {                        
                        number = Integer.parseInt(args[++i]);
                        } catch (NumberFormatException ex) {
                        System.err.println("String \"" + args[i] + "\" couldn't be parsed to an integer. Ignoring the argument.");
                        }
                    if (number >= 1) {
                            WireWorld.generation.numberOfGenerations = number;
                            System.out.println("Number of generations loaded correctly (" + number + ").");
                        } else {
                            System.out.println("Number of generations is smaller than 0 (" + number + "). Ignoring the argument.");
                        } 
                    
                    
                } else {
                    System.err.println("Number of generations wasn't specified after \"--generation\".");
                }
            } else if (args[i].equals("--new")) {
                if (i + 2 < args.length) {
                    try {
                        width = Integer.parseInt(args[++i]);
                        height = Integer.parseInt(args[++i]);
                        if (width >= 1 && height >= 1) {
                            System.out.println("Generation size loaded correctly (" + width + "x" + height + ").");
                            newFileSpecified = true;
                        } else if (width < 1) {
                            System.out.println("Width is smaller than 0 (" + width + "). Ignoring the argument.");
                        } else if (height < 1) {
                            System.out.println("Height is smaller than 0 (" + height + "). Ignoring the argument.");
                        }
                    } catch (NumberFormatException ex) {
                        System.err.println("String \"" + args[i] + "\" couldn't be parsed to an integer.");
                    }
                } else {
                    System.err.println("Expected width and size specified after \"--new\".");
                }
            } else {
                System.err.println("Couldn't recognize argument option: \"" + args[i] + "\".");
            }
            if (!fileToOpenSpecified && !newFileSpecified) {
                System.out.println("No file to open specified. Creating new default generation.");
                WireWorld.generation = new Generation();
            } else if (!fileToOpenSpecified && newFileSpecified) {
                System.out.println("No file to open specified. Creating new generation with the specified size.");
                WireWorld.generation = new Generation(width, height);
            } else if (fileToOpenSpecified && !newFileSpecified) {
                System.out.println("Loading the generation from the specified file.");
                WireWorld.generation = FileManager.loadGenerationFromFile(args[++i]);
            } else if (fileToOpenSpecified && newFileSpecified) {
                System.out.println("Loading the generation from the specified file and extending its size to the new specified size.");
                WireWorld.generation = FileManager.loadGenerationFromFile(args[++i]);
                WireWorld.generation.extendToSize(width, height);
            }
        }
    }

}