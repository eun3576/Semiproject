package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Inquiry;
import dto.InquiryAnswer;
import util.Paging;

public interface InquiryService {
	public List<Inquiry> getInquiryList(Paging paging);
	
	public Paging getPaging(HttpServletRequest req);
	
	public void insertInquiry(HttpServletRequest req);
	
	public Inquiry getInquiry(HttpServletRequest req);
	
	public InquiryAnswer getInquiryAnswer(HttpServletRequest req);
	
	public void deleteInquiry(HttpServletRequest req);
}
