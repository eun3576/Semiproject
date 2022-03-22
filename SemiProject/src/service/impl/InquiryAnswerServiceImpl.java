package service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.JDBCTemplate;
import dao.face.InquiryAnswerDao;
import dao.impl.InquiryAnswerDaoImpl;
import dto.ManagerInquiryAnswer;
import dto.ManagerReviewComment;
import dto.ManagerInquiryAnswer;
import service.face.InquiryAnswerService;
import util.Paging;

public class InquiryAnswerServiceImpl implements InquiryAnswerService {
	private Connection conn;
	private InquiryAnswerDao inquiryAnswerDao = new InquiryAnswerDaoImpl();
	
	@Override
	public List<ManagerInquiryAnswer> getList() {

		return inquiryAnswerDao.selectAll( JDBCTemplate.getConnection() );
	}
	
	@Override
	public List<ManagerInquiryAnswer> getList(Paging paging) {
		
		return inquiryAnswerDao.selectAll( JDBCTemplate.getConnection(), paging );
	}

	@Override
	public Paging getPaging(HttpServletRequest req) {

		String param = req.getParameter("curPage");
		int curPage = 0;
		if( param != null && !"".equals( param ) ) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARN] curPage값이 null이거나 비어있음");
		}
				
		int totalCount = inquiryAnswerDao.selectCntAll(JDBCTemplate.getConnection());
	
		Paging paging = new Paging(totalCount, curPage);
				
		return paging;
	}
	
	
	@Override
	public ManagerInquiryAnswer getInquiryNo(HttpServletRequest req) {

		//전달 파라미터 inquiryNo를 저장할 DTO객체 생성
		ManagerInquiryAnswer inquiryNo = new ManagerInquiryAnswer();
		
		String param = req.getParameter("inquiryNo");	
		if( param != null && !"".equals ( param ) ) {
			inquiryNo.setInquiryNo( Integer.parseInt(param) ); //안전하게 INT형으로 변환
		} else {
			System.out.println("[WARN] inquiryNo값이 null이거나 비어있음");
		}
		
		return inquiryNo;
	} 

		
		@Override
		public ManagerInquiryAnswer view(String inquiryNo) {
			conn = JDBCTemplate.getConnection();
			ManagerInquiryAnswer inquiryAnswer = inquiryAnswerDao.selectInquiryAnswerByInquiryNo(conn, inquiryNo);
			
			return inquiryAnswer;
		}

		//문의답변글 처리하기
		
		@Override
		public void write(HttpServletRequest req) {
			
			ManagerInquiryAnswer inquiryAnswer = new ManagerInquiryAnswer();
			HttpSession session = req.getSession();
			inquiryAnswer.setContent( req.getParameter("content") );
			
			//작성자 관리자번호 처리
			inquiryAnswer.setManagerNo( (int) req.getSession().getAttribute("managerNo") );
			
			Connection conn = JDBCTemplate.getConnection();
			if( inquiryAnswerDao.insert(conn, inquiryAnswer) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
			
		}
		//삭제
		@Override
		public void delete(ManagerInquiryAnswer inquiryAnswer) {
			Connection conn = JDBCTemplate.getConnection();
			
			if( inquiryAnswerDao.delete(conn, inquiryAnswer) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
			
		}			

		//수정
		@Override
		public void update(HttpServletRequest req) {
			
			ManagerInquiryAnswer inquiryAnswer = new ManagerInquiryAnswer();
		
				//DB연결 객체
				Connection conn = JDBCTemplate.getConnection();
				
				inquiryAnswer.setInquiryNo(Integer.parseInt(req.getParameter("inquiryNo")));
				inquiryAnswer.setContent(req.getParameter("content"));

				
				//게시글 정보 삽입
				
				if( inquiryAnswerDao.update(conn, inquiryAnswer) > 0 ) {
					JDBCTemplate.commit(conn);
				} else {
					JDBCTemplate.rollback(conn);
				}
		
		}
		
		//검색창 만들기
		@Override
		public List<ManagerInquiryAnswer> getList(Paging paging, String search) {
			return inquiryAnswerDao.selectAll( JDBCTemplate.getConnection(), paging, search );
		}

		
}

		
