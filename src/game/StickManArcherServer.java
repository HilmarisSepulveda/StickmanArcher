package game;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * This class is a server-side console application for the
 * 	Stickman Archer game.
 * @author 
 * TODO : 	Handle input exceptions
 * 			Add sockets and server app
 */
public class StickManArcherServer {
	
	// The entry point of the application
	public static void main(String[] args) {
		
		try {
			ServerSocket listener = new ServerSocket(1234);
			System.out.println("Server is running.");
			
			try {
				while(true) {
					// Initialize the game
					// Instantiate and define both player as Threads
					// Set opponent for both players
					// Start player threads
				}
			}
			finally {
				listener.close();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
