package tetris;

import java.awt.Color;

/**
 * An object that keeps track of it's own location and can rotate and move it's locations
 * also includes methods that return it's location and it's color
 * 
 * @author Hao Wei
 */
public class Block 
{
	Color color;
	int[][] location = new int [4][2];
	int shape;
	int[][] grid1 = new int[23][10];
	Color[][] gridColor1 = new Color[23][10];
	int rotate;
	boolean break1;
	
	public Block()
	{
		int rndNum = (int) (Math.random() * 7) + 1;
		int startingLocation = (int) (Math.random() * 9);
		shape = rndNum;
		if (rndNum == 1)
		{
			color = Color.CYAN; // light blue
			//l
			location[0][0] = 0;
			location[0][1] = startingLocation;
			
			location[1][0] = 1;
			location[1][1] = startingLocation;
			
			location[2][0] = 2;
			location[2][1] = startingLocation;
			
			location[3][0] = 3;
			location[3][1] = startingLocation;
			
		}
		else if (rndNum == 2)
		{
			color = Color.BLUE;  // dark blue
			//L
			location[0][0] = 1;
			location[0][1] = startingLocation;
			
			location[1][0] = 2;
			location[1][1] = startingLocation;
			
			location[2][0] = 3;
			location[2][1] = startingLocation;
			
			location[3][0] = 3;
			location[3][1] = startingLocation + 1;
		}
		else if (rndNum == 3)
		{
			color = Color.ORANGE;  // orange
			//J
			location[0][0] = 1;
			location[0][1] = startingLocation + 1;
			
			location[1][0] = 2;
			location[1][1] = startingLocation + 1;
			
			location[2][0] = 3;
			location[2][1] = startingLocation + 1;
			
			location[3][0] = 3;
			location[3][1] = startingLocation;
		}
		else if (rndNum == 4)
		{
			color = Color.YELLOW;  // yellow
			//O
			location[0][0] = 2;
			location[0][1] = startingLocation + 1;
			
			location[1][0] = 2;
			location[1][1] = startingLocation;
			
			location[2][0] = 3;
			location[2][1] = startingLocation + 1;
			
			location[3][0] = 3;
			location[3][1] = startingLocation;
		}
		else if (rndNum == 5)
		{
			color = Color.GREEN;  // green
			//  l
			//---
			//l
			location[0][0] = 1;
			location[0][1] = startingLocation + 1;
			
			location[1][0] = 2;
			location[1][1] = startingLocation;
			
			location[2][0] = 2;
			location[2][1] = startingLocation + 1;
			
			location[3][0] = 3;
			location[3][1] = startingLocation;
		}
		else if (rndNum == 6)
		{
			color = Color.RED;  // red
			//l
			//---
			//  l
			location[0][0] = 1;
			location[0][1] = startingLocation;
			
			location[1][0] = 2;
			location[1][1] = startingLocation;
			
			location[2][0] = 2;
			location[2][1] = startingLocation + 1;
			
			location[3][0] = 3;
			location[3][1] = startingLocation + 1;
		}
		else
		{
			color = new Color(128 ,0 ,128);  // purple
			//l
			//l-
			//l
			location[0][0] = 1;
			location[0][1] = startingLocation;
			
			location[1][0] = 2;
			location[1][1] = startingLocation;
			
			location[2][0] = 2;
			location[2][1] = startingLocation + 1;
			
			location[3][0] = 3;
			location[3][1] = startingLocation;
		}
	}  // end constructor method
	
	public Block(int rndNum, int startingLocation)
	{
		shape = rndNum;
		if (rndNum == 1)
		{
			color = Color.CYAN; // light blue
			//l
			location[0][0] = 0;
			location[0][1] = startingLocation;
			
			location[1][0] = 1;
			location[1][1] = startingLocation;
			
			location[2][0] = 2;
			location[2][1] = startingLocation;
			
			location[3][0] = 3;
			location[3][1] = startingLocation;
			
		}
		else if (rndNum == 2)
		{
			color = Color.BLUE;  // dark blue
			//L
			location[0][0] = 1;
			location[0][1] = startingLocation;
			
			location[1][0] = 2;
			location[1][1] = startingLocation;
			
			location[2][0] = 3;
			location[2][1] = startingLocation;
			
			location[3][0] = 3;
			location[3][1] = startingLocation + 1;
		}
		else if (rndNum == 3)
		{
			color = Color.ORANGE;  // orange
			//J
			location[0][0] = 1;
			location[0][1] = startingLocation + 1;
			
			location[1][0] = 2;
			location[1][1] = startingLocation + 1;
			
			location[2][0] = 3;
			location[2][1] = startingLocation + 1;
			
			location[3][0] = 3;
			location[3][1] = startingLocation;
		}
		else if (rndNum == 4)
		{
			color = Color.YELLOW;  // yellow
			//O
			location[0][0] = 2;
			location[0][1] = startingLocation + 1;
			
			location[1][0] = 2;
			location[1][1] = startingLocation;
			
			location[2][0] = 3;
			location[2][1] = startingLocation + 1;
			
			location[3][0] = 3;
			location[3][1] = startingLocation;
		}
		else if (rndNum == 5)
		{
			color = Color.GREEN;  // green
			//  l
			//---
			//l
			location[0][0] = 1;
			location[0][1] = startingLocation + 1;
			
			location[1][0] = 2;
			location[1][1] = startingLocation;
			
			location[2][0] = 2;
			location[2][1] = startingLocation + 1;
			
			location[3][0] = 3;
			location[3][1] = startingLocation;
		}
		else if (rndNum == 6)
		{
			color = Color.RED;  // red
			//l
			//---
			//  l
			location[0][0] = 1;
			location[0][1] = startingLocation;
			
			location[1][0] = 2;
			location[1][1] = startingLocation;
			
			location[2][0] = 2;
			location[2][1] = startingLocation + 1;
			
			location[3][0] = 3;
			location[3][1] = startingLocation + 1;
		}
		else
		{
			color = new Color(128 ,0 ,128);  // purple
			//l
			//l-
			//l
			location[0][0] = 1;
			location[0][1] = startingLocation;
			
			location[1][0] = 2;
			location[1][1] = startingLocation;
			
			location[2][0] = 2;
			location[2][1] = startingLocation + 1;
			
			location[3][0] = 3;
			location[3][1] = startingLocation;
		}
	}  // end constructor method
	
	public Block clone()
	{
		return this;
	}
	
	public int[][] getLocation()
	{
		int[][] location1 = new int[4][2];
		location1 = location;
		return location1;
	}  // end getLocation method
	
	public Color getColor()
	{
		Color color1 = color;
		return color1;
	}  // end getColor method
	
	public void blockDown()
	{
		location[0][0] += 1;
		location[1][0] += 1;
		location[2][0] += 1;
		location[3][0] += 1;
	}  // end blockDown method

	public void blockLeft()
	{
		location[0][1] -= 1;
		location[1][1] -= 1;
		location[2][1] -= 1;
		location[3][1] -= 1;
	}  // end blockLeft method

	public void blockRight()
	{
		location[0][1] += 1;
		location[1][1] += 1;
		location[2][1] += 1;
		location[3][1] += 1;
	}  // end blockRight method

	public void rotateBlock(int grid[][], Color gridColor[][])
	{
		int lm = Math.min(Math.min(location[0][1], location[1][1]), Math.min(location[2][1], location[3][1]));
		int rm = Math.max(Math.max(location[0][1], location[1][1]), Math.max(location[2][1], location[3][1]));
		int tm = Math.min(Math.min(location[0][0], location[1][0]), Math.min(location[2][0], location[3][0]));
		int bm = Math.max(Math.max(location[0][0], location[1][0]), Math.max(location[2][0], location[3][0]));
		
		int width = rm - lm + 1;
		int height = bm - tm + 1;
		int startRow = 0;
		int startColumn;
		int[][] locationBackUp = new int[4][2];
		for (int i = 0; i < 4; i++)
		{
			for (int i1 = 0; i1 < 2; i1++)
			{
				locationBackUp[i][i1] = location[i][i1];
			}
		}
		int[][] gridBackUp = new int[23][10];
		Color[][] gridColorBackUp = new Color[23][10];
		for(int i = 0; i < 23; i ++)
		{
			for(int j = 0; j < 10; j++)
			{
				gridBackUp[i][j] = grid[i][j];
				gridColorBackUp[i][j] = gridColor[i][j];
			}
		}
		if(rotate == 0)
		{
			startRow = tm - ((4 - height) / 2);
			startColumn = lm - ((4 - width) / 2);
			rotate++;
			if(color.equals(Color.CYAN))
				startColumn--;
		}
		else if(rotate == 1)
		{
			startRow = tm - ((4 - height) / 2);
			startColumn = lm - ((4 - width + 1) / 2);
			rotate++;
		}
		else if(rotate == 2)
		{
			startRow = tm - ((4 - height) / 2);
			startColumn = lm - ((4 - width + 1) / 2);
			rotate++;
		}
		else
		{
			startRow = tm - ((4 - height) / 2);
			startColumn = lm - ((4 - width + 1) / 2);
			rotate = 0;
		}

		boolean stop = false;
		int[][] gridr = new int[4][4];
		if(startColumn <= -1)
		{
			if(color.equals(Color.CYAN))
				startColumn = 0;
			else
				startColumn = -1;
		}
		else if(startColumn + 3 >= 10)
		{
			startColumn = 6;
		}
		for (int i = 0; i < 4; i ++)
		{
			for (int i1 = 0; i1 < 4; i1 ++)
			{
				try{
					gridr[i1][3-i] = grid[startRow + i][startColumn + i1];
				}
				catch(ArrayIndexOutOfBoundsException e){
				}
			}
		}
		if(!stop)
		{
			for (int i = 0; i < 4; i++)
			{
				grid[location[i][0]][location[i][1]] = 0;
				gridColor[location[i][0]][location[i][1]] = Color.WHITE;
			}
			int k = 0;
			for (int i = 0; i < 4; i++)
			{
				for (int i1 = 0; i1 < 4; i1++)
				{
					if(gridr[i][i1] == 1)
					{
						location[k][1] = startColumn + i1;
						location[k++][0] = startRow + i;
						if(grid[startRow + i][startColumn + i1] == 2 && gridr[i][i1] == 1)
						{
							break1 = true;
							break;
						}
					}
				}
			}
			if(break1)
			{
				rotate--;
				break1 = false;
				for(int i = 0; i < 23; i ++)
					for(int j = 0; j < 10; j++)
					{
						grid1[i][j] = gridBackUp[i][j];
						gridColor1[i][j] = gridColorBackUp[i][j];
					}
				for (int i = 0; i < 4; i++)
					for (int i1 = 0; i1 < 2; i1++)
					{
						location[i][i1] = locationBackUp[i][i1];
					}
			}
			else
			{
				for (int i = 0; i < 4; i++)
				{
					grid[location[i][0]][location[i][1]] = 1;
					gridColor[location[i][0]][location[i][1]] = color;
				}
				for(int i = 0; i < 23; i ++)
					for(int j = 0; j < 10; j++)
					{
						grid1[i][j] = grid[i][j];
						gridColor1[i][j] = gridColor[i][j];
					}
			}
		}
		else
		{
			for(int i = 0; i < 23; i ++)
				for(int j = 0; j < 10; j++)
				{
					grid[i][j] = gridBackUp[i][j];
					grid1[i][j] = grid[i][j];
					gridColor[i][j] = gridColorBackUp[i][j];
					gridColor1[i][j] = gridColor[i][j];
				}
			for (int i = 0; i < 4; i++)
				for (int i1 = 0; i1 < 2; i1++)
				{
					location[i][i1] = locationBackUp[i][i1];
				}
		
		}
	}  // end rotateBlock method
	
	public int[][] getGrid()
	{
		return grid1;
	}  // end getGrid method
	
	public Color[][] getGridColor()
	{
		return gridColor1;
	}  // end getGridColor method
	
	public void printBlock(int[][] gridr)
	{
		for (int i = 0; i < 4; i ++)
		{
			for (int i1 = 0; i1 < 4; i1 ++)
			{
				System.out.print(gridr[i1][3 - i]);
			}
			System.out.println();
		}
		System.out.println();
	}
}  // end block class
