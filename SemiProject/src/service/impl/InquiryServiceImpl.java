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
		
		//조회된 페이지의 문의글 리스트 반환
		return inquiryDao.getInquiryList(conn, paging);
	}
	
	@Override
	public Paging getPaging(HttpServletRequest req) {
		
		//전달파라미터 curPage 추출하기
		String param = req.getParameter("curPage");
		int curPage = 0;
		if( param != null && !"".equals( param ) ) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARN] BoardService getPaging() - curPage값이 null이거나 비어있음");
		}
		
		//총 게시글 수 조회하기
		int totalCount = inquiryDao.inquiryCntAll(conn);
		
		//Paging 객체 생성 - 페이징 계산
		Paging paging = new Paging(totalCount, curPage);
		
		return paging;
		
	}
	
	@Override
	public void insertInquiry(HttpServletRequest req) {
		
		//세션 정보 가져오기
		HttpSession session = req.getSession();
		
		//회원 DTO 생성
		UserInfo userInfo = new UserInfo();
		
		//세션의 아이디를 DTO에 입력
		userInfo.setId((String)session.getAttribute("userid"));
		
		//아이디를 통하여 회원번호 조회 후 DTO에 입력
		userInfo = inquiryDao.selectUserNoByUserId(conn, userInfo);
		
		//문의글 DTO 생성
		Inquiry inquiry = new Inquiry();
		
		//문의글 DTO에 요청 정보 입력
		inquiry.setTitle(req.getParameter("title"));
		inquiry.setPassword(Integer.parseInt(req.getParameter("password")));
		inquiry.setContent(req.getParameter("content"));
		
		//문의글 DTO에 회원 번호 입력
		inquiry.setUser_no(userInfo.getUserNo());
		
		//문의글 삽입, 문의글 DTO전달
		inquiryDao.insertInquiry(conn, inquiry);
		
	}
	
	@Override
	public Inquiry getInquiry(HttpServletRequest req) {
		
		//문의글 DTO 생성
		Inquiry inquiry = new Inquiry();
		
		//요청 정보를 이용하여 문의글 번호 DTO에 입력
		inquiry.setInquiry_no(Integer.parseInt(req.getParameter("inquiry_no")));
		
		//해당 문의글 번호를 통하여 문의글 조회 후 반환
		return inquiryDao.selectInquiry(conn, inquiry);
	}
	
	@Override
	public InquiryAnswer getInquiryAnswer(HttpServletRequest req) {
		
		//문의글 답변 DTO생성
		InquiryAnswer inquiryAnswer = new InquiryAnswer();
		
		//문의글 답변 DTO에 문의글 번호 입력
		inquiryAnswer.setInquiry_no(Integer.parseInt(req.getParameter("inquiry_no")));
		
		//문의글 번호를 통한 문의글 답변 조회 후 반환
		return inquiryDao.selectInquiryAnswer(conn, inquiryAnswer);
	}
	
	@Override
	public void deleteInquiry(HttpServletRequest req) {
		
		//문의글 DTO 생성
		Inquiry inquiry = new Inquiry();
		
		//요청 정보를 이용하여 문의글 번호 DTO에 입력
		inquiry.setInquiry_no(Integer.parseInt(req.getParameter("inquiry_no")));
		
		//문의글 번호를 이용한 문의글 답변 삭제
		inquiryDao.deleteInquiryAnswer(conn, inquiry);
		
		//문의글 번호를 이용한 문의글 삭제
		inquiryDao.deleteInquiry(conn, inquiry);
	}
}
