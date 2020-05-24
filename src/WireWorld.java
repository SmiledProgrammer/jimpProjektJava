import java.sql.Savepoint;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

public class WireWorld implements Runnable{
	
	//private static Thread runThread;
	private static final int delay = 800; //opoznienie - szybkosc odswiezania
	
    public static Generation generation;
    public static int numberOfGenerations = 10;
    public static GenerationWindow window;


    public static void main(String[] args) {
        WireWorld world = new WireWorld();
        InputData.processArguments(args);
        generation.printToConsole();
        window = new GenerationWindow(generation);
        
        for (int i = 0; i< numberOfGenerations; i++)
        {
        	try {
        		Thread.sleep(delay);
        	} catch (InterruptedException e) {
        		// TODO Auto-generated catch block
			e.printStackTrace();
        	}

        	generation.grid[i+1][i+3] = Generation.FieldState.FIELD_HEAD;
        	//generation.calculateNextGeneration();
        	window.getContentPane().repaint();
        }
        if (FileManager.savedFilePath != null)
        	FileManager.saveGenerationToFile(generation);
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
>>>>>>> Stashed changes
}