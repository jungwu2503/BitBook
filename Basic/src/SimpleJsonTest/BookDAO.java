package SimpleJsonTest;

import java.sql.*;
import java.util.*;

public class BookDAO {
	
	public ArrayList<BookDTO> getDTOList(){
		Connection conn = ConnectionManager.getConnection();
		ArrayList<BookDTO> list = new ArrayList<BookDTO>();
		ResultSet rs = null;
		
		String sql = "select * from bookTable";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
		
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// 로직 안넣는다. DB의 내용을 로컬 데이터셋으로 저장하는 것이 목적.
				BookDTO item = new BookDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				
				list.add(item);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(rs, pstmt, conn);
		}
		
		return list;
	}
	
}
