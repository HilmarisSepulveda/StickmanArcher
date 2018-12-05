package entity;
import geometry.*;

/**
 * This class represents an arrow entity.
 */
public class Arrow {

	/** The damage the arrow deals the player. */
	private int damage = 20;
	
	/** The location of the arrow. */
	private Point location;
	
	/** The velocity of the arrow. */
	private double velocity = 1;
	
	/**
	 * Creates an arrow with the default values.
	 */
	public Arrow() {}
	
	public Point getLocation() {
		return location;
	}

	/**
	 * Sets the location of the arrow.
	 * @param location The location of the arrow in the
	 * 		Cartesian plane.
	 */
	public void setLocation(Point location) {
		this.location = location;
	}


	/**
	 * Returns the damage of the arrow.
	 * @return The damage of the arrow.
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * Sets the damage of the arrow.
	 * @param damage The value to be assigned to the damage
	 * 		of the arrow.
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}
	

	/**
	 * Returns the velocity of the arrow.
	 * @return The velocity of the arrow.
	 */
	public double getVelocity() {
		return velocity;
	}

	/**
	 * Sets the velocity of the arrow.
	 * @param velocity The value to be assigned to the
	 * 		velocity of the arrow.
	 */
	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}
	
	/**
	 * Returns the string representation of an arrow.
	 */
	@Override
	public String toString() {
		return "Arrow [damage=" + damage + ", location=" + location + ", velocity=" + velocity + "]";
	}
	
	
	

}
