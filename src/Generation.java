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

        FieldState[][] newGrid = new FieldState[width][height];
        
        for (int y=0; y<height; y++)
        	for (int x=0; x<width; x++)
        		newGrid[x][y] = FieldState.FIELD_EMPTY;
        
        int stepright = (width-this.width)/2;
        int stepdown = (height - this.height)/2;
        
        for (int x = stepright; x < this.width+stepright; x++) {
            for (int y = stepdown; y < this.height+stepdown; y++)
            	{
                    newGrid[x][y] = grid[x-stepright][y-stepdown];
                }
            }
        
        
        grid = newGrid;
        this.width = width;
        this.height = height;
    }
    
    public void printToConsole()
    {
    	System.out.println(width + " " + height);
    	
    	for (int y=0; y< height; y++)
    	{
    		for (int x=0; x<width; x++)
    		{
    			if (grid[x][y] == Generation.FieldState.FIELD_EMPTY)
    			{
    				System.out.printf( 0 +" ");
    			}
    			if (grid[x][y] == Generation.FieldState.FIELD_CONDUCTOR)
    			{
    				System.out.printf( 1 +" ");
    			}
    			if (grid[x][y] == Generation.FieldState.FIELD_HEAD)
    			{
    				System.out.printf( 2 +" ");
    			}
    			if (grid[x][y] == Generation.FieldState.FIELD_TAIL)
    			{
    				System.out.printf( 3 +" ");
    			}
    		}
    		System.out.println(" "); //nowa linia
    	}
    }

    public void calculateNextGeneration()
    {
    	
    }

}