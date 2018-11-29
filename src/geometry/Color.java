/*
 * File: Color.java
 * Author: Antonio F. Huertas
 * Course: COTI 3102-LR1, Prof. Antonio F. Huertas
 * Date: October 1, 2018
 * This enumeration represents colors.
 */

package geometry;

public enum Color {
	
	// The set of available colors.
	BLACK, RED, GREEN, BLUE, YELLOW, WHITE;
	
	// Returns the string representation of this color.
	public String toString() {
		switch (this) {
			case BLACK: return "Black";
			case RED: return "Red";
			case GREEN: return "Green";
			case BLUE: return "Blue";
			case YELLOW: return "Yellow";
			case WHITE: return "White";
			default: return "unknown color";
		}
	}

}