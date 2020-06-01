package wireworld.system;

import wireworld.gui.GenerationWindow;
import wireworld.gui.PlayButton;

import java.awt.font.TextHitInfo;

public class WireWorld {
	
	//private static Thread runThread;
	private static final int delay = 100; //opoznienie - szybkosc odswiezania
	
    public static Generation generation;
    public static int numberOfGenerations = 1000;
    public static GenerationWindow window;

    public static boolean playing = false;
    public static boolean step_to_next = false;
    public static boolean has_paused_already = false;

    public static WireComponentLibrary componentLibrary = new WireComponentLibrary();

    public static void main(String[] args) throws InterruptedException {
        InputData.processArguments(args);
        //generation.printToConsole();
        window = new GenerationWindow(generation);

        while (true) {
            if (generation.generationNumber == numberOfGenerations && playing == true && has_paused_already==false){
                System.out.println("Generation number is " + numberOfGenerations+". Pausing.");
                window.pause();
                has_paused_already = true;
            }
            if (playing) {
                if (!generation.isGenerationDead) {
                    generation.calculateNextGeneration();
                    System.out.println("Generation #" + generation.generationNumber);
                    window.getContentPane().revalidate();
                    window.getContentPane().repaint();
                    Thread.sleep(1);
                    window.update();

                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    window.pause();
                }
            } else if (step_to_next) {         //it has to be pause (playing == false) to perform this
                    generation.calculateNextGeneration();
                    System.out.println("Generation #" + generation.generationNumber);
                    window.getContentPane().revalidate();
                    window.getContentPane().repaint();
                    Thread.sleep(1);
                    window.update();
                    if (generation.generationNumber == numberOfGenerations) System.out.println("Generation number is " + numberOfGenerations);
                    step_to_next = false; //only one step
            } else {
                window.getContentPane().revalidate();
                window.getContentPane().repaint();
                Thread.sleep(1);
                window.update();
                generation.isGenerationDead = generation.checkIfGenerationIsDead();
                Thread.sleep(10);
            }
        }
    }

}