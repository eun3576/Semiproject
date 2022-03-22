package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.Chat;

public interface ChatDao {

	/**
	 * 채팅 객체를 db에 저장한다
	 * @param conn db연결 객체
	 * @param chat 저장할 채팅 객체
	 * @return 성공 1, 실패 0
	 */
	public int insertChatContent(Connection conn, Chat chat);

	/**
	 * 채팅으로 보낸 글들을 가져온다
	 * @param conn db연결 객체
	 * @return 채팅 글 리스트
	 */
	public List<Chat> selectByChatId(Connection conn, Chat chat);

	/**
	 * 관리자 페이지에서 볼 채팅 목록을 가져온다
	 * @param conn db연결 객체
	 * @return 채팅 목록 리스트
	 */
	public List<Chat> SelectList(Connection conn);

	/**
	 * 채팅을 삭제한다
	 * @param conn db연결 객체
	 * @param chat 삭체할 채팅방 이름
	 * @return 결과값(성공1, 실패0)
	 */
	public int deleteChat(Connection conn, Chat chat);

}
