package controller;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Profile;
import service.face.ProfileService;
import service.impl.ProfileServiceImpl;


@WebServlet("/profile/list")
public class ProfileListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	
	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		ProfileService profileService = new ProfileServiceImpl();
			
		//profile을 Servlet으로 가져오기
		Profile profile = profileService.getProfile(req);
		req.setAttribute("profile", profile );	
		
		
		req.getRequestDispatcher("/WEB-INF/views/profile/ProfileList.jsp").forward(req, resp);
		
		
		}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		System.out.println(req.getParameter("nickname"));
//		System.out.println(req.getParameter("phonenumber"));
//		System.out.println(req.getParameter("password"));
//		
		
		
		ProfileService profileService = new ProfileServiceImpl();
		
		profileService.updateProfile(req);
		
		
		
	}

}
