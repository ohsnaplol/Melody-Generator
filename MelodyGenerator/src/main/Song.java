package main;

import java.util.Random;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

/* Song is used to share data about the song between the MG_Main class
 * and our GUI
 */
public class Song {
	static int tempo = 120;
	static int numOfNotes = 6;
	static Pattern pattern = new Pattern();
	static Player player = new Player();
	
	
	public static void generate() {
		pattern.clear();
		pattern.add("T" + tempo);
		//fill the pattern with notes
		Random rng = new Random();
		for (int i = 0; i < numOfNotes; i++) {
			pattern.add(KEYSIG.CMAJOR.getValue(rng.nextInt(7)));
		}
	}
	
	public static void play(){
		player.play(pattern);
	}
}
