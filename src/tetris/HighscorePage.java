package tetris;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.JButton;
import javax.swing.JFrame;

import fileEditor.TetrisScore;

/**
 * displays a frame that displays the top ten high scores and the names of the players
 * with a button to return to the start page
 * 
 * @author Hao Wei
 */
@SuppressWarnings("serial")
public class HighscorePage extends JFrame implements ActionListener
{
	JButton startPageButton;
	ObjectInputStream input;
	String[] names = new String[10];
	int[] times = new int[10];
	
	/**
	 * Sets the properties of the JFrame
	 * adds and initializes the GUI components
	 */
	public HighscorePage()
	{
		super("Tetris");
		setSize(400, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.GREEN);
		
		startPageButton = new JButton("main menu");
		startPageButton.addActionListener(this);
		startPageButton.setBounds(120, 290, 150, 25);
		
		setLayout(null);
		add(startPageButton);
		setVisible(true);
		
		//UpdateScores.getScores("TetrisScore");
		
		readScore();
	}  // end constructor method
	
	/**
	 * executes when a button is pressed
	 * brings user back to start page
	 * 
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == startPageButton)
		{
			new StartPage();
			this.dispose();
		}
		else
		{
			repaint();
		}
	}  // end actionPerformed method
	
	/**
	 * paints scores and names on frame
	 * 
	 * @param g
	 */
	public void paint(Graphics g)
	{
		super.paint(g);
		g = this.getContentPane().getGraphics();
		int y = 85;
		
		g.setFont(new Font("Times New Roman", Font.BOLD, 17));
		
		g.drawString("Name", 95, 50);
		g.drawString("Time", 290, 50);
		
		g.setFont(new Font("Times New Roman", Font.BOLD, 20));
		g.setColor(new Color(255, 255, 0));
		g.drawString((1) + ".", 70, y);
		g.drawString(names[0], 100, y);
		g.drawString(times[0] / 100.0 + "", 300, y);
		y += 18;
		
		g.setFont(new Font("Times New Roman", Font.BOLD, 19));
		g.setColor(new Color(192, 192, 192));
		g.drawString((2) + ".", 70, y);
		g.drawString(names[1], 100, y);
		g.drawString(times[1] / 100.0 + "", 300, y);
		y += 18;
		
		g.setFont(new Font("Times New Roman", Font.BOLD, 18));
		g.setColor(new Color(128, 64, 0));
		g.drawString((3) + ".", 70, y);
		g.drawString(names[2], 100, y);
		g.drawString(times[2] / 100.0 + "", 300, y);
		y += 18;
		
		g.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		g.setColor(Color.BLACK);
		
		for (int counter = 3; counter < 10; counter++)
		{
			g.drawString((counter + 1) + ".", 70, y);
			g.drawString(names[counter], 100, y);
			g.drawString(times[counter] / 100.0 + "", 300, y);
			y += 18;
		}
	}  // end paint method
	
	/**
	 * reads the score from the score.hao file and saves the scores in names and highTimes
	 */
	public void readScore()
	{
		TetrisScore tetrisScore = null;
		try 
		{
			input = new ObjectInputStream(new FileInputStream("TetrisScore.hao"));
			for (int counter = 0; counter < 10; counter++)
			{
				try 
				{
					tetrisScore = (TetrisScore) input.readObject();
				} 
				catch (ClassNotFoundException e) 
				{
					e.printStackTrace();
				}
				names[counter] = tetrisScore.getName();
				times[counter] = tetrisScore.getTime();
			}
			try {
				System.out.println((Integer) input.readObject());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			input.close();
		} 
		catch (IOException e) 
		{
			System.out.println("file not found");
			System.exit(1);
		}
	}  // end readScore method
}// end HighscorePage class
