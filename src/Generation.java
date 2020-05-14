public class Generation {

    private final int defaultGenerationWidth = 20;
    private final int defaultGenerationHeight = 10;

    public enum FieldState { FIELD_EMPTY, FIELD_CONDUCTOR, FIELD_HEAD, FIELD_TAIL }

    public FieldState[][] grid;
    public int numberOfGenerations;
    public int width, height;

    public Generation() {
        width = defaultGenerationWidth;
        height = defaultGenerationHeight;
        grid = new FieldState[width][height];
    }

    public Generation(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new FieldState[width][height];
    }

    public void extendToSize(int width, int height) { //funkcja rozszerza lub zachowuje rozmiar generacji
        /* do zaimplementowania */
    }

}