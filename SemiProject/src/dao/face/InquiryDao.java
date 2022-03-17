package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.Inquiry;
import dto.InquiryAnswer;
import dto.UserInfo;
import util.Paging;

public interface InquiryDao {
	
	public List<Inquiry> getInquiryList(Connection conn, Paging paging);
	
	public int inquiryCntAll(Connection conn);
	
	public void insertInquiry(Connection conn, Inquiry inquiry);
	
	public Inquiry selectInquiry(Connection conn, Inquiry inquiry);
	
	public InquiryAnswer selectInquiryAnswer(Connection conn, InquiryAnswer inquiryAnswer);
	
	public void deleteInquiry(Connection conn, Inquiry inquiry);
	
	public void deleteInquiryAnswer(Connection conn, Inquiry inquiry);
	
	public UserInfo selectUserNoByUserId(Connection conn, UserInfo userInfo);
}


