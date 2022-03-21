package service.impl;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.JDBCTemplate;
import dao.face.InquiryDao;
import dao.face.ProfileDao;
import dao.impl.ProfileDaoImpl;
import dto.Inquiry;
import dto.Profile;
import dto.UserInfo;
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
		
		//가져오기
		profile.setId((String)session.getAttribute("userid"));
		profile.setNickname((String)req.getParameter("nickname"));
		profile.setPhonenumber((String)req.getParameter("phonenumber"));
		profile.setPassword((String)req.getParameter("password"));
		
		System.out.println(profile);
		
		ProfileDao profileDao = new ProfileDaoImpl();
		
		int res = profileDao.updateProfile(conn, profile);
		
		if (res > 0) {
			
			JDBCTemplate.commit(conn);
			
			
		} else {
			JDBCTemplate.rollback(conn);
			
		}
		
	}
	
	@Override
	public void deleteProfile(HttpServletRequest req) {

		//객체생성
		UserInfo userinfo = new UserInfo();
		
		HttpSession session = req.getSession();
		
		
		
		userinfo.setId((String)session.getAttribute("userid"));
		
		ProfileDao profileDao = new ProfileDaoImpl();

		
		userinfo = profileDao.selectProfile(conn, userinfo) ;
		
		userinfo.setId((String)session.getAttribute("userid"));

		if (req.getParameter("password").equals(userinfo.getPassword())) {
			
		int res = profileDao.deleteProfile(conn, userinfo);
		
		if ( res > 0 ) {
			
			
			JDBCTemplate.commit(conn);
			
		} else {
			
//			System.out.println("true");
			JDBCTemplate.rollback(conn);
			
		}
		
		}
		
	}
	
	@Override
	public int checkProfile(HttpServletRequest req) {
	

			//객체생성
			UserInfo userinfo = new UserInfo();
			
			HttpSession session = req.getSession();
			
			
			
			userinfo.setId((String)session.getAttribute("userid"));
			
			ProfileDao profileDao = new ProfileDaoImpl();

			
			userinfo = profileDao.selectProfile(conn, userinfo) ;
			
			userinfo.setId((String)session.getAttribute("userid"));

			int res = 0;

			if (req.getParameter("password").equals(userinfo.getPassword())) {
				
				//비밀번호가 맞을 경우
			res = 1;
			
			} else {
				
				//틀릴경우
				res = 0;
				
			}
			
			//반환
			 return res;
		}
		
		
		
	}
	

