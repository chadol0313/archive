function logincheck(){
	if(document.loginFrm.id.value==""){
		alert("아이디를 입력해주세요")
		return false;
	}else if(document.loginFrm.pwd.value==""){
		alert("비밀번호를 입력해주세요")
		return false;
	}else{
		return true;
		}
}

function idCheck(){
	if( document.joinFrm.id.value=="" ){
		alert("아이디를 입력해주세요" );
		documnet.joinFrm.id.focus();
		return;
	}
	var url = "idCheckForm?id=" + document.joinFrm.id.value;
	var opt = "toolbar=no, menubar=no, resizable=no, width=500, height=250, scrollbars=no";
	window.open(url, "idcheck", opt);	
}


function idok(id){
	opener.joinFrm.id.value = id;
	opener.joinFrm.re_id.value = id;
	self.close();
}

function go_update(){
	if(document.updateFrm.pwd.value==""){
		alert("비밀번호를 입력해주세요")
		documnet.updateFrm.pwd.focus();
		return false;
	}else return true;
}









