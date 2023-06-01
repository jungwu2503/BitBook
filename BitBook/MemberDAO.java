package bitBook;

import java.io.FileNotFoundException;
import java.sql.*;


public class MemberDAO {

	public boolean insert(MemberDTO dto) throws SQLException, FileNotFoundException {
		boolean flag = false;
		Connection conn = ConnectionManager.getConnection();
		String sql = "insert into member (id, password, name, phone, address, email) values (?, ?, ?, ?, ?, ?);"; // insert 해주는 query 필요
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// memberNumber는 set해주지 않음
		pstmt.setString(1, dto.getId());
		pstmt.setString(2, dto.getPassword());
		pstmt.setString(3, dto.getName());
		pstmt.setString(4, dto.getPhone());
		pstmt.setString(5, dto.getAddress());
		pstmt.setString(6, dto.getEmail());
		int affectedCount = pstmt.executeUpdate();
		
		if (affectedCount > 0) {
			flag = true;
		}
		ConnectionManager.closeConnection(null, pstmt, conn);
		
		return flag;
	}
	
	public boolean checkExistMember(MemberDTO dto) throws SQLException, FileNotFoundException {
		boolean flag = false;
		Connection conn = ConnectionManager.getConnection();
		String sql = "select * from member where id=?;"; // 존재하는 Member인지 찾는 쿼리
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, dto.getId());
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			flag = true;
		}
		ConnectionManager.closeConnection(null, pstmt, conn);
		return flag;
	}
	
	public int checkMember(String id, String password) throws FileNotFoundException {
		Connection conn = ConnectionManager.getConnection();
		int memberNumber = 0;
		
		String sql = "select memberNumber from member where id=? and password=?;"; // ResultSet에 담을 id, password 불러오는 query
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt= conn.prepareStatement(sql);;
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				memberNumber = rs.getInt("memberNumber");
			}
			return memberNumber;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(rs, pstmt, conn);
		}
		return memberNumber;
	}
}
	
