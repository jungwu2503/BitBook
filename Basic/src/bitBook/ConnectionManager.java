package bitBook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class ConnectionManager {
	
	public static void closeConnection(ResultSet rs, Statement stmt, Connection con) {
		
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null;			
		}
		if(stmt!=null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			stmt=null;
		}
		
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			con = null;
		}
		
	}
	public static Connection getConnection() throws FileNotFoundException {
		Connection con = null;
		
		//Properties class 이용하여 값 설정
		String resource = "./data/db/properties";
		Properties props = null;
		try {
			InputStream is = new FileInputStream(new File(resource));
			props = new Properties();
			props.load(is);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String jdbcURL = props.getProperty("jdbcURL");
		String driver = props.getProperty("driver");
		String id = props.getProperty("id");
		String password = props.getProperty("password");

		
		//Stream 이용하여 값 설정
		File file = new File("./data/db/properties");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		ArrayList<String> list = new ArrayList<String>();
		try {
			String line = null;
			while((line=br.readLine()) != null) {
				String[] temp = line.split("=");
				list.add(temp[1]);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		String jdbcURL = list.get(0);
//		String driver = list.get(1);
//		String id = list.get(2);
//		String password = list.get(3);
		
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(jdbcURL,id,password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	
		return con;
	}
}
