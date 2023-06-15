package bitedu.bipa.gisa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TestCenter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String path = "./data/Abc1115.txt";
		TestCenter tc = new TestCenter();
		try {
			tc.startTest();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void startTest() throws IOException {
		System.out.println("시험지를 배부합니다.");
		ArrayList<StudentDTO> data = null;
		data = this.readyData();
		System.out.println("시험을 시작합니다.");
		GisaQuiz quiz = new GisaQuiz(data);
		String answer = null;
		answer = quiz.solveQuiz1();
		System.out.println(answer);
		this.submitAnswer(1, answer);
//		answer = quiz.solveQuiz2();
//		this.submitAnswer(2, answer);
//		answer = quiz.solveQuiz3();
//		this.submitAnswer(3, answer);
//		answer = quiz.solveQuiz4();
//		this.submitAnswer(4, answer);
		System.out.println("답안지를 모두 제출합니다.");
		System.out.println("시험을 종료합니다.");
	}
	
	private ArrayList<StudentDTO> readyData() throws IOException {
		// TODO Auto-generated method stub
		ArrayList<StudentDTO> data = null;
		//파일 접속
		File file = new File("./data/Abc1115.csv");
//		if(file.exists()) {
//			System.out.println(file.length());
//		} else {
//			System.out.println("fails");
//		}
		//파일 한 라인 읽기
		FileReader fr = new FileReader(file);
		//라인 분리하기
		BufferedReader br = new BufferedReader(fr);
		
		String line = null;
		data = new ArrayList<StudentDTO>();
		StudentDTO dto = null;
		while((line=br.readLine())!=null) {
			//System.out.println(line);
			//하나의 라인에서 11개의 데이터 분리 
			String[] temp = line.split(",");
			int stdNo = Integer.parseInt(temp[0].trim());
			String email = temp[1].trim();
			int kor = Integer.parseInt(temp[2].trim());
			int eng = Integer.parseInt(temp[3].trim());
			int math = Integer.parseInt(temp[4].trim());
			int sci = Integer.parseInt(temp[5].trim());
			int hist = Integer.parseInt(temp[6].trim());
			int total = Integer.parseInt(temp[7].trim());
			String mgr = temp[8].trim();
			String acc = temp[9].trim();
			
			String loc = temp[10].trim();
			//DTO객체 만들기
			//리스트에 저장하기
			data.add(new StudentDTO(stdNo,email,kor,eng,math,sci,hist,total,mgr,acc,loc));
		}
		
		
		
		
		//위의 작업 1000번 반복
		br.close();
		fr.close();
		return data;
	}

	private void submitAnswer(int num, String answer) throws IOException {
		File file = new File("./data/Ans"+num+".txt");
		FileWriter fw = new FileWriter(file);
		PrintWriter pw = new PrintWriter(fw);
		pw.println(answer);		
		pw.close();
		fw.close();
	}

}
