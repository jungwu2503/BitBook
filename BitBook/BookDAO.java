package bitBook;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookDAO {


	//책 이름으로 조회
	public ArrayList<BookDTO> searchTitle(String title) throws FileNotFoundException, SQLException {
		Connection conn = ConnectionManager.getConnection();
		ArrayList<BookDTO> list = new ArrayList<BookDTO>();
		
		String sql = "select * from book where title like ? and state!=3;"; // insert 해주는 query 필요
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, "%"+title+"%");
		ResultSet rs = pstmt.executeQuery();

		while(rs.next()) {
			BookDTO dto = new BookDTO();
			dto.setBookNumber(rs.getInt(1));
			dto.setTitle(rs.getString(2));
			dto.setGrade(rs.getInt(3));
			dto.setState(rs.getString(4));
			list.add(dto);
		}
		
		ConnectionManager.closeConnection(null, pstmt, conn);
		
		return list;
	}
	
	
	public boolean bookBorrow(int bookNumber,int memberNumber) throws FileNotFoundException, SQLException {
		Connection conn = ConnectionManager.getConnection();

		conn.setAutoCommit(false);
		int state = 2;
		
		String sql = "select count(memberNumber) as count from borrow where memberNumber=? and state=1;";
	
		String checkSQL = "select * from book where bookNumber = ?; ";
		String insertSQL = "insert into borrow (memberNumber, bookNumber, borrowDate, state) values (?, ?, CURRENT_TIME, ?);"; // insert 해주는 query 필요
		String updateSQL = "update book set state=? where bookNumber=? and state=1;";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, memberNumber);
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			System.out.println(rs.getInt(1));
			if(rs.getInt(1) > 1) {
				return false;
			}
		}
		
		pstmt = conn.prepareStatement(checkSQL);
		pstmt.setInt(1, bookNumber);
		rs = pstmt.executeQuery();
		if(!rs.next()) {
			return false;
		}
		
		pstmt = conn.prepareStatement(insertSQL);
		pstmt.setInt(1, memberNumber);
		pstmt.setInt(2, bookNumber);
		pstmt.setString(3, "1");
		int rs1 = pstmt.executeUpdate();
		if(rs1 < 1) {
			conn.rollback();
			return false;
		}
		pstmt = conn.prepareStatement(updateSQL);
		pstmt.setInt(1, state);
		pstmt.setInt(2, bookNumber);
		
		int rs2 = pstmt.executeUpdate();
		if(rs2 < 1) {
			conn.rollback();
			return false;
		}
		conn.commit();
		conn.setAutoCommit(true);
		ConnectionManager.closeConnection(null, pstmt, conn);
		return true;
		
	}
	
	public ArrayList<BorrowDTO> searchBorrowList(int memberNumber) throws SQLException, FileNotFoundException{
		Connection conn = ConnectionManager.getConnection();
		ArrayList<BorrowDTO> list = new ArrayList<BorrowDTO>();
		
		String sql = "select * from borrow where memberNumber = ?;"; // insert 해주는 query 필요
		
	
		
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, memberNumber);
		ResultSet rs = pstmt.executeQuery();

		while(rs.next()) {
			BorrowDTO dto = new BorrowDTO();
			dto.setMemberNumber(rs.getInt(1));
			dto.setBookNumber(rs.getInt(2));
			dto.setBorrowDate(rs.getDate(3));
			dto.setState(rs.getString(4));
			list.add(dto);
		}
		
		ConnectionManager.closeConnection(rs, pstmt, conn);
		
		return list;
		
	}
	
	public boolean borrowUpdate(int bookNumber) throws SQLException, FileNotFoundException {
		
		Connection conn = ConnectionManager.getConnection();
		conn.setAutoCommit(false);
		int borrowState = 2;
		int bookState = 1;
		
		String updateSQL1 = "update borrow set state=? where bookNumber=? and state=1;";
		String updateSQL2 = "update book set state=? where bookNumber=? and state=2;";
		
		
		PreparedStatement pstmt = conn.prepareStatement(updateSQL1);
		pstmt.setInt(1, borrowState);
		pstmt.setInt(2, bookNumber);
		int rs1 = pstmt.executeUpdate();
		
		if(rs1 < 1) {
			
			conn.rollback();
			return false;
		}
		
		pstmt = conn.prepareStatement(updateSQL2);
		pstmt.setInt(1, bookState);
		pstmt.setInt(2, bookNumber);
		
		int rs2 = pstmt.executeUpdate();
		
		if(rs2 < 1) {
			
			conn.rollback();
			return false;
		}
		
		conn.commit();
		conn.setAutoCommit(true);
		ConnectionManager.closeConnection(null, pstmt, conn);
		
		return true;
	}
	
}
