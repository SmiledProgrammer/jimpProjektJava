package wireworld.gui;

import wireworld.system.*;

import javax.swing.*;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GenerationWindow extends JFrame implements MouseListener {

    private static final int minWidth = 640;
    private static final int maxHeight = 960;
    private static final int maxWidth = 1600;
    private static final int preferredFieldSize = 100;
    private static final int border = 60;
    private static final int heightForButtons = 160;
    private int width, height;
    private int fieldWidth, fieldHeight;

    private GenerationGrid grid;
    private PlayButton playButton;
    private Button nextButton;
    private Button gateButton;
    private Button saveButton;

    private boolean mouseDown = false;
    private boolean blockingEditing = false;

    public GenerationWindow(Generation gen) {
        super("Generation Window");
        calculateSize(gen);
        grid = new GenerationGrid(this, gen, border, border, fieldWidth, fieldHeight);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setResizable(false);
        this.setContentPane(new DrawPane());
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                FileManager.saveGenerationToFile(gen, FileManager.savedFilePath);
            }
        });
        this.addMouseListener(this);
        setupButtons();
        this.setVisible(true);
    }

    private void calculateSize(Generation gen) {
        int tmpHeight = border * 2 + gen.height * preferredFieldSize + heightForButtons;
        if (tmpHeight > maxHeight) {
            height = maxHeight;
            fieldHeight = (maxHeight - border * 2 - heightForButtons) / gen.height;
        } else {
            height = tmpHeight;
            fieldHeight = preferredFieldSize;
        }

        int tmpWidth = border * 2 + gen.width * fieldHeight;
        if (tmpWidth < minWidth) {
            width = minWidth;
            fieldWidth = (minWidth - border * 2) / gen.width;
        } else if (tmpWidth > maxWidth) {
            width = maxWidth;
            fieldWidth = (maxWidth - border * 2) / gen.width;
        } else {
            width = tmpWidth;
            fieldWidth = fieldHeight;
        }
    }

    private void setupButtons() {
        int buttonsSpacing = (grid.getFieldWidth() * grid.getGenerationWidth() - PlayButton.width - NextButton.width - GateButton.width - SaveButton.width) / 3;
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

    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            if (!blockingEditing) {
                mouseDown = true;
                grid.updateStateBeingChanged();
            }
        }
        if (SwingUtilities.isRightMouseButton(e)) { //obracanie wybranej bramki logicznej
            grid.rotateChosenComponent();
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            playButton.clickAction();
            nextButton.clickAction();
            gateButton.clickAction();
            saveButton.clickAction();
            mouseDown = false;
        }
    }
    @Override
    public void mouseEntered(MouseEvent e) { }
    @Override
    public void mouseExited(MouseEvent e) { }
    @Override
    public void mouseClicked(MouseEvent e) { }

    public class DrawPane extends JPanel {
        public void paintComponent(Graphics g) {
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, 0, width, height); //szare tło

            if (grid.isGateChosen()) { //rysowanie informacji o tym jak obrócić bramkę, jeśli jakaś jest wybrana
                g.setColor(Color.YELLOW);
                g.setFont(new Font("tipFont", Font.BOLD, 24));
                g.drawString("Tip: To rotate the gate press Right Click.", 20, 40);
            }

            grid.paint(g);
            playButton.paint(g);
            nextButton.paint(g);
            gateButton.paint(g);
            saveButton.paint(g);
        }
    }

    public void update() {
        if (mouseDown) {
            grid.clickAction();
        }
    }

    public void setBlockingEditing(boolean value) {
        blockingEditing = value;
    }

    public void setChosenGateType(WireComponentLibrary.Type gate) {
        grid.setChosenGateType(gate);
    }

    public void resetChosenGate() {
        grid.setChosenGateType(null);
    }

    public void resetMouseDown() {
        mouseDown = false;
    }

    public void pause() {
        playButton.pause();
    }

    public boolean isBlockingEditing() {
        return blockingEditing;
    }

}