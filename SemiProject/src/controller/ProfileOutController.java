package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Profile;
import service.face.ProfileService;
import service.impl.ProfileServiceImpl;


@WebServlet("/profile/out")
public class ProfileOutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ProfileService profileService = new ProfileServiceImpl();
		
		//profile을 Servlet으로 가져오기
		Profile profile = profileService.getProfile(req);
		req.setAttribute("profile", profile );	
		
		HttpSession session = req.getSession();
		
		String id = (String)session.getAttribute("userid");
		
		req.setAttribute("userid", id);
		
		req.getRequestDispatcher("/WEB-INF/views/profile/Out.jsp").forward(req, resp);
		
		 
		
		}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		System.out.println("test");
		
		ProfileService profileService = new ProfileServiceImpl();
		
		profileService.deleteProfile(req);
		
		req.getSession().invalidate();
		
	
		req.getRequestDispatcher("/WEB-INF/views/profile/Out_ok.jsp").forward(req, resp);
		
		
	}
}
