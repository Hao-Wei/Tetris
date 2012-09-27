package tetris;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;

import fileEditor.TetrisScore;

/**
 * display an frame with the amount of the time(seconds) the player played and
 * the amount of lines that the player didn't clear with buttons on the frame that
 * brings the player to the other frames
 *  
 * @author Hao Wei
 */
@SuppressWarnings("serial")
public class LosePage extends JFrame implements ActionListener
{
	int time;
	String[] names = new String[10];
	int[] highTimes = new int[10];
	ObjectInputStream input;
//	JLabel label1;
	JLabel label2;
	JLabel label3;
	JButton replayButton;
	JButton veiwHighscoresButton;
	JButton StartPageButton;
	JButton enterHighscoresButton;
	boolean submit;
	
	/**
	 * Sets the properties of the JFrame
	 * adds and initializes the GUI components
	 * sets time and submit as time1 and submint1 respectively
	 * 
	 * @param time1
	 * @param remainingLines
	 * @param submit1
	 */
	public LosePage(int time1, int remainingLines, boolean submit1)
	{
		super("Tetris");
		
		setSize(400, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.GREEN);
		
		time = time1;
		submit = submit1;
		readScore();
		
//		label1 = new JLabel("congratulations!");
//		label1.setFont(new Font("Times New Roman", Font.BOLD, 25));
//		label1.setBounds(20, 50, 400, 40);
		
		label2 = new JLabel("you have " + remainingLines + " uncleared lines");
		label2.setFont(new Font("Times New Roman", Font.BOLD, 25));
		label2.setBounds(20, 80, 400, 40);
		
		label3 = new JLabel("and you took " + time / 100.0 + " seconds");
		label3.setFont(new Font("Times New Roman", Font.BOLD, 25));
		label3.setBounds(20, 110, 400, 40);
		
		replayButton = new JButton("play again");
		replayButton.addActionListener(this);
		replayButton.setBounds(30, 230, 150, 25);
		
		veiwHighscoresButton = new JButton("view highscores");
		veiwHighscoresButton.addActionListener(this);
		veiwHighscoresButton.setBounds(200, 230, 150, 25);
		
		StartPageButton = new JButton("main menu");
		StartPageButton.addActionListener(this);
		StartPageButton.setBounds(120, 260, 150, 25);
		
		if (time < highTimes[9] && submit && remainingLines == 0)
		{
			enterHighscoresButton = new JButton("submit highscore");
			enterHighscoresButton.addActionListener(this);
			enterHighscoresButton.setBounds(120, 200, 150, 25);
			add(enterHighscoresButton);
		}
		
		setLayout(null);
		add(replayButton);
		add(veiwHighscoresButton);
		add(StartPageButton);
//		add(label1);
		add(label2);
		add(label3);
		setVisible(true);
	}  // end constructor method
	
	/**
	 * executes if a button id pressed
	 * display the frame corresponding to the button that was pressed
	 * disposes current frame
	 * 
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) 
	{
		dispose();
		if (e.getSource() == replayButton)
		{
			new Tetris();
		}
		if (e.getSource() == veiwHighscoresButton)
		{
			new HighscorePage();
		}
		if (e.getSource() == StartPageButton)
		{
			new StartPage();
		}
		if (e.getSource() == enterHighscoresButton)
		{
			new SubmitHighscorePage(time);
		}
	}  // end actionPerformed method
	
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
				highTimes[counter] = tetrisScore.getTime();
			}
			input.close();
		} 
		catch (IOException e) 
		{
			System.out.println("file not found");
			System.exit(1);
		}
	}  // end readScore method
}  // end LosePage class
