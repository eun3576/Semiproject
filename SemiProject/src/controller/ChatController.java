package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Chat;
import service.face.ChatService;
import service.impl.ChatServiceImpl;

@WebServlet("/chat/user")
public class ChatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ChatService chatService = new ChatServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/chat/chat.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
//		System.out.println("/chat/user[POST]");
		Chat chat = new Chat();
		chat.setChatRoom(req.getParameter("chatroom"));
//		System.out.println(chat);
		chatService.deleteChat(chat);
	}
	
}
