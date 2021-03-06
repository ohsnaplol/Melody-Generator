package main;

import java.awt.EventQueue;

public class MG_Main {
	
	/* first argument is num of notes, second is tempo, third is the index of the key
	 * key you want in KEYSIG, fourth is whether you want to hear the song if there is 
	 * no fourth argument, it'll assume you want no sound output.
	 */
	public static void main(String[] args) {
		// If we're given arguments
		if (args.length > 0) {
			boolean soundOutput = false;
			try {
				Song.numOfNotes = Integer.parseInt(args[0]);
				Song.tempo = Integer.parseInt(args[1]);
				Song.keyIndex = Integer.parseInt(args[2]);
			} catch (Exception f) {
				System.out.println("ERROR: Invalid arguments");
				return;
			}
			if (Song.keyIndex > 51 || Song.keyIndex < 0) {
				System.out.println("keyIndex must be between 0 and 51");
				return;
			}
			try {
				soundOutput = Boolean.valueOf(args[3]);
			} catch (Exception b) {};
			Song.generate(true);
			if (soundOutput)
				Song.play();
			System.out.println(Song.pattern.toString());
		} else { // Launch GUI
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						GenerateWindow window = new GenerateWindow();
						window.frmMelodyGenerator.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
}
