package ch20.oracle.sec09.exam01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSelectExample {
	public static void main(String[] args) {

		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "java", "12345");

			String sql = "" + "SELECT userid, username, userpassword, userage, useremail " + "FROM users "
					+ "WHERE userid=?";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "winter");

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.setUserID(rs.getString("userid"));
				user.setUserName(rs.getString("username"));
				user.setUserPassword(rs.getString("userpassword"));
				user.setUserAge(rs.getInt(4));
				user.setUserEmail(rs.getString(5));
				System.out.println(user);
			} else {
				System.out.println("사용자 아이디가 존재하지 않음");
			}
			rs.close();

			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO: handle exception
				}
			}
		}
	}
}
