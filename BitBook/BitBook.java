package bitBook;

import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BitBook {

	private Scanner sc;
	int memberNumber;
	BookService bs;
	MemberService ms;
	public BitBook() {
		sc= new Scanner(System.in);
		bs = new BookService();
		ms = new MemberService();
	}
	
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
	
	public void executeService() throws FileNotFoundException, SQLException {;
		System.out.println("1.로그인 2.회원가입");
		System.out.print("메뉴선택 : ");
		String menu = sc.next();
	
		switch(menu) {
			case "1" -> login();
			case "2" -> register();
		}
		
	}
	public void executeMenu() throws FileNotFoundException, SQLException {
		String menu = "0";
		
		while(!(menu.equals("3"))){
			System.out.println("1.검색 2.대출리스트 3.로그아웃");
			System.out.print("메뉴선택 : ");
			menu = sc.next();
			switch(menu) {
				case "1" -> search();
				case "2" -> borrowList(memberNumber);
			}
			
		}
		sc.close();
	}
	
	public void login() throws FileNotFoundException, SQLException {
		System.out.print("아이디 : ");	
		String id = sc.next();
		System.out.print("패스워드: ");
		String pw = sc.next();
		
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
		System.out.print("아이디 : ");
		md.setId(sc.next());
		System.out.print("패스워드 : ");
		md.setPassword(sc.next());
		System.out.print("이름 : ");
		md.setName(sc.next());
		System.out.print("전화번호 : ");
		md.setPhone(sc.next());
		System.out.print("주소 : ");
		md.setAddress(sc.next());
		System.out.print("이메일 : ");
		md.setEmail(sc.next());
		ms.register(md);
		System.out.println("회원가입이 완료되었습니다. ");
		//if 성공시 실패시
		executeService();
	}
	
	public void search() throws FileNotFoundException, SQLException {
		int bookNumber;
		System.out.print("책 제목을 입력해주세요 : ");
		String bookTitle = sc.next();
		
		
		System.out.println("┌────────────────────책 검색─────────────────────┐");
		System.out.printf("%-9s%-30s%-5s%s\n", " 책 번호", "제목", "상태","");
		System.out.println("└──────────────────────────────────────────────┘");
	
		ArrayList<BookDTO> bookList = bs.search(bookTitle);
		for(BookDTO dto: bookList) {
			String stateStr = "대여가능";
			if(dto.getState().equals("2")) {
				stateStr = "대여중";
			}
			System.out.printf("%s%-9s%-30s%-6s\n", "  ", dto.getBookNumber() ,dto.getTitle(), stateStr);
		}
		
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
	
	public void borrow(int bookNumber) throws FileNotFoundException, SQLException {
		
		if(bs.borrow(bookNumber,memberNumber)) {
			System.out.println("대출이 완료되었습니다");
			
		}
		else {
			System.out.println("대출 실패");
		}
	}
	
	public void borrowList(int memberNumber) throws FileNotFoundException, SQLException {
		
		int bookNumber;
		ArrayList<BorrowDTO> list = bs.borrowList(memberNumber);
		System.out.println("┌────────────────────대출 리스트──────────────────┐");
		System.out.printf("%-9s%-30s%-5s\n", "책 번호", "빌린날짜", "반납여부");
		System.out.println("└──────────────────────────────────────────────┘");
		for(BorrowDTO dto: list) {
			String stateStr = "반납완료";
			if(dto.getState().equals("1")) {
				stateStr = "대여중";
			}
			System.out.printf("%s%-9s%-30s%-6s\n", "  ", dto.getBookNumber() ,dto.getBorrowDate() , stateStr);
		}
		
		
		
		System.out.println("1.반납 2.취소");
		System.out.print("메뉴 선택 : ");
		String menu = sc.next();

		if(menu.equals("1")) {
			System.out.println("반납할 책 번호를 입력해주세요 : ");
			System.out.print("책 번호 : ");
			bookNumber = sc.nextInt();
			bookReuturn(bookNumber);
		}
	
		

	}
	public void bookReuturn(int bookNumber) throws FileNotFoundException, SQLException {
		if(bs.bookReuturn(bookNumber)) {
			System.out.println("해당 책이 반납 되었습니다.");	
		}
		else{
			System.out.println("반납실패");
		}
		
	}
	


	
}


