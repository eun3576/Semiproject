package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.ManagerInquiryAnswer;
import dto.ManagerInquiryAnswer;
import util.Paging;

public interface InquiryAnswerDao {
	
	public List<ManagerInquiryAnswer> selectAll(Connection conn);
	
	public List<ManagerInquiryAnswer> selectAll(Connection conn, Paging paging);

	public int selectCntAll(Connection conn);

	public ManagerInquiryAnswer selectInquiryAnswerByInquiryNo(Connection conn, String inquiryNo);
	
	//삽입
	public int insert(Connection conn, ManagerInquiryAnswer inquiryAnswer);

	//삭제
	public int delete(Connection conn, ManagerInquiryAnswer inquiryAnswer);
	
	//수정
	public int update(Connection conn, ManagerInquiryAnswer inquiryAnswer);

	//검색창
	public List<ManagerInquiryAnswer> selectAll(Connection conn, Paging paging, String search);

}

	

