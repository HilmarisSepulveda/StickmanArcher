package utility;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

import entity.Arrow;
import entity.Bow;
import entity.Player;
import geometry.*;

/**
 * This class is a console application for the
 * 	Stickman Archer game.
 * @author Hilmaris Sepulveda - La Durota
 * @author Axel Calderon - La Maquina
 * @author Jorge Morales - Jorgito.exe
 */
public class ServerUtilities {
	
	public static boolean playerIsDead(Player p) {
		return p.getHP() == 0;
	}


	/**
	 * Makes the player hit calculation and provides hints
	 * 	for the player 
	 * @param player The player hit.
	 * @param arrow The projectile.
	 */
	
	// TODO : Add OutputStream to parameters
	public static void calculateHit(Player player, Arrow arrow) {
		System.out.print("x location: " + arrow.getLocation().getX() + "\n");
		System.out.print("y location: " + arrow.getLocation().getY() + "\n");

		// Calculate missed distance
		double missedHint =  arrow.getLocation().getX() -
				player.getLocation().getX(); 

		// If missed distance negative, it has not reached the target
		if (missedHint < 0) {
			System.out.println("Arrow missed target by " +
					(missedHint + player.getPlayerSize()) + " meters");
			System.out.println("Hint: Try adding more power or "
					+ "increasing the angle");
		}

		// If missed distance positive, it passed the target.
		if (missedHint > 0) {
			System.out.println("Arrow missed target by " +
					(missedHint - player.getPlayerSize()) + " meters");
			System.out.println("Hint: Try dimishing power or "
					+ "decreasing the angle.");
		}
		System.out.println();

	}

	/**
	 * Calculates the trajectory of a projectile
	 * @param arrow The arrow being fired.
	 * @param bow The bow used to fire the arrow.
	 * @param p2 
	 */
	public static void calculateTrajectory(Arrow arrow, Bow bow, Player p2) {

		final double ACCELERATION = - 9.8;
		double velocity = bow.getPower() * arrow.getVelocity();
		double angle = bow.getAngle();
		int steps = 600;

		double xVelocity = velocity * Math.cos(angle);
		double yVelocity = velocity * Math.sin(angle);
		double totalTime = - 2.0 * yVelocity / ACCELERATION;
		double timeIncrement = totalTime / steps;
		double xIncrement = xVelocity * timeIncrement;

		double x = 0.0;
		double y = 0.0;
		double t = 0.0;


		for (int i = 1; i <= steps -1; i++) {
			t += timeIncrement;

			x += xIncrement;
			y = yVelocity * t + 0.5 * ACCELERATION * t * t;

			Point location = new Point ();
			location.setX(x);
			location.setY(y);

			arrow.setLocation(location);

			playerWasHit(p2, arrow);
			System.out.println(location);

		}



	}

	/**
	 * Returns true if the arrow's coordinate 
	 * 	intersect with the player given by the circle formula
	 * 	 (x – h)^2 + (y – k)^2 <= r^2.
	 * @param player The player being attacked.
	 * @param arrow The opponent's arrow.
	 * @return True if the arrow hits the player, otherwise false. 
	 */
	public static boolean playerWasHit(Player player, Arrow arrow)	{

		// The x coordinate of the circle's origin
		double h = player.getPlayerLocation().getX();

		// The y coordinate of the circle's origin
		double k = player.getPlayerLocation().getY();

		// The x point to be evaluated in the equation
		double x = arrow.getLocation().getX();

		// The y point to be evaluated in the equation.
		double y = arrow.getLocation().getY();


		if ((Math.pow((x-h), 2) + Math.pow((y- k), 2) <= 
				Math.pow(player.getPlayerSize(), 2))) {
			System.out.println("Arrow hit!");
			player.setHP(0);
			return true;
		} else 
			return false;
	}

	/*
	 * Prints the players' hit points.
	 */
	public static void printHP(Player p1, Player p2) {
		System.out.println(p1.getName() + " HP: " + p1.getHP());
		System.out.println(p2.getName() + " HP: " + p2.getHP());
		System.out.println();
	}
	
	public static void showPlayerLives(Player activePlayer, 
			Player waitingPlayer, Player p1, Player p2, 
			int p1Lives, int p2Lives, boolean hit, PrintWriter output) {
		
		try {
			if(waitingPlayer.equals(p1)) {
				
				if (hit)
					p1Lives--;

				output = new PrintWriter(waitingPlayer.getSocket().getOutputStream(), true);
				output.println(waitingPlayer.getName() +"'s lives = " + p1Lives + "\t" +
						activePlayer.getName() +"'s lives = " + p2Lives);
				
				output = new PrintWriter(activePlayer.getSocket().getOutputStream(), true);
				output.println(waitingPlayer.getName() +"'s lives = " + p1Lives + "\t" +
					activePlayer.getName() +"'s lives = " + p2Lives);
					
			}

			if(waitingPlayer.equals(p2)) {
				
				System.out.println("inside");
				
				if(hit)
					p2Lives--;

				output = new PrintWriter(waitingPlayer.getSocket().getOutputStream(), true);
				output.println(waitingPlayer.getName() +"'s lives = " + p2Lives + "\t" +
					activePlayer.getName() +"'s lives = " + p1Lives);
				
				output = new PrintWriter(activePlayer.getSocket().getOutputStream(), true);
				output.println(waitingPlayer.getName() +"'s lives = " + p2Lives + "\t" +
					activePlayer.getName() +"'s lives = " + p1Lives);
			}
			
			System.out.println("Finished SPL");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		

}

	



}
