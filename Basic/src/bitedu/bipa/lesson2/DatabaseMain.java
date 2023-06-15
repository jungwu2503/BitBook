package bitedu.bipa.lesson2;
 
import java.io.*;
import java.sql.*;
import java.util.*;

public class DatabaseMain {
	public static void main(String[] args) {
		DatabaseMain main = new DatabaseMain();
		//main.testStart();
		main.makeTable();
	}
	
	public void testStart() {
		ArrayList<StudentDTO> data = this.readyData();
		GisaQuiz quiz = new GisaQuiz(data);
		String answer = quiz.solveQuiz1();
		System.out.println(answer);
	}
	
	private ArrayList<StudentDTO> readyData() {
		ArrayList<StudentDTO> data = null;
		// File 처리 대신 DB 처리
		DatabaseWork work = new DatabaseWork();
		try {
			data = work.getStudentData();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	public void makeTable() {
		try {
			makeData();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void makeData() throws IOException{
		ArrayList<StudentDTO> data = null;
		//파일 접속
		File file = new File("./data/Abc1115.csv");
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
		
		DatabaseWork work = new DatabaseWork();
		work.insertData(data);
	}
	
}
