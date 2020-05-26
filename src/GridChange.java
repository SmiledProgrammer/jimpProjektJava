public class GridChange {

    public int x;
    public int y;
    public Generation.FieldState state;

    public GridChange(int x, int y, Generation.FieldState state)
    {
        this.x = x;
        this.y = y;
        this.state = state;
    }

}
