package edu.mum.cs472.slitherio.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import edu.mum.cs472.slitherio.constant.ContextAttributeName;
import edu.mum.cs472.slitherio.entity.Food;
import edu.mum.cs472.slitherio.entity.Player;
import edu.mum.cs472.slitherio.entity.Status;

@SuppressWarnings("unchecked")
public class ContextUtil {

	private ContextUtil() {
	}
	
	public static final Map<Integer, Player> getPlayers(ServletContext context) {
		return (Map<Integer, Player>)context.getAttribute(ContextAttributeName.MAP_PLAYER);
	}
	
	public static final Map<Integer, Player> getPlayingPlayers(ServletContext context) {
		Map<Integer, Player> list = getPlayers(context);
		Map<Integer, Player> returnList = new HashMap<Integer, Player>();
		list.forEach((id, player) -> {
			if (Status.PLAYING.equals(player.getStatus())) {
				try {
					returnList.put(id, (Player) player.clone());
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}
		});
		return returnList;
	}
	
	public static final List<Food> getFoods(ServletContext context) {
		return (List<Food>) context.getAttribute(ContextAttributeName.LIST_FOOD);
	}
}
