package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.JDBCTemplate;
import dao.face.AccountDao;
import dao.impl.AccountDaoImpl;
import dto.UserInfo;

@WebServlet("/user/signup/check")
public class SignUpCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccountDao accountDao = new AccountDaoImpl();
	private Connection conn = JDBCTemplate.getConnection();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8;");
		UserInfo user = new UserInfo();
		if(req.getParameter("userJoinid")!=null) {
			user.setId(req.getParameter("userJoinid"));
			while(accountDao.userIdCheck(conn, user)==false) {
				PrintWriter out = resp.getWriter();
				out.write("사용중인 아이디입니다.");
				break;
			}
		}
		if(req.getParameter("userJoinnick")!=null) {
			user.setNickname(req.getParameter("userJoinnick"));
			while(accountDao.userNickCheck(conn, user)==false) {
				PrintWriter out = resp.getWriter();
				out.write("사용중인 닉네임입니다");
				break;
			}
		}
	}
}
