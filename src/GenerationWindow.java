import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GenerationWindow extends JFrame {

    private static final int width = 800; //zmienić potem, żeby dostosowywało się do wielkości generacji
    private static final int height = 800;
    private static final int spacing = 3;

    public GenerationWindow() {
        super("Generation Window");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setResizable(false);
        this.setContentPane(new DrawPane());
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Generation has been saved.");
            }
        });
        this.setVisible(true);
    }

    private class DrawPane extends JPanel {
        public void paintComponent(Graphics g) {
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, 0, width, height);
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
                    //g.fillRect();
                }
            }
        }
    }

}