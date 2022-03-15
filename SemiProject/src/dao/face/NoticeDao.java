package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.Notice;
import util.Paging;

public interface NoticeDao {
	
	public List<Notice> getNoticeList(Connection conn, Paging paging);
	
	public int selectCntAll(Connection conn);
	
	public Notice getNoticeList(Connection conn, Notice notice);
	
}
