package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Chat;

public interface ChatService {

	/**
	 * 채팅으로 보낸 내용을 chat객체로 저장
	 * @param req 저장할 chat객체
	 * @return chat객체
	 */
	public Chat getChatContent(HttpServletRequest req);

	/**
	 * chat객체를 db에 저장
	 * @param chat 저장할 chat객체
	 */
	public boolean insertChatContent(Chat chat);

	/**
	 * 채팅한 리스트를 가져옴
	 * @return 채팅 리스트
	 */
	public List<Chat> getChatList(Chat chat);

	/**
	 * 채팅리스트를 서식으로 변환해서 화면에 출력해줄 수 있게 해준다
	 * @param cList 서식으로 변환할 채팅리스트
	 * @return html을 적용한 string
	 */
	public String getListFromFormat(List<Chat> cList);

	/**
	 * 관리자가 현재 개설되어있는 채팅방 확인
	 * @return 채팅방 목록
	 */
	public List<Chat> getChatList();

	/**
	 * 채팅 삭제하기
	 * @param chat 지울 채팅방 이름
	 */
	public void deleteChat(Chat chat);


}
