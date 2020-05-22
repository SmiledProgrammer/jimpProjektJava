import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

public class WireWorld {

    public static Generation generation;
    public static int numberOfGenerations;
    public static GenerationWindow window;

    public static void main(String[] args) {
        InputData.processArguments(args);
        generation.printToConsole();
        window = new GenerationWindow(generation);
    }

}