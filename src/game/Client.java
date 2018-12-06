package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import entity.Player;
import geometry.Point;

public class Client {
	/**
	 * Runs the client as an application.  First it displays a dialog
	 * box asking for the IP address or hostname of a host running
	 * the date server, then connects to it and displays the date that
	 * it serves.
	 */
	public static void main(String[] args) throws IOException {


		String serverAddress = "192.168.1.118";		
		Socket socket = new Socket(serverAddress, 9090);

		BufferedReader input;
		PrintWriter output;

		boolean myTurn = false;

		boolean isConnected = socket.isConnected();

		printIntroDrawing();
		
		while(isConnected) {

			try {

				// 2 and 4: Print intro and Receive player name and send to server


				Scanner in = new Scanner (System.in);
				String playerName = in.nextLine();


				System.out.println("Connected to server.");

				// Send player name to server
				output = new PrintWriter(socket.getOutputStream(), true);
				output.println(playerName);
				
				

				// Receive turn
				input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String turn = input.readLine();

				if (turn == "true") {
					myTurn = true;
					System.out.println("TESTSTSTST");
				}

				if (myTurn) {


					// Receive prompt to enter angle
					input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					String prompt = input.readLine();
					System.out.println(prompt);

					// Sends angle to server
					output = new PrintWriter(socket.getOutputStream(), true);
					output.println(in.nextDouble());

					// Receive prompt to enter power
					input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					prompt = input.readLine();
					System.out.println(prompt);

					// Sends power to Server
					output = new PrintWriter(socket.getOutputStream(), true);
					output.println(in.nextDouble());

					myTurn = false;
				}

				else {
					System.out.println("Waiting for opponent...");
				}





				//				isConnected = true;
				//				printIntroDrawing();
				//
				//				ObjectOutputStream outputStream = 
				//						new ObjectOutputStream(socket.getOutputStream());
				//
				//				Player player = new Player(in.nextLine(), new Point(0,0));
				//				System.out.println("Object to be written = " + player.toString());
				//				outputStream.writeObject(player);
				//				
				//				outputStream.flush(); // necessary to avoid SocketException
				//				outputStream.close();
				////				socket.close();

			}
			catch (ConnectException e) {
				e.printStackTrace();
			}
			catch (SocketException se) {
				se.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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