package bitedu.bipa.lesson2;

import java.util.*;

public class GisaQuiz {
	private ArrayList<StudentDTO> dataList;
	
	public GisaQuiz(ArrayList<StudentDTO> dataList) {
		this.dataList = dataList;
	}
	
	public String solveQuiz1() {
		String answer = null;
		ArrayList<StudentDTO> q1Data = new ArrayList<StudentDTO>();
		for(StudentDTO dto : dataList) {
			if(dto.getLocCode().equals("B")) {
				q1Data.add(dto);
			}
		}
		Collections.sort(q1Data, new GisaComparator());
		for(int i=0;i<10;i++) {
			System.out.printf("%d %d %s \n",q1Data.get(i).getStdNo(),q1Data.get(i).getKor()+q1Data.get(i).getEng(),q1Data.get(i).getEmail());
		}
		
		answer = String.valueOf(q1Data.get(4).getStdNo());
		return answer;
	}
	
	public String solveQuiz2() {
		String answer = null;
		
		return answer;
	}
	
	public String solveQuiz3() {
		String answer = null;
		
		return answer;
	}
	
	public String solveQuiz4() {
		String answer = null;
		
		return answer;
	}
		
}
