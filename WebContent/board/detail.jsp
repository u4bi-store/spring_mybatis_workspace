<%@page import="com.mvc.board.domain.ReBoard"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	ReBoard board = (ReBoard) request.getAttribute("board");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style>
#box {
	border: 1px solid #CCCCCC
}

#writer, #title, #content {
	font-size: 9pt;
	font-weight: bold;
	color: #7F7F7F;
	돋움
}

input {
	font-size: 9pt;
	border-left: 1px solid #C3C3C3;
	border-top: 1px solid #C3C3C3;
	border-right: 1px solid #C3C3C3;
	border-bottom: 1px solid #C3C3C3;
	color: #7F7F7F;
	돋움
}

#title input {
	width: 250px;
}

#content textarea {
	width: 503px;
	border: 0;
	height: 153;
	background: url("/images/write_bg.gif");
	border: #C3C3C3 1px solid
}

#copyright {
	font-size: 9pt;
}

a {
	text-decoration: none
}

img {
	border: 0px
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
	function edit() {
		form1.action = "/board/edit.do";
		form1.submit();
	}
	
	function del(){
		if(confirm("삭제하시겠습니까?")){
			form1.action="/board/delete.do";
			form1.submit();
		}	
		
	}
	
	function regist(){
		//비동기 요청!!
		$.post("/reply/regist.do", {
			"writer" : form2.writer.value, 
			"msg" : form2.msg.value, 
			"board_id" : <%=board.getReboard_id()%>
		}, function(data, status){
			if(data=="ok"){
				//서버에 리스트를 요청!!
				getList();
			}else if(data=="fail"){
				alert("등록에 실패하였습니다.");
			}
		});
	}
	
	function getList(){
		$.get("/reply/list.do?board_id=<%=board.getReboard_id()%>", function(data, status){
			//alert(data[0].writer);
			//문서내의 replyTable 에  tr과 td 자식요소를 동적으로 추가!!
			var replyTable=document.getElementById("replyTable");
			
			//기존에 등록되어있는 자식요소는 싹~~~지우고!!
			alert(replyTable.childNodes.length);
			for(var i=0;i<replyTable.childNodes.length;i++){
				replyTable.removeChild(replyTable.childNodes[0]);
			}
			
			for(var i=0; i<data.length;i++){
				//기존에 없었던 태그를 동적으로 생성하자
				var tr=document.createElement("tr");
				
				var writer_td=document.createElement("td");
				writer_td.innerText=data[i].writer;
				tr.appendChild(writer_td);
				
				var msg_td=document.createElement("td");
				msg_td.innerText=data[i].msg;
				tr.appendChild(msg_td);
				
				var regdate_td=document.createElement("td");
				regdate_td.innerText=data[i].regdate;
				tr.appendChild(regdate_td);
				
				//테이블에 tr 부착!!
				replyTable.appendChild(tr);
			}
		});
	}
	
	function replyForm(){
		//답변 등록 
		form3.action="/board";
		form3.submit();
		
	}
</script>
</head>
<body onLoad="getList()">	
		<input type="hidden" name="board_id" value="<%=board.getReboard_id()%>">
		<table id="box" align="center" width="603" border="0" cellpadding="0"
			cellspacing="0">
			<form name="form1" method="post">
			<tr>
				<td><img src="/images/ceil.gif" width="603" height="25"></td>
			</tr>
			<tr>
				<td height="2" bgcolor="#6395FA"><img src="/images/line_01.gif"></td>
			</tr>
			<tr>
				<td height="1" bgcolor="#CCCCCC"></td>
			</tr>
			<tr>
				<td id="list"><table width="100%" border="0" cellspacing="0"
						cellpadding="0">
						<tr>
							<td width="100">&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr id="writer">
							<td height="25" align="center">작성자</td>
							<td><input type="text" name="writer"
								value="<%=board.getWriter()%>"></td>
						</tr>
						<tr id="title">
							<td height="25" align="center">제목</td>
							<td><input type="text" name="title"
								value="<%=board.getTitle()%>"></td>
						</tr>
						<tr id="content">
							<td align="center">내용</td>
							<td><textarea name="content" style=""><%=board.getContent()%></textarea></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
					</table></td>
			</tr>
			<tr>
				<td height="30" align="right" style="padding-right: 2px;"><img
					src="/images/write_btin.gif" width="61" height="20"
					onClick="replyForm()"> <img src="/images/delete_btn.gif"
					width="61" height="20" onClick="del()"> 
					<a href="/board/list.do"><img
						src="/images/list_btn.gif" width="61" height="20" border="0"></a>
				</td>
			</tr>
			<tr>
				<td height="1" bgcolor="#CCCCCC"></td>
			</tr>
			</form>
			
			<form name="form3" method="post">
			<tr>
				<td height="2" bgcolor="#6395FA"><img src="/images/line_01.gif"></td>
			</tr>
			<tr>
				<td height="1" bgcolor="#CCCCCC"></td>
			</tr>
			<tr>
				<td id="list"><table width="100%" border="0" cellspacing="0"
						cellpadding="0">
						<tr>
							<td width="100">&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr id="writer">
							<td height="25" align="center">작성자</td>
							<td><input type="text" name="writer"></td>
						</tr>
						<tr id="title">
							<td height="25" align="center">제목</td>
							<td><input type="text" name="title"></td>
						</tr>
						<tr id="content">
							<td align="center">내용</td>
							<td><textarea name="content" style=""></textarea></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
					</table></td>
			</tr>
			<tr>
				<td height="30" align="right" style="padding-right: 2px;"><img
					src="/images/write_btin.gif" width="61" height="20"
					onClick="replyForm()"> <img src="/images/delete_btn.gif"
					width="61" height="20" onClick="del()"> 
					<a href="/board/list.do"><img
						src="/images/list_btn.gif" width="61" height="20" border="0"></a>
				</td>
			</tr>
			<tr>
				<td height="1" bgcolor="#CCCCCC"></td>
			</tr>
			</form>
			
			<!-- 여기서 부터 댓글 테이블 영역 -->
			<form name="form2" method="post">
			<tr>
				<td colspan="2">
					<input type="text" name="writer">
					<input type="text" name="msg">
					<input type="button" value="댓글등록" onClick="regist()">
				</td>	
			</tr>
			<tr>
				<td>
					<table width="100%" id="replyTable"></table>
				</td>
			</tr>
			</form>
			<!-- 여기까지 댓글 영역 끝 -->
			<tr>
				<td height="20" align="center" id="copyright">Copyright zino
					All Rights Reserved</td>
			</tr>
		</table>
	
</body>
</html>
