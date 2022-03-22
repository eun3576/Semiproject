package controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Profile;
import dto.Review;
import service.face.ProfileBoardService;
import service.impl.ProfileBoardServiceImpl;


@WebServlet("/profile/board")
public class ProfileBoardCotroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Profile profile = new Profile();
	
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		ProfileBoardService profileboardservice = new ProfileBoardServiceImpl();
		
		List<Review> Blist = profileboardservice.Blist(req);
		
		req.setAttribute("Blist", Blist);
		
//		System.out.println(Blist);
		
		//지정 및 응답
		req.getRequestDispatcher("/WEB-INF/views/profile/MyBoard.jsp").forward(req, resp);		
		
		
		
	}
	

	
}
