package service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.JDBCTemplate;
import dao.face.InquiryDao;
import dao.impl.InquiryDaoImpl;
import dto.Inquiry;
import dto.InquiryAnswer;
import dto.UserInfo;
import service.face.InquiryService;
import util.Paging;

public class InquiryServiceImpl implements InquiryService{
	Connection conn = JDBCTemplate.getConnection();
	InquiryDao inquiryDao = new InquiryDaoImpl();
	
	@Override
	public List<Inquiry> getInquiryList(Paging paging) {
		
		return inquiryDao.getInquiryList(conn, paging);
	}
	
	@Override
	public Paging getPaging(HttpServletRequest req) {
		
		String param = req.getParameter("curPage");
		int curPage = 0;
		if( param != null && !"".equals( param ) ) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARN] BoardService getPaging() - curPage값이 null이거나 비어있음");
		}
		
		int totalCount = inquiryDao.inquiryCntAll(conn);
		
		Paging paging = new Paging(totalCount, curPage);
		
		return paging;
		
	}
	
	@Override
	public void insertInquiry(HttpServletRequest req) {
		HttpSession session = req.getSession();
		
		UserInfo userInfo = new UserInfo();
		userInfo.setId((String)session.getAttribute("userid"));
		userInfo = inquiryDao.selectUserNoByUserId(conn, userInfo);
		
		Inquiry inquiry = new Inquiry();
		inquiry.setTitle(req.getParameter("title"));
		inquiry.setPassword(Integer.parseInt(req.getParameter("password")));
		inquiry.setContent(req.getParameter("content"));
		inquiry.setUser_no(userInfo.getUserNo());
		inquiryDao.insertInquiry(conn, inquiry);
		
	}
	
	@Override
	public Inquiry getInquiry(HttpServletRequest req) {
		Inquiry inquiry = new Inquiry();
		inquiry.setInquiry_no(Integer.parseInt(req.getParameter("inquiry_no")));
		
		return inquiryDao.selectInquiry(conn, inquiry);
	}
	
	@Override
	public InquiryAnswer getInquiryAnswer(HttpServletRequest req) {
		InquiryAnswer inquiryAnswer = new InquiryAnswer();
		inquiryAnswer.setInquiry_no(Integer.parseInt(req.getParameter("inquiry_no")));
		
		return inquiryDao.selectInquiryAnswer(conn, inquiryAnswer);
	}
	
	@Override
	public void deleteInquiry(HttpServletRequest req) {
		Inquiry inquiry = new Inquiry();
		inquiry.setInquiry_no(Integer.parseInt(req.getParameter("inquiry_no")));
		
		inquiryDao.deleteInquiryAnswer(conn, inquiry);
		inquiryDao.deleteInquiry(conn, inquiry);
	}
}
