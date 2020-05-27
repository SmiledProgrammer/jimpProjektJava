import java.awt.*;
import java.sql.Savepoint;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

public class WireWorld implements Runnable {
	
	//private static Thread runThread;
	private static final int delay = 500; //opoznienie - szybkosc odswiezania
	
    public static Generation generation;
    public static int numberOfGenerations = 30;
    public static GenerationWindow window;


    public static void main(String[] args) {
        WireWorld world = new WireWorld();
        InputData.processArguments(args);
        generation.printToConsole();
        window = new GenerationWindow(generation);

        for (int i = 0; i < numberOfGenerations; i++) {
            if ( generation.isGenerationDead == false ) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                generation.calculateNextGeneration();
                window.getContentPane().revalidate();
                window.getContentPane().repaint();
            } else break;
        }

        while (true) {
            window.getContentPane().revalidate();
            window.getContentPane().repaint();
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

/*	@Override
	public void run() {
		while (true)
		{
			generation.calculateNextGeneration();
			window.getContentPane().repaint();
			i++;
			if (i==1) 
				generation.grid[3][5] = Generation.FieldState.FIELD_HEAD;
			try {
				Thread.currentThread();
				Thread.sleep(delay);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
 	*/

}