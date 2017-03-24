package edu.mum.cs472.slitherio.schedule;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import edu.mum.cs472.slitherio.constant.GameConfig;
import edu.mum.cs472.slitherio.dao.FoodDao;
import edu.mum.cs472.slitherio.dao.SnakeDao;
import edu.mum.cs472.slitherio.dao.impl.FoodDaoImpl;
import edu.mum.cs472.slitherio.dao.impl.SnakeDaoImpl;
import edu.mum.cs472.slitherio.entity.Food;
import edu.mum.cs472.slitherio.entity.Move;
import edu.mum.cs472.slitherio.entity.Player;
import edu.mum.cs472.slitherio.entity.Status;
import edu.mum.cs472.slitherio.util.ContextUtil;

public class PositionJob implements Runnable {

	private ServletContext context;

	private FoodDao foodDao;

	private SnakeDao snakeDao;

	// private HighScoreDao highScoreDao;

	public PositionJob(ServletContext context) {
		this.context = context;
		foodDao = new FoodDaoImpl(context);
		snakeDao = new SnakeDaoImpl(context);
	}

	@Override
	public void run() {
		// update position of snakes
		Map<Integer, Player> players = ContextUtil.getPlayers(context);
		List<Food> foods = ContextUtil.getFoods(context);

		// in try catch cause Scheduler doesn't show error.
		try {
			if (!players.isEmpty()) {
				players.forEach((id, player) -> {
					if (Status.PLAYING.equals(player.getStatus())) {
						Move move = snakeDao.move(id);
						switch (move) {
						case HIT:
							player.setStatus(Status.LOST);
							foodDao.snakeDies(player.getSnake());
							break;
						case EAT:
							Food food = player.getSnake().getHead().toFood();
							foodDao.removeFood(food);
							break;
						case NONE:
							break;
						}
					}
				});
			}

			// create more foods
			if (foods.size() < GameConfig.getInstance().foodThresholdPerPlayer * players.size()) {
				foodDao.createMoreFoods();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
