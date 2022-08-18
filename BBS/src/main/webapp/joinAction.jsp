<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="user.UserDAO" %>   <!-- UserDAO 임포트 -->
<%@ page import="java.io.PrintWriter" %> <!-- 자바 스크립트 문장 작성위해 필요 -->
<% request.setCharacterEncoding("UTF-8"); %> <!-- 건너오는 모든 데이터를 UTF-8으로 받을수 있게함 -->
<jsp:useBean id="user" class="user.User" scope="page"/> <!-- java Beans를 현재페이지 에서만 사용하는것을 명시 -->
<jsp:setProperty name="user" property="userID"/> <!-- 로그인 페이지에서 넘겨주는 userID를 프로퍼티에 담음 -->
<jsp:setProperty name="user" property="userPassword"/> <!-- 로그인 페이지에서 넘겨주는 userPassword를 프로퍼티에 담음 -->
<jsp:setProperty name="user" property="userPasswordChk"/> <!-- 로그인 페이지에서 넘겨주는 userPasswordChk를 프로퍼티에 담음 -->
<jsp:setProperty name="user" property="userName"/> <!-- 로그인 페이지에서 넘겨주는 userName를 프로퍼티에 담음 -->
<jsp:setProperty name="user" property="userGender"/> <!-- 로그인 페이지에서 넘겨주는 userGender를 프로퍼티에 담음 -->
<jsp:setProperty name="user" property="userEmail"/> <!-- 로그인 페이지에서 넘겨주는 userEmail를 프로퍼티에 담음 -->
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
		if(user.getUserID() == null || user.getUserPassword() == null || user.getUserName() == null 
			|| user.getUserGender() == null || user.getUserEmail() == null ) { // 회원가입시 필요항목 입력누락이 있는지 체크
			PrintWriter script = response.getWriter(); // 스크립트 문장을 사용할수 있게함
			script.println("<script>");
			script.println("alert('입력이 안된 사항이 있습니다.')"); 
			script.println("history.back()"); // 이전 페이지로 사용자를 돌려보냄
			script.println("</script>");	
		}else{ // 필요 항목 전부 입력 완료시
			if(!user.getUserPassword().equals(user.getUserPasswordChk())){ // 패스워드 체크 불일치
				PrintWriter script = response.getWriter(); // 스크립트 문장을 사용할수 있게함
				script.println("<script>");
				script.println("alert('패스워드를 다시 입력해 주십시오.')"); 
				script.println("history.back()"); // 이전 페이지로 사용자를 돌려보냄
				script.println("</script>");
			}else {
				UserDAO userDAO = new UserDAO(); // DAO인스턴스 생성
				int result = userDAO.join(user); // 로그인 페이지에서 입력받은 아이디와 패스워드를 파라메터로 받음
				if(result == -1){ // 데이터베이스 오류 (PRIMARY KEY 중복 -> 동일한 아이디가 존재함)
					PrintWriter script = response.getWriter(); // 스크립트 문장을 사용할수 있게함
					script.println("<script>");
					script.println("alert('동일한 아이디가 존재 합니다.')"); 
					script.println("history.back()"); // 이전 페이지로 사용자를 돌려보냄
					script.println("</script>");
				}else { // 회원가입 성공 -> main.jsp로 이동 (result가 -1이 아닌경우는 전부 회원가입 성공으로 판단)
					session.setAttribute("userID", user.getUserID()); // 회원가입 성공시 세션 생성
					PrintWriter script = response.getWriter(); // 스크립트 문장을 사용할수 있게함
					script.println("<script>");
					script.println("location.href = 'main.jsp'"); // 이전 페이지로 사용자를 돌려보냄
					script.println("</script>");
				}
			}
		}	
	%>
</body>
</html>