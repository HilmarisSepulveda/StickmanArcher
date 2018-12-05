package game;
import java.util.Random;
import java.util.Scanner;

import entity.Arrow;
import entity.Bow;
import entity.Player;
import geometry.*;

/**
 * This class is a console application for the
 * 	Stickman Archer game.
 * @author 
 * TODO : 	Handle input exceptions
 * 			Add sockets and server app
 */
public class StickmanArcherClient {

	public static void main(String[] args) {

		Scanner in = new Scanner (System.in);

		printIntroDrawing();
		Random rand = new Random();
//		double randomNum = rand.nextDouble() * 100;

		Player player = new Player(in.nextLine(), new Point(0,0));
		
		// Must remove the player computer
		Player p2 = new Player("Computer", new Point(40, 0));
		Arrow arrow = new Arrow();
		Bow bow = new Bow();
		
		showGreeting(player, in);
		
		// For testing purposes (to kill him quickly)
		arrow.setDamage(100);

		// Game loop
		while((player.getHP() > 0 && p2.getHP() > 0)) {
			System.out.println("Enter the angle: ");
			bow.setAngle(in.nextDouble()); // 50
			System.out.println("Enter the power: ");
			bow.setPower(in.nextDouble()); // 20
			System.out.println();

			printHP(player, p2);
			calculateTrajectory(arrow, bow, p2);
			calculateHit(p2, arrow);
			printHP(player, p2);

		} // end while
		
		if (player.getHP() <= 0)
			System.out.println(player.getName() + " has died.");
		if (p2.getHP() <= 0)
			System.out.println(p2.getName() + " has died.");
		System.out.println();
		
		
		in.close();
		
	}

	private static void showGreeting(Player player, Scanner in) {
		System.out.println();
		System.out.println("***************************************************************");
		System.out.println(" Hello " +  player.getName() + "!");
		System.out.println("***************************************************************");


		System.out.println();
		System.out.println(" Press enter to start the adventure.");
		System.out.println();
		System.out.println("***************************************************************");
		in.nextLine();		
	}



	/**
	 * Makes the player hit calculation and provides hints
	 * 	for the player 
	 * @param player The player hit.
	 * @param arrow The projectile.
	 */
	private static void calculateHit(Player player, Arrow arrow) {
		System.out.print("x location: " + arrow.getLocation().getX() + "\n");
		System.out.print("y location: " + arrow.getLocation().getY() + "\n");
		if(playerWasHit(player, arrow)) {
			System.out.println("Arrow hit!");
			System.out.println();
			player.setHP(player.getHP() - arrow.getDamage());
		}

		else {
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
	}

	/**
	 * Calculates the trajectory of a projectile
	 * @param arrow The arrow being fired.
	 * @param bow The bow used to fire the arrow.
	 * @param p2 
	 */
	private static void calculateTrajectory(Arrow arrow, Bow bow, Player p2) {

		final double ACCELERATION = - 9.8;
		double velocity = bow.getPower() * arrow.getVelocity();
		double angle = bow.getAngle();
		int steps = 2;

		double xVelocity = velocity * Math.cos(angle);
		double yVelocity = velocity * Math.sin(angle);
		double totalTime = - 2.0 * yVelocity / ACCELERATION;
		double timeIncrement = totalTime / steps;
		double xIncrement = xVelocity * timeIncrement;

		double x = 0.0;
		double y = 0.0;
		double t = 0.0;

		for (int i = 1; i <= steps; i++) {
			t += timeIncrement;

			x += xIncrement;
			y = yVelocity * t + 0.5 * ACCELERATION * t * t;

			Point location = new Point ();
			location.setX(x);
			location.setY(y);

			arrow.setLocation(location);	
			calculateHit(p2,arrow);
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
	private static boolean playerWasHit(Player player, Arrow arrow)	{

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
//		if ((x > h - player.getPlayerSize() && x < h + player.getPlayerSize()) &&
//				(y > k - player.getPlayerSize() && y < k + player.getPlayerSize())) {
			return true;

		} else 
			return false;
	}

	/*
	 * Prints the players' hit points.
	 */
	private static void printHP(Player p1, Player p2) {
		System.out.println(p1.getName() + " HP: " + p1.getHP());
		System.out.println(p2.getName() + " HP: " + p2.getHP());
		System.out.println();
	}

	private static void printIntroDrawing() {
		System.out.println("***************************************************************");
		System.out.println("**********              STICKMAN ARCHER              **********");
		System.out.println("***************************************************************");
		System.out.println();
		System.out.println("                                                        |\r\n" + 
				"                                                         \\.\r\n" + 
				"                                                         /|.\r\n" + 
				"                                                       /  `|.\r\n" + 
				"                                                     /     |.\r\n" + 
				"                                                   /       |.\r\n" + 
				"                                                 /         `|.\r\n" + 
				"                                               /            |.\r\n" + 
				"                                             /              |.\r\n" + 
				"                                           /                |.\r\n" + 
				"      __                                 /                  `|.\r\n" + 
				"       -\\                              /                     |.\r\n" + 
				"         \\\\                          /                       |.\r\n" + 
				"           \\\\                      /                         |.\r\n" + 
				"            \\|                   /                           |\\\r\n" + 
				"              \\#####\\          /                             ||\r\n" + 
				"          ==###########>     /                               ||\r\n" + 
				"           \\##==      \\    /                                 ||\r\n" + 
				"      ______ =       =|__/___                                ||\r\n" + 
				"  ,--' ,----`-,__ ___/'  --,-`-==============================##==========>\r\n" + 
				" \\               '        ##_______ ______   ______,--,____,=##,__\r\n" + 
				"  `,    __==    ___,-,__,--'#'  ==='      `-'              | ##,-/\r\n" + 
				"    `-,____,---'       \\####\\              |        ____,--\\_##,/\r\n" + 
				"        #_              |##   \\  _____,---==,__,---'         ##\r\n" + 
				"         #              ]===--==\\                            ||\r\n" + 
				"         #,             ]         \\                          ||\r\n" + 
				"          #_            |           \\                        ||\r\n" + 
				"           ##_       __/'             \\                      ||\r\n" + 
				"            ####='     |                \\                    |/\r\n" + 
				"             ###       |                  \\                  |.\r\n" + 
				"             ##       _'                    \\                |.\r\n" + 
				"            ###=======]                       \\              |.\r\n" + 
				"           ///        |                         \\           ,|.\r\n" + 
				"           //         |                           \\         |.\r\n" + 
				"                                                    \\      ,|.\r\n" + 
				"                                                      \\    |.\r\n" + 
				"                                                        \\  |.\r\n" + 
				"                                                          \\|.\r\n" + 
				"                                                          /.\r\n" + 
				" Image from: cyu@athena.mit.edu (Erorppn Xrzavgm)              |");
		System.out.println();
		System.out.println("***************************************************************");
		System.out.println("***************************************************************");
		System.out.println();
		System.out.println("What is your name? ");
	}



}
