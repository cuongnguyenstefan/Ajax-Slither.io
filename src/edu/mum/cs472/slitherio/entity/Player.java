package edu.mum.cs472.slitherio.entity;

import java.io.Serializable;

import edu.mum.cs472.slitherio.constant.GameConfig;

public class Player implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	private String name;
	
	private int id;
	
	private Snake snake;
	
	private Status status;
	
	private String color;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Player(String name, String color, boolean cheat) {
		this.name = name;
		this.id = (int) (Math.random() * 10000000);
		this.snake = new Snake(new Body(GameConfig.getInstance().initSnakeX, GameConfig.getInstance().initSnakeY), cheat);
		this.status = Status.PLAYING;
		this.color = color;
	}

	public Snake getSnake() {
		return snake;
	}

	public void setSnake(Snake snake) {
		this.snake = snake;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
