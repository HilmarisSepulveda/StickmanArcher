package entity;
/**
 * This class represents a bow entity.
 */
public class Bow {

	/** The angle the bow is aimed. */
	private double angle = Math.toRadians(45);
	
	/** The shooting power of the bow. */
	private double power = 0;
	
	/**
	 * Creates a bow with the default values.
	 */
	public Bow() {}
	
	/**
	 * Returns the angle the bow is aiming.
	 * @return The angle of the bow.
	 */
	public double getAngle() {
		return angle;
	}
	
	/**
	 * Sets the aiming angle of the bow.
	 * @param angle The angle of the bow.
	 */
	public void setAngle(double angle) {
		if (angle < 0 || angle > 90)
			throw new IllegalArgumentException();
		
		this.angle = Math.toRadians(angle);
	}
	
	/** Returns the shooting power of the bow.
	 * @return The shooting power of the bow.
	 */
	public double getPower() {
		return power;
	}
	
	/**
	 * Sets the shooting power of the bow.
	 * @param power The shooting power of the bow.
	 */
	public void setPower(double power) {
		this.power = power;
	}
	
	
	
}
