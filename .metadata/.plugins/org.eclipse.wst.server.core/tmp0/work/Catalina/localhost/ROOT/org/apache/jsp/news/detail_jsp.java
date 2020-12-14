/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.61
 * Generated at: 2020-12-11 00:39:37 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.news;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import board.model.Comments;
import java.util.ArrayList;
import board.model.CommentsDAO;
import board.model.News;
import board.model.NewsDAO;

public final class detail_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("board.model.NewsDAO");
    _jspx_imports_classes.add("java.util.List");
    _jspx_imports_classes.add("board.model.CommentsDAO");
    _jspx_imports_classes.add("board.model.News");
    _jspx_imports_classes.add("board.model.Comments");
    _jspx_imports_classes.add("java.util.ArrayList");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

	String news_id = request.getParameter("news_id");//파라미터 받기
	NewsDAO dao = new NewsDAO();
	News news = dao.select(Integer.parseInt(news_id));//데이터 한 건 가져오기!
	
	//이 게시물에 딸려있는 모든 코멘트 게시물 가져오기!!
	CommentsDAO commentDAO = new CommentsDAO();
	List<Comments> list = commentDAO.selectAll(Integer.parseInt(news_id));

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"utf-8\">\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("<style>\r\n");
      out.write("table {\r\n");
      out.write("\twidth: 70%;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("td {\r\n");
      out.write("\tborder: 2px solid #cccccc;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("td input, textarea {\r\n");
      out.write("\twidth: 97%;\r\n");
      out.write("\tfont-size: 1em;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("textarea {\r\n");
      out.write("\theight: 150px;\r\n");
      out.write("}\r\n");
      out.write("input[name ='msg']{\r\n");
      out.write("\twidth:65%;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("input[name ='author']{\r\n");
      out.write("\twidth:15%;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("p{\r\n");
      out.write("\tdisplay: inline-block;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".msg{\r\n");
      out.write("\twidth:65%\r\n");
      out.write("}\r\n");
      out.write(".author{\r\n");
      out.write("\twidth:10%\r\n");
      out.write("}\r\n");
      out.write(".cdate{\r\n");
      out.write("\twidth:13%\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\r\n");
      out.write("<script>\r\n");
      out.write("\tvar author;\r\n");
      out.write("\tvar msg;\r\n");
      out.write("\tvar cdate;\r\n");
      out.write("\r\n");
      out.write("\t$(function() {\r\n");
      out.write("\t\t$($(\"button\")[0]).click(function() {//수정\r\n");
      out.write("\t\t\tif(confirm(\"수정하시겠습니까?\")){\r\n");
      out.write("\t\t\t\tupdate();\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\r\n");
      out.write("\t\t$($(\"button\")[1]).click(function() {//삭제\r\n");
      out.write("\t\t\tif(confirm(\"삭제하시겠습니까?\")){\r\n");
      out.write("\t\t\t\tdel();\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$($(\"button\")[2]).click(function() {//목록\r\n");
      out.write("\t\t\tlocation.href = \"list.jsp\";\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$($(\"button\")[3]).click(function() {//댓글 등록\r\n");
      out.write("\t\t\treply();\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$($(\"button\")[4]).click(function() {//비동기 방식의 댓글 등록\r\n");
      out.write("\t\t\tasyncReply();\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t//댓글 목록 가져오기!!\r\n");
      out.write("\t\tasyncList();\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\t//비동기로 목록 가져오기!!\r\n");
      out.write("\tfunction asyncList(){\r\n");
      out.write("\t\tvar xhttp = new XMLHttpRequest(); //비동기 통신 객체\r\n");
      out.write("\t\txhttp.onreadystatechange = function() {\r\n");
      out.write("\t\t\tif (this.readyState == 4 && this.status == 200) {\r\n");
      out.write("\t\t\t\tgetList(this.responseText);//코멘트 리스트 동적 출력\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t};\r\n");
      out.write("\t\txhttp.open(\"get\", \"/news/asynclist.jsp?news_id=");
      out.print(news_id);
      out.write("\", true);\r\n");
      out.write("\t\t\r\n");
      out.write("\t\txhttp.send();\t\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction asyncReply(){\r\n");
      out.write("\t\tvar xhttp = new XMLHttpRequest(); //비동기 통신 객체\r\n");
      out.write("\t\t/*\r\n");
      out.write("\t\t0: request not initialized : 요청준비도 않된상태\r\n");
      out.write("\t\t1: server connection established : 서버와 네트워크 연결이 된 상태 \r\n");
      out.write("\t\t2: request received : 요청이 서버에 도달함\r\n");
      out.write("\t\t3: processing request : 서버가 요청을 처리중...\r\n");
      out.write("\t\t4: request finished and response is ready : 요청처리가 완료, 응답을 받는 단계\r\n");
      out.write("\t\t*/\r\n");
      out.write("\t\txhttp.onreadystatechange = function() {\r\n");
      out.write("\t\t\tif (this.readyState == 4 && this.status == 200) {\r\n");
      out.write("\t\t\t\t//alert(this.responseText);\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t//전체화면 갱신이 아닌, 부분화면 갱신...(새로고침이 되지 않음)\r\n");
      out.write("\t\t\t\t//SPA==Single Page Application\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t//최신 댓글목록 가져오기\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tgetList(this.responseText);//코멘트 리스트 동적 출력\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t};\r\n");
      out.write("\t\tauthor=$(\"input[name='author']\").val();\r\n");
      out.write("\t\tmsg=$(\"input[name='msg']\").val();\r\n");
      out.write("\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tvar params=\"news_id=");
      out.print(news_id);
      out.write("&author=\"+author+\"&msg=\"+msg;\r\n");
      out.write("\t\t\r\n");
      out.write("\t\txhttp.open(\"post\", \"/news/asyncreply.jsp\", true);\r\n");
      out.write("\t\t//반드시 open()메서드로  post 를 지정한 후에나 아래의 post 속성이 지정이 가능\r\n");
      out.write("\t\txhttp.setRequestHeader(\"Content-type\", \"application/x-www-form-urlencoded\");\r\n");
      out.write("\t\txhttp.send(params);\t\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction getList(data){\r\n");
      out.write("\t\t//alert(data);//서버로부터 응답받은 데이터를 alert\r\n");
      out.write("\t\t//json을 파싱하자!!!\r\n");
      out.write("\t\tvar commentBox = document.getElementById(\"commentBox\");\r\n");
      out.write("\t\tcommentBox.innerHTML = \"\";//기존 데이터 삭제!!!\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tvar tag = \"\";//div가 누적될 변수\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tvar json = JSON.parse(data);//파싱을 하게된면, 그 결과로 반환되는 결과물은 객체가 된다..\r\n");
      out.write("\t\t\t\t\t\t\t\t\t//따라서 이 시점부터는 문자열에 불과했던 데이터를 객체처럼 접근하여 사용할 수 있다.\r\n");
      out.write("\t\tif(json.resultCode==0){\r\n");
      out.write("\t\t\t//alert(\"등록실패 ㅜㅜ\");\r\n");
      out.write("\t\t}else{\r\n");
      out.write("\t\t\tvar jsonArray = json.commentsList;//배열을 반환\r\n");
      out.write("\t\t\t//alert(\"현재까지 등록된 댓글의 수는 \"+jsonArray.length);\r\n");
      out.write("\t\t\tfor (var i = 0; i < jsonArray.length; i++) {\r\n");
      out.write("\t\t\t\tvar comments = jsonArray[i];\r\n");
      out.write("\t\t\t\ttag += \"<div>\";\r\n");
      out.write("\t\t\t\ttag += \"<p class = \\\"msg\\\">\"+comments.msg+\"</p>\";\r\n");
      out.write("\t\t\t\ttag += \"<p class = \\\"author\\\">\"+comments.author+\"</p>\";\r\n");
      out.write("\t\t\t\ttag += \"<p class = \\\"cdate\\\">\"+comments.cdate+\"</p>\";\r\n");
      out.write("\t\t\t\ttag += \"<p class = \\\"del\\\"><button type = \\\"button\\\" onClick=\\\"delComments(\"+comments.comments_id+\")\\\">삭제</button></p>\";\r\n");
      out.write("\t\t\t\ttag += \"</div>\"\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tcommentBox.innerHTML = tag;\r\n");
      out.write("\t\t\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t//코멘트 삭제\r\n");
      out.write("\tfunction delComments(comments_id){\r\n");
      out.write("\t\tvar ans =\tconfirm(comments_id+\"를 삭제하길 원해?\")\r\n");
      out.write("\t\tif(ans){\r\n");
      out.write("\t\t\t//삭제 후(비동기), 리스트 가져오기(비동기)\r\n");
      out.write("\t\t\tvar xhttp = new XMLHttpRequest(); //비동기 통신 객체\r\n");
      out.write("\t\t\txhttp.onreadystatechange = function() {\r\n");
      out.write("\t\t\t\tif (this.readyState == 4 && this.status == 200) {\r\n");
      out.write("\t\t\t\t\tif(this.responseText==0){\r\n");
      out.write("\t\t\t\t\t\talert(\"삭제 실패\");\r\n");
      out.write("\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\tasyncList();\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t};\r\n");
      out.write("\t\t\txhttp.open(\"get\", \"/news/asyncdelete.jsp?comments_id=\"+comments_id, true);\r\n");
      out.write("\t\t\txhttp.send();\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction update(){\r\n");
      out.write("\t\t$(\"form\").attr({\r\n");
      out.write("\t\t\tmethod : \"post\",\r\n");
      out.write("\t\t\taction: \"update.jsp\"\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t$(\"form\").submit();\t\t\r\n");
      out.write("\t\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction del(){\r\n");
      out.write("\t\t");
if(list.size() > 0){
      out.write("\r\n");
      out.write("\t\t\t//자식 코멘트가 존재한다면, 업데이트!!!\r\n");
      out.write("\t\t\t$(\"form\").attr({\r\n");
      out.write("\t\t\t\tmethod : \"post\",\r\n");
      out.write("\t\t\t\taction: \"replace.jsp\"\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t$(\"form\").submit();\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t");
}else{
      out.write("\r\n");
      out.write("\t\t\t//자식 코멘트가 없다면 그냥 삭제\r\n");
      out.write("\t\t\t$(\"form\").attr({\r\n");
      out.write("\t\t\t\tmethod : \"post\",\r\n");
      out.write("\t\t\t\taction: \"delete.jsp\"\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t$(\"form\").submit();\t\t\t\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t");
}
      out.write("\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t//서버에 댓글 등록 요청하기!!\r\n");
      out.write("\tfunction reply(){\r\n");
      out.write("\t\t$(\"form\").attr({\r\n");
      out.write("\t\t\tmethod : \"post\",\r\n");
      out.write("\t\t\taction: \"reply.jsp\"\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t$(\"form\").submit();\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction regist() {\r\n");
      out.write("\t\t$(\"form\").attr({\r\n");
      out.write("\t\t\tmethod : \"post\",\r\n");
      out.write("\t\t\taction: \"regist.jsp\"\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t$(\"form\").submit();\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<form>\r\n");
      out.write("\t\t<input type=\"hidden\" name=\"news_id\" value = \"");
      out.print(news.getNews_id() );
      out.write("\">\r\n");
      out.write("\t\t<table align=\"center\">\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td><input type=\"text\" name=\"writer\" value = \"");
      out.print(news.getWriter() );
      out.write("\"></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td><input type=\"text\" name=\"title\" value = \"");
      out.print(news.getTitle() );
      out.write("\"></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td><textarea name=\"content\">");
      out.print(news.getContent() );
      out.write("</textarea></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td align=\"center\">\r\n");
      out.write("\t\t\t\t\t<button type = \"button\">수정</button>\r\n");
      out.write("\t\t\t\t\t<button type = \"button\">삭제</button>\r\n");
      out.write("\t\t\t\t\t<button type = \"button\">목록</button>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t<div>\r\n");
      out.write("\t\t\t\t\t\t<input type = \"text\" placeholder = \"댓글을 입력\"  name = \"msg\">\r\n");
      out.write("\t\t\t\t\t\t<input type = \"text\" placeholder = \"작성자 입력\"  name = \"author\">\r\n");
      out.write("\t\t\t\t\t\t<button type = \"button\">등록</button>\r\n");
      out.write("\t\t\t\t\t\t<button type = \"button\">비동기 등록</button>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<!-- 댓글 리스트 영역 -->\r\n");
      out.write("\t\t\t\t<tr >\r\n");
      out.write("\t\t\t\t\t<td id =\"commentBox\"></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t</form>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}