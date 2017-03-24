package edu.mum.cs472.slitherio.dao.impl;

import java.util.Map;

import javax.servlet.ServletContext;

import edu.mum.cs472.slitherio.dao.PlayerDao;
import edu.mum.cs472.slitherio.entity.Player;
import edu.mum.cs472.slitherio.entity.Status;
import edu.mum.cs472.slitherio.util.ContextUtil;

public class PlayerDaoImpl implements PlayerDao {

	private ServletContext context;

	private Map<Integer, Player> players;

	public PlayerDaoImpl(ServletContext context) {
		this.context = context;
	}

	private Map<Integer, Player> getPlayers() {
		if (players == null) {
			this.players = ContextUtil.getPlayers(context);
		}
		return players;
	}
	
	@Override
	public void addPlayer(Player player) {
		if (getPlayers() != null) {
			players.put(player.getId(), player);
		}
	}

	@Override
	public Player getPlayer(int id) {
		if (getPlayers() != null) {
			return players.get(id);
		}
		return null;
	}

	@Override
	public void removePlayer(int id) {
		Player player = getPlayers().get(id);
		if (Status.LOST.equals(player.getStatus())) {
			getPlayers().remove(id);
		}
	}

}
