<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bbs.BbsDAO" %>   <!-- BbsDAO 임포트 -->
<%@ page import="bbs.Bbs" %>
<%@ page import="java.io.PrintWriter" %> <!-- 자바 스크립트 문장 작성위해 필요 -->
<% request.setCharacterEncoding("UTF-8"); %> <!-- 건너오는 모든 데이터를 UTF-8으로 받을수 있게함 -->
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
		if(userID == null){ // 로그인 되어있지 않은경우 로그인을 유도
			PrintWriter script = response.getWriter(); // 스크립트 문장을 사용할수 있게함
			script.println("<script>");
			script.println("alert('로그인을 하세요.')"); 
			script.println("location.href = 'login.jsp'"); // 이전 페이지로 사용자를 돌려보냄
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
		} else {
			if(request.getParameter("bbsTitle") == null || request.getParameter("bbsContent") == null
				|| request.getParameter("bbsTitle") == "" || request.getParameter("bbsContent") == "") { // 제목과 글 내용에 누락이 있는지 확인
				PrintWriter script = response.getWriter(); // 스크립트 문장을 사용할수 있게함
				script.println("<script>");
				script.println("alert('입력이 안된 사항이 있습니다.')"); 
				script.println("history.back()"); // 이전 페이지로 사용자를 돌려보냄
				script.println("</script>");	
			} else {
				BbsDAO bbsDAO = new BbsDAO(); // DAO인스턴스 생성
				int result = bbsDAO.update(bbsID, request.getParameter("bbsTitle"), request.getParameter("bbsContent")); // 글수정 페이지에서 글제목, 글내용을 파라메터로 받음 
				if(result == -1){ // 데이터베이스 오류
					PrintWriter script = response.getWriter(); // 스크립트 문장을 사용할수 있게함
					script.println("<script>");
					script.println("alert('글수정에 실패하였습니다.')"); 
					script.println("history.back()"); // 이전 페이지로 사용자를 돌려보냄
					script.println("</script>");
				}else { // 글수정 성공
					PrintWriter script = response.getWriter(); // 스크립트 문장을 사용할수 있게함
					script.println("<script>");
					script.println("location.href = 'bbs.jsp'"); // 게시글 페이지로 이동
					script.println("</script>");
				}
				
			}
		}
			
	%>
</body>
</html>