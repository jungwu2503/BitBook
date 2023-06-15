package SimpleJsonTest;

import java.io.*;
import java.util.*;

import org.json.simple.*;

public class ExecuteService {
	private BookDAO dao;
	private ArrayList<BookDTO> list;
	
	public ExecuteService() {
		dao = new BookDAO();
		list = new ArrayList<BookDTO>();
		
		list = dao.getDTOList();
		
		JSONArray jsonArray = new JSONArray();
		
		for (int i = 0; i < list.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("bookNumber", list.get(i).getBookNumber());
			jsonObject.put("bookName", list.get(i).getBookName());
			jsonObject.put("burrowState", list.get(i).getBurrowState());
			jsonObject.put("bookState", list.get(i).getBookState());
			
			jsonArray.add(jsonObject);			
		}
		System.out.println(jsonArray);
		
		try {
			FileWriter fw = new FileWriter("C:\\Users\\jungw\\Desktop\\BitBook\\bitbook\\assets\\data\\test.json");
			fw.write(jsonArray.toJSONString());
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*
		for (int i = 0; i < 3; i++) {
			jsonObject.put("bookNumber", "100000");
			jsonObject.put("bookName", "javajava");
			jsonObject.put("burrowState", "대출중");
			jsonObject.put("bookState", "1급");

			jsonArray.add(jsonObject);
		}			
		
		try(FileWriter fw = new FileWriter("C:\\Users\\jungw\\Desktop\\BitBook\\bitbook\\assets\\data\\test.json")) {
			fw.write(jsonArray.toJSONString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
