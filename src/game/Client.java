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
import utility.ClientUtilities;
import utility.ServerUtilities;

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

		ClientUtilities.printIntroDrawing();

		// 2 and 4: Print intro and Receive player name and send to server

		Scanner in = new Scanner (System.in);
		String playerName = in.nextLine();
		
		String prompt;


		// Send player name to server
		output = new PrintWriter(socket.getOutputStream(), true);
		output.println(playerName);
		
		ClientUtilities.showGreeting(playerName);

		while(isConnected) {

			try {

				// Receive turn
				input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String turn = input.readLine();
				System.out.println("received turn = " + turn);

				if (turn.equals("true")) {
					myTurn = true;
				}

				if (!myTurn) {
					System.out.println("Waiting for opponent...");
					System.out.println();
					
//					// Arrow hit / miss
//					input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//					prompt = input.readLine();
//					System.out.println(prompt);
//					System.out.println("receive hit/miss prompt not my turn");
//					
//					// Players' lives
//					input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//					prompt = input.readLine();
//					System.out.println(prompt);
//					System.out.println("receive player lives not my turn");
				}
			

				while (myTurn) {

					// Receive prompt to enter angle
					input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					prompt = input.readLine();
					System.out.println(prompt);
					System.out.println("receive angle");

					// Sends angle to server
					output = new PrintWriter(socket.getOutputStream(), true);
					output.println(in.nextDouble());
					System.out.println("sent angle");

					// Receive prompt to enter power
					input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					prompt = input.readLine();
					System.out.println(prompt);
					System.out.println();
					System.out.println("receive power");

					// Sends power to Server
					output = new PrintWriter(socket.getOutputStream(), true);
					output.println(in.nextDouble());
					System.out.println("sent power");
					
//					// Arrow hit / miss
//					input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//					prompt = input.readLine();
//					System.out.println(prompt);
//					System.out.println("receive hit/miss prompt my turn");
//					
//					// Players' lives
//					input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//					prompt = input.readLine();
//					System.out.println(prompt);
					
						
					myTurn = false;
					
				}
				
				// Arrow hit / miss
				input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				prompt = input.readLine();
				System.out.println(prompt);
				System.out.println("receive hit/miss prompt not my turn");
				
				// Players' lives
				input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				prompt = input.readLine();
				System.out.println(prompt);
				System.out.println("receive player lives not my turn");

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
}