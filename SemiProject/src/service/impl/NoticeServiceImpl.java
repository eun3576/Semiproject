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
		Notice notice = new Notice();
		notice.setNotice_no(Integer.parseInt(req.getParameter("notice_no")));
		
		return noticeDao.getNoticeList(conn, notice);
	}
	
	@Override
	public int cntList() {
		int cntList = noticeDao.selectCntAll(conn);
		return cntList;
	}
}
