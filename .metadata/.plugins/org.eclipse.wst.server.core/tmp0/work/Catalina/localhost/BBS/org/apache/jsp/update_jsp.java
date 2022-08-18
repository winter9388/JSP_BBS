/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/10.0.23
 * Generated at: 2022-08-18 11:43:32 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;
import java.io.PrintWriter;
import bbs.Bbs;
import bbs.BbsDAO;

public final class update_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final jakarta.servlet.jsp.JspFactory _jspxFactory =
          jakarta.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("jakarta.servlet");
    _jspx_imports_packages.add("jakarta.servlet.http");
    _jspx_imports_packages.add("jakarta.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("java.io.PrintWriter");
    _jspx_imports_classes.add("bbs.Bbs");
    _jspx_imports_classes.add("bbs.BbsDAO");
  }

  private volatile jakarta.el.ExpressionFactory _el_expressionfactory;
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

  public jakarta.el.ExpressionFactory _jsp_getExpressionFactory() {
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

  public void _jspService(final jakarta.servlet.http.HttpServletRequest request, final jakarta.servlet.http.HttpServletResponse response)
      throws java.io.IOException, jakarta.servlet.ServletException {

    if (!jakarta.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
        return;
      }
    }

    final jakarta.servlet.jsp.PageContext pageContext;
    jakarta.servlet.http.HttpSession session = null;
    final jakarta.servlet.ServletContext application;
    final jakarta.servlet.ServletConfig config;
    jakarta.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    jakarta.servlet.jsp.JspWriter _jspx_out = null;
    jakarta.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write(" \n");
      out.write(" \n");
      out.write(" \n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta charset=\"UTF-8\">\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width\", initial-scale=\"1\">\n");
      out.write("<link rel=\"stylesheet\" href=\"css/bootstrap.css\">\n");
      out.write("<title>JSP 게시판 웹 사이트</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("	");

		String userID = null;
		if(session.getAttribute("userID") != null){ // 세션값이 존재하면
			userID = (String)session.getAttribute("userID"); // userID에 새션값을 입력
		}
		if(userID == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인을 하세요.')");
			script.println("location.href = 'login.jsp'");
			script.println("</script>");
		}
		int bbsID = 0;
		if (request.getParameter("bbsID") != null) {
			bbsID = Integer.parseInt(request.getParameter("bbsID"));
		}
		if (bbsID == 0) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('유효하지 않은 글입니다.')");
			script.println("location.href = 'bbs.jsp'");
			script.println("</script>");
		}
		Bbs bbs = new BbsDAO().getBbs(bbsID); //글 작성 아이디가 다르면 삭제 권한 없음
		if (!userID.equals(bbs.getUserID())) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('권한이 없습니다.')");
			script.println("location.href = 'bbs.jsp'");
			script.println("</script>");
		}
	
      out.write("\n");
      out.write("	<nav class=\"navbar navbar-default\">\n");
      out.write("		<div class=\"navbar-header\">\n");
      out.write("			<button type=\"button\" class=\"navbar-toggle collapsed\" \n");
      out.write("				data-toggle=\"collapse\" data-target=\"#bs-example-navbar-collapse-1\"\n");
      out.write("				aria-expended=\"false\">\n");
      out.write("				<span class=\"icon-bar\"></span>\n");
      out.write("				<span class=\"icon-bar\"></span>\n");
      out.write("				<span class=\"icon-bar\"></span>\n");
      out.write("			</button>\n");
      out.write("			<a class=\"navbar-brand\" href=\"main.jsp\">JSP 게시판 웹 사이트</a>\n");
      out.write("		</div>\n");
      out.write("		<div class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\"\n");
      out.write("			aria-expended=\"false\">\n");
      out.write("			<ul class=\"nav navbar-nav\">\n");
      out.write("				<li><a href=\"main.jsp\">메인</a></li>\n");
      out.write("				<li class=\"active\"><a href=\"bbs.jsp\">게시판</a></li>\n");
      out.write("			</ul>\n");
      out.write("			<ul class=\"nav navbar-nav navbar-right\">\n");
      out.write("				<li class=\"dropdown\">\n");
      out.write("					<a href=\"#\" class=\"dropdown-toggle\"\n");
      out.write("						data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\"\n");
      out.write("						aria-expanded=\"false\">회원관리<span class=\"caret\"></span></a>\n");
      out.write("					<ul class=\"dropdown-menu\">\n");
      out.write("						<li><a href=\"logoutAction.jsp\">로그아웃</a></li>\n");
      out.write("					</ul>\n");
      out.write("				</li>\n");
      out.write("			</ul>\n");
      out.write("		</div>\n");
      out.write("	</nav>\n");
      out.write("	\n");
      out.write("	<!--  -->\n");
      out.write("	<div class=\"container\">\n");
      out.write("		<div class=\"row\">\n");
      out.write("			<form method=\"post\" action=\"updateAction.jsp?bbsID=");
      out.print( bbsID );
      out.write("\">\n");
      out.write("				<table class=\"table table-striped\" style=\"text-align: center; border: 1px solid #dddddd\"> <!-- table-striped -> 테이블의 짝수 홀수 열 구분 -->\n");
      out.write("					<thead>\n");
      out.write("						<tr>\n");
      out.write("							<th colspan=\"2\" style=\"background-color: #eeeeee; text-align: center\">게시판 글 수정 양식</th>\n");
      out.write("						</tr>\n");
      out.write("					</thead>\n");
      out.write("					<tbody>\n");
      out.write("						<tr>\n");
      out.write("							<td><input type=\"text\" class=\"form-control\" placeholder=\"글 제목\" name=\"bbsTitle\" maxlength=\"50\" value=\"");
      out.print( bbs.getBbsTitle() );
      out.write("\"></td> <!-- 이전 글 내용 표시 -->\n");
      out.write("						</tr>\n");
      out.write("						<tr>\n");
      out.write("							<td><textarea class=\"form-control\" placeholder=\"글 내용\" name=\"bbsContent\" maxlength=\"2048\" style=\"height: 350px;\">");
      out.print( bbs.getBbsContent() );
      out.write("</textarea></td>\n");
      out.write("						</tr>\n");
      out.write("					</tbody>\n");
      out.write("				</table>\n");
      out.write("				<input type=\"submit\" class=\"btn btn-primary pull-right\" value=\"글수정\">\n");
      out.write("			</form>\n");
      out.write("		</div>\n");
      out.write("	</div>\n");
      out.write("	\n");
      out.write("	<script src=\"https://code.jquery.com/jquery-3.1.1.min.js\"></script>\n");
      out.write("	<script src=\"js/bootstrap.js\"></script>\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof jakarta.servlet.jsp.SkipPageException)){
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
