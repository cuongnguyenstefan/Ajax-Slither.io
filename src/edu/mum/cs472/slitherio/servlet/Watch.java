package edu.mum.cs472.slitherio.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.mum.cs472.slitherio.constant.Pages;
import edu.mum.cs472.slitherio.constant.SessionAttributeName;
import edu.mum.cs472.slitherio.entity.Food;
import edu.mum.cs472.slitherio.entity.Player;
import edu.mum.cs472.slitherio.util.ContextUtil;

/**
 * Servlet implementation class Watch
 */
@WebServlet("/Watch")
public class Watch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Watch() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<Integer, Player> players = ContextUtil.getPlayingPlayers(getServletContext());
		List<Player> listPlaying = new ArrayList<Player>(players.values());
		List<Food> foods = ContextUtil.getFoods(getServletContext());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("players", listPlaying);
		map.put("foods", foods);
		
		Integer id = (Integer) request.getSession().getAttribute(SessionAttributeName.PLAYER_ID);
		if (id != null) {
			Player player = ContextUtil.getPlayers(getServletContext()).get(id);
			map.put("current", player);
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(map);
		request.setAttribute("data", json);
		request.getRequestDispatcher(Pages.WATCH_JSP).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
