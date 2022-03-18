package service.impl;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.JDBCTemplate;
import dao.face.ProfileDao;
import dao.impl.ProfileDaoImpl;
import dto.Profile;
import service.face.ProfileService;

public class ProfileServiceImpl implements ProfileService{

	
	Connection conn = JDBCTemplate.getConnection();
	
	
	@Override
	public Profile getProfile(HttpServletRequest req) {
	
	HttpSession session = req.getSession();
	
	Profile profile = new Profile();
	
	profile.setId((String)session.getAttribute("userid"));

	//Dao
	ProfileDao profileDao = new ProfileDaoImpl();
	
	//오라클이랑 Dao에서 정보 받기
	 profile = profileDao.selectProfile(conn, profile);
	
	 
	return profile;
}
	
	@Override
	public void updateProfile(HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		
		Profile profile = new Profile();
		
		profile.setId((String)session.getAttribute("userid"));
		
		ProfileDao profileDao = new ProfileDaoImpl();
		
		int res = profileDao.updateProfile(conn, profile);
		
		if (res > 0) {
			
			JDBCTemplate.commit(conn);
			
			
		} else {
			JDBCTemplate.rollback(conn);
			
		}
		
	}
	
	
	
}
