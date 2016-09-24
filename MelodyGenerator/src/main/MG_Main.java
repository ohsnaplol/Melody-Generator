package main;

public class MG_Main {

	public static void main(String[] args) throws InterruptedException {
		String[] myNotes = {"A","B"};
		int tempo = 123;
		
		Melody melody = new Melody(tempo, myNotes);
		melody.play();
		Thread.sleep(500);
		
		System.out.println("actually wait I want B to be D now");
		melody.change(1,"D");
		melody.play();
	}

}
