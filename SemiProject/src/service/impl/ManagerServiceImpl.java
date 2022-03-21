package service.impl;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.ManagerDao;
import dao.impl.ManagerDaoImpl;
import dto.Manager;
import service.face.ManagerService;

public class ManagerServiceImpl implements ManagerService {

	private ManagerDao managerDao = new ManagerDaoImpl(); //DAO를 사용하기 위한 객체 선언
	
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
	
}
