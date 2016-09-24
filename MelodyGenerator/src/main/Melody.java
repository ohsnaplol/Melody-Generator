package main;
import org.jfugue.player.Player;

/*
 * all of the data about the current song will be held in the Melody class
 * The way our program will work is we create a melody object in MG_Main,
 * then we store the notes as a String array. From here we can manipulate
 * the length of the array, what notes are in each element of the array 
 * with the add, change etc methods.
 * 
 * WE NEED TO READ MORE DOCUMENTATION TO SEE IF THE LIBRARY DOES ANY OF THIS FOR US!!!!!
 */

public class Melody {
	private String[] notesArray = {};
	private int tempo =0;
	
	//constructor
	public Melody(int tempo, String[] notes) {
		this.tempo = tempo;
		this.notesArray = notes;
	}

	//inserts notes at end, beginning or adds more notes to the end.. do we need this?
	public void add(String note) {
		
	}
	
	//change note in notePosition to note
	public void change(int notePosition, String note) {
		notesArray[notePosition] = note;
	}
	
	public void play() {
		Player player = new Player();
		player.play(notesArray);
	}
}
