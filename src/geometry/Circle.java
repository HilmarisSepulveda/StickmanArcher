/*
 * File: Circle.java
 * Author: Antonio F. Huertas
 * Course: COTI 3102-LR1, Prof. Antonio F. Huertas
 * Date: October 15, 2018
 * This class represents circles.
 */

package geometry;

public class Circle {

	/***********************************************************
	 * Class-level constants for the default values            *
	 ***********************************************************/
	public static final double DEFAULT_RADIUS = 1.0;
	public static final Point DEFAULT_LOCATION = new Point();
	public static final Color DEFAULT_COLOR = Color.BLACK;

	/***********************************************************
	 * Class-level variable for the number of circles          *
	 ***********************************************************/
	private static int numberOfCircles = 0;

	/***********************************************************
	 * Fields (attributes, instance variables)                 *
	 ***********************************************************/

	// The radius of this circle.
	private double radius;

	// The location of this circle.
	private Point location;

	// The color of this circle.
	private Color color;

	/***********************************************************
	 * Constructors (initializers)                             *
	 ***********************************************************/

	// Creates a circle with the given radius, location and color.
	// Exits if the given radius is negative.
	public Circle(double radius, Point location, Color color) {
		setRadius(radius);
		setLocation(location);
		setColor(color);
		numberOfCircles++;
	}

	// Creates a circle with the default values.
	public Circle() {
		this(DEFAULT_RADIUS, DEFAULT_LOCATION, DEFAULT_COLOR);
	}

	/***********************************************************
	 * Methods (operations)                                    *
	 ***********************************************************/

	// Returns the radius of this circle.
	public double getRadius() {
		return radius;
	}

	// Sets the radius of this circle to the given value.
	// Exits if the given radius is negative.
	public void setRadius(double rad) {
		if (rad < 0.0)
			die("Error: invalid radius: " + rad);
		radius = rad;
	}

	// Returns a deep copy of the location of this circle.
	public Point getLocation() {
		return new Point(location);
	}

	// Sets the location of this circle to a deep copy of the given value.
	public void setLocation(Point location) {
		this.location = new Point(location);
	}

	// Returns the color of this circle.
	public Color getColor() {
		return color;
	}

	// Sets the color of this circle to the given value.
	public void setColor(Color color) {
		this.color = color;
	}

	// Returns the area of this circle.
	public double getArea() {
		return Math.PI * Math.pow(radius, 2.0);
	}

	// Returns the perimeter of this circle.
	public double getPerimeter() {
		return 2 * Math.PI * radius;
	}

	// Returns the distance from this circle to the other given circle.
	public double getDistanceTo(Circle other) {
		return this.location.getDistanceTo(other.location);
	}

	// Returns the string representation of this circle.
	@Override
	public String toString() {
		return "radius: " + radius + ", location: " + location + 
				", color: " + color;
	}

	// Ends execution with a fatal error message.
	private void die(String msg) {
		System.err.println(msg);
		System.exit(1);
	}

	/***********************************************************
	 * Class-level method
	 ***********************************************************/

	// Returns the number of circles.
	public static int getNumberOfCircles() {
		return numberOfCircles;
	}

}