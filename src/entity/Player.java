package entity;
import java.io.Serializable;
import java.net.Socket;

import geometry.*;

/**
 * This class represents a player entity represented 
 * by a circle.
 */
public class Player extends Circle implements Serializable, Runnable{

	/** Default player size */
	private final int DEFAULT_RADIUS = 3;

	/** Player name */
	private String name;

	@Override
	public String toString() {
		return "Player [DEFAULT_RADIUS=" + DEFAULT_RADIUS + ", name=" + name + ", HP=" + HP + ", socket=" + socket
				+ "]";
	}


	/** Player hit points */
	private int HP = 5;
	
	Socket socket;
	
	public Player()	{}
	
	/**
	 * Creates a player with the given name and location.
	 * @param name Player name.
	 * @param location Player location.
	 */
	public Player (Socket socket, String name, Point location) {
		super();
		setPlayerLocation(location);
		setPlayerSize(DEFAULT_RADIUS);
		setName(name);
		setHP(this.HP);
		this.socket = socket;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	/**
	 * Creates a player with the given name and location.
	 * @param name Player name.
	 * @param location Player location.
	 */
	public Player (String name, Point location) {
		super();
		setPlayerLocation(location);
		setPlayerSize(DEFAULT_RADIUS);
		setName(name);
		setHP(this.HP);
	}

	/**
	 * Returns the player location.
	 * @return The player location.
	 */
	public Point getPlayerLocation() {
		return this.getLocation();
	}


	/**
	 * Sets the player location on the Cartesian plane.
	 * @param location The value to be assigned to the 
	 * player location.
	 */
	public void setPlayerLocation(Point location) {
		this.setLocation(location);
	}

	/**
	 * Returns the radius of the circle that represents the player.
	 * @return The player size.
	 */
	public double getPlayerSize() {
		return this.getRadius();
	}

	/**
	 * Sets the radius size of the circle that represents the player.
	 * @param rad The value to be assigned to the player size.
	 */
	public void setPlayerSize(double rad) {
		this.setRadius(rad);
	}


	/**
	 * Returns the player name.
	 * @return The player name.
	 */
	public String getName() {
		return name;
	}


	/** Sets the player name with given value
	 * 
	 * @param name The value to be assigned to player name.
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * Returns the player HP
	 * @return The player HP
	 */
	public int getHP() {
		return HP;
	}


	/**
	 * Sets the value of the player's hit points.
	 * @param hP The player's hit points.
	 */
	public void setHP(int hP) {
		HP = hP;
	}

	public void run() {

			System.out.println("All players connected.");
		
	}

}
