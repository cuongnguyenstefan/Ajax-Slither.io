package edu.mum.cs472.slitherio.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.mum.cs472.slitherio.constant.Pages;
import edu.mum.cs472.slitherio.constant.ParameterName;
import edu.mum.cs472.slitherio.constant.SessionAttributeName;
import edu.mum.cs472.slitherio.dao.PlayerDao;
import edu.mum.cs472.slitherio.dao.impl.PlayerDaoImpl;
import edu.mum.cs472.slitherio.entity.Player;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(Pages.LOGIN_JSP).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter(ParameterName.LOGIN_TYPE);
		if (type != null && !type.isEmpty()) {
			HttpSession session = request.getSession();
			if ("watch".equals(type)) {
				session.setAttribute(SessionAttributeName.WATCHER, true);
			}
			if ("play".equals(type)) {
				String name = request.getParameter(ParameterName.LOGIN_PLAYERNAME);
				String color = request.getParameter(ParameterName.LOGIN_COLOR);
				String cheat = request.getParameter(ParameterName.CHEAT_ENABLE);
				boolean cheatEnable = false;
				if (color.isEmpty()) {
					int random = (int) (Math.random() * 1000000);
					color = "#" + random; 
				}
				if (name.isEmpty()) {
					name = "Anonymous";
				}
				if (ParameterName.CHEAT_ENABLE.equals(cheat)) {
					cheatEnable = true;
				}
				PlayerDao playerDao = new PlayerDaoImpl(getServletContext());
				Player player = new Player(name, color, cheatEnable);
				session.setAttribute(SessionAttributeName.PLAYER_ID, player.getId());
				playerDao.addPlayer(player);
			}
		}
		response.sendRedirect(Pages.CONTEXT);
	}

}
