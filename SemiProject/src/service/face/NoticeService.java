package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Notice;
import util.Paging;

public interface NoticeService {
	
	/**
	 * 페이지에 해당하는 문의글 리스트 가져오기 
	 * @param paging 페이징 정보 객체
	 * @return 공지사항 리스트 반환
	 */
	public List<Notice> getNoticeList(Paging paging);
	
	/**
	 * 요청 정보를 이용하여 문의글 DTO가져오기
	 * @param req 요청 정보 객체
	 * @return 공지사항 DTO
	 */
	public Notice getNoticeDetail(HttpServletRequest req);
	
	/**
	 * 공지사항의 총 페이지 수 조회
	 * @param req 요청 정보 객체
	 * @return 페이징 계산이 완료된 Paging객체
	 */
	public Paging getPaging(HttpServletRequest req);
	
	/**
	 * 전체 문의글 개수 가져오기
	 * @return 전체 문의글 개수 
	 */
	public int cntList();
	
}
