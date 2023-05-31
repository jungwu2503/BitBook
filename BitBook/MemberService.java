package bitBook;

import java.sql.SQLException;

public class MemberService {
	private MemberDAO memberDAO;
	private MemberDTO memberDTO;
	
	public MemberService() {
		memberDAO = new MemberDAO();
	}
	
	public boolean register(MemberDTO dto) throws SQLException { // 회원가입
		boolean isExistMember = memberDAO.checkExistMember(dto); // 존재하는 유저인가
		
		if (isExistMember) {
			System.out.println("이미 존재하는 아이디입니다.");
			return false; // 회원가입 안했으므로 false 리턴
		}
		
		memberDAO.insert(dto);
		return true; // 회원가입 성공
	}
	
	public boolean login(MemberDTO dto) { // 로그인
		String id = dto.getId();
		String password = dto.getPassword();
		if (memberDAO.checkMember(id, password)) {
			System.out.println("로그인 완료");
			return true;
		}
		
		System.out.println("아이디 패스워드를 다시 확인해주세요"); // 로그인 실패
		return false;
	}
	
}