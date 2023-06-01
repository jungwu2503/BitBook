package bitBook;

import java.util.ArrayList;

public class BookService {
	BookDAO bd;

	public BookService(){
		bd = new BookDAO();
	}
	
	//책 제목으로 검색
	public ArrayList<BookDTO> search(String title) {		

		ArrayList<BookDTO> searchList = bd.searchTitle(title);
		return searchList;
	}
	
	public void borrow(int bookNumber) {	
		bd.bookBorrow(bookNumber);
		
		//유효성검사(책이 대여가능한 상태인지)
	}

	//대출한 책 리스트
	public ArrayList<BorrowDTO> borrowList(int memberNumber) {
		
		ArrayList<BorrowDTO> list = bd.searchBorrowList(memberNumber);
		return list;
	}
	
	public void bookReuturn(int bookNumber) {
		bd.borrowUpdate(bookNumber);
	}
	
	
	public boolean checkEndDate() {
		return false;
		
	}

}
