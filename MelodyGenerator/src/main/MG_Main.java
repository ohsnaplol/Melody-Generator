package main;

import org.jfugue.pattern.Pattern;
import java.awt.EventQueue;
import java.util.Random;

public class MG_Main {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerateWindow window = new GenerateWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void generate(int tempo, int numOfNotes) {
		Pattern myPattern = new Pattern();
		
		myPattern.add("T" + tempo); //no, this isn't why the first note is sometimes held longer
		//fill the pattern with notes
		Random rng = new Random();
		for (int i = 0; i < numOfNotes; i++) {
			myPattern.add(KEYSIG.GSMELMIN.getValue(rng.nextInt(7)));
		}
		//put the pattern into song data (this kinda sucks)
		SongData.storedPattern = myPattern;
	}
}
