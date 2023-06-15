package bitedu.bipa.lesson4.copy;

import java.sql.*;

public class GisaDAO {
	// DB관 작업 전용 클래스
	
	// 1번 문제
	public int selectQuiz1(String sql) {
		int stdNo = 0;
		Connection con;
		try {
			con = ConnectionManager.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				stdNo = rs.getInt(1);
			}
			ConnectionManager.closeConnection(rs, stmt, con);
		} catch (SQLException se) {
			se.printStackTrace();
		}
		
		return stdNo;
	}
	
	// 2번 문제
	public int selectQuiz2(String sql) {
		int value = 0;
		Connection con;
		try {
			con = ConnectionManager.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				value = rs.getInt(1);
			}
			ConnectionManager.closeConnection(rs, stmt, con);
		} catch (SQLException se) {
			se.printStackTrace();
		}
		
		return value;
	}
	
	// 3번 문제
	public String selectQuiz3(String sql) {
		int total = 0;
		String mgrCode;
		String answer = "";
		Connection con;
		try {
			con = ConnectionManager.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				total = rs.getInt(1);
				mgrCode = rs.getString(2);
				if (mgrCode.equals("A")) total += 5;
				else if (mgrCode.equals("B")) total += 15;
				else if (mgrCode.equals("C")) total += 20;
				answer += total + "\n";
				System.out.println(answer);
			}
			ConnectionManager.closeConnection(rs, stmt, con);
		} catch (SQLException se) {
			se.printStackTrace();
		}
		
		return answer;
	}
	
	// 4번 문제
	public int selectQuiz4(String sql) {
		int cnt = 0;
		Connection con;
		try {
			con = ConnectionManager.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				cnt = rs.getInt(1);
			}
			ConnectionManager.closeConnection(rs, stmt, con);
		} catch (SQLException se) {
			se.printStackTrace();
		}
		
		return cnt;
	}
}
