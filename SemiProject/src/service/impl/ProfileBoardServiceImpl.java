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
import util.Paging;

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

	
	@Override
	public List<Review> Blist(HttpServletRequest req, Paging paging) {
		
		HttpSession session = req.getSession();
		
		UserInfo userinfo = new UserInfo();
		userinfo.setId((String)session.getAttribute("userid"));
		
		//페이징 적용해서 조회결과 반환
		return profileboard.Blist(conn, userinfo , paging);
	}
	
	
	@Override
	public Paging getPaging(HttpServletRequest req) {
		
		//전달파라미터 curPage 추출하기
		ProfileBoard profileboard = new ProfileBoardImpl();
		
		HttpSession session = req.getSession();
		
		UserInfo userinfo = new UserInfo();
		userinfo.setId((String)session.getAttribute("userid"));
		
		String param = req.getParameter("curPage");
		
		int curPage = 0;
		if (param != null && !"".equals(param) ) {
			curPage = Integer.parseInt(param);
		} else {
			
			System.out.println("[warn] ProfileBoardService getPaging ");
		}
		
		//총 게시글 조회하기
		int totalCount = profileboard.selectCntAll(conn , userinfo);
		
		//Paging객체 생성 - 페이징 계산
		Paging paging = new Paging(totalCount, curPage);
		
		
		return paging;
	}
	
//
//	@Override
//	public Review getReviewno(Review review) {
//		
//		ProfileBoard profileboard = new ProfileBoardImpl();
//		
//		review = profileboard.selectReview(conn, review);
//		
//		
//		
//		
//		return review;
//	}
}
