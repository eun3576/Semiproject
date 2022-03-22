package dto;

import java.util.Date;

public class Chat {
	private int chatNo;
	private String chatId;
	private String chatRoom; //방이름은 사용자 아이디와 같다
	private String chatContent;
	private String chatTime;

	@Override
	public String toString() {
		return "Chat [chatNo=" + chatNo + ", chatId=" + chatId + ", chatRoom=" + chatRoom + ", chatContent="
				+ chatContent + ", chatTime=" + chatTime + "]";
	}

	public int getChatNo() {
		return chatNo;
	}

	public void setChatNo(int chatNo) {
		this.chatNo = chatNo;
	}

	public String getChatId() {
		return chatId;
	}

	public void setChatId(String chatId) {
		this.chatId = chatId;
	}

	public String getChatRoom() {
		return chatRoom;
	}

	public void setChatRoom(String chatRoom) {
		this.chatRoom = chatRoom;
	}

	public String getChatContent() {
		return chatContent;
	}

	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}

	public String getChatTime() {
		return chatTime;
	}

	public void setChatTime(String chatTime) {
		this.chatTime = chatTime;
	}
	
	

	
}
