package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Profile;
import service.face.ProfileService;
import service.impl.ProfileServiceImpl;
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

	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
	ProfileService profileService = new ProfileServiceImpl();
		
		Profile profile = profileService.getProfile(req);
		
		req.setAttribute("profile", profile );	
	
		//1아님 0이 반환
		int res = profileService.checkProfile(req);
		
		//맞는 조건
		if (res > 0){
			req.getRequestDispatcher("/WEB-INF/views/profile/ProfileList.jsp").forward(req, resp);
			
			
			
		} else {
			
			//한글설정
			resp.setContentType("text/html;charset=utf-8");
			
			//html
			PrintWriter out = resp.getWriter();
			
			out.println("<script> alert ('비밀번호가 틀렸습니다.'); location.href = '/profile/main' </script>");
			
			//비우기
			out.flush();
			
			//닫기
			out.close();
			
			
			
		} 
		
		
		
		
		
		
		
	}
}
