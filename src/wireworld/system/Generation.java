package wireworld.system;

import java.util.ArrayList;

public class Generation {

    private final int defaultGenerationWidth = 20;
    private final int defaultGenerationHeight = 10;

    public ArrayList<GridChange> changes;

    public enum FieldState { FIELD_EMPTY, FIELD_CONDUCTOR, FIELD_HEAD, FIELD_TAIL }

    private FieldState[][] grid;
    public int generationNumber = 0; //zmienilem nazwe calkiem
    public int width, height;
    public boolean isGenerationDead = false;

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
        if (width>this.width && height>this.height) {

            FieldState[][] newGrid = new FieldState[width][height];

            for (int y = 0; y < height; y++) //FILLING THE NEW GRID WITH FIELD_EMPTY
                for (int x = 0; x < width; x++)
                    newGrid[x][y] = FieldState.FIELD_EMPTY;

            int stepright = (width - this.width) / 2;
            int stepdown = (height - this.height) / 2;

            for (int x = stepright; x < this.width + stepright; x++) { //PLACING THE PREVIOUS GRID IN THE APROX. CENTER
                for (int y = stepdown; y < this.height + stepdown; y++) {
                    newGrid[x][y] = grid[x - stepright][y - stepdown];
                }
            }

            grid = newGrid;
            this.width = width;
            this.height = height;
        }
        else {
            System.out.println("New x and y too small. Aborting grid extension.");
        }
    }
    
    public void printToConsole() { //MOSTLY FOR DEBUGGING
    	System.out.println(width + " " + height);
    	for (int y=0; y< height; y++) {
    		for (int x=0; x<width; x++) {
    			if (grid[x][y] == Generation.FieldState.FIELD_EMPTY) {
    				System.out.printf( 0 +" ");
    			} else if (grid[x][y] == Generation.FieldState.FIELD_CONDUCTOR) {
    				System.out.printf( 1 +" ");
    			} else if (grid[x][y] == Generation.FieldState.FIELD_HEAD) {
    				System.out.printf( 2 +" ");
    			} else if (grid[x][y] == Generation.FieldState.FIELD_TAIL) {
    				System.out.printf( 3 +" ");
    			}
    		}
    		System.out.println(" "); //nowa linia
    	}
    }

    public void calculateNextGeneration() {
        changes = new ArrayList<GridChange>();
        replaceCells2(grid,FieldState.FIELD_TAIL,FieldState.FIELD_CONDUCTOR);
        replaceCells2(grid,FieldState.FIELD_HEAD, FieldState.FIELD_TAIL);
        calculateHeads2();
        applyChanges(grid);
        generationNumber++;
        isGenerationDead = checkIfGenerationIsDead();
    }

    public void applyChanges(FieldState[][] grid) { //you can choose, what grid you would like to apply changes to.
        for (GridChange G: changes)
            grid[G.x][G.y] = G.state;
    }

    //OGOLNA FUNKCJA DO ZAMIANY JEDNEGO STANU NA INNY
    public void replaceCells2(FieldState[][] oldGrid, FieldState oldOne, FieldState newOne) {
        for (int y=0; y<height; y++)
            for (int x=0; x<width; x++)
                if (oldGrid[x][y] == oldOne)
                    changes.add(new GridChange(x,y,newOne));
    }

    public void calculateHeads2() {
        for (int y=0; y<height; y++)
            for (int x=0; x<width; x++)
                if ( (grid[x][y] == FieldState.FIELD_CONDUCTOR) &&
                        ( (getNeighboursOfStateFromGrid(grid,FieldState.FIELD_HEAD, x, y) == 2) || (getNeighboursOfStateFromGrid(grid,FieldState.FIELD_HEAD, x, y) == 1) ) )
                    changes.add(new GridChange(x,y,FieldState.FIELD_HEAD));
    }

    public FieldState getCellFromGrid(FieldState[][] grid, int x, int y) {
        if ( x < 0 || x >=width || y < 0 || y >= height )
            return FieldState.FIELD_EMPTY; //jezeli poza granicami, to przyjmujemy, ze to FIELD_EMPTY
        else
            return grid[x][y];
    }

    public int getNeighboursOfStateFromGrid(FieldState[][] grid, FieldState state, int x, int y) {
        int neighbours = 0;
        if ( getCellFromGrid(grid, x - 1, y - 1) == state ) neighbours++;
        if ( getCellFromGrid(grid, x, y - 1) == state ) neighbours++;
        if ( getCellFromGrid(grid, x + 1, y - 1) == state ) neighbours++;
        if ( getCellFromGrid(grid, x - 1, y) == state ) neighbours++;
        if ( getCellFromGrid(grid, x + 1, y) == state ) neighbours++;
        if ( getCellFromGrid(grid, x - 1, y + 1) == state ) neighbours++;
        if ( getCellFromGrid(grid, x, y + 1) == state ) neighbours++;
        if ( getCellFromGrid(grid, x + 1, y + 1) == state ) neighbours++;

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

    public void setCell(FieldState state, int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height)
            grid[x][y] = state;
    }

    public FieldState getCell(int x, int y) {
        return grid[x][y];
    }

}