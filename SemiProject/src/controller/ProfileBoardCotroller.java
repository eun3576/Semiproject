package controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Profile;
import dto.Review;
import service.face.ProfileBoardService;
import service.face.ProfileService;
import service.impl.ProfileBoardServiceImpl;
import util.Paging;


@WebServlet("/profile/board")
public class ProfileBoardCotroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Profile profile = new Profile();
	
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		ProfileBoardService profileboardservice = new ProfileBoardServiceImpl();
		
		//전달파라미터에서 현재 페이징 객채 알아내기
		Paging paging = profileboardservice.getPaging(req);
		System.out.println("ProfileBoardController doget" + paging);
	
		
		//게시글 조회
//		List<Review> Blist = profileboardservice.Blist(req);

		//게시글 페이징 목록 조회
		List<Review> Blist = profileboardservice.Blist( req, paging );
		
		req.setAttribute("Blist", Blist);
		req.setAttribute("paging", paging);
		
//		System.out.println(Blist);
		
		//지정 및 응답
		req.getRequestDispatcher("/WEB-INF/views/profile/MyBoard.jsp").forward(req, resp);		
		
		
		
	}
	

	
}
