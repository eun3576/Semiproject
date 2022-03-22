package service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.JDBCTemplate;
import dao.face.ProfileBoard;
import dao.impl.ProfileBoardImpl;
import dto.Review;
import dto.UserInfo;
import service.face.ProfileBoardService;

public class ProfileBoardServiceImpl implements ProfileBoardService {
	
	Connection conn = JDBCTemplate.getConnection();
	ProfileBoard profileboard = new ProfileBoardImpl();
	
	
	@Override
	public List<Review> Blist(HttpServletRequest req) {
		
		//세션가져오기
		HttpSession session = req.getSession();
		
		UserInfo userinfo = new UserInfo();
		
		userinfo.setId((String)session.getAttribute("userid"));
		
		
		
		return profileboard.Blist(conn, userinfo);
		
	}

}
