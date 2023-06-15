package BoardTest;

import java.sql.*;
import java.util.*;
import java.io.*;

public class ConnectionManager {
	
	public static void closeConnection(ResultSet rs, Statement stmt, Connection con) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null;
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			stmt = null;
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			con = null;
		}
	}
	
	public static Connection getConnection() {
		Connection con = null;
		// 환경정보는 값이 바뀌기 때문에 밖에서 결정되어야 함
		
		// Properties class 이용해서 값 설정
		/*Properties properties = new Properties();
		try {
			properties.load(new FileReader("C:\\Users\\jungw\\eclipse-workspace\\Basic\\data\\db\\properties"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		String jdbcURL = properties.getProperty("jdbcURL");
		String driver = properties.getProperty("driver");
		String id = properties.getProperty("id");
		String password = properties.getProperty("password");*/
		
		/*for (int i = 0; i < properties.size(); i++) { ---x---
			list.add(properties.values().toString());
		}*/
		
		// Stream 이용하여 값 설정
		File file = new File("./data/db/properties");
		FileReader fr;
		BufferedReader br;
		String line;
		StringTokenizer st;
		ArrayList<String> list = new ArrayList<String>();
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
		
			while ((line = br.readLine()) != null) {
				st = new StringTokenizer(line, "=");
				st.nextToken();
				list.add(st.nextToken());
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String jdbcURL = list.get(0);
		String driver = list.get(1);
		String id = list.get(2);
		String password = list.get(3);
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(jdbcURL, id, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
}
