function check(){
	if(document.frm.joinUserId.value==""){
		alert("아이디를 입력해주세요");
		document.frm.joinUserId.focus();
		return false;
	} 
	if(!/^[a-zA-Z0-9]{4,6}$/.test(joinUserId.value)){
		alert("ID는 4~6자의 영어 대소문자와 숫자로만 입력해주세요")
		document.frm.joinUserId.value="";
		document.frm.joinUserId.focus();
		return false
	}
	
	
	if(document.frm.joinUserNick.value==""){
		alert("닉네임을 입력해주세요");
		document.frm.userJoinnick.focus();
		return false;
	} 
	
	if(!/^[a-zA-Z0-9ㄱ-ㅎㅏ-ㅣ가-힣]{4,6}$/.test(joinUserNick.value)){
		alert("닉네임은는 4~6자의 영어 대소문자와 숫자로만 입력해주세요")
		return false
	}
	
	if(document.frm.userJoinpw.value==""){
		alert("비밀번호를 입력해주세요");
		document.frm.userJoinpw.value=""
		document.frm.userJoinpw.focus();
		return false;
	}
	if(!/^[a-zA-Z0-9]{6,8}$/.test(userJoinpw.value)){
		alert("password는 6~8자의 영어 대소문자와 숫자로만 입력해주세요")
		document.frm.userJoinpw.value=""
		document.frm.userJoinpw.focus();
		return false
	}
	if(document.frm.userJoinpwcheck.value==""){
		alert("비밀번호확인을 해주세요");
		document.frm.userJoinpwcheck.focus();
		return false;
	}
	
	if(document.frm.userJoinpw.value!=document.frm.userJoinpwcheck.value){
		alert("비밀번호가 같지 않습니다");
		document.frm.userJoinpw.focus();
		return false;
	}
	
	
	if(isNaN(document.frm.userJoinphone.value)){
		alert("숫자로 입력해주세요");
		document.frm.age.focus();
		return false;
	} 
	if(document.frm.userJoinphone.value==""){
		alert("휴대전화번호를 입력해주세요");
		document.frm.userJoinphone.value=""
		document.frm.userJoinphone.focus();
		return false;
	}
	if(!/^[0-9]{10,11}$/.test(userJoinphone.value)){
		alert("휴대전화번호는 10~11자의 숫자로만 입력해주세요")
		document.frm.userJoinphone.value=""
		document.frm.userJoinphone.focus();
		return false
	}
	
	return true;
}
