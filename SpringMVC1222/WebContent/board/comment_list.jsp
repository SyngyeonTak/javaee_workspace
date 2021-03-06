<%@page import="com.model2.domain.Comment"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/json;charset=utf-8"%>
<%
	List list = (List)request.getAttribute("commentList");
	StringBuilder sb = new StringBuilder();
	sb.append("{");
	sb.append("\"list\":[");
	for(int i = 0; i< list.size(); i++){
		Comment comment = (Comment)list.get(i);
		sb.append("{");
		sb.append("\"msg\":\""+comment.getMsg()+"\",");
		sb.append("\"author\":\""+comment.getAuthor()+"\",");
		sb.append("\"cdate\":\""+comment.getCdate().substring(0, 10)+"\"");
		
		if(i < list.size()-1){
			sb.append("},");
		}else{
			sb.append("}");
		}
		
	}
	sb.append("]");
	sb.append("}");


	out.print(sb.toString());//응답 컨텐츠 구성하기!! 즉, response 객체에 담아놓는 것임!!
%>