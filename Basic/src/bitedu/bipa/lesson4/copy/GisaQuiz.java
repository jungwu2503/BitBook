package bitedu.bipa.lesson4.copy;

import java.util.*;

public class GisaQuiz {
	private GisaDAO dao;
	public GisaQuiz() {
		dao = new GisaDAO();		
	}

	public String solveQuiz(int num) {
		switch (num) {
			case 1:
				return solveQuiz1();
			case 2:
				return solveQuiz2();
			case 3:
				return solveQuiz3();
			case 4:
				return solveQuiz4();
		}
		return null;
	}
	
	public String solveQuiz1() {
		String answer = null;
		// 로직 처리(SQL)
		/*
		 *  select std_no
			from gisaTBL
			where loc_code = 'B' order by (kor+eng) desc, std_no asc limit 4, 1;
		 */
		StringBuffer sb = new StringBuffer("select std_no from gisaTBL ");
		sb.append("where loc_code = 'B' order by (kor+eng) desc, ");
		sb.append("std_no asc limit 4, 1");
		String sql = sb.toString();
		System.out.println(sql);
		int stdNo = dao.selectQuiz1(sql);
		answer = String.valueOf(stdNo);
		return answer;
	}
	
	public String solveQuiz2() {
		String answer = null;
		
		StringBuffer sb = new StringBuffer("select (kor+eng) from gisaTBL ");
		sb.append("where loc_code = 'B' order by (kor+eng) desc limit 1");
		String sql = sb.toString();
		System.out.println(sql);
		int value = dao.selectQuiz2(sql);
		answer = String.valueOf(value);
		
		return answer;
	}
	
	public String solveQuiz3() {
		String answer = null;
		
		/*
		 * select (total+score) as result from (select total, case mgr when "A" then 5
		 * when "B" then 10 else 15 end as score from gisaTBL where (eng+math)>=120) as t; -> 팀원분 query문
		 */
		StringBuffer sb = new StringBuffer("select total, mgr_code from gisaTBL ");
		sb.append("where (eng+math) >= 120");
		String sql = sb.toString();
		System.out.println(sql);
		answer = dao.selectQuiz3(sql);
		//sb.delete(0, sb.length());		
		
		//answer = String.valueOf(total);
		
		return answer;
	}
	
	public String solveQuiz4() {
		String answer = "";
		
		StringBuffer sb = new StringBuffer("select count(kor) from (select kor, case acc_code when \"A\" then 5 ");
		sb.append("when \"B\" then 10 end as score from gisatbl where acc_code = \"A\" ");
		sb.append("or acc_code = \"B\") as t where (kor+score) > 50");
		String sql = sb.toString();
		System.out.println(sql);
		int cnt = dao.selectQuiz2(sql);
		answer = String.valueOf(cnt);
		
		return answer;
	}		
}
