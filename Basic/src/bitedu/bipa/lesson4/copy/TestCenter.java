package bitedu.bipa.lesson4.copy;

import java.io.*;
import java.util.*;

public class TestCenter {
	private Scanner scanner = new Scanner(System.in);
	
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
		int input = selectTest();
		answer = quiz.solveQuiz(input);
		System.out.println(answer);
		this.submitAnswer(input, answer);
		System.out.println("답안지를 모두 제출합니다.");
		System.out.println("시험을 종료합니다.");
	}
	
	public int selectTest() {
		System.out.println();
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("메인메뉴: 1번 퀴즈 | 2번 퀴즈 | 3번 퀴즈 | 4번 퀴즈");
		System.out.print("메뉴선택: ");
		String menuNo = scanner.nextLine();
		System.out.println();
		
		switch(menuNo) {
			case "1": {
				return 1;
			}
			case "2": {
				return 2;
			}
			case "3": {
				return 3;
			}
			case "4": {
				return 4;
			}
		}
		return 0;
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
