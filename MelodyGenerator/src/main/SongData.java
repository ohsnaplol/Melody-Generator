package main;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

/* I'm using this to share data about the song between the MG_Main class
 * and our GUI since i don't know how to do it any other way :/ I could
 * just pass arguments between them? That just sounds annoying.
 */
public class SongData {
	static int tempo = 120;
	static int numOfNotes = 6;
	//wooow
	static Pattern storedPattern = new Pattern(); // i dont like this
	
	/* 
	 * sole purpose of this method is for the play button. So we have two
	 * players and two pattern objects between these two classes? What a
	 * mess. There has to be a better way.
	 */
	
	public static void play() {
		Player player = new Player();
		player.play(storedPattern);
	}
}
