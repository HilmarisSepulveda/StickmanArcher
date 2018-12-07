package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Random;
import entity.Arrow;
import entity.Bow;
import entity.Player;
import geometry.Point;
import utility.ServerUtilities;

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
				PrintWriter output;

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


				Player activePlayer = new Player();
				Player waitingPlayer = new Player();

				activePlayer = p1;
				waitingPlayer = p2;

				while(socketC1.isConnected() && socketC2.isConnected()) {

					// 6. Turns
					while(p1Lives > 0 && p2Lives > 0) {
						

						// Send turn
						output = new PrintWriter(activePlayer.getSocket().getOutputStream(), true);
						output.println("true");
						output.flush();

						// Send turn
						output = new PrintWriter(waitingPlayer.getSocket().getOutputStream(), true);
						output.println("false");
						output.flush();

						// Send enter angle prompt
						output = new PrintWriter(activePlayer.getSocket().getOutputStream(), true);
						output.println("Enter the angle: ");
						output.flush();

						// Receive angle
						input = new BufferedReader(new InputStreamReader(activePlayer.getSocket().getInputStream()));
						String angleString = input.readLine();
						bow.setAngle(Double.parseDouble(angleString));

						// Send enter power prompt
						output = new PrintWriter(activePlayer.getSocket().getOutputStream(), true);
						output.println("Enter the power: ");
						output.flush();

						// Receive power
						String powerString = input.readLine();
						bow.setPower(Double.parseDouble(powerString));
					
						ServerUtilities.calculateTrajectory(arrow, bow, opponent);
						
						Random rand = new Random();
						int randNum = rand.nextInt(300);

						output.flush();
						if (opponent.getHP() <= 0) {
							System.out.println(waitingPlayer.getName() + " has died.");

							output = new PrintWriter(waitingPlayer.getSocket().getOutputStream(), true);
							output.println("You have been hit!");
							output.flush();
							
							output = new PrintWriter(activePlayer.getSocket().getOutputStream(), true);
							output.println("Arrow hit!");
							output.println();
							output.flush();
							

							ServerUtilities.showPlayerLives(activePlayer, waitingPlayer, 
									p1, p2, p1Lives, p2Lives, true, output);
							
							
							if(waitingPlayer.equals(p1)) 
									p1Lives--;
							
							if(waitingPlayer.equals(p2))
									p2Lives--;
						
							System.out.println("Changing opponent.");

							opponent.setLocation(new Point (randNum ,0));

							System.out.println("Opponent HP before increase: "  + opponent.getHP());
							opponent.setDefaultHP();

							System.out.println("Opponet location after reset:" + opponent.getLocation());
							System.out.println("Opponent HP after increase: "  + opponent.getHP());


							System.out.println("P1 lives = " + p1Lives);
							System.out.println("P2 lives = " + p2Lives);

						}
						else {

							// Arrow hit / miss to waiting player
							output = new PrintWriter(waitingPlayer.getSocket().getOutputStream(), true);
							output.println("You live to see another day!");
							output.flush();
							
							// Arrow hit / miss to active player
							ServerUtilities.calculateHit(activePlayer, arrow, output);
							
							ServerUtilities.showPlayerLives(activePlayer, waitingPlayer, 
									p1, p2, p1Lives, p2Lives, false, output);

							System.out.println("P1 lives = " + p1Lives);
							System.out.println("P2 lives = " + p2Lives);
						}

						Player temp = activePlayer;
						activePlayer = waitingPlayer;
						waitingPlayer = temp;
						
						
						System.out.println("Switch players...");
						System.out.println("Active p:" + activePlayer.getName());
						System.out.println("Waiting p: " + waitingPlayer.getName());
						
						System.out.println(p1Lives);
						System.out.println(p2Lives);
						
						p1Lives = 0;
						p2Lives = 0;
						
				
						
						output.flush();
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
