public class ApplyLogic extends Generation {

    private  FieldState[][] grid;
    public FieldState[][] newGrid;



    public ApplyLogic(Generation prev_gen) {
        this.height = prev_gen.height;
        this.width = prev_gen.width;
        grid = prev_gen.grid;
        newGrid = new FieldState[width][height];
        calculate();
        isGenerationDead = checkIfGenerationIsDead();
    }

    public void calculate()
    {
        newGrid = fillGridWithFieldConductor(newGrid); //FILL THE NEW GRID WITH FIELD_CONDUCTOR
        newGrid = refillFieldEmpty(newGrid); //IF CELL WAS EMPTY, IT BECOMES EMPTY //tak, wiem, ze to sie dalo zrobic funckja replaceCells()
        newGrid = calculateHeads(newGrid); //THE HEAD LOGIC IS APPLIED
        newGrid = replaceCells(grid, newGrid, FieldState.FIELD_HEAD, FieldState.FIELD_TAIL); //THE HEADS OF THE OLD GRID ARE REPLACED WITH TAILS IN THE NEW GRID
    }

    //OGOLNA FUNKCJA DO ZAMIANY JEDNEGO STANU NA INNY
    public FieldState[][] replaceCells(FieldState[][] oldGrid, FieldState[][] newGrid, FieldState oldOne, FieldState newOne)
    {
        for (int y=0; y<height; y++)
            for (int x=0; x<width; x++)
                if (oldGrid[x][y] == oldOne)
                    newGrid[x][y] = newOne;
        return  newGrid;
    }

    public FieldState[][] fillGridWithFieldConductor(FieldState[][] newGrid)
    {
        for (int y=0; y<height; y++)
            for (int x=0; x<width; x++)
                newGrid[x][y] = FieldState.FIELD_CONDUCTOR;

        return newGrid;
    }

    public FieldState[][] refillFieldEmpty(FieldState[][] newGrid)
    {
        for (int y=0; y<height; y++)
            for (int x=0; x<width; x++)
                if (grid[x][y] == FieldState.FIELD_EMPTY)
                    newGrid[x][y] = FieldState.FIELD_EMPTY;

        return newGrid;
    }



    public FieldState[][] calculateHeads(FieldState[][] newGrid)
    {
        for (int y=0; y<height; y++)
            for (int x=0; x<width; x++)
                if ( (grid[x][y] == FieldState.FIELD_CONDUCTOR) &&
                        ( (getNeighboursOfState(grid,FieldState.FIELD_HEAD, x, y) == 2) || (getNeighboursOfState(grid,FieldState.FIELD_HEAD, x, y) == 1) ) )
                    newGrid[x][y] = FieldState.FIELD_HEAD;

                return newGrid;

    }


    public FieldState getCell(FieldState[][] grid, int x, int y)
    {
        if ( x < 0 || x >=width || y < 0 || y >= height ) {
            return FieldState.FIELD_EMPTY; //jezeli poza granicami, to przyjmujemy, ze to FIELD_EMPTY
        }
        else return grid[x][y];
    }

    public int getNeighboursOfState(FieldState[][] grid, FieldState state, int x, int y)
    {
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

    public boolean checkIfGenerationIsDead()
    {
        for (int y=0; y<height; y++)
            for (int x=0; x<width; x++)
                if ( (grid[x][y] == FieldState.FIELD_TAIL) || (grid[x][y] == FieldState.FIELD_HEAD))
                    return false;

                return true;
    }

}
