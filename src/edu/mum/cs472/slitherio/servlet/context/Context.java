package edu.mum.cs472.slitherio.servlet.context;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.mum.cs472.slitherio.constant.Pages;
import edu.mum.cs472.slitherio.constant.SessionAttributeName;
import edu.mum.cs472.slitherio.dao.PlayerDao;
import edu.mum.cs472.slitherio.dao.impl.PlayerDaoImpl;
import edu.mum.cs472.slitherio.entity.Player;
import edu.mum.cs472.slitherio.entity.Status;

/**
 * Servlet implementation class Context
 */
@WebServlet("/Context")
public class Context extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PlayerDao playerDao;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Context() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Integer id = (Integer) session.getAttribute(SessionAttributeName.PLAYER_ID);
		// not a player 
		if (id == null) {
			
			// if a watcher
			if (session.getAttribute(SessionAttributeName.WATCHER) != null) {
				response.sendRedirect(Pages.GAMEPLAY_SERVLET);
				return;
			}
			
			// not both
			response.sendRedirect(Pages.LOGIN_SERVLET);
			return;
		}
		playerDao = new PlayerDaoImpl(getServletContext());
		Player player = playerDao.getPlayer(id);
		// a player still playing
		if (Status.PLAYING.equals(player.getStatus())) {
			response.sendRedirect(Pages.GAMEPLAY_SERVLET);
			return;
		}
		// already lost
		response.sendRedirect(Pages.LOST_SERVLET);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
