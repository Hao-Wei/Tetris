    package tetris;
/**
 * displays the start page and plays the music
 * 
 * @author Hao Wei
 */
public class Run 
{
	static Music mainSong;
	
	/***************************************************************+------------------------------------------+-+-+
	 * creates an instance of the StartPage class and initializes the mainSong variable
	 * 
	 * @param args
	 */
	public static void main(String args[])
	{
		mainSong = new Music("unreal superhero 3.wav");  // i didn't have much time to chose a song...
		mainSong.start();     
		new StartPage();
	}  // end main method
}// end Run class
