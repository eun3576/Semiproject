package service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.NoticeDao;
import dao.impl.NoticeDaoImpl;
import dto.Notice;
import service.face.NoticeService;
import util.Paging;

public class NoticeServiceImpl implements NoticeService{
	Connection conn = JDBCTemplate.getConnection();
	NoticeDao noticeDao = new NoticeDaoImpl();
	
	@Override
	public List<Notice> getNoticeList(Paging paging) {
		
		//문의글 리스트 가져오기 후 반환
		return noticeDao.getNoticeList(conn, paging);
		
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
		
		//총 게시글 수 조회하기
		int totalCount = noticeDao.selectCntAll(conn);
		
		//Paging 객체 생성 - 페이징 계산
		Paging paging = new Paging(totalCount, curPage);
		
		return paging;
	}
	
	@Override
	public Notice getNoticeDetail(HttpServletRequest req) {
		
		//문의글 DTO 생성
		Notice notice = new Notice();
		
		//문의글 DTO에 문의글 번호 입력
		notice.setNotice_no(Integer.parseInt(req.getParameter("notice_no")));
		
		//문의글 DTO 가져오기 후 반환
		return noticeDao.getNoticeList(conn, notice);
	}
	
	@Override
	public int cntList() {
		
		//전체 문의글 개수 가져오기
		int cntList = noticeDao.selectCntAll(conn);
		
		//전체 문의글 개수 반환
		return cntList;
	}
}
