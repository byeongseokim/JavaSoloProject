package ch20.oracle.sec07;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BoardUpdateExample {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "java", "12345");
			
			String sql = new StringBuilder()
			.append("UPDATE boards SET ")
			.append("btitle=?, ")
			.append("bcontent=?, ")
			.append("bfilename=?, ")
			.append("bfiledata=? ")
			.append("WHERE bno=?")
			.toString();
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "눈사람");
			pstmt.setString(2, "눈으로 만든 사람");
			pstmt.setString(3, "snowman.jpg");
			pstmt.setBlob(4, new FileInputStream("src/ch20/oracle/sec07/snowman.jpg"));
			pstmt.setInt(5, 1);
			
			int rows = pstmt.executeUpdate();
			System.out.println("수정된 행 수: " + rows);
			
			pstmt.close();				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}

	}

}
