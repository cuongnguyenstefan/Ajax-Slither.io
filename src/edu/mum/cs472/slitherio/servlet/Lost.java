package edu.mum.cs472.slitherio.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.mum.cs472.slitherio.constant.Pages;
import edu.mum.cs472.slitherio.constant.ParameterName;
import edu.mum.cs472.slitherio.constant.SessionAttributeName;
import edu.mum.cs472.slitherio.dao.HighScoreDao;
import edu.mum.cs472.slitherio.dao.PlayerDao;
import edu.mum.cs472.slitherio.dao.impl.HighScoreDaoImpl;
import edu.mum.cs472.slitherio.dao.impl.PlayerDaoImpl;
import edu.mum.cs472.slitherio.entity.Player;
import edu.mum.cs472.slitherio.entity.Score;
import edu.mum.cs472.slitherio.entity.Status;
import edu.mum.cs472.slitherio.util.ContextUtil;

/**
 * Servlet implementation class Lose
 */
@WebServlet("/Lost")
public class Lost extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Lost() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = (int) request.getSession().getAttribute(SessionAttributeName.PLAYER_ID);
		if (ContextUtil.getPlayers(getServletContext()).containsKey(id)) {
			if (Status.LOST.equals(ContextUtil.getPlayers(getServletContext()).get(id).getStatus())) {
				HighScoreDao highScoreDao = new HighScoreDaoImpl();
				PlayerDao playerDao = new PlayerDaoImpl(getServletContext());
				Player player = playerDao.getPlayer(id);
				String color = player.getColor();
				String name = player.getName();
				boolean cheat = player.getSnake().isCheat();
				Player playAgain = new Player(name, color, cheat);
				playerDao.removePlayer(id);
				
				// get highscore list
				highScoreDao.updateHighScore(player);
				List<Score> highScore = highScoreDao.getHighScore(10);
				
				request.getSession().invalidate();
				request.getSession().setAttribute(ParameterName.HIGHSCORE, highScore);
				request.getSession().setAttribute(ParameterName.LOGIN_AGAIN, playAgain);
				response.sendRedirect(Pages.LOSE_JSP);
				return;
			}
		}
		response.sendRedirect(Pages.CONTEXT);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Player playAgain = (Player) request.getSession().getAttribute(ParameterName.LOGIN_AGAIN);
		request.getSession().setAttribute(SessionAttributeName.PLAYER_ID, playAgain.getId());
		(new PlayerDaoImpl(getServletContext())).addPlayer(playAgain);
		response.sendRedirect(Pages.CONTEXT);
	}

}
