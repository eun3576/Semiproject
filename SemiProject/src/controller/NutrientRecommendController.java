package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/nutrient/recommend")
public class NutrientRecommendController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("nutrient/recommend [GET]");
		
		
		req.getRequestDispatcher("/WEB-INF/views/recommend/nutrient_recommend.jsp").forward(req, resp);
	}

}
