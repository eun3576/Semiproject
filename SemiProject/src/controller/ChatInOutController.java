package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Chat;
import service.face.ChatService;
import service.impl.ChatServiceImpl;


@WebServlet("/chat/inout")
public class ChatInOutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ChatService chatService = new ChatServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//인코딩 설정
		req.setCharacterEncoding("UTF-8");
		//채팅 내용을 저장
		Chat chat = chatService.getChatContent(req);
		
		PrintWriter out = resp.getWriter();
		
		boolean chatRes = chatService.insertChatContent(chat);
		
		if(chatRes==true) {
			out.print("true");
		}else {
			out.print("false");
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("/chat/inout[POST]");
		
		//인코딩 설정
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		Chat chat = new Chat();
		chat.setChatRoom(req.getParameter("chatroom"));
		List<Chat> cList = chatService.getChatList(chat);
		String list = chatService.getListFromFormat(cList);
		if(list!=null) {
			out.print(list);			
		}else {
			out.print("");
		}
	}
}
