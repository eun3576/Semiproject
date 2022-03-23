package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/nutrient/self")
public class NutrientSelfTestController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/nutrient/recommend [GET]");
		
		//세션 닉네임 가져오기
		String sessionNick = (String) req.getSession().getAttribute("usernick");
		//세션 닉네임 TEST
//		System.out.println(sessionNick);
		
		if (sessionNick == null || "".equals(sessionNick) ) {
//			System.out.println("비로그인 상태");
			resp.sendRedirect("/");
		} else {
			
			//VIEW 지정 및 응답 - forward
			req.getRequestDispatcher("/WEB-INF/views/recommend/self_test.jsp").forward(req, resp);
		}
	}
}
