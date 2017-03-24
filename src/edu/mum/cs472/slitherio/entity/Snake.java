package edu.mum.cs472.slitherio.entity;

import java.io.Serializable;
import java.util.ArrayDeque;

public class Snake implements Serializable, Cloneable {
	
	private static final long serialVersionUID = 1L;

	private Boolean eaten;
	
	private Direction direction;
	
	private ArrayDeque<Body> body;
	
	private Body head;
	
	private boolean cheat;

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Body getHead() {
		return head;
	}

	public void setHead(Body head) {
		this.head = head;
	}

	public Snake(Body head, boolean cheat) {
		super();
		this.head = head;
		this.setEaten(false);
		this.setBody(new ArrayDeque<Body>());
		this.cheat = cheat;
	}

	public Boolean getEaten() {
		return eaten;
	}

	public void setEaten(Boolean eaten) {
		this.eaten = eaten;
	}

	public ArrayDeque<Body> getBody() {
		return body;
	}

	public void setBody(ArrayDeque<Body> body) {
		this.body = body;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public boolean isCheat() {
		return cheat;
	}

	public void setCheat(boolean cheat) {
		this.cheat = cheat;
	}
}
