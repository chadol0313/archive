function boardWrite(){
	if( document.frm.pwd.value==""){
		alert('비밀번호를 입력해주세요');
		document.frm.pwd.focus();
	}else if( document.frm.title.value==""){
		alert('제목을 입력해주세요');
		document.frm.title.focus();
	}else if( document.frm.b_content.value==""){
		alert('내용을 입력해주세요');
		document.frm.b_content.focus();
	}else{
		document.frm.action = " boardWrite";
		document.frm.submit();
	}
}