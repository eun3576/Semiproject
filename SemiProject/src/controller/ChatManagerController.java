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

@WebServlet("/chat/manager")
public class ChatManagerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ChatService chatService = new ChatServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Chat> cList = chatService.getChatList();
		req.setAttribute("cList", cList);
		req.getRequestDispatcher("/WEB-INF/views/chat/chatManagerList.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//인코딩 설정
		req.setCharacterEncoding("UTF-8");
		
		Chat chat = new Chat();
		chat.setChatRoom(req.getParameter("chatroom"));
//		System.out.println(chat);

		req.setAttribute("chat", chat);
		
		req.getRequestDispatcher("/WEB-INF/views/chat/chatManager.jsp").forward(req, resp);
	}
}
