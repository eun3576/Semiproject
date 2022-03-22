package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.Attachment;
import dto.Notice;
import util.Paging;

public interface NoticeDao {
	
	/**
	 * 페이지에 해당하는 공지사항 리스트를 가져온다
	 * @param conn DB연결 객체
	 * @param paging 페이징 정보 객체
	 * @return 페이지에 해당하는 공지사항 목록
	 */
	public List<Notice> getNoticeList(Connection conn, Paging paging);
	
	/**
	 * 전체 공지사항의 수를 가져온다
	 * @param conn DB연결 객체
	 * @return 전체 공지사항 수
	 */
	public int selectCntAll(Connection conn);
	
	/**
	 * 공지사항 DTO를 가져온다
	 * @param conn DB연결 객체
	 * @param notice 선택한 공지사항을 식별할 수 있는 정보
	 * @return 해당 공지사항의 DTO 객체
	 */
	public Notice getNoticeList(Connection conn, Notice notice);
	
	/**
	 * 공지사항 첨부파일 리스트를가져온다
	 * @param conn DB연결 객체
	 * @param notice 선택한 공지사항을 식별할 수 있는 정보
	 * @return 해당 공지사항의 첨부파일 리스트
	 */
	public List<Attachment> getAttachmentList(Connection conn, Notice notice);
	
}
