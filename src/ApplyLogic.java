import java.util.ArrayList;


public class ApplyLogic extends Generation {

    public  FieldState[][] grid;

    public ArrayList<GridChange> changes = new ArrayList<GridChange>();

    public ApplyLogic(Generation prev_gen) {
        this.height = prev_gen.height;
        this.width = prev_gen.width;
        grid = prev_gen.grid;
        calculate();
        isGenerationDead = checkIfGenerationIsDead();
    }

    public void calculate()
    {
        replaceCells2(grid,FieldState.FIELD_TAIL,FieldState.FIELD_CONDUCTOR);
        replaceCells2(grid,FieldState.FIELD_HEAD, FieldState.FIELD_TAIL);
        calculateHeads2();
        applyChanges(grid);
    }


    public void applyChanges(FieldState[][] grid) //you can choose, what grid you would like to apply changes to.
    {
        for (GridChange G: changes)
            grid[G.x][G.y] = G.state;
    }

    //OGOLNA FUNKCJA DO ZAMIANY JEDNEGO STANU NA INNY

    public void replaceCells2(FieldState[][] oldGrid, FieldState oldOne, FieldState newOne)
    {
        for (int y=0; y<height; y++)
            for (int x=0; x<width; x++)
                if (oldGrid[x][y] == oldOne)
                    changes.add(new GridChange(x,y,newOne));

    }

    public void calculateHeads2()
    {
        for (int y=0; y<height; y++)
            for (int x=0; x<width; x++)
                if ( (grid[x][y] == FieldState.FIELD_CONDUCTOR) &&
                        ( (getNeighboursOfState(grid,FieldState.FIELD_HEAD, x, y) == 2) || (getNeighboursOfState(grid,FieldState.FIELD_HEAD, x, y) == 1) ) )
                    changes.add(new GridChange(x,y,FieldState.FIELD_HEAD));


    }

    public FieldState getCell(FieldState[][] grid, int x, int y)
    {
        if ( x < 0 || x >=width || y < 0 || y >= height ) {
            return FieldState.FIELD_EMPTY; //jezeli poza granicami, to przyjmujemy, ze to FIELD_EMPTY
        }
        else return grid[x][y];
    }

    public int getNeighboursOfState(FieldState[][] grid, FieldState state, int x, int y) {
        int neighbours = 0;
        if ( getCell(grid, x - 1, y - 1) == state ) neighbours++;
        if ( getCell(grid, x, y - 1) == state ) neighbours++;
        if ( getCell(grid, x + 1, y - 1) == state ) neighbours++;
        if ( getCell(grid, x - 1, y) == state ) neighbours++;
        if ( getCell(grid, x + 1, y) == state ) neighbours++;
        if ( getCell(grid, x - 1, y + 1) == state ) neighbours++;
        if ( getCell(grid, x, y + 1) == state ) neighbours++;
        if ( getCell(grid, x + 1, y + 1) == state ) neighbours++;

        return neighbours;
    }

    public boolean checkIfGenerationIsDead() {
        for (int y=0; y<height; y++) {
            for (int x = 0; x < width; x++) {
                if ((grid[x][y] == FieldState.FIELD_TAIL) || (grid[x][y] == FieldState.FIELD_HEAD))
                    return false;
            }
        }
        return true;
    }

}
