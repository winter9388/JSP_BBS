<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="user.UserDAO" %>   <!-- UserDAO 임포트 -->
<%@ page import="java.io.PrintWriter" %> <!-- 자바 스크립트 문장 작성위해 필요 -->
<% request.setCharacterEncoding("UTF-8"); %> <!-- 건너오는 모든 데이터를 UTF-8으로 받을수 있게함 -->
<jsp:useBean id="user" class="user.User" scope="page"/> <!-- java Beans를 현재페이지 에서만 사용하는것을 명시 -->
<jsp:setProperty name="user" property="userID"/> <!-- 로그인 페이지에서 넘겨주는 userID를 프로퍼티에 담음 -->
<jsp:setProperty name="user" property="userPassword"/> <!-- 로그인 페이지에서 넘겨주는 userPassword를 프로퍼티에 담음 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 게시판 웹 사이트</title>
</head>
<body>
	<%
		String userID = null;
		if(session.getAttribute("userID") != null){ // 세션이 있을경우 (누군가 이미 로그인을 한 경우)
			userID = (String)session.getAttribute("userID"); // 할당된 세션 아이디를 변수에 입력
		}
		if(userID != null){ // 로그인 되어있는경우 메인페이지로 이동
			PrintWriter script = response.getWriter(); // 스크립트 문장을 사용할수 있게함
			script.println("<script>");
			script.println("alert('이미 로그인 되어있습니다.')"); 
			script.println("location.href = 'main.jsp'"); // 이전 페이지로 사용자를 돌려보냄
			script.println("</script>");
		}
		UserDAO userDAO = new UserDAO(); // DAO인스턴스 생성
		int result = userDAO.login(user.getUserID(), user.getUserPassword()); // 로그인 페이지에서 입력받은 아이디와 패스워드를 파라메터로 받음
		if(result == 1){ // 로그인 성공
			session.setAttribute("userID", user.getUserID()); // 로그인 성공시 세션 생성
			PrintWriter script = response.getWriter(); // 스크립트 문장을 사용할수 있게함
			script.println("<script>");
			script.println("location.href = 'main.jsp'"); // 아이디 패스워드 일치시 메인 페이지 이동
			script.println("</script>");
		}
		else if (result == 0){
			PrintWriter script = response.getWriter(); // 스크립트 문장을 사용할수 있게함
			script.println("<script>");
			script.println("alert('비밀번호가 틀립니다.')"); 
			script.println("history.back()"); // 이전 페이지로 사용자를 돌려보냄
			script.println("</script>");
		}
		else if (result == -1){
			PrintWriter script = response.getWriter(); // 스크립트 문장을 사용할수 있게함
			script.println("<script>");
			script.println("alert('존재하지 않는 아이디 입니다.')"); 
			script.println("history.back()"); // 이전 페이지로 사용자를 돌려보냄
			script.println("</script>");
		}
		else if (result == -2){
			PrintWriter script = response.getWriter(); // 스크립트 문장을 사용할수 있게함
			script.println("<script>");
			script.println("alert('데이터 베이스 오류가 발생했습니다.')"); 
			script.println("history.back()"); // 이전 페이지로 사용자를 돌려보냄
			script.println("</script>");
		}
	%>
</body>
</html>