package tetris;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * displays a frame that displays the title of the game and the buttons that the user can click to navigate
 * through the frames
 * 
 * @author Hao Wei
 */
@SuppressWarnings("serial")
public class StartPage extends JFrame implements ActionListener
{
	JLabel titleLable;
	JLabel authorLabel;
	JButton playButton;
	JButton highscoresButton;
	JButton instructionsButton;
	
	/**
	 * Sets the properties of the JFrame
	 * adds and initializes the GUI components
	 */
	public StartPage()
	{
		super("Tetris");
		setSize(400, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.GREEN);
		
		titleLable = new JLabel("Tetris!!!");
		titleLable.setFont(new Font("Times New Roman", Font.BOLD, 40));
		titleLable.setBounds(130, 90, 400, 40);
		
		authorLabel = new JLabel("by: Hao Wei");
		authorLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		authorLabel.setBounds(140, 130, 300, 40);
		
		playButton = new JButton("Play");
		playButton.addActionListener(this);
		playButton.setBounds(120, 200, 150, 25);
		
		highscoresButton = new JButton("view highscores");
		highscoresButton.addActionListener(this);
		highscoresButton.setBounds(120, 230, 150, 25);
		
		setLayout(null);
		add(titleLable);
		add(authorLabel);
		add(playButton);
		add(highscoresButton);
		setVisible(true);
	}  // end constructor method
	
	/**
	 * displays the frame corresponding to the button that was pressed
	 * 
	 * @param e
	 */
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == playButton)
		{
			new Tetris();
		}
		else if (e.getSource() == highscoresButton)
		{
			new HighscorePage();
		}
		this.dispose();
	}  // end actionPerformed method
}  // end StartPage class
