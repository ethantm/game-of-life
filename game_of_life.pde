
/**
 * Put in folder game_of_life
 * Open game_of_life.pde with Processing
 */

/**
 * Press enter to pause/resume game of life
 * Press backspace to create random game
 */ 

int cell_width;
int cell_height;
int cells_x;
int cells_y;

boolean continue_update = false;
public world game;

public void setup()
{
  background(255);
  
  cells_x = 50;
  cells_y = 50;
  
  size(800, 800);
  game = new world(cells_x, cells_y);
  
  cell_width = width / cells_x;
  cell_height = height / cells_y;
  
  cells();
  frameRate(15); // Set speed of game of life
}

public void draw()
{
  if (continue_update)
  {
    game.update();
  }
  cells();
}

public void mousePressed()
{
  int cell_clicked[] = clicked();
  game.world[cell_clicked[0]][cell_clicked[1]] = !game.world[cell_clicked[0]][cell_clicked[1]];
}

public void keyPressed()
{
  if (keyCode == ENTER)
  {
    continue_update = !continue_update;
  }
  else if (keyCode == BACKSPACE)
  {
    rand();
  }
}

public int[] clicked()
{
  double temp_x = mouseX / cell_width;
  double temp_y = mouseY / cell_height;
  int result[] = {(int) temp_x, (int) temp_y};
  return result;
}

public void rand()
{
  game.blank();
  for (int i = 0; i < game.world.length; i++)
  {
    for (int j = 0; j < game.world[i].length; j++)
    {
      if ((int) random(2) == 1)
        game.world[i][j] = true;
    }
  }
}

public void cells()
{
  for (int i = 0; i < game.world.length; i++)
  {
    for (int j = 0; j < game.world[i].length; j++)
    {      
      stroke(150, 150, 150);
      strokeWeight(1);
      int temp = (game.world[i][j]) ? 0 : 255;
      fill(temp);
      rect(cell_width * i, cell_height * j, cell_width, cell_height); 
    }
  }
}
