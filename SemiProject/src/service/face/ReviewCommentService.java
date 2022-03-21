package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.ManagerReviewComment;
import util.Paging;

public interface ReviewCommentService {

	/**
	 * 댓글 전체 조회
	 * 
	 * @return List<ReviewComment> - 댓글 전체 조회 목록
	 */
	public List<ManagerReviewComment> getList();
	
	/**
	 * 댓글 페이징 목록 조회
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return List<ReviewComment> - 페이징이 반영된 게시글 조회 결과 목록
	 */
	public List<ManagerReviewComment> getList(Paging paging);
	
	/**
	 * 페이징 객체 생성
	 * 
	 * @param req - 요청 정보 객체
	 * @return Paging - 페이징 계산이 완료된 Paging객체
	 */
	public Paging getPaging(HttpServletRequest req);
	
	/**
	 * 댓글 삭제
	 * 
	 * @param board - 삭제할 댓글 번호를 가진 객체
	 */
	public void delete(ManagerReviewComment reviewComment);

	public List<ManagerReviewComment> getList(Paging paging, String search);

}
