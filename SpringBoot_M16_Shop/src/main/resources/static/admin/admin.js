function worker_check()
{
	  if(document.frm.workId.value==""){
	      alert("아이디를 입력하세요.");
	      return false;
	  }else if(document.frm.workPwd.value==""){
	     alert("비밀번호를 입력하세요.");
	      return false;
	  }
	  return true;  
}




function go_search( requestName ){
	if( document.frm.key.value=="") return;
	document.frm.action = requestName + "?page=1";
	document.frm.submit();
}


function go_total( requestName ){
	document.frm.key.value="";
	document.frm.action = requestName + "?page=1";
	document.frm.submit();
}



function go_wrt(){
	document.frm.action = "productWriteForm";
	document.frm.submit();
}



function cal(){
	if( document.frm.price2.value == "" || document.frm.price1.value=="")return; 
	document.frm.price3.value = document.frm.price2.value - document.frm.price1.value; 
}



function go_mov(){
	location.href = "productList";
}

function go_save(){
	var theForm = document.frm;  //폼객체를 변수에 저장하고 변수이름으로 객체를 사용
	if (theForm.name.value == '') {
		alert('상품명을 입력하세요.'); 	
		theForm.name.focus();	
	} else if (theForm.price1.value == '') {
		alert('원가를 입력하세요.'); 		
		theForm.price1.focus();
	} else if (theForm.price2.value == '') {
		alert('판매가를 입력하세요.'); 		
		theForm.price2.focus();
	} else if (theForm.content.value == '') {
		alert('상품상세를 입력하세요.'); 		
		theForm.content.focus();
	} else if (theForm.image.value == '') {
		alert('상품이미지들 입력하세요.'); 	
		theForm.image.focus();	
	} else{
		theForm.action = "productWrite";
		theForm.submit();
	}
}


function go_detail(pseq){
	document.frm.action = "adminProductDetail?pseq=" + pseq;
	document.frm.submit();
}


function go_mod(pseq){
	document.frm.action = "productUpdateForm?pseq=" + pseq;
	document.frm.submit();
}



function go_mod_save(){
	if (document.frm.name.value == '') {
		alert('상품명을 입력하세요');	  
		document.frm.name.focus();
	 } else if (document.frm.price1.value == '') {
		alert('원가를 입력하세요');	  
		document.frm.price1.focus();
	 } else if (document.frm.price2.value == '') {
		alert('판매가를 입력하세요');	  
		document.frm.price2.focus();
	 } else if (document.frm.content.value == '') {
		alert('상품상세를 입력하세요');	  
		document.frm.content.focus();
	 }else{
		if( confirm('수정하시겠습니까?') ){
			 document.frm.action = "productUpdate";
			 document.frm.submit();
		}
	}
}






function go_order_save(){
	var count=0;
	if(document.frm.result.length==undefined){
		if( document.frm.result.checked == true ) count++;
	} else {
		for(var i=0; i<document.frm.result.length; i++){
			if( document.frm.result[i].checked == true ) count++;
		}
	}
	if( count == 0) {
		alert("삭제할 항목을 선택해 주세요.");
	}else{
		document.frm.action = "orderUpdateResult";
		document.frm.submit();
	}
}




function go_view( qseq ){
	location.href = "adminQnaView?qseq=" + qseq;
}




function go_rep(){
	document.frm.action = "adminQnaRepSave";
	document.frm.submit();
}














