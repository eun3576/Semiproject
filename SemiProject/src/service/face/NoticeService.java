package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Notice;
import util.Paging;

public interface NoticeService {
	public List<Notice> getNoticeList(Paging paging);
	
	public Notice getNoticeDetail(HttpServletRequest req);
	
	public Paging getPaging(HttpServletRequest req);
	
	public int cntList();
	
}
