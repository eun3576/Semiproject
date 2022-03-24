package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import dto.UserInfo;
import service.face.UserLoginService;
import service.impl.UserLoginServiceImpl;

@WebServlet("/user/login")
public class UserLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserLoginService userLoginService = new UserLoginServiceImpl();

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("/user/login[post]");
		String uid = req.getParameter("userid");
		String upw = req.getParameter("userpw");
		
		UserInfo user = new UserInfo();
		user.setId(uid);
		user.setPassword(upw);
		int logincnt = userLoginService.login(user);
//		System.out.println(logincnt);
		
		//세션정보 저장하기
		HttpSession session = req.getSession();
		
		
		//세션 정보 처리
		if( logincnt == 1) { //로그인 인증 성공
			
			user = userLoginService.info(user);
			
			session.setAttribute("login", true);
			session.setAttribute("userid", user.getId());
			session.setAttribute("usernick", user.getNickname());
		}
		
		//메인페이지로 리다이렉트
		resp.sendRedirect("/");
		
	}

}
