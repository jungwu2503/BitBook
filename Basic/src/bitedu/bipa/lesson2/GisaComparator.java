package bitedu.bipa.lesson2;

import java.util.*;

public class GisaComparator implements Comparator<StudentDTO> {

	@Override
	public int compare(StudentDTO o1, StudentDTO o2) {
		// TODO Auto-generated method stub
		int order = 0;
		order = (o2.getKor()+o2.getEng()) - (o1.getKor()+o1.getEng());
		if(order==0) {
			//order = o1.getStdNo() - o2.getStdNo();
			//이메일을 내림차순으로 정렬하는 정렬 코드 작성
			order = o2.getEmail().compareTo(o1.getEmail());
		}	
		return order;
	}
}