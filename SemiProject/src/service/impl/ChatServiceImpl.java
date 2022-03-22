package service.impl;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.ChatDao;
import dao.impl.ChatDaoImpl;
import dto.Chat;
import service.face.ChatService;

public class ChatServiceImpl implements ChatService{
	private ChatDao chatDao = new ChatDaoImpl();
	
	@Override
	public Chat getChatContent(HttpServletRequest req) {
		Chat chat = new Chat();
		chat.setChatId(req.getParameter("chatId"));
		chat.setChatContent(req.getParameter("chatRcontent"));
		chat.setChatRoom(req.getParameter("chatroom"));
		
		return chat;
	}

	@Override
	public boolean insertChatContent(Chat chat) {
		Connection conn = JDBCTemplate.getConnection();
		int res = chatDao.insertChatContent(conn, chat);
		if(res>0) {
			JDBCTemplate.commit(conn);
			return true;
		}else {
			JDBCTemplate.rollback(conn);
			return false;
		}
	}

	@Override
	public List<Chat> getChatList(Chat chat) {
		Connection conn = JDBCTemplate.getConnection();
		return chatDao.selectByChatId(conn, chat);
	}

	@Override
	public String getListFromFormat(List<Chat> cList) {
		String res = "";
		for(int i=0;i<cList.size();i++) {
			res += "<img alt='사용자 이미지' src='../../resources/img/person_round_user_icon.png' width='40' height='40' style='float:left;margin-right:10px;'>";
			res += "<table style='float:left;margin-bottom:10px;'>";
			res += "<tr><td class='chat_id' style='font-weight:bold;'>"+cList.get(i).getChatId()+"</td>";
			res += "<td class='chat_time'>"+cList.get(i).getChatTime()+"</td></tr>";
			res += "<tr><td class='chat_content_fromId' style='width:400px;overflow:auto;'>"+cList.get(i).getChatContent()+"</td></tr>";
			res += "</table>";
			res += "<hr style='clear:both;'>";
		}
		return res;
	}

	@Override
	public List<Chat> getChatList() {
		Connection conn = JDBCTemplate.getConnection();
		return chatDao.SelectList(conn);
	}

	@Override
	public void deleteChat(Chat chat) {
		Connection conn = JDBCTemplate.getConnection();
		
		int res = chatDao.deleteChat(conn, chat);
		
		//삭제후 성공 커밋, 실패 롤백
		if(res>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
	}
	
	

}
