package wireworld.system;

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
                    FileManager.openedFilePath = args[++i];
                } else {
                    System.err.println("Filepath to file to open wasn't specified after \"--open\".");
                }
            } else if (args[i].equals("--save")) {
                if (i + 1 < args.length) {
                    FileManager.savedFilePath = args[++i];
                    if(!FileManager.savedFilePath.endsWith(".gen")) {
                        System.out.println("The filename to save doesn't end with \".gen\". Adding \".gen\"");
                        FileManager.savedFilePath = FileManager.savedFilePath.concat(".gen");
                    }
                    System.out.println("Last generation will be saved in filepath \"" + FileManager.savedFilePath + "\".");
                } else {
                    System.err.println("Filepath to save file in wasn't specified after \"--save\". wireworld.system.Generation will not be saved.");
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
                        WireWorld.numberOfGenerations = number;
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
        }
        if (!fileToOpenSpecified && !newFileSpecified) {
            System.out.println("No file to open specified. Creating new default generation.");
            WireWorld.generation = new Generation();
        } else if (!fileToOpenSpecified && newFileSpecified) {
            System.out.println("No file to open specified. Creating new generation with the specified size.");
            WireWorld.generation = new Generation(width, height);
        } else if (fileToOpenSpecified && !newFileSpecified) {
            System.out.println("Loading the generation from file \"" + FileManager.openedFilePath + "\".");
            WireWorld.generation = FileManager.loadGenerationFromFile(FileManager.openedFilePath);
        } else if (fileToOpenSpecified && newFileSpecified) {
            System.out.println("Loading the generation from file \"" + FileManager.openedFilePath + "\" and extending its size to the new specified size.");
            WireWorld.generation = FileManager.loadGenerationFromFile(FileManager.openedFilePath);
            WireWorld.generation.extendToSize(width, height);
        }
        if (FileManager.openedFilePath != null && FileManager.savedFilePath != null && FileManager.openedFilePath.equals(FileManager.savedFilePath)) {
            System.out.println("Warning: The opened generation filepath is same as the saved generation filepath.");
        }
    }

}