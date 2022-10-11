function loginCheck(){
	if(document.loginFrm.id.value==""){
		alert("아이디는 필수입력사항입니다");
		document.loginFrm.id.focus();
		return false;
	}else if(document.loginFrm.pwd.value==""){
		alert("비밀번호는 필수입력사항입니다");
		document.loginFrm.pwd.focus();
		return false;
	}else{
		return true;	
	}	
}


function go_next(){
	 if( document.contractFrm.okon[1].checked==true){
		alert('회원 약관에 동의 하셔야 회원으로 가입이 가능합니다');
	}else{
		document.contractFrm.action = "joinForm";
		document.contractFrm.submit();
	}
}






function idcheck(){
	if( document.formm.id.value=="" ){
		alert("아이디를 입력하고 중복체크를 진행하세요" );
		documnet.formm.id.focus();
		return;
	}
	var url = "idCheckForm?id=" + document.formm.id.value;
	var opt = "toolbar=no, menubar=no, resizable=no, width=500, height=250, scrollbars=no";
	window.open(url, "IdCheck", opt);	
}



function idok( userid ){
	opener.formm.id.value = userid;
	opener.formm.reid.value = userid;
	self.close();
}













function find_account(){

	var url = "shop.do?command=findAccount";
	var opt = "toolbar=no, menubar=no, scrollbars=no, resizable=no, width=700,";
	opt = opt + "height=500, top=300, left=300";
	window.open(url, "Find Id/Pw", opt);
	
}


function move_login(){
	opener.location.href='shop.do?command=loginForm';
	self.close();
}




function resetPw(){
	if(document.frm.pwd.value==""){
		alert("비밀번호를 입력하세요"); 		
		document.frm.pwd.focus(); 		
		return false;
	}else if(document.frm.pwd.value!=document.frm.pwd_chk.value){
		alert("비밀번호확인이 일치하지 않습니다"); 		
		document.frm.pwd_chk.focus(); 	
		return false;
	}else{
		return true;	
	}
	
}









