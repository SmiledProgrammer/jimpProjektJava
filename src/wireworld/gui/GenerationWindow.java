package wireworld.gui;

import wireworld.system.Generation;
import wireworld.system.WireWorld;

import javax.swing.*;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GenerationWindow extends JFrame implements MouseListener {

    private static final int width = 800;
    private static final int height = 960;
    private static final int border = 60;
    private static final int heightForButtons = 160;
    private int buttonsSpacing;

    private GenerationGrid grid;
    private Button playButton;
    private Button nextButton;
    private Button gateButton;
    private Button saveButton;

    public GenerationWindow(Generation gen) {
        super("Generation Window");
        setupGrid(gen);
        calculateSizes();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setResizable(false);
        this.setContentPane(new DrawPane());
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Generation has been saved."); //do poprawki potem
            }
        });
        this.addMouseListener(this);
        setupButtons();
        this.setVisible(true);
    }

    private void setupGrid(Generation gen) {
        int fieldWidth = (width - border * 2) / gen.width;
        int fieldHeight = (height - border * 2  - heightForButtons) / gen.height;
        grid = new GenerationGrid(this, gen, border, border, fieldWidth, fieldHeight);
    }

    private void setupButtons() {
        int buttonsY = border + grid.getFieldHeight() * grid.getGenerationHeight() + (heightForButtons - border) / 2;

        int pauseButtonX = border;
        playButton = new PlayButton(this, pauseButtonX, buttonsY);

        int nextButtonX = border + PlayButton.width + buttonsSpacing;
        nextButton = new NextButton(this, nextButtonX, buttonsY);

        int gateButtonX = border + PlayButton.width + buttonsSpacing + NextButton.width + buttonsSpacing;
        gateButton = new GateButton(this, gateButtonX, buttonsY);

        int saveButtonX = border + PlayButton.width + buttonsSpacing + NextButton.width + buttonsSpacing + GateButton.width + buttonsSpacing;
        saveButton = new SaveButton(this, saveButtonX, buttonsY);
    }

    private void calculateSizes() {
        buttonsSpacing = (grid.getFieldWidth() * grid.getGenerationHeight() - PlayButton.width - NextButton.width - GateButton.width - SaveButton.width) / 3;
    }

    @Override
    public void mousePressed(MouseEvent e) { }
    @Override
    public void mouseReleased(MouseEvent e) { }
    @Override
    public void mouseEntered(MouseEvent e) { }
    @Override
    public void mouseExited(MouseEvent e) { }
    @Override
    public void mouseClicked(MouseEvent e) {
        grid.clickAction();
        playButton.clickAction();
        nextButton.clickAction();
        gateButton.clickAction();
        saveButton.clickAction();
    }

    public class DrawPane extends JPanel {
        public void paintComponent(Graphics g) {
        	//generation.printToConsole(); //debugging
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, 0, width, height);

            grid.paint(g);
            playButton.paint(g);
            nextButton.paint(g);
            gateButton.paint(g);
            saveButton.paint(g);
        }
    }

}