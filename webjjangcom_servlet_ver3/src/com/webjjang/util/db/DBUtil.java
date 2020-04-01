package com.webjjang.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {

	private static final String driver
	="oracle.jdbc.driver.OracleDriver";
	
	//접속정보
	//객체에서 사용할 DB 정보 -> list(),view().. 다른 메서드에서 다 필요로하기 때문에
	
	private static final String url 
	= "jdbc:oracle:thin:@402-oracle:1521:orcl";
	private static final String id = "c##java09";
	private static final String pw = "java09";
	
	// 드라이버가 있는지 체크하는 변수
	//true - 드라이버가 있음, false - 드라이버가 없음
	private static boolean checkDriver = false;

	
	// 클래스가 맨처음 선언되어진 경우 실행이된다.
	static {  //static 초기화 블록
		// 1드라이버 확인
		
		try {
			Class.forName(driver);
			checkDriver = true;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			checkDriver = false;
			e.printStackTrace();
		}
	}
// 연결객체를 전달하는 메서드 -DBUtil.getConnection()
	public static Connection getConnection() throws SQLException {
		if(checkDriver)
			return DriverManager.getConnection(url, id, pw);
		throw new SQLException("드라이버가 존재하지 않습니다");
	}
	// DB처리하면서 사용한 객체 닫기
	public static void close
	(Connection con, PreparedStatement pstmt) 
			throws SQLException {
		con.close();
		pstmt.close();
	}
	public static void close
	(Connection con, PreparedStatement pstmt, ResultSet rs) 
			throws SQLException {
		close(con, pstmt);
		rs.close();
	}
}

