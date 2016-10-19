<%@page import="com.mvc.reply.domain.Reply"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/json; charset=UTF-8"%>

<%
	List<Reply> list=(List)request.getAttribute("list");
	
	StringBuffer sb = new StringBuffer();
	
	sb.append("{");
	sb.append("\"replyList\": [");
	for(int i=0;i<list.size();i++){
		Reply reply=list.get(i);
		
		sb.append("{");
		sb.append("\"reply_id\" : 1,");
		sb.append("\"writer\": \""+reply.getWriter()+"\",");
		sb.append("\"msg\":\""+reply.getMsg()+"\",");
		sb.append("\"regdate\": \""+reply.getRegdate()+"\",");
		sb.append("\"board_id\":"+reply.getBoard_id());		
		
		if(i< list.size()-1){
			sb.append("},");
		}else{
			sb.append("}");
		}
	}
	sb.append("]");	
	sb.append("}");
	
	out.print(sb.toString());
%>

