package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.ManagerNotice;
import util.Paging;

public interface ManagerNoticeService {

	public List<ManagerNotice> getList(); 
	
	public List<ManagerNotice> getList(Paging paging); 
	
	public Paging getPaging(HttpServletRequest req);
	
	public ManagerNotice getNoticeNo(HttpServletRequest req);

	public ManagerNotice view(String noticeNo);

	public void write(HttpServletRequest req);

	public void delete(ManagerNotice notice);
	
	public void update(HttpServletRequest req);

	public List<ManagerNotice> getList(Paging paging, String search);




}
