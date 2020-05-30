package wireworld.system;

import wireworld.utils.Vector2D;

import java.util.ArrayList;

public class WireComponent {

    public enum Orientation { HORIZONTAL, VERTICAL };

    private Vector2D size;
    private ArrayList<Vector2D> structure;

    public WireComponent() {
        size = new Vector2D(0, 0);
        structure = new ArrayList<>();
    }

    public void addPointToStructure(int x, int y) {
        structure.add(new Vector2D(x, y));
        if (x + 1 > size.x) size.x = x + 1; //zwiększanie rozmiaru komponentu, jeśli nowo dodany punkt wybiega poza obecne rozmiary
        if (y + 1 > size.y) size.y = y + 1;
    }

    public void place(Generation gen, int x, int y, Orientation orientation, boolean flipped) {
        int horizontalSize, verticalSize;
        if (orientation == Orientation.HORIZONTAL) {
            horizontalSize = size.x;
            verticalSize = size.y;
        } else {
            horizontalSize = size.y;
            verticalSize = size.x;
        }
        if (x < 0 || x + horizontalSize > gen.width || y < 0 || y + verticalSize > gen.height) { //sprawdzanie czy komponent zmieści się na planszy
            System.err.println("The component can't be placed here. It would be outside of grid boundaries.");
            return;
        }
    }

    public ArrayList<Vector2D> getPoints(Orientation orientation, boolean flipped) {
        ArrayList<Vector2D> points = new ArrayList<>();
        System.arraycopy(structure, 0, points, 0, structure.size());
        if (orientation == Orientation.VERTICAL) {
            for (Vector2D v : points) {
                // TODO
            }
        }
        return points;
    }

}