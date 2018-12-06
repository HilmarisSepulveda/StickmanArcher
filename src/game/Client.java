package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
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
		
		
		
		
		
		

		Scanner in = new Scanner(System.in);
		//
		//		System.out.println(
		//				"Enter IP Address of a machine that is\n running "
		//						+ "the date service on port 9090:");
		String serverAddress = "172.20.10.4";
		//in.nextLine();

		boolean isConnected = false;
		while(!isConnected) {

			try {
				
				Socket socket = new Socket(serverAddress, 9090);
				System.out.println("Connected to server.");
				
				
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