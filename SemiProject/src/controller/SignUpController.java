package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.UserInfo;
import service.face.AccountService;
import service.impl.AccountServiceImpl;

@WebServlet("/user/signup")
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AccountService accountService = new AccountServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		//UserInfo객체 생성
		UserInfo user = new UserInfo();
		user.setId(req.getParameter("joinUserId"));
		user.setNickname(req.getParameter("joinUserNick"));
		user.setPassword(req.getParameter("userJoinpw"));
		user.setGender(req.getParameter("gender"));
		user.setPhonenumber(req.getParameter("userJoinphone"));
		
		//기존회원인지 확인
		boolean isExistence = accountService.userCheck(user);
		System.out.println(isExistence);
		
		
		if(isExistence) {
			accountService.insertUser(user);
		}
		
		resp.sendRedirect("/");
	}
}
