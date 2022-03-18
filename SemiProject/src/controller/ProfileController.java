package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Profile;
import service.face.ProfileService;
import util.Paging;


@WebServlet("/profile/main")
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Profile profile = new Profile();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	
		//지정 및 응답
		req.getRequestDispatcher("/WEB-INF/views/profile/ProfileMain.jsp").forward(req, resp);		
		
		
	}

}
