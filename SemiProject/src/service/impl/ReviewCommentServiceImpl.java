package service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.ReviewCommentDao;
import dao.impl.ReviewCommentDaoImpl;
import dto.ManagerReviewComment;
import service.face.ReviewCommentService;
import util.Paging;

public class ReviewCommentServiceImpl implements ReviewCommentService {

	private ReviewCommentDao reviewCommentDao = new ReviewCommentDaoImpl();
	
	@Override
	public List<ManagerReviewComment> getList() {
		
		return reviewCommentDao.selectAll( JDBCTemplate.getConnection() );
	}

	@Override
	public List<ManagerReviewComment> getList(Paging paging) {
				return reviewCommentDao.selectAll( JDBCTemplate.getConnection(), paging );
	}

	@Override
	public Paging getPaging(HttpServletRequest req) {

				String param = req.getParameter("curPage");
				int curPage = 0;
				if( param != null && !"".equals( param ) ) {
					curPage = Integer.parseInt(param);
				} else {
					System.out.println("[WARN] ReviewCommentService getPaging() - curPage값이 null이거나 비어있음");
				}
				
				int totalCount = reviewCommentDao.selectCntAll(JDBCTemplate.getConnection());
				
				Paging paging = new Paging(totalCount, curPage);
				
				return paging;
	}

	@Override
	public void delete(ManagerReviewComment reviewComment) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		if( reviewCommentDao.delete(conn, reviewComment) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
	}

	// 재렬
	@Override
	public List<ManagerReviewComment> getList(Paging paging, String search) {
		return reviewCommentDao.selectAll( JDBCTemplate.getConnection(), paging, search );
	}


}
