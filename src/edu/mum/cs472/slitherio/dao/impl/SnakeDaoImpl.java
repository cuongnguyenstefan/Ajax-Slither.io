package edu.mum.cs472.slitherio.dao.impl;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import edu.mum.cs472.slitherio.constant.GameConfig;
import edu.mum.cs472.slitherio.dao.SnakeDao;
import edu.mum.cs472.slitherio.entity.Body;
import edu.mum.cs472.slitherio.entity.Direction;
import edu.mum.cs472.slitherio.entity.Food;
import edu.mum.cs472.slitherio.entity.Move;
import edu.mum.cs472.slitherio.entity.Player;
import edu.mum.cs472.slitherio.entity.Snake;
import edu.mum.cs472.slitherio.util.ContextUtil;

public class SnakeDaoImpl implements SnakeDao {

	private ServletContext context;

	public SnakeDaoImpl(ServletContext context) {
		this.context = context;
	}

	private Snake getSnake(int playerId) {
		Map<Integer, Player> players = ContextUtil.getPlayers(context);
		return players.get(playerId).getSnake();
	}

	@Override
	public void changeDirection(int playerId, Direction direction) {
		// check if next move is valid
		Snake snake = getSnake(playerId);
		Direction currentDirection = snake.getDirection();
		if (currentDirection == null) {
			snake.setDirection(direction);
			return;
		}
		List<Direction> nextValidMoves = currentDirection.nextValidMoves();
		if (nextValidMoves.contains(direction)) {
			snake.setDirection(direction);
		}
	}

	@Override
	public Move move(int playerId) {
		Snake snake = getSnake(playerId);
		if (snake.getDirection() == null) {
			return Move.NONE;
		}
		Body newHead = nextMove(snake.getHead(), snake.getDirection());
		if (newHead != null) {
			Move isHitAnything = isHitAnything(snake, newHead);
			if (!Move.HIT.equals(isHitAnything)) {
				// move it
				Body oldHead = snake.getHead();
				ArrayDeque<Body> body = snake.getBody();
				body.addFirst(oldHead);
				snake.setHead(newHead);
				if (!snake.getEaten()) {
					body.removeLast();
				}
				snake.setEaten(false);
			}
			return isHitAnything;
		} else {
			throw new IllegalArgumentException("Can not find next move for player " + playerId);
		}
	}

	private Move isHitAnything(Snake snake, Body newHead) {
		// boundary
		if (snake.isCheat()) {
			if (newHead.getX() < GameConfig.getInstance().boundaryXMin) {
				newHead.setX(GameConfig.getInstance().gameCellPerWidth);
			}
			if (newHead.getX() > GameConfig.getInstance().gameCellPerWidth) {
				newHead.setX(GameConfig.getInstance().boundaryXMin);
			}
			if (newHead.getY() < GameConfig.getInstance().boundaryYMin) {
				newHead.setY(GameConfig.getInstance().gameCellPerHeight);
			}
			if (newHead.getY() > GameConfig.getInstance().gameCellPerHeight) {
				newHead.setY(GameConfig.getInstance().boundaryYMin);
			}
		} else {
			boolean extendMinBoundary = newHead.getX() < GameConfig.getInstance().boundaryXMin
					|| newHead.getY() < GameConfig.getInstance().boundaryYMin;
			boolean extendMaxBoundary = newHead.getX() > GameConfig.getInstance().gameCellPerWidth
					|| newHead.getY() > GameConfig.getInstance().gameCellPerHeight;
			if (extendMinBoundary || extendMaxBoundary) {
				return Move.HIT;
			}
		}

		// eat anything
		Food headFood = new Food(newHead.getX(), newHead.getY());
		List<Food> foods = ContextUtil.getFoods(context);
		long eatAnyFood = foods.stream().filter(f -> headFood.equals(f)).count();
		if (eatAnyFood > 0) {
			(new FoodDaoImpl(context)).removeFood(headFood);
			snake.setEaten(true);
			return Move.EAT;
		}

		// hit any thing else
		Map<Integer, Player> players = ContextUtil.getPlayingPlayers(context);
		List<Body> obstacles = new ArrayList<Body>();
		players.forEach((key, val) -> obstacles.addAll(val.getSnake().getBody()));
		long hitThings = obstacles.stream().filter(b -> b.equals(newHead)).count();
		if (hitThings > 0) {
			return Move.HIT;
		}

		return Move.NONE;
	}

	private Body nextMove(Body head, Direction direction) {
		switch (direction) {
		case DOWN:
			return new Body(head.getX(), head.getY() + 1);
		case UP:
			return new Body(head.getX(), head.getY() - 1);
		case LEFT:
			return new Body(head.getX() - 1, head.getY());
		case RIGHT:
			return new Body(head.getX() + 1, head.getY());
		}
		return null;
	}
}
