package utility;

import java.io.IOException;
import java.io.PrintWriter;

import entity.Player;

public class ServerUtility {
	public static void showPlayerLives(Player activePlayer, 
			Player waitingPlayer, Player p1, Player p2, 
			int p1Lives, int p2Lives, boolean hit, PrintWriter output) {

		try {
			if(waitingPlayer.equals(p1)) {

//				if (hit)
//					p1Lives--;

				output = new PrintWriter(waitingPlayer.getSocket().getOutputStream(), true);
				output.println(waitingPlayer.getName() +"'s lives = " + p1Lives + "\t" +
						activePlayer.getName() +"'s lives = " + p2Lives);

				output = new PrintWriter(activePlayer.getSocket().getOutputStream(), true);
				output.println(waitingPlayer.getName() +"'s lives = " + p1Lives + "\t" +
						activePlayer.getName() +"'s lives = " + p2Lives);

			}

			if(waitingPlayer.equals(p2)) {

				System.out.println("inside");

//				if(hit)
//					p2Lives--;

				output = new PrintWriter(waitingPlayer.getSocket().getOutputStream(), true);
				output.println(waitingPlayer.getName() +"'s lives = " + p2Lives + "\t" + 
					activePlayer.getName() +"'s lives = " + p1Lives);

				output = new PrintWriter(activePlayer.getSocket().getOutputStream(), true);
				output.println(waitingPlayer.getName() +"'s lives = " + p2Lives+ "\t" +
					activePlayer.getName() +"'s lives = " + p1Lives);
			}

			System.out.println("Finished SPL");
		}
		catch (IOException e) {
			e.printStackTrace();
		}


	}
}
