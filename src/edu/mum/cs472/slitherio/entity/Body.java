package edu.mum.cs472.slitherio.entity;

import java.io.Serializable;

public class Body implements Serializable, Cloneable {

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

	public Body(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj instanceof Body) {
			Body body = (Body) obj;
			if (this.x == body.x && this.y == body.y) {
				return true;
			}
		}
		return false;
	}
	
	public Food toFood() {
		return new Food(x, y);
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
