package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Inquiry;
import dto.InquiryAnswer;
import util.Paging;

public interface InquiryService {
	
	/**
	 * 요청된 페이지에 해당하는 문의글 조회
	 *  
	 * @param paging 페이징 정보 객체
	 * @return 페이지에 들어갈 문의글 리스트 반환
	 */
	public List<Inquiry> getInquiryList(Paging paging);
	
	/**
	 *  문의글의 총 페이지 수 조회
	 * @param req 요청 정보 객체
	 * @return 페이징 계산이 완료된 Paging객체
	 */
	public Paging getPaging(HttpServletRequest req);
	
	/**
	 *  문의글 작성 
	 * @param req 요청 정보 객체(제목, 비밀번호, 내용)
	 */
	public void insertInquiry(HttpServletRequest req);
	
	/**
	 * 문의글 번호를 통한 조회 
	 * @param req 요청 정보 객체(문의글 번호)
	 * @return 해당 번호의 문의글 반환
	 */
	public Inquiry getInquiry(HttpServletRequest req);
	
	/**
	 * 문의글 번호를 통한 해당 문의글의 답변 조회
	 * @param req 요청 정보 객체(문의글 번호)
	 * @return 해당 번호의 문의글 답변 반환
	 */
	public InquiryAnswer getInquiryAnswer(HttpServletRequest req);
	
	/**
	 * 문의글 번호를 통하여 해당 문의글, 문의글 답변 삭제
	 * @param req 요청 정보 객체(문의글 번호)
	 */
	public void deleteInquiry(HttpServletRequest req);
}
