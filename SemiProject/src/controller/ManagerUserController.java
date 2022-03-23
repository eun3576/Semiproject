package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.UserInfo;
import service.face.ManagerService;
import service.impl.ManagerServiceImpl;

@WebServlet(urlPatterns = "/manager/user")
public class ManagerUserController extends HttpServlet{

	//서비스 객체
	private ManagerService managerSevice= new ManagerServiceImpl();
		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//베스트 목록 가져오기
		List<UserInfo> userList = managerSevice.selectUserList();
		
		
		String search = req.getParameter("search");
		if(search != null && search.length() > 0) {
			userList = managerSevice.searchUserList(search); 
		}
		
		//조회된 결과 view로 전달
		req.setAttribute("userList", userList);
		
		req.getRequestDispatcher("/WEB-INF/views/manager/user.jsp").forward(req, resp); // 로그인 폼 보여주기
	
	}

	
	
}
