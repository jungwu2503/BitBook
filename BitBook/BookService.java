package bitBook;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookService {
	BookDAO bd;

	public BookService(){
		bd = new BookDAO();
	}
	
	//책 제목으로 검색
	public ArrayList<BookDTO> search(String title) throws FileNotFoundException, SQLException {		

		ArrayList<BookDTO> searchList = bd.searchTitle(title);
		return searchList;
	}
	
	public boolean borrow(int bookNumber,int memberNumber) throws FileNotFoundException, SQLException {	
		return bd.bookBorrow(bookNumber,memberNumber);
		
		
	}

	//대출한 책 리스트
	public ArrayList<BorrowDTO> borrowList(int memberNumber) throws FileNotFoundException, SQLException {
		
		ArrayList<BorrowDTO> list = bd.searchBorrowList(memberNumber);
		return list;
	}
	
	public boolean bookReuturn(int bookNumber) throws FileNotFoundException, SQLException {
		return bd.borrowUpdate(bookNumber);
		
	}

}
