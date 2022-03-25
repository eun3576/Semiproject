package service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.ArrayList;
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
import dao.face.ManagerDao;
import dao.face.ReviewDao;
import dao.impl.ManagerDaoImpl;
import dao.impl.ReviewDaoImpl;
import dto.Attachment;
import dto.Manager;
import dto.Product;
import dto.ProductCategory;
import dto.Review;
import dto.UserInfo;
import service.face.ManagerService;

public class ManagerServiceImpl implements ManagerService {

	private ManagerDao managerDao = new ManagerDaoImpl(); //DAO를 사용하기 위한 객체 선언
	public Connection conn = JDBCTemplate.getConnection();
	
	@Override
	public Manager getLoginManager(HttpServletRequest req) {

		Manager manager = new Manager();
		
		manager.setId( req.getParameter("id") );
		manager.setPassword( req.getParameter("password") );
		return manager;
	}

	@Override
	public boolean login(Manager manager) {

		int cnt = managerDao.selectCntManagerByIdPassword(JDBCTemplate.getConnection(), manager);
		//로그인 인증 성공
		if( cnt > 0) {
			return true;
		}
		//로그인 인증 실패
		return false;
	}
	
	@Override
	public Manager info(Manager manager) {
		return managerDao.selectManagerById(JDBCTemplate.getConnection(), manager);
	}

	@Override
	public List<UserInfo> selectUserList() {
		
		return managerDao.selectUserList(conn);
	}

	@Override
	public void userDelete(HttpServletRequest req) {
		
		UserInfo userInfo = new UserInfo();	

		userInfo.setUserNo(Integer.parseInt(req.getParameter("user_no")));
		
		managerDao.userDelete(conn, userInfo);
		
	}

	@Override
	public List<UserInfo> searchUserList(String req) {
	
		return managerDao.selectUserSearchList(JDBCTemplate.getConnection(), req);

	}

	@Override
	public void reviewDelete(HttpServletRequest req) {
		
		Review review = new Review();
		ReviewDao reviewDao = new ReviewDaoImpl();
		ManagerDao mangerDao = new ManagerDaoImpl();
		
		review.setReview_no(Integer.parseInt(req.getParameter("review_no")));
		
		int count = reviewDao.deleteCommentAllByReview(conn, review);
		int count2 = managerDao.reviewImgDelete(conn, review);
		
		//if(count > 0) {
			managerDao.reviewDelete(conn, review);	
		//}
		
	}
	
	@Override
	public void insertProduct(HttpServletRequest req) {
		
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		
		if( !isMultipart ) {
			return;
		}
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		int maxMem = 1 * 1024 * 1024; // 1MB == 1048576B
		factory.setSizeThreshold(maxMem);
		
		//서블릿컨텍스트 객체
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

		

		//게시글 정보 DTO객체
		Product product = new Product();
		
		//카테고리 정보 DTO
		List<ProductCategory> categoryList = new ArrayList<>();
		
		//첨부파일 정보 DTO객체
		List<Attachment> attachmentList = new ArrayList<>();
		
		//파일아이템 반복자
		Iterator<FileItem> iter = items.iterator();
		
		while( iter.hasNext() ) {
			FileItem item = iter.next();
			
			//--- 1) 빈 파일에 대한 처리 ---
			if( item.getSize() <= 0 ) {
				
				//빈 파일은 무시하고 다음 FileItem처리로 넘어간다
				continue;
			}
			
			
			//--- 2) 폼 필드에 대한 처리 ---
			if( item.isFormField() ) {
				
				//키 추출하기
				String key = item.getFieldName();
				
				//값 추출하기
				String value = null;
				try {
					 value = item.getString("UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				
				//key에 맞게 value를 DTO에 삽입
				if("product_name".equals(key) ) {
					product.setProduct_name(value);
					System.out.println(product.getProduct_name());
					
				} else if ("content".equals(key) ) {
					product.setProduct_content(value);
					
				} 
				if("child".equals(value)) {
					ProductCategory category = new ProductCategory();
					category.setCategory_name(value);
					categoryList.add(category);
				} else if("woman".equals(value)) {
					ProductCategory category = new ProductCategory();
					category.setCategory_name(value);
					categoryList.add(category);
				} else if("man".equals(value)) {
					ProductCategory category = new ProductCategory();
					category.setCategory_name(value);
					categoryList.add(category);
				} else if("aged".equals(value)) {
					ProductCategory category = new ProductCategory();
					category.setCategory_name(value);
					categoryList.add(category);
				} else if("eye".equals(value)) {
					ProductCategory category = new ProductCategory();
					category.setCategory_name(value);
					categoryList.add(category);
				} else if("intestine".equals(value)) {
					ProductCategory category = new ProductCategory();
					category.setCategory_name(value);
					categoryList.add(category);
				} else if("vitamin".equals(value)) {
					ProductCategory category = new ProductCategory();
					category.setCategory_name(value);
					categoryList.add(category);
				} else if("exercise".equals(value)) {
					ProductCategory category = new ProductCategory();
					category.setCategory_name(value);
					categoryList.add(category);
				}
				
				
			} //if( item.isFormField() ) end
			
			//--- 3) 파일에 대한 처리 ---
			if( !item.isFormField() ) {
				
				//UUID생성
				String uid = UUID.randomUUID().toString().split("-")[0]; //8자리 UUID
				
				//파일 업로드 폴더
				File uploadFolder = new File( context.getRealPath("upload") );
				uploadFolder.mkdir();

				//파일명 처리
				String origin = item.getName();
				String stored = uid;
				
				//업로드할 파일 객체 생성하기
				File up = new File(uploadFolder, stored);
				
				try {
					item.write(up); // 임시파일 -> 실제 업로드 파일
					item.delete(); // 임시파일 제거
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				Attachment attachment = new Attachment();
				//업로드된 파일의 정보를 DTO객체에 저장하기
				attachment.setOrigin_img(origin);
				attachment.setStored_img(stored);
				attachment.setFilesize((int)item.getSize());
				attachmentList.add(attachment);
				
			} //if( !item.isFormField() ) end
			
		} //while( iter.hasNext() ) end
		
		
		
		//DB연결 객체
		Connection conn = JDBCTemplate.getConnection();
		
		
		if(product.getProduct_name()==null || "".equals(product.getProduct_name())) {
			product.setProduct_name("(제목없음)");
		}
		
		if(product.getProduct_content()==null || "".equals(product.getProduct_content())) {
			product.setProduct_content("내용없음");
		}
		
		product.setProduct_no(managerDao.selectProductNo(conn).getProduct_no());
		if(attachmentList.size() > 0) {
		product.setProduct_img(attachmentList.get(attachmentList.size()-1).getStored_img());
		}
		int res = managerDao.insertProduct(conn, product);
		if(res>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		for(int i=0; i<categoryList.size(); i++) {
			categoryList.get(i).setProduct_no(product.getProduct_no());
			System.out.println(categoryList.get(i).getProduct_no());
			int cres = managerDao.insertProductCategory(conn, categoryList.get(i));
			if(cres>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		}
		
		for(int i=0; i<attachmentList.size(); i++) {
			attachmentList.get(i).setProduct_no(product.getProduct_no());
			int ares = managerDao.insertAttachment(conn, attachmentList.get(i));
			if(ares>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		}
		
	}

	@Override
	public List<Product> selectProductList() {
		
		return managerDao.selectProductList(JDBCTemplate.getConnection());
	}

	@Override
	public List<Product> selectSearchProductList(String search) {
		
		return managerDao.selectSearchProductList(JDBCTemplate.getConnection(), search);
	}

	@Override
	public void productEdit(HttpServletRequest req) {
		
boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		
		if( !isMultipart ) {
			return;
		}
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		int maxMem = 1 * 1024 * 1024; // 1MB == 1048576B
		factory.setSizeThreshold(maxMem);
		
		//서블릿컨텍스트 객체
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

		

		//게시글 정보 DTO객체
		Product product = new Product();
		
		//카테고리 정보 DTO
		List<ProductCategory> categoryList = new ArrayList<>();
		
		//첨부파일 정보 DTO객체
		List<Attachment> attachmentList = new ArrayList<>();
		
		//파일아이템 반복자
		Iterator<FileItem> iter = items.iterator();
		
		while( iter.hasNext() ) {
			FileItem item = iter.next();
			
			//--- 1) 빈 파일에 대한 처리 ---
			if( item.getSize() <= 0 ) {
				
				//빈 파일은 무시하고 다음 FileItem처리로 넘어간다
				continue;
			}
			
			
			//--- 2) 폼 필드에 대한 처리 ---
			if( item.isFormField() ) {
				
				//키 추출하기
				String key = item.getFieldName();
				
				//값 추출하기
				String value = null;
				try {
					 value = item.getString("UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				
				//key에 맞게 value를 DTO에 삽입
				if("product_name".equals(key) ) {
					product.setProduct_name(value);
					System.out.println(product.getProduct_name());
					
				} else if ("content".equals(key) ) {
					product.setProduct_content(value);
					
				} else if ("product_no".equals(key)) {
					product.setProduct_no(Integer.parseInt(value));
				}
				
				System.out.println("메소드 수정: " + product.getProduct_no());
				System.out.println("메소드 수정: " + product.getProduct_name());
				
				if("child".equals(value)) {
					ProductCategory category = new ProductCategory();
					category.setCategory_name(value);
					categoryList.add(category);
				} else if("woman".equals(value)) {
					ProductCategory category = new ProductCategory();
					category.setCategory_name(value);
					categoryList.add(category);
				} else if("man".equals(value)) {
					ProductCategory category = new ProductCategory();
					category.setCategory_name(value);
					categoryList.add(category);
				} else if("aged".equals(value)) {
					ProductCategory category = new ProductCategory();
					category.setCategory_name(value);
					categoryList.add(category);
				} else if("eye".equals(value)) {
					ProductCategory category = new ProductCategory();
					category.setCategory_name(value);
					categoryList.add(category);
				} else if("intestine".equals(value)) {
					ProductCategory category = new ProductCategory();
					category.setCategory_name(value);
					categoryList.add(category);
				} else if("vitamin".equals(value)) {
					ProductCategory category = new ProductCategory();
					category.setCategory_name(value);
					categoryList.add(category);
				} else if("exercise".equals(value)) {
					ProductCategory category = new ProductCategory();
					category.setCategory_name(value);
					categoryList.add(category);
				}
				
				
			} //if( item.isFormField() ) end
			
			//--- 3) 파일에 대한 처리 ---
			if( !item.isFormField() ) {
				
				//UUID생성
				String uid = UUID.randomUUID().toString().split("-")[0]; //8자리 UUID
				
				//파일 업로드 폴더
				File uploadFolder = new File( context.getRealPath("upload") );
				uploadFolder.mkdir();

				//파일명 처리
				String origin = item.getName();
				String stored = uid;
				
				//업로드할 파일 객체 생성하기
				File up = new File(uploadFolder, stored);
				
				try {
					item.write(up); // 임시파일 -> 실제 업로드 파일
					item.delete(); // 임시파일 제거
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				Attachment attachment = new Attachment();
				//업로드된 파일의 정보를 DTO객체에 저장하기
				attachment.setOrigin_img(origin);
				attachment.setStored_img(stored);
				attachment.setFilesize((int)item.getSize());
				attachmentList.add(attachment);
				
			} //if( !item.isFormField() ) end
			
		} //while( iter.hasNext() ) end
		
		if (categoryList.size() > 0) {
			
			if (managerDao.productCategoryDelete(conn, product) > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
				
			for(int i=0; i<categoryList.size(); i++) {
				categoryList.get(i).setProduct_no(product.getProduct_no());
				System.out.println(categoryList.get(i).getProduct_no());
				int cres = managerDao.insertProductCategory(conn, categoryList.get(i));
				if(cres>0) {
					JDBCTemplate.commit(conn);
				}else {
					JDBCTemplate.rollback(conn);
				}
			}
		} 
		
		
//		if (attachmentList.size() > 0) {
//			//삭제
//				
//		}
		
		if(attachmentList.size() > 0) {
			
			//삭제
			if (managerDao.productImgDelete(conn, product) > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
			
			product.setProduct_img(attachmentList.get(attachmentList.size()-1).getStored_img());
			
			if (managerDao.productUpdateImg(conn, product) > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
			
			for(int i=0; i<attachmentList.size(); i++) {
				attachmentList.get(i).setProduct_no(product.getProduct_no());
				int ares = managerDao.insertAttachment(conn, attachmentList.get(i));
				if(ares>0) {
					JDBCTemplate.commit(conn);
				}else {
					JDBCTemplate.rollback(conn);
				}
			}
		}
		
		managerDao.productEdit(JDBCTemplate.getConnection(), product);
	}

	@Override
	public void productDelete(HttpServletRequest req) {

		Product product = new Product();
		ManagerDao managerDao = new ManagerDaoImpl();
		
		product.setProduct_no(Integer.parseInt(req.getParameter("product_no")));
		
		int count = managerDao.productCategoryDelete(conn, product);
		int count2 = managerDao.productImgDelete(conn, product);
		
		
		//if(count > 0 && count2 > 0) { // ( 카테고리 o, 이미지 o)
			managerDao.productDelete(conn, product);
		//}
		
		
	}
	
}
