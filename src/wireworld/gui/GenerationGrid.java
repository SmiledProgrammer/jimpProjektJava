package wireworld.gui;

import wireworld.system.Generation;
import wireworld.utils.Utils;
import wireworld.utils.Vector2D;
import wireworld.system.WireWorld;

import java.awt.*;

public class GenerationGrid implements WindowComponent {

    private GenerationWindow window;
    private Generation generation;
    private int x, y;
    private int fieldWidth, fieldHeight;
    private Generation.FieldState stateBeingChanged;

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
            if (chosenFieldX < generation.width && chosenFieldY < generation.height)
                return new Vector2D(chosenFieldX, chosenFieldY);
            else
                return new Vector2D(-1, -1);
        }
        return new Vector2D(-1, -1);
    }

    @Override
    public void paint(Graphics g) {
        Generation gen = WireWorld.generation;
        Vector2D selectedField = getFieldWithMouseOn();
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
                if (!window.isBlockingEditing()) { //blokowanie podświetlania aktualnego pola, jeśli menu z bramkami logicznymi jest otwarte
                    if (x == selectedField.x && y == selectedField.y) { //jeśli nad polem jest myszka, to dodatkowy kolorek
                        Color inverse = Utils.inverseColor(g.getColor());
                        Color c = new Color(inverse.getRed(), inverse.getGreen(), inverse.getBlue(), 64);
                        g.setColor(c);
                        g.fillRect(this.x + fieldWidth * x, this.y + fieldHeight * y, fieldWidth, fieldHeight);
                    }
                }
            }
        }
    }

    @Override
    public void clickAction() {
        Vector2D selectedField = getFieldWithMouseOn();
        if (selectedField.x >= 0 && selectedField.x < generation.width && selectedField.y >= 0 && selectedField.y < generation.height) {
            Generation.FieldState selectedFieldState = generation.getCell(selectedField.x, selectedField.y);
            if (stateBeingChanged != null && selectedFieldState == stateBeingChanged) {
                if (selectedFieldState == Generation.FieldState.FIELD_EMPTY) {
                    generation.setCell(Generation.FieldState.FIELD_CONDUCTOR, selectedField.x, selectedField.y);
                } else if (selectedFieldState == Generation.FieldState.FIELD_CONDUCTOR) {
                    generation.setCell(Generation.FieldState.FIELD_HEAD, selectedField.x, selectedField.y);
                } else if (selectedFieldState == Generation.FieldState.FIELD_HEAD) {
                    generation.setCell(Generation.FieldState.FIELD_EMPTY, selectedField.x, selectedField.y);
                }
            }
        }
    }

    public void updateStateBeingChanged() {
        Vector2D selectedField = getFieldWithMouseOn();
        if (selectedField.x >= 0 && selectedField.x < generation.width && selectedField.y >= 0 && selectedField.y < generation.height)
            stateBeingChanged = generation.getCell(selectedField.x, selectedField.y);
        else
            stateBeingChanged = null;
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

}