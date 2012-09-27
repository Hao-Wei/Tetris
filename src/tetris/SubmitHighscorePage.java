package tetris;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import fileEditor.TetrisScore;
/**
 * displays a frame with a text box to enter the player's name in and a button which
 * the player clicks to submit his/her score
 * 
 * @author Hao Wei
 */
@SuppressWarnings("serial")
public class SubmitHighscorePage extends JFrame implements ActionListener
{
	JButton submitHighscoreButton;
	ObjectInputStream input;
	ObjectOutputStream output;
	String[] names = new String[10];
	int[] times = new int[10];
	JTextField textField1;
	JLabel label1;
	int time;
	String name;
	int version;
	boolean changed;
	boolean updated;
	
	/**
	 * Sets the properties of the JFrame
	 * adds and initializes the GUI components
	 * sets time as time1
	 * 
	 * @param time1
	 */
	public SubmitHighscorePage(int time1)
	{
		super("Tetris");
		setSize(400, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.GREEN);
		
		time = time1;
		
		setLayout(null);
		submitHighscoreButton = new JButton("submit");
		submitHighscoreButton.addActionListener(this);	
		submitHighscoreButton.setBounds(120, 230, 150, 25);
		add(submitHighscoreButton);
		
		textField1 = new JTextField("name");
		textField1.setBounds(120, 200, 150, 25);
		add(textField1);
		
		label1 = new JLabel("submit your highscore");
		label1.setFont(new Font("Times New Roman", Font.BOLD, 30));
		label1.setBounds(40, 90, 400, 40);
		add(label1);
		
		setVisible(true);
		
		//updated = UpdateScores.getScores("TetrisScore");
		//if(updated)
			readScore();
	}  // end constructor method
	
	/**
	 * executes when button is pressed and updates the score.hao file with the paryer's score
	 *
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) 
	{
//		if(updated)
//		{
			if(time > times[9])
				changed = false;
			else
				changed = true;
			if(changed)
			{
				addHighscore();
				addScore();
				//UpdateScores.sendScores("TetrisScore", name);
			}
//		}
//		else
//		{
//			JOptionPane.showMessageDialog(this, "unable to connect to server");
//		}
//		
		new LosePage(time, 0, false);
		dispose();
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
				times[counter] = tetrisScore.getTime();
			}
			try {
				version = (Integer) input.readObject();
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
	
	/**
	 * adds the plsyer's score to the highTimes and adds the player's name to
	 * names variable
	 */
	public void addHighscore()
	{
		int change = 9;
		for (int counter = 8; counter > -1; counter--)
		{
			if (time < times[counter])
			{
				change = counter;
			}
		}
		
		name = textField1.getText();
		if (!(name == null || name.equals("")))
		{		
			for (int counter = 9; counter > change; counter--)
			{
				times[counter] = times[counter - 1];
				names[counter] = names[counter - 1];
			}
			times[change] = time;
			names[change] = name;
		}
	}  // end addHighscore method
	
	/**
	 * adds the updated list of scores to the score.hao file
	 */
	public void addScore()
	{
		TetrisScore tetrisScore;
		try 
		{
			output = new ObjectOutputStream(new FileOutputStream("TetrisScore.hao"));
			for (int counter = 0; counter < 10; counter++)
			{
				tetrisScore = new TetrisScore(names[counter], times[counter]);
				output.writeObject(tetrisScore);
			}
			output.writeObject(version+1);
			if( output != null)
			{
				output.close();
			}
		} catch (IOException e) 
		{
			System.out.println("IO Error");
			System.exit(1);
		}
	}  // end addScore method
} // end SubmitHighscorePage class
