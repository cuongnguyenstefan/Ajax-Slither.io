package edu.mum.cs472.slitherio.entity;

import java.util.Arrays;
import java.util.List;

public enum Direction {
	LEFT("left"), RIGHT("right"), UP("up"), DOWN("down");

	private String direction;

	private Direction(String d) {
		direction = d;
	}

	public String getDirection() {
		return direction;
	}

	public static Direction fromString(String text) {
		if (text != null) {
			for (Direction b : Direction.values()) {
				if (text.equalsIgnoreCase(b.direction)) {
					return b;
				}
			}
		}
		return null;
	}
	
	public List<Direction> nextValidMoves() {
		if (this.direction.equals("left") || this.direction.equals("right")) {
			return Arrays.asList(new Direction[] {UP, DOWN});
		} else {
			return Arrays.asList(new Direction[] {LEFT, RIGHT});
		}
	}
}
