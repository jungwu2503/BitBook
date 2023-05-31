package bitBook;

import java.sql.*;

import BoardTest.ConnectionManager;

public class MemberDAO {

	public boolean insert(MemberDTO dto) throws SQLException {
		boolean flag = false;
		Connection conn = ConnectionManager.getConnection();
		String sql = ""; // insert 해주는 query 필요
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// memberNumber는 set해주지 않음
		pstmt.setString(1, dto.getId());
		pstmt.setString(2, dto.getPassword());
		pstmt.setString(3, dto.getName());
		pstmt.setString(4, dto.getPhone());
		pstmt.setString(5, dto.getAddress());
		pstmt.setString(6, dto.getEmail());
		pstmt.setString(7, "준회원");
		int affectedCount = pstmt.executeUpdate();
		if (affectedCount > 0) {
			flag = true;
		}
		ConnectionManager.closeConnection(null, pstmt, conn);
		
		return flag;
	}
	
	public boolean checkExistMember(MemberDTO dto) throws SQLException {
		boolean flag = false;
		Connection conn = ConnectionManager.getConnection();
		String sql = ""; // 존재하는 Member인지 찾는 쿼리
		PreparedStatement pstmt = conn.prepareStatement(sql);
		int affectedCount = pstmt.executeUpdate();
		if (affectedCount > 0) { // 존재함 
			flag = true; 
		}
		ConnectionManager.closeConnection(null, pstmt, conn);
		
		return false;
	}
	
	public boolean checkMember(String id, String password) throws SQLException {
		boolean flag = false;
		Connection conn = ConnectionManager.getConnection();
		String sql = ""; // ResultSet에 담을 id, password 불러오는 query
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			if (rs.getString(1).equals(id) && rs.getString(2).equals(password)) { // id, password 비교
				return true;
			}
		}
		
		ConnectionManager.closeConnection(null, pstmt, conn);
		
		return false;
	}
	
}
