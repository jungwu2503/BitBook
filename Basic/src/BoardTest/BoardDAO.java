package BoardTest;

import java.sql.*;
import java.util.*;

public class BoardDAO {
	public boolean insert(BoardDTO item) throws SQLException {
		boolean flag = false;
		Connection conn = ConnectionManager.getConnection();
		String sql = "insert into boards2(btitle,bcontent,bwriter) values (?,?,?) ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, item.getBtitle());
		pstmt.setString(2, item.getBcontent());
		pstmt.setString(3, item.getBwriter());
		int affectedCount = pstmt.executeUpdate();
		if (affectedCount > 0) {
			flag = true;
		}
		ConnectionManager.closeConnection(null, pstmt, conn);
		
		return flag;
	}
	
	public BoardDTO select(int bno) throws SQLException {
		BoardDTO item = null;
		Connection conn = ConnectionManager.getConnection();
		String sql = "select * from boards2 where bno = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, bno);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			// 로직 안넣는다. DB의 내용을 로컬 데이터셋으로 저장하는 것이 목적.
			item = new BoardDTO(bno, rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4));
		}
		ConnectionManager.closeConnection(rs, pstmt, conn);
		
		return item;
	}
	
	public boolean delete(String sql) throws SQLException {
		boolean flag = false;
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		int affectedCount = pstmt.executeUpdate();
		if (affectedCount > 0) {
			flag = true;
		}
		ConnectionManager.closeConnection(null, pstmt, conn);
		
		return flag;
	}
	
	public ArrayList<BoardDTO> selectAll() throws SQLException {
		ArrayList<BoardDTO> list = null;
		BoardDTO item = null;
		Connection conn = ConnectionManager.getConnection();
		String sql = "select * from boards2";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		list = new ArrayList<BoardDTO>();
		BoardDTO item2 = null;
		while (rs.next()) {
			// 로직 안넣는다. DB의 내용을 로컬 데이터셋으로 저장하는 것이 목적.
			item2 = new BoardDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5));
			list.add(item2);
		}
		ConnectionManager.closeConnection(rs, pstmt, conn);
		
		return list;
	}
	
	public boolean update(BoardDTO item) throws SQLException {
		boolean flag = false;
		Connection conn = ConnectionManager.getConnection();
		String sql = "update boards2 set btitle = ?, bcontent = ?, bwriter = ? where bno = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, item.getBtitle());
		pstmt.setString(2, item.getBcontent());
		pstmt.setString(3, item.getBwriter());
		int affectedCount = pstmt.executeUpdate();
		if (affectedCount > 0) {
			flag = true;
		}
		ConnectionManager.closeConnection(null, pstmt, conn);
		
		return flag;
	}
}
