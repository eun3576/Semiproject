package service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.ManagerDao;
import dao.face.ReviewDao;
import dao.impl.ManagerDaoImpl;
import dao.impl.ReviewDaoImpl;
import dto.Manager;
import dto.Review;
import dto.UserInfo;
import service.face.ManagerService;

public class ManagerServiceImpl implements ManagerService {

	private ManagerDao managerDao = new ManagerDaoImpl(); //DAO를 사용하기 위한 객체 선언
	public Connection conn = JDBCTemplate.getConnection();
	
	@Override
	public Manager getLoginManager(HttpServletRequest req) {

		Manager manager = new Manager();
		
		manager.setId( req.getParameter("id") );
		manager.setPassword( req.getParameter("password") );
		return manager;
	}

	@Override
	public boolean login(Manager manager) {

		int cnt = managerDao.selectCntManagerByIdPassword(JDBCTemplate.getConnection(), manager);
		//로그인 인증 성공
		if( cnt > 0) {
			return true;
		}
		//로그인 인증 실패
		return false;
	}
	
	@Override
	public Manager info(Manager manager) {
		return managerDao.selectManagerById(JDBCTemplate.getConnection(), manager);
	}

	@Override
	public List<UserInfo> selectUserList() {
		
		return managerDao.selectUserList(conn);
	}

	@Override
	public void userDelete(HttpServletRequest req) {
		
		UserInfo userInfo = new UserInfo();	

		userInfo.setUserNo(Integer.parseInt(req.getParameter("user_no")));
		
		managerDao.userDelete(conn, userInfo);
		
	}

	@Override
	public List<UserInfo> searchUserList(String req) {
	
		return managerDao.selectUserSearchList(JDBCTemplate.getConnection(), req);

	}

	@Override
	public void reviewDelete(HttpServletRequest req) {
		
		Review review = new Review();
		ReviewDao reviewDao = new ReviewDaoImpl();
		
		review.setReview_no(Integer.parseInt(req.getParameter("review_no")));
		
		int count = reviewDao.deleteCommentAllByReview(conn, review);
		
		if(count > 0) {
			managerDao.reviewDelete(conn, review);	
		}
		
		
	}
	

	
}
