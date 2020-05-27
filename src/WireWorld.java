import java.awt.*;
import java.sql.Savepoint;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

public class WireWorld implements Runnable {
	
	//private static Thread runThread;
	private static final int delay = 500; //opoznienie - szybkosc odswiezania
	
    public static Generation generation;
    public static int numberOfGenerations = 30;
    public static GenerationWindow window;

    public static boolean playing = false;
    public static boolean step_to_next = false;


    public static void main(String[] args) {
        WireWorld world = new WireWorld();
        InputData.processArguments(args);
        generation.printToConsole();
        window = new GenerationWindow(generation);
        

        while (true) {
            if (playing) {
                if ( generation.isGenerationDead == false ) {

                    generation.calculateNextGeneration();
                    window.getContentPane().revalidate();
                    window.getContentPane().repaint();

                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            } else if (step_to_next)  //it has to be pause (playing == false) to perform this
            {
                if ( generation.isGenerationDead == false ) {

                    generation.calculateNextGeneration();
                    window.getContentPane().revalidate();
                    window.getContentPane().repaint();
                    step_to_next = false; //only one step
                }
            }

            else {
                window.getContentPane().revalidate();
                window.getContentPane().repaint();
            }
        }
/*
        System.out.println("Stopped at generation #" + generation.generationNumber);
        if ( FileManager.savedFilePath != null )
            FileManager.saveGenerationToFile(generation);*/
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}


}