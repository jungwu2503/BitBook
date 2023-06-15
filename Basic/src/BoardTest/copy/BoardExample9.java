package BoardTest.copy;

import java.sql.*;
import java.util.*;

public class BoardExample9 {
	//Field
	private Connection conn;
	private BoardDAO dao;
	
	//Constructor
	public BoardExample9() {
		conn = ConnectionManager.getConnection();
		dao = new BoardDAO(conn);
		dao.list();
	}

	public static void main(String[] args) {
		BoardExample9 boardExample = new BoardExample9();
	}
}