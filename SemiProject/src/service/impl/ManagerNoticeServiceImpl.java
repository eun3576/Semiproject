package service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.JDBCTemplate;
import dao.face.ManagerNoticeDao;
import dao.impl.ManagerNoticeDaoImpl;
import dto.ManagerNotice;
import dto.ManagerReviewComment;
import service.face.ManagerNoticeService;
import util.Paging;

public class ManagerNoticeServiceImpl implements ManagerNoticeService {
	private Connection conn;
	private ManagerNoticeDao noticeDao = new ManagerNoticeDaoImpl(); 

	@Override
	public List<ManagerNotice> getList() {

		return noticeDao.selectAll(JDBCTemplate.getConnection()); 
	}

	@Override
	public List<ManagerNotice> getList(Paging paging) {

		return noticeDao.selectAll(JDBCTemplate.getConnection(), paging);
	}

	@Override
	public Paging getPaging(HttpServletRequest req) {

		// 전달 파라미터 curPage 추출하기
		String param = req.getParameter("curPage");
		int curPage = 0;
		if (param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param); // 안전하게 INT형으로 변환
		} else {
			System.out.println("[WARN] curPage값이 null이거나 비어있음");
		}

		int totalCount = noticeDao.selectCntAll(JDBCTemplate.getConnection());

		// Paging 객체 생성 - 페이징 계산
		Paging paging = new Paging(totalCount, curPage);

		return paging;
	}

	@Override
	public ManagerNotice getNoticeNo(HttpServletRequest req) {

		// 전달 파라미터 noticeNo를 저장할 DTO객체 생성
		ManagerNotice noticeNo = new ManagerNotice();

		String param = req.getParameter("noticeNo");
		if (param != null && !"".equals(param)) {
			noticeNo.setNoticeNo(Integer.parseInt(param)); // 안전하게 INT형으로 변환
		} else {
			System.out.println("[WARN] noticeNo값이 null이거나 비어있음");
		}

		return noticeNo;
	}

	@Override
	public ManagerNotice view(String noticeNo) {
		conn = JDBCTemplate.getConnection();
		ManagerNotice notice = noticeDao.selectNoticeByNoticeNo(conn, noticeNo);

		return notice;
	}

	// 공지사항 글 처리하기

	@Override
	public void write(HttpServletRequest req) {
		
		ManagerNotice notice = new ManagerNotice();
		HttpSession session = req.getSession();
		notice.setTitle(req.getParameter("title"));
		notice.setContent(req.getParameter("content"));

		// 작성자 관리자번호 처리
		notice.setManagerNo((int) req.getSession().getAttribute("managerNo"));

		if (notice.getTitle() == null || "".equals(notice.getTitle())) {
			notice.setTitle("(제목없음)");
		}

		Connection conn = JDBCTemplate.getConnection();
		if (noticeDao.insert(conn, notice) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

	}

	// 삭제
	@Override
	public void delete(ManagerNotice notice) {
		Connection conn = JDBCTemplate.getConnection();

		if (noticeDao.delete(conn, notice) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

	}

	// 수정
	@Override
	public void update(HttpServletRequest req) {

		ManagerNotice notice = new ManagerNotice();

		// DB연결 객체
		Connection conn = JDBCTemplate.getConnection();

		notice.setNoticeNo(Integer.parseInt(req.getParameter("noticeno")));
		notice.setTitle(req.getParameter("title"));
		notice.setContent(req.getParameter("content"));

		if (notice.getTitle() == null || "".equals(notice.getTitle())) {
			notice.setTitle("(제목없음)");
		}
		if (noticeDao.update(conn, notice) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

	}

	@Override
	public List<ManagerNotice> getList(Paging paging, String search) {
		return noticeDao.selectAll( JDBCTemplate.getConnection(), paging, search );
	}
}