/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.61
 * Generated at: 2020-12-11 08:18:57 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.board;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import board.model.Board;
import board.model.BoardDAO;

public final class list_jsp extends org.apache.jasper.runtime.HttpJspBase
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
    _jspx_imports_classes.add("board.model.BoardDAO");
    _jspx_imports_classes.add("java.util.List");
    _jspx_imports_classes.add("board.model.Board");
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

	BoardDAO dao = new BoardDAO();
	List<Board> list = dao.selectAll();

	int totalRecord = list.size();
	int currentPage = 1;
	if(request.getParameter("currentPage") != null){
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	int pageSize = 10;
	int totalPage = (int)Math.ceil((float)totalRecord/pageSize);

	int blockSize = 10;
	int firstPage = currentPage - (currentPage-1)%blockSize;
	int lastPage = firstPage + (blockSize-1);
	int curPos = (currentPage-1)*pageSize;
	int num = totalRecord - curPos;
	
	String realPath = application.getRealPath("/data");

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n");
      out.write("<style>\r\n");
      out.write("table {\r\n");
      out.write("\tborder-collapse: collapse;\r\n");
      out.write("\tborder-spacing: 0;\r\n");
      out.write("\twidth: 100%;\r\n");
      out.write("\tborder: 1px solid #ddd;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("th, td {\r\n");
      out.write("\ttext-align: left;\r\n");
      out.write("\tpadding: 16px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("tr:nth-child(even) {\r\n");
      out.write("\tbackground-color: #f2f2f2;\r\n");
      out.write("}\r\n");
      out.write("a{\r\n");
      out.write(" \ttext-decoration: none;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".pageNum{\r\n");
      out.write("\tfont-size : 20px;\r\n");
      out.write("\tfont-weight : bold;\r\n");
      out.write("\tcolor : red;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("\t<table>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<th>No</th>\r\n");
      out.write("\t\t\t<th>이미지</th>\r\n");
      out.write("\t\t\t<th>제목</th>\r\n");
      out.write("\t\t\t<th>작성자</th>\r\n");
      out.write("\t\t\t<th>등록일</th>\r\n");
      out.write("\t\t\t<th>조회수</th>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t");
for(int i = 0; i<pageSize; i++) {
      out.write("\r\n");
      out.write("\t\t\t");
if(num <1) break; 
      out.write("\r\n");
      out.write("\t\t\t");
 Board board = list.get(curPos++); 
      out.write("\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td>");
      out.print(num-- );
      out.write("</td>\r\n");
      out.write("\t\t\t\t<td><img src = \"");
      out.print(realPath );
      out.write('/');
      out.print(board.getFilename() );
      out.write("\" width = \"15px\"/></td>\r\n");
      out.write("\t\t\t\t<td>");
      out.print(board.getTitle() );
      out.write("</td>\r\n");
      out.write("\t\t\t\t<td>");
      out.print(board.getWriter() );
      out.write("</td>\r\n");
      out.write("\t\t\t\t<td>");
      out.print(board.getRegdate() );
      out.write("</td>\r\n");
      out.write("\t\t\t\t<td>");
      out.print(board.getHit() );
      out.write("</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t");
} 
      out.write("\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td colspan=\"6\" style = \"text-align:center;\">\r\n");
      out.write("\t\t\t\t");
if(firstPage > 1) {
      out.write("\r\n");
      out.write("\t\t\t\t\t<a href =\"list.jsp?currentPage=");
      out.print(firstPage-1);
      out.write("\">◀</a>\r\n");
      out.write("\t\t\t\t");
}else{ 
      out.write("\r\n");
      out.write("\t\t\t\t\t<a href =\"javascript:alert('처음 페이지 입니다.')\">◀</a>\r\n");
      out.write("\t\t\t\t");
} 
      out.write("\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\t");
for(int i=firstPage; i<=lastPage; i++){ 
      out.write("\r\n");
      out.write("\t\t\t\t\t");
if(i > totalPage) break; 
      out.write("\r\n");
      out.write("\t\t\t\t\t<a href = \"list.jsp?currentPage=");
      out.print(i);
      out.write('"');
      out.write(' ');
if(i == currentPage){ 
      out.write("class = \"pageNum\" ");
} 
      out.write('>');
      out.write('[');
      out.print(i);
      out.write("]</a>\r\n");
      out.write("\t\t\t\t");
} 
      out.write("\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t");
if(lastPage < totalPage) {
      out.write("\r\n");
      out.write("\t\t\t\t\t<a href =\"list.jsp?currentPage=");
      out.print(lastPage+1);
      out.write("\">▶</a>\r\n");
      out.write("\t\t\t\t");
}else{ 
      out.write("\r\n");
      out.write("\t\t\t\t\t<a href =\"javascript:alert('마지막 페이지 입니다.')\">▶</a>\r\n");
      out.write("\t\t\t\t");
} 
      out.write("\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td colspan=\"6\">\r\n");
      out.write("\t\t\t\t<button onclick = \"location.href = 'regist_form.jsp'\">글 쓰기</button>\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t</table>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
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
