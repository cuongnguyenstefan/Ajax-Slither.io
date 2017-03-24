package edu.mum.cs472.slitherio.schedule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import edu.mum.cs472.slitherio.constant.ContextAttributeName;
import edu.mum.cs472.slitherio.constant.GameConfig;
import edu.mum.cs472.slitherio.dao.FoodDao;
import edu.mum.cs472.slitherio.dao.impl.FoodDaoImpl;
import edu.mum.cs472.slitherio.entity.Food;
import edu.mum.cs472.slitherio.entity.Player;

@WebListener
public class Scheduler implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ServletContext servletContext = arg0.getServletContext();
		FoodDao foodDao = new FoodDaoImpl(servletContext);
		// create context data
		Map<Integer, Player> players = new HashMap<Integer, Player>();
		servletContext.setAttribute(ContextAttributeName.MAP_PLAYER, players);
		List<Food> foods = new ArrayList<Food>();
		servletContext.setAttribute(ContextAttributeName.LIST_FOOD, foods);
		foodDao.createMoreFoods();
		
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    	scheduler.scheduleAtFixedRate(new PositionJob(servletContext), 0, GameConfig.getInstance().gameSpeed,
    			TimeUnit.MILLISECONDS);
	}

}
