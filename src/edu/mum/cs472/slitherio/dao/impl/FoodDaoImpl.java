package edu.mum.cs472.slitherio.dao.impl;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;

import edu.mum.cs472.slitherio.constant.GameConfig;
import edu.mum.cs472.slitherio.dao.FoodDao;
import edu.mum.cs472.slitherio.entity.Body;
import edu.mum.cs472.slitherio.entity.Food;
import edu.mum.cs472.slitherio.entity.Player;
import edu.mum.cs472.slitherio.entity.Snake;
import edu.mum.cs472.slitherio.util.ContextUtil;

public class FoodDaoImpl implements FoodDao {
	
	private ServletContext context;
	
	public FoodDaoImpl(ServletContext context) {
		this.context = context;
	}
	
	private List<Food> getFoods() {
		return ContextUtil.getFoods(context);
	}
	
	
	@Override
	public void createMoreFoods() {
		int numberOfPlayer = ContextUtil.getPlayers(context).size();
		List<Food> currentFood = getFoods();
		/*if (currentFood == null) {
			currentFood = new ArrayList<Food>();
		}*/
		// add more food to list
		int multiplier = ThreadLocalRandom.current().nextInt(1, numberOfPlayer + 2);
		int totalFood = multiplier * GameConfig.getInstance().foodCreateNumber;
		
		
		List<Food> inappropriateFoodPosition = inappropriateFoodPosition();
		
		List<Food> newFoods = new ArrayList<Food>();
		for (int i = 0; i < totalFood; i++) {
			int x = ThreadLocalRandom.current().nextInt(0, GameConfig.getInstance().gameCellPerWidth);
			int y = ThreadLocalRandom.current().nextInt(0, GameConfig.getInstance().gameCellPerHeight);
			if (!inappropriateFoodPosition.contains(newFoods)) {
				newFoods.add(new Food(x, y));
			}
		}
		currentFood.addAll(newFoods);
	}
	
	private List<Food> inappropriateFoodPosition() {
		List<Food> inapproriatePosition = new ArrayList<Food>();
		Map<Integer, Player> players = ContextUtil.getPlayers(context);
		players.forEach((id, player) -> {
			player.getSnake().getBody().forEach(b -> {
				Food fd = new Food(b.getX(), b.getY());
				inapproriatePosition.add(fd);
			});
			Food fd = player.getSnake().getHead().toFood();
			inapproriatePosition.add(fd);
		});
		List<Food> list  = getFoods();
		if (list != null) {
			inapproriatePosition.addAll(list);
		}
		return inapproriatePosition;
	}


	@Override
	public void snakeDies(Snake snake) {
		ArrayDeque<Body> body = snake.getBody();
		List<Food> collect = body.stream().map(b -> b.toFood()).collect(Collectors.toList());
		getFoods().addAll(collect);
		getFoods().add(snake.getHead().toFood());
	}

	@Override
	public void removeFood(Food food) {
		for (Iterator<Food> i = getFoods().iterator(); i.hasNext();) {
			Food f = i.next();
			if (f.equals(food)) {
				i.remove();
			}
		}
	}

}
