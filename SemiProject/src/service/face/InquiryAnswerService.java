package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.ManagerInquiryAnswer;
import dto.ManagerInquiryAnswer;
import util.Paging;

public interface InquiryAnswerService {
	
	public List<ManagerInquiryAnswer> getList();

	public List<ManagerInquiryAnswer> getList(Paging paging);
	
	public Paging getPaging(HttpServletRequest req);
	
	public ManagerInquiryAnswer getInquiryNo(HttpServletRequest req);
	
	public ManagerInquiryAnswer view(String inquiryNo);
	
	public void write(HttpServletRequest req);

	public void delete(ManagerInquiryAnswer inquiryAnswer);

	public void update(HttpServletRequest req);

	public List<ManagerInquiryAnswer> getList(Paging paging, String search);

}
