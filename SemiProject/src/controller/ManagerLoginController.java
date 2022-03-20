package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Manager;
import service.face.ManagerService;
import service.impl.ManagerServiceImpl;

@WebServlet("/manager/login")
public class ManagerLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ManagerService managerService = new ManagerServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/WEB-INF/views/manager/login.jsp").forward(req, resp); // 로그인 폼 보여주기
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//전달 파라미터 얻기 - 로그인 정보(id, password)
		Manager manager = managerService.getLoginManager(req);

		//로그인 인증 - ManagerService이용
		boolean isLogin = managerService.login(manager);
		//세션 정보 처리
		if( isLogin ) { //로그인 인증 성공
			
			manager = managerService.info(manager);
			
			//세션정보 저장하기
			HttpSession session = req.getSession();
			
			session.setAttribute("login", isLogin);
			session.setAttribute("id", manager.getId());
			session.setAttribute("managerNo", manager.getManager_no());
		}
		
		//메인페이지로 리다이렉트
		resp.sendRedirect("/managerMain");
		
	}
	
}
