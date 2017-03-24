package edu.mum.cs472.slitherio.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import edu.mum.cs472.slitherio.dao.HighScoreDao;
import edu.mum.cs472.slitherio.entity.Player;
import edu.mum.cs472.slitherio.entity.Score;

public class HighScoreDaoImpl implements HighScoreDao {

	private Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/wap", "root", "admin");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateHighScore(Player player) {
		Connection con = getConnection();
		if (con != null) {
			Score score = new Score(player.getName(), player.getSnake().getBody().size());
			String statement = "INSERT INTO highscore(name,score) VALUES(?, ?)";
			PreparedStatement ps = null;
			try {
				ps = con.prepareStatement(statement);
				ps.setString(1, score.getName());
				ps.setInt(2, score.getScore());
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (ps != null)
						ps.close();
					if (con != null)
						con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public List<Score> getHighScore(int top) {
		Connection con = getConnection();
		List<Score> scores = new ArrayList<Score>();
		if (con != null) {
			String statement = "SELECT * FROM highscore";
			PreparedStatement ps = null;
			try {
				ps = con.prepareStatement(statement);
				ResultSet executeQuery = ps.executeQuery();
				while (executeQuery.next()) {
					int score = executeQuery.getInt("score");
					String name = executeQuery.getString("name");
					scores.add(new Score(name, score));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (ps != null)
						ps.close();
					if (con != null)
						con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		List<Score> collect = scores.stream().sorted((s1, s2) -> {
			if (s1.getScore() > s2.getScore())
				return -1;
			if (s1.getScore() < s2.getScore())
				return 1;
			return 0;
		}).collect(Collectors.toList());

		if (collect.size() > top) {
			return new ArrayList<>(collect.subList(0, top));
		}
		return collect;
	}

}
