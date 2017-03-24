package edu.mum.cs472.slitherio.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.mum.cs472.slitherio.constant.GameConfig;
import edu.mum.cs472.slitherio.constant.Pages;
import edu.mum.cs472.slitherio.constant.ParameterName;
import edu.mum.cs472.slitherio.constant.SessionAttributeName;
import edu.mum.cs472.slitherio.dao.SnakeDao;
import edu.mum.cs472.slitherio.dao.impl.SnakeDaoImpl;
import edu.mum.cs472.slitherio.entity.Direction;
import edu.mum.cs472.slitherio.util.ContextUtil;

/**
 * Servlet implementation class GamePlay
 */
@WebServlet("/GamePlay")
public class GamePlay extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GamePlay() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO send config + status (play or watch)
		if (request.getSession().getAttribute(SessionAttributeName.PLAYER_ID) == null
				&& request.getSession().getAttribute(SessionAttributeName.WATCHER) == null) {
			response.sendRedirect(Pages.CONTEXT);
			return;
		}
		request.setAttribute(ParameterName.GAME_CONFIG, GameConfig.getInstance());
		String type = "";
		Boolean isWatcher = (Boolean) request.getSession().getAttribute(SessionAttributeName.WATCHER);
		if (isWatcher != null && isWatcher) {
			type = "watch";
		} else {
			type = "play";
		}
		request.setAttribute(ParameterName.LOGIN_TYPE, type);
		request.getRequestDispatcher(Pages.GAMEPLAY_JSP).forward(request, response);

		// response.sendRedirect(Pages.GAMEPLAY_JSP);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer id = (Integer) request.getSession().getAttribute(SessionAttributeName.PLAYER_ID);
		// check if is a player
		if (id != null && ContextUtil.getPlayers(getServletContext()).containsKey(id)) {
			// update snake's new direction
			String d = request.getParameter(ParameterName.PLAY_DIRECTION);
			Direction direction = Direction.fromString(d);
			if (direction != null) {
				SnakeDao snakeDao = new SnakeDaoImpl(getServletContext());
				snakeDao.changeDirection(id, direction);
				return;
			}
		}
		response.sendRedirect(Pages.CONTEXT);
	}

}
