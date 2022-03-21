package service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import common.JDBCTemplate;
import dao.face.ReviewDao;
import dao.impl.ReviewDaoImpl;
import dto.Attachment;
import dto.Review;
import dto.ReviewComment;
import dto.UserInfo;
import service.face.ReviewService;

public class ReviewServiceImpl implements ReviewService{

	private ReviewDao reviewDao = new ReviewDaoImpl();
	
	@Override
	public List<Review> getList() {
		
		//게시글 전체 조회 결과 반환
		return reviewDao.selectReviewAll(JDBCTemplate.getConnection());
	}

	@Override
	public List<UserInfo> getNickSympList() {
		
		//게시글을 작성한 작성자 닉네임 전체 조회 결과 반환
		return reviewDao.selectNickSympAll(JDBCTemplate.getConnection());
	}
	
	
	@Override
	public Review getReviewno(HttpServletRequest req) {
		
		//전달 파라미터 review_no를 저장할 DTO 객체 생성
		Review reviewno = new Review();
		
		//주소창을 통하여 파라미터 가져오기?
		String param = req.getParameter("review_no");
		
		if (param != null && !"".equals(param)) {
			reviewno.setReview_no(Integer.parseInt(param));
		} else {
			System.out.println("[WARN] ReviewService getReviewno() - reviewno값이 비어있거나 null 이다!!");
		}
		
		return reviewno;
	}
	
	
	@Override
	public UserInfo getNickSymptonByReviewno(Review reviewno) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		//전달된 review_no에 따른 유저 정보 유저 객체에 저장 
		UserInfo userInfo = reviewDao.selectNickSympByReviewno(conn, reviewno);
		
		return userInfo;
	}
	
	
	@Override
	public Review view(Review reviewno) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		//조회수 증가
		if (reviewDao.updateView(conn, reviewno) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		//게시글 조회
		Review review = reviewDao.selectReviewByReviewno(conn, reviewno);
		
		return review;
	}

	
	@Override
	public void write(HttpServletRequest req) {

		//--- 첨부 파일 없이 게시글 처리 하기 --- 
//		Review review = new Review();
//		
//		review.setTitle(req.getParameter("reviewTitle"));
//		review.setContent(req.getParameter("reviewContent"));
//		
////		review.setUser_no( (String) req.getSession().getAttribute("유저 정보"));
//		
//		//작성자가 작성글 제목에 아무것도 입력하지 않았을 때 제목없음 추가 된다.
//		if (review.getTitle() == null || "".equals(review.getTitle())) {
//			review.setTitle("(제목없음)");
//		}
//		
//		Connection conn = JDBCTemplate.getConnection();
//		
//		if (reviewDao.insert(conn, review) > 0) {
//			
//			//insert를 통한 쿼리문이 정상적으로 실행되면 commit
//			JDBCTemplate.commit(conn);
//		} else {
//			//insert를 통한 쿼리문이 에러가 나면 rollback
//			JDBCTemplate.rollback(conn);
//		}
		
		//------------------------------------------------------------------------
		
		//첨부파일 + 게시글 삽입 처리 하기 
		
		//파일 업로드 형식 인코딩 검사
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		
		//multipart/form-data 형식이 아닐 경우 파일 업로드 처리 중단.
		if (!isMultipart) {
			System.out.println("[ERROR] 파일 업로드 형식 데이터가 아님!");
			return;
		}
		
		
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//메모리에서 처리 사이즈 설정
		int maxMem = 1 * 1024 * 1024; //1MB == 1048576B
		factory.setSizeThreshold(maxMem);
		
		//서블릿 컨텍스트 객체
		ServletContext context = req.getServletContext();
		
		//임시파일 폴더
		String path = context.getRealPath("tmp");
		File tmpRepository = new File(path);
		tmpRepository.mkdir();
		factory.setRepository(tmpRepository);
		
		//파일업로드 수행 객체 
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//파일 업로드 용량 제한 사이즈 설정
		int maxFile = 10 * 1024 * 1024; //10MB
		upload.setFileSizeMax(maxFile);
		
		
		
		//파일 업로드된 데이터 파싱
		List<FileItem> items = null;
		
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		
		
		//후기글 정보 DTO 객체  
		Review review = new Review();
		
		//첨부파일 정보 DTO 객체
		Attachment attach = new Attachment();
		
		
		//파일 아이템 반복자
		Iterator<FileItem> iter = items.iterator();
		
		while (iter.hasNext()) {
			FileItem item = iter.next();
			
			//--- 1. 빈 파일에 대한 처리 ---
			if (item.getSize() <= 0) {
				
				//빈 파일은 무시하고 다음 FileItem처리로 넘어간다.
				continue;
			}
			
			//--- 2. form field에 대한 처리 ---
			if (item.isFormField() ) {
				
				//키 추출 하기 
				String key = item.getFieldName();
				
				//값 추출 하기
				String value = null;
				try {
					value = item.getString("UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				
				//key맞게 value를 DTO 삽입
				if ("reviewTitle".equals(key)) {
					review.setTitle(value);
					
				} else if ("reviewContent".equals(key)) {
					review.setContent(value);
				}
				
			}//if (item.isFormField()) end
			
			
			//--- 3. 파일에 대한 처리 ---
			if (!item.isFormField()) {
				
				//UUID생성
				String uid = UUID.randomUUID().toString().split("-")[0]; //8자리 UUID
				
				//파일 업로드 폴더
				File uploadFolder = new File(context.getRealPath("upload"));
				uploadFolder.mkdir();
				
				//파일명 처리
				String origin = item.getName();
				String stored = uid;
				
				//업로드할 파일 객체 생성하기 
				File up = new File(uploadFolder, stored);
				
				try {
					item.write(up); //임시파일 -> 실제 업로드 파일
					item.delete(); //임시파일 제거
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//업로드된 파일의 정보를 DTO객체에 저장
				attach.setOrigin_img(origin);
				attach.setStored_img(stored);
				attach.setFilesize((int)item.getSize());
				
			} //if(!item.isFormField() end
			
		} //while end
		
		//DB연결 객체
		Connection conn = JDBCTemplate.getConnection();
		
		//세션을 통하여 nick을 저장할 DTO객체 생성
		UserInfo userInfo = new UserInfo(); 
		
		userInfo.setNickname((String) req.getSession().getAttribute("usernick"));
		
		//닉네임을 이용하여 user_no 조회하여 DTO객체에 저장
		reviewDao.selectUsernoByNick(conn, userInfo);
		
		//구한 user_no를 review DTO객체에 저장
		review.setUser_no(userInfo.getUserNo());
		
		
		//게시글 번호 생성
		int reviewno = reviewDao.selectReivewno(conn);
		
		//게시글 정보 삽입
		review.setReview_no(reviewno);
		if (review.getTitle() == null || "".equals(review.getTitle())) {
			review.setTitle("(제목 없음)");
		}
		if (reviewDao.insert(conn, review) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		//첨부파일 정보 삽입
		if (attach.getFilesize() != 0) {
			attach.setReview_no(reviewno);
			
			if (reviewDao.insertFile(conn, attach) > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
	}

	@Override
	public Attachment viewFile(Review review) {
		return reviewDao.selectFile(JDBCTemplate.getConnection(), review);
	}
	
	@Override
	public void update(HttpServletRequest req) {
		
		//파일 업로드 형식 인코딩이 맞는지 검사 
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		
		//Multipart/form-data 형식이 아닐 경우 파일업로드 처리 중단
		if( !isMultipart ) {
			System.out.println("[ERROR] 파일 업로드 형식 데이터가 아님");
			return;
		}
		
		
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//메모리에서 처리 사이즈 설정
		int maxMem = 1 * 1024 * 1024; //1MB == 1048576B
		factory.setSizeThreshold(maxMem);
		
		//서블릿 컨텍스트 객체
		ServletContext context = req.getServletContext();
		
		//임시파일 폴더
		String path = context.getRealPath("tmp");
		File tmpRepository = new File(path);
		tmpRepository.mkdir();
		factory.setRepository(tmpRepository);
		
		//파일업로드 수행 객체 
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//파일 업로드 용량 제한 사이즈 설정
		int maxFile = 10 * 1024 * 1024; //10MB
		upload.setFileSizeMax(maxFile);
		
		
		
		//파일 업로드된 데이터 파싱
		List<FileItem> items = null;
		
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		
		
		//후기글 정보 DTO 객체  
		Review review = new Review();
		
		//첨부파일 정보 DTO 객체
		Attachment attach = new Attachment();
		
		
		//파일 아이템 반복자
		Iterator<FileItem> iter = items.iterator();
		
		while (iter.hasNext()) {
			FileItem item = iter.next();
			
			//--- 1. 빈 파일에 대한 처리 ---
			if (item.getSize() <= 0) {
				
				//빈 파일은 무시하고 다음 FileItem처리로 넘어간다.
				continue;
			}
			
			//--- 2. form field에 대한 처리 ---
			if (item.isFormField() ) {
				
				//키 추출 하기 
				String key = item.getFieldName();
				
				//값 추출 하기
				String value = null;
				try {
					value = item.getString("UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				
				//key맞게 value를 DTO 삽입
				if ("reviewNo".equals(key)) {
					review.setReview_no(Integer.parseInt(value));
					
				} else if ("reviewTitle".equals(key)) {
					review.setTitle(value);
					
				} else if ("reviewContent".equals(key)) {
					review.setContent(value);
				}
				
			}//if (item.isFormField()) end
			
			
			//--- 3. 파일에 대한 처리 ---
			if (!item.isFormField()) {
				
				//UUID생성
				String uid = UUID.randomUUID().toString().split("-")[0]; //8자리 UUID
				
				//파일 업로드 폴더
				File uploadFolder = new File(context.getRealPath("upload"));
				uploadFolder.mkdir();
				
				//파일명 처리
				String origin = item.getName();
				String stored = uid;
				
				//업로드할 파일 객체 생성하기 
				File up = new File(uploadFolder, stored);
				
				try {
					item.write(up); //임시파일 -> 실제 업로드 파일
					item.delete(); //임시파일 제거
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//업로드된 파일의 정보를 DTO객체에 저장
				attach.setOrigin_img(origin);
				attach.setStored_img(stored);
				attach.setFilesize((int)item.getSize());
				
			} //if(!item.isFormField() end
			
		} //while end
		
		//DB연결 객체
		Connection conn = JDBCTemplate.getConnection();
		
		//게시글 정보 삽입
		if (review.getTitle() == null || "".equals(review.getTitle())) {
			review.setTitle("(제목 없음)");
		}
		if (reviewDao.update(conn, review) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		//첨부파일 정보 삽입
		if (attach.getFilesize() != 0) {
			attach.setReview_no(review.getReview_no());
			
			if (reviewDao.insertFile(conn, attach) > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
	}

	@Override
	public void delete(Review review) {
		
		Connection conn = JDBCTemplate.getConnection();
	
		//테이블 구조가 댓글 테이블에 후기 테이블을 참조하기 때문에 
		//후기글 번호에 해당되는 댓글 테이블의 데이터를 먼저 삭제 해주어야 한다.
		if (reviewDao.deleteCommentAllByReview(conn, review) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		if (reviewDao.deleteFile(conn, review) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		if(reviewDao.delete(conn, review) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn); 
		}
		
	}

	@Override
	public List<ReviewComment> getCommentList(Review reviewno) {
		
		return reviewDao.selectReviewCommentByReviewno(JDBCTemplate.getConnection(), reviewno);
		
	}

	@Override
	public void writeComment(HttpServletRequest req) {
		
		Connection conn = JDBCTemplate.getConnection();
	
		ReviewComment reviewComment = new ReviewComment();
		
		//form데이터 에서 reviewNo, commentText input값 가져오기 
		reviewComment.setReview_no(Integer.parseInt(req.getParameter("reviewNo")));
		reviewComment.setComment_text(req.getParameter("commentText"));
		
		//세션 로그인 정보를 이용하여 user_no를 구한다.
		String sessionNick = (String) req.getSession().getAttribute("usernick");
		
		//userInfo 객체를 생성하여 세션닉네임을 저장해준다.
		UserInfo userInfo = new UserInfo();
		userInfo.setNickname(sessionNick);
		
		//세션 닉네임을 저장한 DTO객체를 이용해 user_no구하는 함수를 이용한다. 
		reviewDao.selectUsernoByNick(conn, userInfo);
		
		//userInfo에 저장 되어있는 user_no를 reviewComment DTO 에 저장
		reviewComment.setUser_no(userInfo.getUserNo());
		
		//reviewComment객체에 들어갔는지 확인 test
		//System.out.println("reviewComment user_no + " + reviewComment);
		
		
		if (reviewComment.getComment_text() == null || "".equals(reviewComment.getComment_text())) {
			reviewComment.setComment_text("(내용없음)");
		}
		
		if (reviewDao.insertComment(conn, reviewComment) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
	}

	@Override
	public void deleteCommentByCommentno(ReviewComment reviewComment) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		if (reviewDao.deleteComment(conn, reviewComment) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
	}

	@Override
	public List<Attachment> getAttachList() {
		
		return reviewDao.selectStoredImg(JDBCTemplate.getConnection());
	}

}
