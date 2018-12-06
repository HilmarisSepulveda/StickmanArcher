package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import entity.Arrow;
import entity.Bow;
import entity.Player;
import geometry.Point;

public class Server {

	/**
	 * Runs the server.
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {

		// 1. Open server connection
		ServerSocket listener = new ServerSocket(9090);
		System.out.println("Stickman Archer server is running.");


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
				p2.setPlayerLocation(new Point(40,0));
				p2.setSocket(socketC2);

				Player activePlayer = new Player();
				Player waitingPlayer = new Player();

				activePlayer = p1;
				waitingPlayer = p2;

				while(socketC1.isConnected() 
						&& socketC2.isConnected()
						) {

					// 6. Turns
//					while((p1lives > 0 &&
//							p2lives > 0
//							)) {

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

						StickmanCalculations.calculateTrajectory(arrow, bow, waitingPlayer);
						
						// Mandar al cliente
						StickmanCalculations.calculateHit(waitingPlayer, arrow);
						

						if (p1.getHP() <= 0)
							System.out.println(p1.getName() + " has died.");
						if (p2.getHP() <= 0)
							System.out.println(p2.getName() + " has died.");
						System.out.println();
						
						Player temp = activePlayer;
						activePlayer = waitingPlayer;
						waitingPlayer = temp;
					}


				}
//			}
		}

		catch (SocketException se) {
			//			System.exit(0);
			se.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
