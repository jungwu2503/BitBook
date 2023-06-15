package bitedu.bipa.lesson2;

import java.sql.*;
import java.util.*;

public class DatabaseWork {
	public void insertData(ArrayList<StudentDTO> list) {
		// DB에 1000개의 DTO를 저장
		System.out.println(list.size());
		System.out.println(list.get(0));
		
		this.testConnection();
		try {
			//this.insert(list);
			insertWithBatchAndTransaction(list);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}				
		
		/*int i = 0;
		for (StudentDTO dto : list) {
			System.out.println(i + " " + list.get(i++));
		}*/
	}
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection con = null;
		String jdbcURL = "jdbc:mysql://localhost:3306/bitedu";
		String driver = "com.mysql.cj.jdbc.Driver";
		String id = "root";
		String password = "1234";
		
		Class.forName(driver);
		con = DriverManager.getConnection(jdbcURL, id, password);
		return con;
	}
	
	private void insert(ArrayList<StudentDTO> list) throws ClassNotFoundException, SQLException {
		Connection con = this.getConnection();
		// SQL 작성
		String sql = "insert into gisaTBL values (?,?,?,?,?,?,?,?,?,?,?)";
		// Query 전용 전송 통로 생성
		PreparedStatement pstmt = con.prepareStatement(sql);
		for (StudentDTO dto : list) {
			// 전송할 객체 자료
			//StudentDTO dto = list.get(0);
			
			// 통로를 통해 Query 실행
			pstmt.setInt(1, dto.getStdNo());
			pstmt.setString(2, dto.getEmail());
			pstmt.setInt(3, dto.getKor());
			pstmt.setInt(4, dto.getEng());
			pstmt.setInt(5, dto.getMath());
			pstmt.setInt(6, dto.getSci());
			pstmt.setInt(7, dto.getHist());
			pstmt.setInt(8, dto.getTotal());
			pstmt.setString(9, dto.getMgrCode());
			pstmt.setString(10, dto.getAccCode());
			pstmt.setString(11, dto.getLocCode());
			pstmt.executeUpdate();
		}
		// 통로 정리
		pstmt.close();
		// 커넥션 정리
		con.close();
		System.out.println("1개 자료 입력 완료");
	}
	
	// 한꺼번에 모아서 DB에 입력 (batch 기능 사용)
	// 만약 1000개의 데이터가 입력되지 않는다면 에러처리 해야 함 (트랜잭션)
	private void insertWithBatchAndTransaction(ArrayList<StudentDTO> list) throws ClassNotFoundException, SQLException {
		// batch와 transaction을 적용하는 코드 작성
		Connection con = this.getConnection();
		// SQL 작성
		String sql = "insert into gisaTBL values (?,?,?,?,?,?,?,?,?,?,?)";
		// Query 전용 전송 통로 생성
		PreparedStatement pstmt = con.prepareStatement(sql);
		int count = 0;
		con.setAutoCommit(false);
		for (StudentDTO dto : list) {
			// 전송할 객체 자료
			//StudentDTO dto = list.get(0);
			
			count++;
			// 통로를 통해 Query 실행
			pstmt.setInt(1, dto.getStdNo());
			pstmt.setString(2, dto.getEmail());
			pstmt.setInt(3, dto.getKor());
			pstmt.setInt(4, dto.getEng());
			pstmt.setInt(5, dto.getMath());
			pstmt.setInt(6, dto.getSci());
			pstmt.setInt(7, dto.getHist());
			pstmt.setInt(8, dto.getTotal());
			pstmt.setString(9, dto.getMgrCode());
			pstmt.setString(10, dto.getAccCode());
			pstmt.setString(11, dto.getLocCode());
			pstmt.addBatch();
			pstmt.clearBatch();
			
			/*if (count % 1000 == 0) {
				pstmt.executeBatch();
				count = 0;
			}*/
			
			if (list.size() <= 1000) {
				pstmt.executeBatch();
				pstmt.clearBatch();
				con.commit();
			} else {
				con.rollback();
				pstmt.close();
				con.close();
				System.out.println("Rollback 됨");
				return;
			}
		}
		
		pstmt.executeBatch();
		con.setAutoCommit(true);
		
		// 통로 정리
		pstmt.close();
		// 커넥션 정리
		con.close();
		System.out.println(count + "개 자료 입력 완료");
	}
	
	private void testConnection() {
		Connection con = null;
		try {
			con = this.getConnection();
			if (con != null) {
				System.out.println("connected");
				con.close();
			} else {
				System.out.println("fails");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<StudentDTO> getStudentData() throws ClassNotFoundException, SQLException {
		ArrayList<StudentDTO> data = new ArrayList<StudentDTO>();
		// DB의 데이터를 가져와서 List로 변경
		
		// Connection 생성
		Connection con = this.getConnection();
		// Query 생성
		String sql = "select * from gisaTBL";
		// Query 통로 생성
		Statement stmt = con.createStatement();
		// Query 실행
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			// Query 결과 받고 처리하기 (List로 변경 작업)
			int stdNo = rs.getInt("std_no");
			String email = rs.getString("email");
			int kor = rs.getInt("kor");
			int eng = rs.getInt("eng");
			int math = rs.getInt("math");
			int sci = rs.getInt("sci");
			int hist = rs.getInt("hist");
			int total = rs.getInt("total");
			String mgrCode = rs.getString("mgr_code");
			String accCode = rs.getString("acc_code");
			String locCode = rs.getString("loc_code");
			data.add(new StudentDTO(stdNo,email,kor,eng,math,sci,hist,total,mgrCode,accCode,locCode));		
		}
		// 통로 정리
		stmt.close();
		// Connection 정리
		con.close();
		
		return data;
	}	
}
