package tetris;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * displays a frame that has labels that display the location of the blocks and the time and the remaining lines
 * 
 * @author Hao Wei
 */
@SuppressWarnings("serial")
public class Tetris extends JFrame implements KeyListener
{
	Timer mainTimer;
	protected int[][] grid = new int[23][10];
	protected int[][] preGrid = new int[23][10];
	protected JLabel[][] grid1 = new JLabel[20][10];
	protected Color[][] gridColor = new Color[23][10];
	protected Color[][] preGridColor = new Color[23][10];
	protected int[][] shadowLocation;
	protected boolean pause;
	protected int time;
	protected int delay;
	protected int delay2;
	protected int timeCounter;
	protected int remainingLines;
	protected int timeCounter2;
	protected JLabel remainingLinesLabel;
	protected JLabel timeLabel;
	protected JLabel secondsLabel;
	protected JLabel nBlockLabel;
	protected JLabel hBlockLabel;
	protected Block block;
	protected Block nextBlock;
	protected Block hold;
	protected boolean landed;
	protected JLabel[][] nBlock = new JLabel[4][4];
	protected JLabel[][] hBlock = new JLabel[4][4];
	
	public Tetris()
	{
		super("Tetris");
		
		setSize(450, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		resetComplete();
		block = new Block();
		int[][] location = new int [4][2];
		location = block.getLocation();
		for (int i = 0; i < 4; i++)
		{
			grid[location[i][0]][location[i][1]] = 1;
			gridColor[location[i][0]][location[i][1]] = block.getColor();
		}
		nextBlock = new Block();
		hold = null;
		updateShadow();
		updateLocations();
		updateNextBlock();
		startGame();
		
		addKeyListener(this);
		
		setVisible(true);
	}  // end constructor method
	
	synchronized public void keyPressed(KeyEvent e) 
	{
		int[][] loc = new int [4][2];
		loc = block.getLocation();
		int lm = Math.min(Math.min(loc[0][1], loc[1][1]), Math.min(loc[2][1], loc[3][1]));
		int rm = Math.max(Math.max(loc[0][1], loc[1][1]), Math.max(loc[2][1], loc[3][1]));
		
		boolean nextToBlockl = false;
		boolean nextToBlockr = false;
		for (int i = 0; i < 4; i++)
		{
			if(rm < 9)
			{
				if (grid[loc[i][0]][loc[i][1] + 1] == 2)
				{
					nextToBlockr = true;
				}
			}
			if(lm > 0)
			{
				if (grid[loc[i][0]][loc[i][1] - 1] == 2)
				{
					nextToBlockl = true;
				}
			}
		}
		
		if(e.getKeyCode() == 37 && lm > 0 && !nextToBlockl) //left
		{
			int[][] location = new int [4][2];
			location = block.getLocation();
			for (int i = 0; i < 4; i++)
			{
				grid[location[i][0]][location[i][1]] = 0;
				gridColor[location[i][0]][location[i][1]] = Color.WHITE;
			}
			block.blockLeft();
			location = block.getLocation();
			for (int i = 0; i < 4; i++)
			{
				grid[location[i][0]][location[i][1]] = 1;
				gridColor[location[i][0]][location[i][1]] = block.getColor();
			}
		}
		else if(e.getKeyCode() == 39 && rm < 9 && !nextToBlockr) // right
 		{
			int[][] location = new int [4][2];
			location = block.getLocation();
			for (int i = 0; i < 4; i++)
			{
				grid[location[i][0]][location[i][1]] = 0;
				gridColor[location[i][0]][location[i][1]] = Color.WHITE;
			}
			block.blockRight();
			location = block.getLocation();
			for (int i = 0; i < 4; i++)
			{
				grid[location[i][0]][location[i][1]] = 1;
				gridColor[location[i][0]][location[i][1]] = block.getColor();
			}
		}
		else if(e.getKeyCode() == 40 && !landed) // down
		{
			timeCounter = 0;
			int[][] location = new int [4][2];
			location = block.getLocation();
			for (int i = 0; i < 4; i++)
			{
				grid[location[i][0]][location[i][1]] = 0;
				gridColor[location[i][0]][location[i][1]] = Color.WHITE;
			}
			block.blockDown();
			location = block.getLocation();
			for (int i = 0; i < 4; i++)
			{
				grid[location[i][0]][location[i][1]] = 1;
				gridColor[location[i][0]][location[i][1]] = block.getColor();
			}
		}
		else if(e.getKeyCode() == 38 && !landed) //  rotate
		{
			if(!block.color.equals(Color.YELLOW) && block.location[3][0] > 3)
			{
				block.rotateBlock(grid, gridColor);
				int[][] location = new int [4][2];
				location = block.getLocation();
				for (int i = 0; i < 4; i++)
				{
					grid[location[i][0]][location[i][1]] = 1;
					gridColor[location[i][0]][location[i][1]] = block.getColor();
				}
			}
 		}
		else if(e.getKeyCode() == 32 && !landed)
		{
			boolean stop = false;
			pause = true;
			while (true)
			{
				loc = block.getLocation();
				for (int i = 0; i < 4; i++)
				{
					if (loc[i][0] == 22 || grid[loc[i][0] + 1][loc[i][1]] == 2)
					{
						newBlock();
						stop = true;
						break;
					}
				}
				if (stop)
				{
					pause = false;
					break;
				}
				for (int i = 0; i < 4; i++)
				{
					grid[loc[i][0]][loc[i][1]] = 0;
					gridColor[loc[i][0]][loc[i][1]] = Color.WHITE;
				}
				block.blockDown();
				loc = block.getLocation();
				for (int i = 0; i < 4; i++)
				{
					grid[loc[i][0]][loc[i][1]] = 1;
					gridColor[loc[i][0]][loc[i][1]] = block.getColor();
				}
			}
		}
		else if(e.getKeyChar() == 'h')
		{
			holdBlock();
		}
		updateShadow();
		updateLocations();
	}  // end keyPressed method
	
	public void keyReleased(KeyEvent e) 
	{
		
	}  // end keyReleased method

	public void keyTyped(KeyEvent e) 
	{
		
	}  // end keyTyped method
	
	synchronized public void startGame()
	{
		mainTimer = new Timer();
		mainTimer.scheduleAtFixedRate(new TimerTask() {
			/**
			 * moves the block down every half a second and calls the update location method\
			 * increments the time every hundredth of a second
			 */
			public void run()
			{
				if (timeCounter == delay && !pause)
				{
					timeCounter = 0;
					if(landed)
						checkLanded();
					else
					{
						int[][] location = new int [4][2];
						location = block.getLocation();
						for (int i = 0; i < 4; i++)
						{
							grid[location[i][0]][location[i][1]] = 0;
							gridColor[location[i][0]][location[i][1]] = Color.WHITE;
						}
						block.blockDown();
						location = block.getLocation();
						for (int i = 0; i < 4; i++)
						{
							grid[location[i][0]][location[i][1]] = 1;
							gridColor[location[i][0]][location[i][1]] = block.getColor();
						}
					}
					updateShadow();
					//printGrid();
					updateLocations();
				}
				else
					timeCounter++;
				if (timeCounter2 == delay2)
				{
					timeCounter2 = 0;
					time++;
					updateScore();
				}
				else
					timeCounter2++;
			}
		}, 1, 1);
	}  // end startGame method
	
	synchronized public void updateShadow()
	{
		int[][] location = new int [4][2];
		location = block.getLocation();
		shadowLocation = new int[4][2];
		for(int i = 0; i < 4; i++)
		{
			shadowLocation[i][0] = location[i][0];
			shadowLocation[i][1] = location[i][1];
		}
		boolean stop = false;
		while(!stop)
		{
			for (int i = 0; i < 4; i++)
			{
				if (shadowLocation[i][0] == 22 || grid[shadowLocation[i][0] + 1][shadowLocation[i][1]] == 2)
				{
					stop = true;
				}
			}
			if(!stop)
			{
				shadowLocation[0][0] += 1;
				shadowLocation[1][0] += 1;
				shadowLocation[2][0] += 1;
				shadowLocation[3][0] += 1;
			}
		}
	}

	synchronized public void checkLanded()
	{
		int[][] location = new int [4][2];
		location = block.getLocation();
		for (int i = 0; i < 4; i++)
		{
			if (location[i][0] == 22 || grid[location[i][0] + 1][location[i][1]] == 2)
			{
				newBlock();
				break;
			}
		}
	}  // end checkLanded method
	
	synchronized public void checkLanded1()
	{
		int[][] location = new int [4][2];
		location = block.getLocation();
		for (int i = 0; i < 4; i++)
		{
			if (location[i][0] == 22 || grid[location[i][0] + 1][location[i][1]] == 2)
			{
				landed = true;
				return;
			}
		}
		landed = false;
	}  // end checkLanded1 method
	
	synchronized public void newBlock()
	{
		timeCounter = 0;
		landed = false;
		int[][] location = new int [4][2];
		location = block.getLocation();
		for (int i = 0; i < 4; i++)
		{
			grid[location[i][0]][location[i][1]] = 2;
		}
		
		block = nextBlock.clone();
		nextBlock = new Block();
		location = block.getLocation();
		for (int i = 0; i < 4; i++)
		{
			if(grid[location[i][0]][location[i][1]] == 2)
			{
				lose();
				break;
			}
			grid[location[i][0]][location[i][1]] = 1;
			gridColor[location[i][0]][location[i][1]] = block.getColor();
		}
		
		updateNextBlock();
		updateLocations();
	}  // end newBlock method
	
	synchronized protected void updateLocations()
	{
		checkLanded1();
		
		for (int i = 0; i < 20; i ++)
		{
			for (int i1 = 0; i1 < 10; i1 ++)
			{
				if(grid[i + 3][i1] == -1)
				{
					grid1[i][i1].setIcon(new ImageIcon(("pictures/white.png")));
					grid[i + 3][i1] = 0;
				}
				if (grid[i + 3][i1] != preGrid[i+3][i1] || !(gridColor[i+3][i1].equals(preGridColor[i+3][i1])))
				{
					if(gridColor[i+3][i1].equals(Color.CYAN))
					{
						grid1[i][i1].setIcon(new ImageIcon(("pictures/cyan.png")));
					}
					else if(gridColor[i+3][i1].equals(Color.BLUE))
					{
						grid1[i][i1].setIcon(new ImageIcon(("pictures/blue.png")));
					}
					else if(gridColor[i+3][i1].equals(Color.GREEN))
					{
						grid1[i][i1].setIcon(new ImageIcon(("pictures/green.png")));
					}
					else if(gridColor[i+3][i1].equals(Color.RED))
					{
						grid1[i][i1].setIcon(new ImageIcon(("pictures/red.png")));
					}
					else if(gridColor[i+3][i1].equals(Color.YELLOW))
					{
						grid1[i][i1].setIcon(new ImageIcon(("pictures/yellow.png")));
					}
					else if(gridColor[i+3][i1].equals(Color.ORANGE))
					{
						grid1[i][i1].setIcon(new ImageIcon(("pictures/orange.png")));
					}
					else if(gridColor[i+3][i1].equals(new Color(128 ,0 ,128)))
					{
						grid1[i][i1].setIcon(new ImageIcon(("pictures/purple.png")));
					}
					else
					{
						grid1[i][i1].setIcon(new ImageIcon(("pictures/white.png")));
					}
					preGrid[i + 3][i1] = grid[i+3][i1];
					preGridColor[i + 3][i1] = gridColor[i+3][i1];
				}
			}
		}
		
		for(int i = 0; i < 4; i++)
		{
			if(shadowLocation[i][0]-3 > 0 && grid[shadowLocation[i][0]][shadowLocation[i][1]] == 0)
			{
				grid[shadowLocation[i][0]][shadowLocation[i][1]] = -1;
				if(block.getColor().equals(Color.CYAN))
				{
					grid1[shadowLocation[i][0]-3][shadowLocation[i][1]].setIcon(new ImageIcon(("pictures/cyans.png")));
				}
				else if(block.getColor().equals(Color.BLUE))
				{
					grid1[shadowLocation[i][0]-3][shadowLocation[i][1]].setIcon(new ImageIcon(("pictures/blues.png")));
				}
				else if(block.getColor().equals(Color.GREEN))
				{
					grid1[shadowLocation[i][0]-3][shadowLocation[i][1]].setIcon(new ImageIcon(("pictures/greens.png")));
				}
				else if(block.getColor().equals(Color.RED))
				{
					grid1[shadowLocation[i][0]-3][shadowLocation[i][1]].setIcon(new ImageIcon(("pictures/reds.png")));
				}
				else if(block.getColor().equals(Color.YELLOW))
				{
					grid1[shadowLocation[i][0]-3][shadowLocation[i][1]].setIcon(new ImageIcon(("pictures/yellows.png")));
				}
				else if(block.getColor().equals(Color.ORANGE))
				{
					grid1[shadowLocation[i][0]-3][shadowLocation[i][1]].setIcon(new ImageIcon(("pictures/oranges.png")));
				}
				else if(block.getColor().equals(new Color(128 ,0 ,128)))
				{
					grid1[shadowLocation[i][0]-3][shadowLocation[i][1]].setIcon(new ImageIcon(("pictures/purples.png")));
				}
			}
		}
		
		checkLines();
		checkLose();
	}  // end updateLocations method
	
	synchronized public void checkLines()
	{
		int blocks = 0;
		for(int i = 3; i < 23; i++)
		{
			for(int i1 = 0; i1 < 10; i1++)
			{
				if (grid[i][i1] == 2)
				{
					blocks++;
				}
			}
			if (blocks == 10)
			{
				clearLines(i);
			}
			blocks = 0;
		}
	}  // end checkLines method
	
	synchronized public void clearLines(int row)
	{
		for (int i = 0; i < 20; i ++)
		{
			for (int i1 = 0; i1 < 10; i1 ++)
			{
				if(grid[i + 3][i1] == -1)
				{
					grid1[i][i1].setIcon(new ImageIcon(("pictures/white.png")));
					grid[i + 3][i1] = 0;
				}
			}
		}
		for(int i = row; i > 0; i--)
		{
			for(int i1 = 0; i1 < 10; i1++)
			{
				if(grid[i - 1][i1] == 2)
				{
					grid[i][i1] = grid[i - 1][i1];
					gridColor[i][i1] = gridColor[i - 1][i1];
				}
				else
				{
					grid[i][i1] = 0;
					gridColor[i][i1] = Color.WHITE;
				}
			}
		}
		updateLocations();
		remainingLines--;
		if(remainingLines == 0)
			lose();
	}  // end clearLines method
	
	synchronized public void lose()
	{
		mainTimer.cancel();
		dispose();
		new LosePage(time, remainingLines, true);
	}  // end lose method
	
	synchronized public void checkLose()
	{
		for(int i = 0; i < 10; i++)
		{
			if(grid[2][i] == 2)
			{
				lose();
				break;
			}
		}
	}  // end checkLose method
	
	synchronized public void updateScore()
	{
		remainingLinesLabel.setText("Remaining Lines: " + remainingLines);
		timeLabel.setText(time / 100.0 + "");
	}  // end updateScore method
	
	synchronized public void updateNextBlock()
	{
		int[][] location = new int[4][2];
		int startingLocation = 1;
		
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				nBlock[i][j].setIcon(new ImageIcon(("pictures/gray.png")));
			}
		}
		
		if (nextBlock.color.equals(Color.CYAN))
		{
			//l
			location[0][0] = 0;
			location[0][1] = startingLocation;
			
			location[1][0] = 1;
			location[1][1] = startingLocation;
			
			location[2][0] = 2;
			location[2][1] = startingLocation;
			
			location[3][0] = 3;
			location[3][1] = startingLocation;
			
			nBlock[location[0][1]][location[0][0]].setIcon(new ImageIcon(("pictures/cyan.png")));
			nBlock[location[1][1]][location[1][0]].setIcon(new ImageIcon(("pictures/cyan.png")));
			nBlock[location[2][1]][location[2][0]].setIcon(new ImageIcon(("pictures/cyan.png")));
			nBlock[location[3][1]][location[3][0]].setIcon(new ImageIcon(("pictures/cyan.png")));
		}
		else if (nextBlock.color.equals(Color.BLUE))
		{
			//L
			location[0][0] = 1;
			location[0][1] = startingLocation;
			
			location[1][0] = 2;
			location[1][1] = startingLocation;
			
			location[2][0] = 3;
			location[2][1] = startingLocation;
			
			location[3][0] = 3;
			location[3][1] = startingLocation + 1;
			
			nBlock[location[0][1]][location[0][0]-1].setIcon(new ImageIcon(("pictures/blue.png")));
			nBlock[location[1][1]][location[1][0]-1].setIcon(new ImageIcon(("pictures/blue.png")));
			nBlock[location[2][1]][location[2][0]-1].setIcon(new ImageIcon(("pictures/blue.png")));
			nBlock[location[3][1]][location[3][0]-1].setIcon(new ImageIcon(("pictures/blue.png")));
		}
		else if (nextBlock.color.equals(Color.ORANGE))
		{
			//J
			location[0][0] = 1;
			location[0][1] = startingLocation + 1;
			
			location[1][0] = 2;
			location[1][1] = startingLocation + 1;
			
			location[2][0] = 3;
			location[2][1] = startingLocation + 1;
			
			location[3][0] = 3;
			location[3][1] = startingLocation;
			
			nBlock[location[0][1]][location[0][0]-1].setIcon(new ImageIcon(("pictures/orange.png")));
			nBlock[location[1][1]][location[1][0]-1].setIcon(new ImageIcon(("pictures/orange.png")));
			nBlock[location[2][1]][location[2][0]-1].setIcon(new ImageIcon(("pictures/orange.png")));
			nBlock[location[3][1]][location[3][0]-1].setIcon(new ImageIcon(("pictures/orange.png")));
		}
		else if (nextBlock.color.equals(Color.YELLOW))
		{
			//O
			location[0][0] = 2;
			location[0][1] = startingLocation + 1;
			
			location[1][0] = 2;
			location[1][1] = startingLocation;
			
			location[2][0] = 3;
			location[2][1] = startingLocation + 1;
			
			location[3][0] = 3;
			location[3][1] = startingLocation;
			
			nBlock[location[0][1]][location[0][0]-1].setIcon(new ImageIcon(("pictures/yellow.png")));
			nBlock[location[1][1]][location[1][0]-1].setIcon(new ImageIcon(("pictures/yellow.png")));
			nBlock[location[2][1]][location[2][0]-1].setIcon(new ImageIcon(("pictures/yellow.png")));
			nBlock[location[3][1]][location[3][0]-1].setIcon(new ImageIcon(("pictures/yellow.png")));
		}
		else if (nextBlock.color.equals(Color.GREEN))
		{
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
			
			nBlock[location[0][1]][location[0][0]-1].setIcon(new ImageIcon(("pictures/green.png")));
			nBlock[location[1][1]][location[1][0]-1].setIcon(new ImageIcon(("pictures/green.png")));
			nBlock[location[2][1]][location[2][0]-1].setIcon(new ImageIcon(("pictures/green.png")));
			nBlock[location[3][1]][location[3][0]-1].setIcon(new ImageIcon(("pictures/green.png")));
		}
		else if (nextBlock.color.equals(Color.RED))
		{
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
			
			nBlock[location[0][1]][location[0][0]-1].setIcon(new ImageIcon(("pictures/red.png")));
			nBlock[location[1][1]][location[1][0]-1].setIcon(new ImageIcon(("pictures/red.png")));
			nBlock[location[2][1]][location[2][0]-1].setIcon(new ImageIcon(("pictures/red.png")));
			nBlock[location[3][1]][location[3][0]-1].setIcon(new ImageIcon(("pictures/red.png")));
		}
		else
		{
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
			
			nBlock[location[0][1]][location[0][0]-1].setIcon(new ImageIcon(("pictures/purple.png")));
			nBlock[location[1][1]][location[1][0]-1].setIcon(new ImageIcon(("pictures/purple.png")));
			nBlock[location[2][1]][location[2][0]-1].setIcon(new ImageIcon(("pictures/purple.png")));
			nBlock[location[3][1]][location[3][0]-1].setIcon(new ImageIcon(("pictures/purple.png")));
		}
	}
	
	synchronized public void updateHeldBlock()
	{
		int[][] location = new int[4][2];
		int startingLocation = 1;
		
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				hBlock[i][j].setIcon(new ImageIcon(("pictures/gray.png")));
			}
		}
		
		if (hold.color.equals(Color.CYAN))
		{
			//l
			location[0][0] = 0;
			location[0][1] = startingLocation;
			
			location[1][0] = 1;
			location[1][1] = startingLocation;
			
			location[2][0] = 2;
			location[2][1] = startingLocation;
			
			location[3][0] = 3;
			location[3][1] = startingLocation;
			
			hBlock[location[0][1]][location[0][0]].setIcon(new ImageIcon(("pictures/cyan.png")));
			hBlock[location[1][1]][location[1][0]].setIcon(new ImageIcon(("pictures/cyan.png")));
			hBlock[location[2][1]][location[2][0]].setIcon(new ImageIcon(("pictures/cyan.png")));
			hBlock[location[3][1]][location[3][0]].setIcon(new ImageIcon(("pictures/cyan.png")));
		}
		else if (hold.color.equals(Color.BLUE))
		{
			//L
			location[0][0] = 1;
			location[0][1] = startingLocation;
			
			location[1][0] = 2;
			location[1][1] = startingLocation;
			
			location[2][0] = 3;
			location[2][1] = startingLocation;
			
			location[3][0] = 3;
			location[3][1] = startingLocation + 1;
			
			hBlock[location[0][1]][location[0][0]-1].setIcon(new ImageIcon(("pictures/blue.png")));
			hBlock[location[1][1]][location[1][0]-1].setIcon(new ImageIcon(("pictures/blue.png")));
			hBlock[location[2][1]][location[2][0]-1].setIcon(new ImageIcon(("pictures/blue.png")));
			hBlock[location[3][1]][location[3][0]-1].setIcon(new ImageIcon(("pictures/blue.png")));
		}
		else if (hold.color.equals(Color.ORANGE))
		{
			//J
			location[0][0] = 1;
			location[0][1] = startingLocation + 1;
			
			location[1][0] = 2;
			location[1][1] = startingLocation + 1;
			
			location[2][0] = 3;
			location[2][1] = startingLocation + 1;
			
			location[3][0] = 3;
			location[3][1] = startingLocation;
			
			hBlock[location[0][1]][location[0][0]-1].setIcon(new ImageIcon(("pictures/orange.png")));
			hBlock[location[1][1]][location[1][0]-1].setIcon(new ImageIcon(("pictures/orange.png")));
			hBlock[location[2][1]][location[2][0]-1].setIcon(new ImageIcon(("pictures/orange.png")));
			hBlock[location[3][1]][location[3][0]-1].setIcon(new ImageIcon(("pictures/orange.png")));
		}
		else if (hold.color.equals(Color.YELLOW))
		{
			//O
			location[0][0] = 2;
			location[0][1] = startingLocation + 1;
			
			location[1][0] = 2;
			location[1][1] = startingLocation;
			
			location[2][0] = 3;
			location[2][1] = startingLocation + 1;
			
			location[3][0] = 3;
			location[3][1] = startingLocation;
			
			hBlock[location[0][1]][location[0][0]-1].setIcon(new ImageIcon(("pictures/yellow.png")));
			hBlock[location[1][1]][location[1][0]-1].setIcon(new ImageIcon(("pictures/yellow.png")));
			hBlock[location[2][1]][location[2][0]-1].setIcon(new ImageIcon(("pictures/yellow.png")));
			hBlock[location[3][1]][location[3][0]-1].setIcon(new ImageIcon(("pictures/yellow.png")));
		}
		else if (hold.color.equals(Color.GREEN))
		{
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
			
			hBlock[location[0][1]][location[0][0]-1].setIcon(new ImageIcon(("pictures/green.png")));
			hBlock[location[1][1]][location[1][0]-1].setIcon(new ImageIcon(("pictures/green.png")));
			hBlock[location[2][1]][location[2][0]-1].setIcon(new ImageIcon(("pictures/green.png")));
			hBlock[location[3][1]][location[3][0]-1].setIcon(new ImageIcon(("pictures/green.png")));
		}
		else if (hold.color.equals(Color.RED))
		{
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
			
			hBlock[location[0][1]][location[0][0]-1].setIcon(new ImageIcon(("pictures/red.png")));
			hBlock[location[1][1]][location[1][0]-1].setIcon(new ImageIcon(("pictures/red.png")));
			hBlock[location[2][1]][location[2][0]-1].setIcon(new ImageIcon(("pictures/red.png")));
			hBlock[location[3][1]][location[3][0]-1].setIcon(new ImageIcon(("pictures/red.png")));
		}
		else
		{
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
			
			hBlock[location[0][1]][location[0][0]-1].setIcon(new ImageIcon(("pictures/purple.png")));
			hBlock[location[1][1]][location[1][0]-1].setIcon(new ImageIcon(("pictures/purple.png")));
			hBlock[location[2][1]][location[2][0]-1].setIcon(new ImageIcon(("pictures/purple.png")));
			hBlock[location[3][1]][location[3][0]-1].setIcon(new ImageIcon(("pictures/purple.png")));
		}
	}
	
	synchronized public void holdBlock()
	{
		int[][] loc = block.location.clone();
		for (int i = 0; i < 4; i++)
		{
			grid[loc[i][0]][loc[i][1]] = 0;
			gridColor[loc[i][0]][loc[i][1]] = Color.WHITE;
		}
		if(hold == null)
		{
			hold = block.clone();
			timeCounter = 0;
			block = nextBlock.clone();
			nextBlock = new Block();
			
			int[][] location = block.getLocation().clone();

			for (int i = 0; i < 4; i++)
			{
				if(grid[location[i][0]][location[i][1]] == 2)
				{
					lose();
					break;
				}
				grid[location[i][0]][location[i][1]] = 1;
				gridColor[location[i][0]][location[i][1]] = block.getColor();
				if(i == 3)
				{
					hold = new Block(hold.shape, 0);
					updateNextBlock();
				}
			}
		}
		else
		{
			Block temp = block.clone();
			block = hold.clone();
			hold = temp.clone();
			block = new Block(block.shape, (int) (Math.random() * 9));
			
			int[][] location = block.getLocation().clone();
			for (int i = 0; i < 4; i++)
			{
				if(grid[location[i][0]][location[i][1]] == 2)
				{
					lose();
					break;
				}
				grid[location[i][0]][location[i][1]] = 1;
				gridColor[location[i][0]][location[i][1]] = block.getColor();
				if(i == 3)
				{
					hold = new Block(hold.shape, 0);
				}
			}
		}
		updateHeldBlock();
		updateLocations();
	}
	
	synchronized public void resetComplete() 
	{
		for (int i = 0; i < 10; i++)
		{
			for (int i1 = 0; i1 < 20; i1++)
			{
				grid1[i1][i] = new JLabel();
				grid1[i1][i].setBounds(i*20 + 50, i1*20 + 50, 20, 20);
				add(grid1[i1][i]);
				grid1[i1][i].setOpaque(true);
			}
			for (int i2 = 0; i2 < 23; i2++)
			{
				grid[i2][i] = 0;
				preGrid[i2][i] = -1;
				preGridColor[i2][i] = null;
				gridColor[i2][i] = Color.WHITE;
			}
		}
		
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				nBlock[i][j] = new JLabel();
				nBlock[i][j].setBounds(i*20 + 300, j*20 + 70, 20, 20);
				add(nBlock[i][j]);
				nBlock[i][j].setOpaque(true);
				nBlock[i][j].setIcon(new ImageIcon(("pictures/gray.png")));
				
				hBlock[i][j] = new JLabel();
				hBlock[i][j].setBounds(i*20 + 300, j*20 + 195, 20, 20);
				add(hBlock[i][j]);
				hBlock[i][j].setOpaque(true);
				hBlock[i][j].setIcon(new ImageIcon(("pictures/gray.png")));
			}
		}
		
		delay = 500;
		delay2 = 2;
		pause = false;
		time = 0;
		timeCounter = 0;
		remainingLines = 15;
		timeCounter2 = 0;
		
		remainingLinesLabel = new JLabel("Remaining Lines: " + remainingLines);
		remainingLinesLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		remainingLinesLabel.setBounds(270, 300, 400, 40);
		add(remainingLinesLabel);
		
		timeLabel = new JLabel(time / 100.0 + "");
		timeLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		timeLabel.setBounds(270, 350, 400, 40);
		add(timeLabel);
		
		secondsLabel = new JLabel("seconds");
		secondsLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		secondsLabel.setBounds(350, 350, 400, 40);
		add(secondsLabel);
		
		nBlockLabel = new JLabel("next block");
		nBlockLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		nBlockLabel.setBounds(305, 35, 400, 40);
		add(nBlockLabel);
		
		hBlockLabel = new JLabel("held block");
		hBlockLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		hBlockLabel.setBounds(305, 160, 400, 40);
		add(hBlockLabel);
		
		landed = false;
	}  // end resetComplete method

	synchronized public void printGrid()
	{
		for (int i = 0; i < 23; i++)
		{
			for (int i1 = 0; i1 < 10; i1++)
			{
				System.out.print(grid[i][i1]);
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}  // end printGrid method
}  // end Tetris class
