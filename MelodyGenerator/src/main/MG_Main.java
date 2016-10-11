package main;

import java.awt.EventQueue;

public class MG_Main {
	
	//first argument is num of notes, second is tempo
	public static void main(String[] args) {
		
		if (args.length > 0) {
			try {
				Song.numOfNotes = Integer.parseInt(args[0]);
				Song.tempo = Integer.parseInt(args[1]);
			} catch (Exception f) {
				System.out.println("ERROR: Could not convert all arguments into numbers");
				return;
			}
			Song.generate();
			Song.play();
			System.out.println(Song.pattern.toString());
		} else {
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
	}
}
