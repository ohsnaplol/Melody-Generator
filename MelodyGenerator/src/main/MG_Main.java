package main;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.IOException;
import static java.lang.System.out;

public class MG_Main {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		Pattern myPattern = new Pattern();
		Player player = new Player();
		Random rng = new Random();

		//fill the pattern with notes
		for (int i = 0; i < 15; i++) {
			myPattern.add(KEYSIG.GSMELMIN.getValue(rng.nextInt(7)));
		}
		
		//play our tune!
		player.play(myPattern);
		//Show what we've got in myPattern
		out.println(myPattern.getTokens());
		keyboard.close();
	}
}
