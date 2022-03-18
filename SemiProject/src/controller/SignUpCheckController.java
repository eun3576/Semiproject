package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.UserInfo;
import service.face.AccountService;
import service.impl.AccountServiceImpl;

@WebServlet("/user/signup/check")
public class SignUpCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccountService accountService = new AccountServiceImpl();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8;");
		PrintWriter out = resp.getWriter();
		
		if(req.getParameter("joinUserId")!=null) {
			//userid를 저장하는 메소드 실행
			UserInfo user = accountService.getUserJoinId(req);
			//userid로 기존 아이디인지 확인
			String str = accountService.userIdCheck(user);
			if(str!=null) {
				out.write(str);
			}
		}
		
		if(req.getParameter("joinUserNick")!=null) {
			
			//userNick을 저장하는 메소드 실행
			UserInfo user = accountService.getUserJoinNick(req);

			//userNick으로 기존 닉네임인지 확인
			String str = accountService.userNickCheck(user);
			if(str!=null) {
				out.write(str);
			}
		}
		
	}
}
