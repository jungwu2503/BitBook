package BoardTest;

import java.sql.*;
import java.util.*;

public class BoardService {
	private BoardDAO dao;
	
	public BoardService() {
		dao = new BoardDAO();
	}
	
	public void registItem(BoardDTO item) {
		try {
			dao.insert(item);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public BoardDTO readItem(int bno) {
		BoardDTO item = null;
		try {
			item = dao.select(bno);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return item;
	}
	
	public boolean removeAll() {
		boolean flag = false;
		String sql = "delete from boards";
		try {
			flag = dao.delete(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return flag;
	}
	
	public boolean remove(int bno) {
		boolean flag = false;
		String sql = "delete from boards where bno = " + bno;
		try {
			flag = dao.delete(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	public ArrayList<BoardDTO> readAll() {
		ArrayList<BoardDTO> list = null;
		try {
			list = dao.selectAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public boolean modify(BoardDTO item) {
		boolean flag = false;
		try {
			flag = dao.update(item);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
}
