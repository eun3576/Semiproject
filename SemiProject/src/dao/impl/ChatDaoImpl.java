package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.ChatDao;
import dto.Chat;

public class ChatDaoImpl implements ChatDao{
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public int insertChatContent(Connection conn, Chat chat) {
		String sql = "";
		sql += "INSERT INTO chat(CHAT_NO, CHAT_ID, CHAT_CONTENT, chat_room, CHAT_TIME)";
		sql += " VALUES(CHAT_SEQ.nextval, ?, ?, ?, to_char(sysdate,'mm-dd HH24:MI:ss') ) ";
		
		int cnt = 0;
		
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, chat.getChatId());
			ps.setString(2, chat.getChatContent());
			ps.setString(3, chat.getChatRoom());
			cnt = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		return cnt;
	}

	@Override
	public List<Chat> selectByChatId(Connection conn, Chat chat) {
		String sql = "";
		sql += "SELECT chat_id, chat_content, chat_time FROM chat";
		sql += " WHERE chat_room = ?";
		sql += " ORDER BY chat_time asc";
		
		List<Chat> cList = new ArrayList<>();
		
		try {
			//sql 수행객체 생성
			ps = conn.prepareStatement(sql);
			ps.setString(1, chat.getChatRoom());
			//sql 수행 및 결과 저장
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Chat c = new Chat();
				c.setChatId(rs.getString("chat_id"));
				c.setChatContent(rs.getString("chat_content"));
				c.setChatTime((rs.getString("chat_time")));
				cList.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return cList;
	}

	@Override
	public List<Chat> SelectList(Connection conn) {
		String sql = "";
		sql += "SELECT DISTINCT CHAT_ROOM FROM CHAT ORDER BY CHAT_ROOM desc";
		
		List<Chat> cList = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
				
			while(rs.next()) {
				Chat chat = new Chat();
				chat.setChatRoom(rs.getString(1));
				cList.add(chat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return cList;
	}

	@Override
	public int deleteChat(Connection conn, Chat chat) {
		String sql = "";
		sql += "DELETE FROM chat";
		sql += " WHERE chat_room =?";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, chat.getChatRoom());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	
}
