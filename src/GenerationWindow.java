import javax.swing.*;

import com.sun.xml.internal.fastinfoset.tools.PrintTable;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GenerationWindow extends JFrame {

    private Generation generation;

    private static final int maxWidth = 880;
    private static final int maxHeight = 1040;
    private static final int border = 60;
    private static final int preferredFieldWidth = 100;
    private static final int preferredFieldHeight = 100;
    private static final int heightForButtons = 160;
    private int currentWidth;
    private int currentHeight;
    private int currentFieldWidth;
    private int currentFieldHeight;

    public GenerationWindow(Generation gen) {
        super("Generation Window");
        this.generation = gen;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calculateSizes();
        this.setSize(currentWidth, currentHeight);
        this.setResizable(false);
        this.setContentPane(new DrawPane());
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Generation has been saved."); //do poprawki potem
            }
        });
        this.setVisible(true); 
    }

    private void calculateSizes() {
        currentWidth = border * 2 + preferredFieldWidth * generation.width;
        currentHeight = border * 2 + preferredFieldHeight * generation.height + heightForButtons;
        currentFieldWidth = preferredFieldWidth;
        currentFieldHeight = preferredFieldHeight;
        if (currentWidth > maxWidth) {
            currentWidth = maxWidth;
            currentFieldWidth = (maxWidth - border * 2) / generation.width;
        }
        if (currentHeight > maxHeight) {
            currentHeight = maxHeight;
            currentFieldHeight = (maxHeight - border * 2  - heightForButtons) / generation.height;
        }
    }

    public class DrawPane extends JPanel {
        public void paintComponent(Graphics g) {
        	
        	generation.printToConsole(); //debugging
        	
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, 0, currentWidth, currentHeight);
            Generation gen = WireWorld.generation;
            for (int x = 0; x < gen.width; x++) {
                for (int y = 0; y < gen.height; y++) {
                    if (gen.grid[x][y] == Generation.FieldState.FIELD_EMPTY) {
                        g.setColor(Color.WHITE);
                    } else if (gen.grid[x][y] == Generation.FieldState.FIELD_CONDUCTOR) {
                        g.setColor(Color.BLACK);
                    } else if (gen.grid[x][y] == Generation.FieldState.FIELD_HEAD) {
                        g.setColor(Color.RED);
                    } else if (gen.grid[x][y] == Generation.FieldState.FIELD_TAIL) {
                        g.setColor(Color.YELLOW);
                    }
                    g.fillRect(border + currentFieldWidth * x, border + currentFieldHeight * y, currentFieldWidth, currentFieldHeight);
                }
            }
        }
    }

}