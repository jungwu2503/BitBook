package bitBook;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class MemberService {
	private MemberDAO memberDAO;
	
	public MemberService() {
		memberDAO = new MemberDAO();
	}
	
	public boolean register(MemberDTO dto) throws SQLException, FileNotFoundException { // 회원가입
		boolean isExistMember = memberDAO.checkExistMember(dto); // 존재하는 유저인가	

		if (isExistMember) {
			System.out.println("이미 존재하는 아이디입니다.");
			return false; // 회원가입 안했으므로 false 리턴
		}
		
		memberDAO.insert(dto);
		return true; // 회원가입 성공
	}
	
	public int login(String id, String pw) throws FileNotFoundException, SQLException { // 로그인
//		int memberNumber = memberDAO.checkMember(id, pw);
		int memberNumber = 10;
		return memberNumber;
	}
	
}