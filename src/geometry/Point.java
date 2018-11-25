/*
 * File: Point.java
 * Author: Antonio F. Huertas
 * Course: COTI 3102-LR1, Prof. Antonio F. Huertas
 * Date: October 1, 2018
 * This class represents points.
 */

package geometry;

public class Point {
	
	/***********************************************************
	 * Class-level constants for default coordinates           *
	 ***********************************************************/
	public static final double DEFAULT_X = 0.0;
	public static final double DEFAULT_Y = 0.0;
	
	/***********************************************************
	 * Class-level variable for the number of points           *
	 ***********************************************************/
	private static int numberOfPoints = 0;
	
	/***********************************************************
	 * Fields (attributes, instance variables)                 *
	 ***********************************************************/

	// The x-coordinate of this point.
	private double x;
	
	// The y-coordinate of this point.
	private double y;
	
	/***********************************************************
	 * Constructors                                            *
	 ***********************************************************/

	// Creates a point with the given x- and y-coordinates.
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	// Creates a point with the default coordinates.
	public Point() {
		this(DEFAULT_X, DEFAULT_Y);
	}
	
	// Creates a point as a deep copy of the other given point.
	public Point(Point other) {
		this(other.x, other.y);
	}

	/***********************************************************
	 * Methods (operations)                                    *
	 ***********************************************************/
	
	// Returns the x-coordinate of this point.
	public double getX() {
		return x;
	}

	// Sets the x-coordinate of this point to the given value.
	public void setX(double x) {
		this.x = x;
	}

	// Returns the y-coordinate of this point.
	public double getY() {
		return y;
	}

	// Sets the y-coordinate of this point to the given value.
	public void setY(double y) {
		this.y = y;
	}
	
	// Returns the distance from this point to the other given point.
	public double getDistanceTo(Point other) {
		return Math.sqrt( Math.pow(other.x - this.x, 2) + Math.pow(other.y - this.y, 2) );
	}

	// Returns the string representation of this point.
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	/***********************************************************
	 * Class-level method
	 ***********************************************************/
	
	// Returns the number of points.
	public static int getNumberOfPoints() {
		return numberOfPoints;
	}

}