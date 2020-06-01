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
        ArrayList<Vector2D> points = getPoints(orientation, flipped);
        for (Vector2D v : points)
            gen.setCell(Generation.FieldState.FIELD_CONDUCTOR, x + v.x, y + v.y);
    }

    public ArrayList<Vector2D> getPoints(Orientation orientation, boolean flipped) {
        ArrayList<Vector2D> points = new ArrayList<>();
        for (Vector2D v : structure) //kopiowanie tablicy
            points.add(new Vector2D(v.x, v.y));
        if (orientation == Orientation.VERTICAL) { //zamienianie "x" z "y" (zmiana orientacji)
            for (Vector2D v : points) {
                int tmp = v.x;
                v.x = v.y;
                v.y = tmp;
            }
        }
        if (flipped) { //lustrzane odbicie
            for (Vector2D v : points) {
                v.x = size.x - 1 - v.x;
                v.y = size.y - 1 - v.y;
            }
        }
        if (orientation == Orientation.VERTICAL && flipped == true) { //naprawienie obrócenia, które odsuwa punkty bramki daleko od punktu początkowego
            int minX = Integer.MAX_VALUE;
            for (Vector2D v : points) { //znalezienie najbardziej wysuniętego w lewo punktu
                if (v.x < minX)
                    minX = v.x;
            }
            for (Vector2D v : points) {
                v.x = v.x - minX; //przesunięcie wszystkich punktów w lewo aż do punktu początkowego
                v.y = v.y + (size.x - size.y); //przesunięcie wszystkich punktów w dół w zależności od różnicy rozmiarów bramki (przesunięcie do punktu początkowego)
            }
        }
        return points;
    }

}