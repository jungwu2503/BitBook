package bitBook;

import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BitBook {

	MemberDTO memberDTO;
	int memberNumber;
	BookService bs = new BookService();
	public static Scanner sc;
	
	public static void main(String[] args) {
		BitBook bitBook = new BitBook();
		try {
			bitBook.executeService();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void executeService() throws FileNotFoundException, SQLException {
		sc = new Scanner(System.in);
		System.out.println("1.로그인 2.회원가입");
		System.out.print("메뉴선택 : ");
		String menu = sc.nextLine();
	
		switch(menu) {
			case "1" -> login();
			case "2" -> register();
		}
		
	}
	public void executeMenu() {
		String menu = "0";
		
		while(!(menu.equals("3"))){
			System.out.println("1.검색 2.대출리스트 3.로그아웃");
			System.out.print("메뉴선택 : ");
			menu = sc.next();
			switch(menu) {
				case "1" -> search();
				case "2" -> BorrowList(memberNumber);
			}
			
		}
		sc.close();
	}
	
	public void login() throws FileNotFoundException, SQLException {
		System.out.print("아이디 : ");	
		String id = sc.next();
		System.out.print("패스워드: ");
		String pw = sc.next();
		
		MemberService ms = new MemberService();
		memberNumber = ms.login(id,pw);
		if(memberNumber > 0 ) {
			System.out.println("로그인 성공");
			executeMenu();
		}
		else {
			System.out.println("로그인 실패(아이디 또는 비밀번호가 맞지 않습니다. 다시 입력해주세요.)");
			login();
		}
		
	}
	
	public void register() throws FileNotFoundException, SQLException {
		MemberDTO md = new MemberDTO();
		MemberService ms = new MemberService();
		System.out.print("아이디 : ");
		md.setName(sc.next());
		System.out.print("패스워드 : ");
		md.setName(sc.next());
		System.out.print("이름 : ");
		md.setName(sc.next());
		System.out.print("전화번호 : ");
		md.setName(sc.next());
		System.out.print("주소 : ");
		md.setName(sc.next());
		System.out.print("이메일 : ");
		md.setName(sc.next());
		ms.register(md);
		
		//if 성공시 실패시
		executeService();
	}
	
	public void search() {
		int bookNumber;
		System.out.print("책 제목을 입력해주세요 : ");
		String bookTitle = sc.next();
		
		
		ArrayList<BookDTO> bookList = bs.search(bookTitle);
		
		System.out.println("======================책 검색=====================");
		System.out.println("  번호\t\t제목\t\t상태");
		System.out.println("================================================");
		
		// bookList 출력
		
		
		System.out.println("1.대출 2.취소");
		System.out.print("메뉴 선택 : ");
		String menu = sc.next();

		switch(menu) {
		case "1" -> {
			System.out.println("대출할 책의 번호를 입력해주세요: ");
			System.out.print("책 번호 : ");
			bookNumber = sc.nextInt();
			borrow(bookNumber);
		}
		case "2" -> executeMenu();
		}
		
		
	}
	
	public void borrow(int bookNumber) {
		bs.borrow(bookNumber);
		System.out.println("대출이 완료되었습니다");
	}
	
	public void BorrowList(int memberNumber) {
		int bookNumber;
		ArrayList<BorrowDTO> list = bs.borrowList(memberNumber);
		
//		private int memberNumber;
//		private int bookNumber;
//		private Date borrowDate;
//		private String state;
		
		//대출한 책 출력
		System.out.println("");
		
		System.out.println("1.반납 2.취소");
		System.out.print("메뉴 선택 : ");
		String menu = sc.nextLine();

		switch(menu) {
		case "1" -> {
			System.out.println("반납할 책 번호를 입력해주세요 : ");
			System.out.print("책 번호 : ");
			bookNumber = sc.nextInt();
			bookReuturn(bookNumber);
			
		}
		case "2" -> executeMenu();
		}
		

	}
	public void bookReuturn(int bookNumber) {
		
		executeMenu();
	}
	

	
//	public boolean select(String str) {
//	
//	}
	
}


