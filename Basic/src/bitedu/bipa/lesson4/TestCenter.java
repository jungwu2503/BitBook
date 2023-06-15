package bitedu.bipa.lesson4;

import java.io.*;
import java.util.*;

public class TestCenter {

	public static void main(String[] args) {
		TestCenter tc = new TestCenter();
		try {
			tc.startTest();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void startTest() throws IOException {
		System.out.println("시험지를 배부합니다.");
		System.out.println("시험을 시작합니다.");
		GisaQuiz quiz = new GisaQuiz();
		String answer = null;
		answer = quiz.solveQuiz2();
		System.out.println(answer);
		this.submitAnswer(2, answer);
		System.out.println("답안지를 모두 제출합니다.");
		System.out.println("시험을 종료합니다.");
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
