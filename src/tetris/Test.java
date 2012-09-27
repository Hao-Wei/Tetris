package tetris;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Test extends JFrame{
	
	JLabel label;
	
	public static void main(String args[])
	{
		new Test();
	}
	public Test()
	{
		super("Test");
		
		setSize(450, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		label = new JLabel();
		label.setBounds(20 + 50, 20 + 50, 20, 20);
		add(label);
		label.setOpaque(true);
		label.setIcon(new ImageIcon("purple.png"));
		System.out.println("ttyl");
		
		setVisible(true);
	}
}
