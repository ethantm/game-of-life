public class world 
{
    private int x;
    private int y;
    public boolean world[][];
    public boolean temp[][];
    private int possible[][] = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}, {-1, -1}, {1, -1}, {-1, 1}, {1, 1}};

    public world(int x, int y)
    {
        this.x = x;
        this.y = y;
        world = new boolean[x][y];
        temp = new boolean[x][y];
    }

    public void update() 
    {
        for (int i = 0; i < world.length; i++)
        {
            for (int j = 0; j < world[i].length; j++)
            {
                int n = neighbours(i, j);
                temp[i][j] = state(n, world[i][j]);
            }
        }
        
        merge();
    }

    public int neighbours(int position_x, int position_y)
    {
        int result = 0;
                
        for (int i = 0; i < possible.length; i++)
        {
          boolean nx = position_x + possible[i][0] > -1 && position_x + possible[i][0] < world.length;
          boolean ny = position_y + possible[i][1] > -1 && position_y + possible[i][1] < world[0].length;
          
          if (nx && ny)
          {
             if (world[position_x + possible[i][0]][position_y + possible[i][1]])
             {
               result++;
             }
          }
        }
        return result;
    }

    public boolean state(int neighbours, boolean cell) 
    {
        boolean alive = ( ((neighbours == 2 || neighbours == 3) && cell) || (neighbours == 3 && !cell));
        boolean dead = ((neighbours < 2 && cell) || (neighbours > 3 && cell));
        boolean result = alive ? true : (dead ? false : cell); 
        return result;
    }
    
    public void merge()
    {
      for (int i = 0; i < world.length; i++)
      {       
        for (int j = 0; j < world[i].length; j++)
        {
          world[i][j] = temp[i][j];
        }
      }
    }
    
    public void blank()
    {
      for (int i = 0; i < world.length; i++)
      {
        for (int j = 0; j < world[i].length; j++)
        {
          world[i][j] = false;
          temp[i][j] = false;
        }
      }
    }
}