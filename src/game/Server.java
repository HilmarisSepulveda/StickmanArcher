package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Random;

import javax.activity.ActivityRequiredException;

import entity.Arrow;
import entity.Bow;
import entity.Player;
import geometry.Point;
import utility.ServerUtilities;
import utility.ServerUtility;
import utility.StickmanCalculations;

public class Server {

	/**
	 * Runs the server.
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {

		// 1. Open server connection
		ServerSocket listener = new ServerSocket(9090);
		System.out.println("Stickman Archer server is running.");

		int p1Lives = 2, p2Lives = 2;
		Player opponent = new Player(new Point(40,0));


		// http://cs.lmu.edu/~ray/notes/javanetexamples/#tictactoe
		try {
			while(true) {

				// Bow and arrow
				Arrow arrow = new Arrow();
				Bow bow = new Bow();

				BufferedReader input;
				PrintWriter output = null;

				// 3. Create player 1 with client 1 input
				// Player 1
				Socket socketC1 = listener.accept();
				input = new BufferedReader(new InputStreamReader(socketC1.getInputStream()));
				String p1Name = input.readLine();
				System.out.println(p1Name + " is connected.");

				//////////

				Player p1 = new Player();
				p1.setName(p1Name);
				//				p1.setHP(lives);
				p1.setPlayerLocation(new Point(0,0));
				p1.setSocket(socketC1);

				// 5. Create player 2 with client 2 input
				// Player 2
				Socket socketC2 = listener.accept();
				input = new BufferedReader(new InputStreamReader(socketC2.getInputStream()));
				String p2Name = input.readLine();
				System.out.println(p2Name + " is connected.");

				Player p2 = new Player();
				p2.setName(p2Name);
				//				p2.setHP(lives);
				p2.setPlayerLocation(new Point(0,0));
				p2.setSocket(socketC2);

//
				Player activePlayer = new Player();
				Player waitingPlayer = new Player();

				activePlayer = p1;
				waitingPlayer = p2;

				while(socketC1.isConnected() 
						&& socketC2.isConnected()
						) {

					// 6. Turns
					while(p1Lives > 0 && p2Lives > 0) {
						
						// Send turn
						output = new PrintWriter(activePlayer.getSocket().getOutputStream(), true);
						output.println("true");

						// Send turn
						output = new PrintWriter(waitingPlayer.getSocket().getOutputStream(), true);
						output.println("false");

						// Send enter angle prompt
						output = new PrintWriter(activePlayer.getSocket().getOutputStream(), true);
						output.println("Enter the angle: ");

						// Receive angle
						input = new BufferedReader(new InputStreamReader(activePlayer.getSocket().getInputStream()));
						String angleString = input.readLine();
						bow.setAngle(Double.parseDouble(angleString));

						// Send enter power prompt
						output = new PrintWriter(activePlayer.getSocket().getOutputStream(), true);
						output.println("Enter the power: ");

						// Receive power
						String powerString = input.readLine();
						bow.setPower(Double.parseDouble(powerString));

						StickmanCalculations.calculateTrajectory(arrow, bow, opponent);

						// Mandar al cliente
						StickmanCalculations.calculateHit(opponent, arrow);
					
						Random rand = new Random();
						int randNum = rand.nextInt(300);
						

						if (opponent.getHP() <= 0) {
							System.out.println(waitingPlayer.getName() + " has died.");

							if(waitingPlayer.equals(p1)) {
								p1Lives--;
								System.out.println(waitingPlayer.getName() +"'s lives = " + p1Lives);
								System.out.println(activePlayer.getName() +"'s lives = " + p2Lives);
							}

							if(waitingPlayer.equals(p2)) {
								p2Lives--;
								System.out.println(waitingPlayer.getName() +"'s lives = " + p2Lives);
								System.out.println(activePlayer.getName() +"'s lives = " + p1Lives);
							}
								
							opponent.setLocation(new Point (randNum ,0));
							
							System.out.println("Opponent HP before increase: "  + opponent.getHP());
							opponent.setDefaultHP();
							
							System.out.println("Opponet location after reset:" + opponent.getLocation());
							System.out.println("Opponent HP after increase: "  + opponent.getHP());

							System.out.println("P1 lives = " + p1Lives);
							System.out.println("P2 lives = " + p2Lives);			
						}
						
//						// Send HP
						output = new PrintWriter(activePlayer.getSocket().getOutputStream(), true);
//						output.println("hello world");
						output.println(activePlayer.getName() + "'s HP" + activePlayer.getHP()
//								+ "\t" + waitingPlayer.getName() + "'s HP" + waitingPlayer.getHP()
								);
		//				output.println(waitingPlayer.getName() + "'s HP" + waitingPlayer.getHP());
//						
//						
//						// Send HP
						output = new PrintWriter(waitingPlayer.getSocket().getOutputStream(), true);
//						output.println("Hello World");
						output.println(activePlayer.getName() + "'s HP" + activePlayer.getHP()
//								+ "\t" + waitingPlayer.getName() + "'s HP" + waitingPlayer.getHP()
								);
	//					output.println(waitingPlayer.getName() + "'s HP" + waitingPlayer.getHP());

						
						output.flush();
						

						Player temp = activePlayer;
						activePlayer = waitingPlayer;
						waitingPlayer = temp;
					}
					
					System.out.println();
					System.out.println("GAME OVER!!!");
					
					if(p1Lives == 0)
						System.out.println(p2.getName() + "wins.");
					
					if(p2Lives == 0)
						System.out.println(p1.getName() + "wins.");
					
					
					socketC1.close();
					socketC2.close();
					System.exit(0);
				}
			}
		}

		catch (SocketException se) {
			//			System.exit(0);
			se.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
