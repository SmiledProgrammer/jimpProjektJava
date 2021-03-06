package wireworld.gui;

import wireworld.system.Generation;
import wireworld.system.WireComponent;
import wireworld.system.WireComponentLibrary;
import wireworld.utils.Utils;
import wireworld.utils.Vector2D;
import wireworld.system.WireWorld;

import java.awt.*;
import java.util.List;

public class GenerationGrid implements WindowComponent {

    private GenerationWindow window;
    private Generation generation;
    private int x, y;
    private int fieldWidth, fieldHeight;
    private Generation.FieldState stateBeingChanged;

    private WireComponentLibrary.Type chosenGateToPlace = null;
    private WireComponent.Orientation chosenGateOrientation = WireComponent.Orientation.HORIZONTAL;
    private boolean chosenGateFlipped = false;

    public GenerationGrid(GenerationWindow window, Generation gen, int x, int y, int fieldWidth, int fieldHeight) {
        this.window = window;
        this.generation = gen;
        this.x = x;
        this.y = y;
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
    }

    private Vector2D getFieldWithMouseOn() {
        Point p = window.getContentPane().getMousePosition();
        if (p != null) {
            int chosenFieldX = (p.x - x) / fieldWidth;
            int chosenFieldY = (p.y - y) / fieldHeight;
            if (chosenFieldX >= 0 && chosenFieldX < generation.width && chosenFieldY >= 0 && chosenFieldY < generation.height)
                return new Vector2D(chosenFieldX, chosenFieldY);
            else
                return new Vector2D(-1, -1);
        }
        return new Vector2D(-1, -1);
    }

    @Override
    public void paint(Graphics g) {
        paintGrid(g); //rysowanie planszy
        if (!window.isBlockingEditing()) { //blokowanie podświetlania aktualnego pola, jeśli menu z bramkami logicznymi jest otwarte
            if (chosenGateToPlace == null) { //rysowanie po planszy tylko, jeśli żadna bramka logiczna nie jest aktualnie wybrana
                paintSelection(g); //jeśli nad polem jest myszka, to dodatkowy kolorek nad tym polem
            } else { //rysowanie wybranej bramki logicznej
                paintChosenGate(g);
            }
        }
    }

    private void paintGrid(Graphics g) {
        Generation gen = WireWorld.generation;
        for (int x = 0; x < gen.width; x++) {
            for (int y = 0; y < gen.height; y++) {
                Generation.FieldState state = gen.getCell(x, y);
                if (state == Generation.FieldState.FIELD_EMPTY) {
                    g.setColor(Color.WHITE);
                } else if (state == Generation.FieldState.FIELD_CONDUCTOR) {
                    g.setColor(Color.BLACK);
                } else if (state == Generation.FieldState.FIELD_HEAD) {
                    g.setColor(Color.RED);
                } else if (state == Generation.FieldState.FIELD_TAIL) {
                    g.setColor(Color.YELLOW);
                }
                g.fillRect(this.x + fieldWidth * x, this.y + fieldHeight * y, fieldWidth, fieldHeight);
            }
        }
    }

    private void paintSelection(Graphics g) {
        Vector2D selectedField = getFieldWithMouseOn();
        if (selectedField.x != -1 && selectedField.y != -1) {
            Color inverse = Utils.inverseColor(g.getColor());
            Color c = new Color(inverse.getRed(), inverse.getGreen(), inverse.getBlue(), 64);
            g.setColor(c);
            g.fillRect(this.x + fieldWidth * selectedField.x, this.y + fieldHeight * selectedField.y, fieldWidth, fieldHeight);
        }
    }

    private void paintChosenGate(Graphics g) {
        Generation gen = WireWorld.generation;
        Vector2D selectedField = getFieldWithMouseOn();
        if (chosenGateToPlace != null && selectedField.x != -1 && selectedField.y != -1) {
            List<Vector2D> points = WireWorld.componentLibrary.getComponentPoints(chosenGateToPlace, chosenGateOrientation, chosenGateFlipped);
            g.setColor(new Color(0, 0, 0, 80));
            for (Vector2D v : points) {
                int actualX = (selectedField.x + v.x);
                int actualY = (selectedField.y + v.y);
                if (actualX >= 0 && actualX < gen.width && actualY >= 0 && actualY < gen.height)
                    g.fillRect(this.x + fieldWidth * actualX, this.y + fieldHeight * actualY, fieldWidth, fieldHeight);
            }
        }
    }

    @Override
    public void clickAction() {
        Vector2D selectedField = getFieldWithMouseOn();
        if (selectedField.x != -1 && selectedField.y != -1) {
            if (chosenGateToPlace == null) {
                Generation.FieldState selectedFieldState = generation.getCell(selectedField.x, selectedField.y);
                if (stateBeingChanged != null && selectedFieldState == stateBeingChanged) {
                    if (selectedFieldState == Generation.FieldState.FIELD_EMPTY) {
                        generation.setCell(Generation.FieldState.FIELD_CONDUCTOR, selectedField.x, selectedField.y);
                    } else if (selectedFieldState == Generation.FieldState.FIELD_CONDUCTOR) {
                        generation.setCell(Generation.FieldState.FIELD_HEAD, selectedField.x, selectedField.y);
                    } else if (selectedFieldState == Generation.FieldState.FIELD_HEAD || selectedFieldState == Generation.FieldState.FIELD_TAIL) {
                        generation.setCell(Generation.FieldState.FIELD_EMPTY, selectedField.x, selectedField.y);
                    }
                }
            } else {
                WireWorld.componentLibrary.placeComponent(generation, selectedField.x, selectedField.y, chosenGateToPlace, chosenGateOrientation, chosenGateFlipped);
                window.resetMouseDown();
                chosenGateToPlace = null;
            }
        }
    }

    public void updateStateBeingChanged() {
        Vector2D selectedField = getFieldWithMouseOn();
        if (selectedField.x != -1 && selectedField.y != -1)
            stateBeingChanged = generation.getCell(selectedField.x, selectedField.y);
        else
            stateBeingChanged = null;
    }

    public void rotateChosenComponent() {
        if (chosenGateOrientation == WireComponent.Orientation.HORIZONTAL && chosenGateFlipped == false) {
            chosenGateOrientation = WireComponent.Orientation.VERTICAL;
            chosenGateFlipped = true;
        } else if (chosenGateOrientation == WireComponent.Orientation.VERTICAL && chosenGateFlipped == true) {
            chosenGateOrientation = WireComponent.Orientation.HORIZONTAL;
            chosenGateFlipped = true;
        } else if (chosenGateOrientation == WireComponent.Orientation.HORIZONTAL && chosenGateFlipped == true) {
            chosenGateOrientation = WireComponent.Orientation.VERTICAL;
            chosenGateFlipped = false;
        } else if (chosenGateOrientation == WireComponent.Orientation.VERTICAL && chosenGateFlipped == false) {
            chosenGateOrientation = WireComponent.Orientation.HORIZONTAL;
            chosenGateFlipped = false;
        }
    }

    public void setChosenGateType(WireComponentLibrary.Type gate) {
        chosenGateToPlace = gate;
    }

    public int getGenerationWidth() {
        return generation.width;
    }

    public int getGenerationHeight() {
        return generation.height;
    }

    public int getFieldWidth() {
        return fieldWidth;
    }

    public int getFieldHeight() {
        return fieldHeight;
    }

    public boolean isGateChosen() {
        return (chosenGateToPlace != null);
    }

}