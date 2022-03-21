package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.ManagerNotice;
import util.Paging;

public interface ManagerNoticeDao {
	
	public List<ManagerNotice> selectAll(Connection conn); 
	
	public List<ManagerNotice> selectAll(Connection conn, Paging paging); 

	public int selectCntAll(Connection conn);

	public ManagerNotice selectNoticeByNoticeNo(Connection conn, String noticeNo);
	
	//삽입
	public int insert(Connection conn, ManagerNotice notice);

	//삭제
	public int delete(Connection conn, ManagerNotice notice);
	
	//수정
	public int update(Connection conn, ManagerNotice notice);

	//검색창
	public List<ManagerNotice> selectAll(Connection conn, Paging paging, String search);
}
