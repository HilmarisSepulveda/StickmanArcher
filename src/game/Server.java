package game;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import entity.Player;

public class Server {

	/**
	 * Runs the server.
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {

		ServerSocket listener = new ServerSocket(9090);
		System.out.println("Stickman Archer server is running.");
		
		Player player1, player2;

		// http://cs.lmu.edu/~ray/notes/javanetexamples/#tictactoe
		try {
			while(true) {
				Socket socket = listener.accept();

				ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
				player1 = (Player) inStream.readObject();
//				player1.setSocket(listener.accept());
				
//				player2 = (Player) inStream.readObject();
//				player2.setSocket(listener.accept());
//				
				
				System.out.println("Object received: Player 1 = " + player1.toString());
//				System.out.println("Object received: Player 2 = " + player2.toString());

//				player1.run();
//				player2.run();
//				socket.close();
//				listener.close();
			}

		} catch (SocketException se) {
			//			System.exit(0);
			se.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException cn) {
			cn.printStackTrace();
		}
	}
}
