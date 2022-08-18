package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public UserDAO() { // DB접속 객체 생성
		try {
			String dbURL = "jdbc:mysql://localhost:3306/BBS"; // 로컬 DB의 BBS데이터베이스 접속
			String dbID = "root";
			String dbPassword = "password";
			Class.forName("com.mysql.jdbc.Driver"); // MySQL드라이버 설정
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword); // DB 접속후 conn에 접속 정보 입력
		}catch(Exception e) {
			e.printStackTrace(); //오류 내용 출력
		}
	}
	
	public int login(String userID, String userPassword) {
		String SQL = "SELECT userPassword FROM USER WHERE userID = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID); // 인젝션 방어 기법
			rs = pstmt.executeQuery(); // 결과 입력
			if(rs.next()) { // 결과가 있다면 
				if(rs.getString(1).equals(userPassword)) { // 변수인 password와 일치한다면
					return 1; // 로그인 성공
				}else {
					return 0; // 비밀번호 불일치
				}
			}
			return -1; // 결과가 없다면
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -2; // 데이터베이스 오류
	}
	
	public int join(User user) { // User 클래스를 객체로 받는 join클래스 생성
		String SQL = "INSERT INTO USER VALUES (?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserID()); // 각 VALUE값에 매개변수로 넘어온 값을 넣어줌
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserGender());
			pstmt.setString(5, user.getUserEmail());
			return pstmt.executeUpdate(); // SQL의 실행 결과를 리턴
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터 베이스 오류
	}
}
