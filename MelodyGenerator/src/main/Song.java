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
	static int keyIndex;
	static Pattern pattern = new Pattern();
	static Player player = new Player();
	static Random rng = new Random();
	
	
	public static void generate() {
		//get rid of our last pattern
		pattern.clear();
		//insert tempo label 
		pattern.add("T" + tempo);
		//fill the pattern with notes
		for (int i = 0; i < numOfNotes; i++) {
			pattern.add(KEYSIG.values()[keyIndex].getValue(rng.nextInt(7)));
		}
	}
	
	public static void play(){
		player.play(pattern);
	}
}
