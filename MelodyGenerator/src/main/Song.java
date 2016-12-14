package main;

import java.util.Random;
import java.io.*;
import java.util.Scanner;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.ManagedPlayer;
import org.jfugue.player.Player;

/**
 * Song is used to share data about the song between the MG_Main class and our GUI
 */
public class Song {
	static int tempo = 120;
	static int numOfNotes = 6;
	static int keyIndex;
	static Pattern pattern = new Pattern();
	static Player player = new Player();
	static ManagedPlayer mplayer = new ManagedPlayer();
	static Random rng = new Random();
	
	public static void generate(boolean tonic) {
		//get rid of our last pattern and put in tempo
		pattern.clear();
		pattern.setTempo(tempo);
		//fill the pattern with notes
		for (int i = 0; i <= numOfNotes-1; i++) {
			if(tonic && i == 0) { // if tonic, make the first note same as first note listed in KEYSIG
				pattern.add(KEYSIG.values()[keyIndex].getValue(0));
			} else {
				pattern.add(KEYSIG.values()[keyIndex].getValue(rng.nextInt(7)));
			}
		}
		if(tonic) { // add the first note we have listed in the KEYSIG
			pattern.add(KEYSIG.values()[keyIndex].getValue(0));
		} else {
			// otherwise just add another random note
			pattern.add(KEYSIG.values()[keyIndex].getValue(rng.nextInt(7)));
		}
		System.out.println(pattern);
	}
	
	/**
	 * Converts the player into a managed player and plays the tune. Doesn't stop playing for some reason.
	 * Just keeps going. Was hoping if (mplayer.getTickLength() == mplayer.getTickPosition()) would be 
	 * a good enough condition for it to determine if its at the end of the song, but it just checks it once,
	 * when it starts (which compares 0 to 768 for instance), then never checks it again.
	 */
	public static void play() {
		mplayer = player.getManagedPlayer();
		try {
			//the regular player "finishes" playing but not the managed player
			mplayer.start(player.getSequence(pattern));
			// this code doesn't work and is supposed to indicate when mplayer is finished playing the track.
			// this way we can actually end the playback as opposed to it going on forever. We may need to
			// use player listeners
			if (mplayer.getTickLength() == mplayer.getTickPosition()) {
				mplayer.finish();
			}
		} catch (Exception e) {
			System.out.println("Couldn't convert to MIDI");
		}
	}

	/**
	 * Let's you know what tick position the player was at when you stopped it
	 * and resets it to 0.
	 */
	public static void reset() {
		System.out.println("Tick position is "+mplayer.getTickPosition());
		mplayer.reset();
	}
	
	/**
	 * Simply writes the contents of the pattern to the file
	 * @param file
	 * @throws IOException
	 */
	public static void save(File file) throws IOException {
		PrintWriter out = new PrintWriter(file);
		out.print(pattern.toString());
		out.close();
	}
	
	/**
	 * Clears the current pattern, and loads it from the file
	 * @param file
	 * @throws IOException
	 */
	public static void load(File file) throws IOException {
		// I would just use pattern.load but it simply doesn't work.
		pattern.clear();
		Scanner s = null;
        try {
            s = new Scanner(new BufferedReader(new FileReader(file)));

            while (s.hasNext()) {
            	pattern.add(s.next());
            }
        } finally {
            if (s != null) {
                s.close();
            }
        }
	}
}
