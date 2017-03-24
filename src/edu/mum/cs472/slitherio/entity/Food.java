package edu.mum.cs472.slitherio.entity;

import java.io.Serializable;

public class Food implements Serializable {

	private static final long serialVersionUID = 1L;

	private int x;

	private int y;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Food(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj instanceof Food) {
			Food food = (Food) obj;
			if (this.x == food.x && this.y == food.y) {
				return true;
			}
		}
		return false;
	}
}
