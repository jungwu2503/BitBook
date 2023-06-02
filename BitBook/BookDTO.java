package bitBook;

public class BookDTO {
	private int bookNumber;
	private String title;
	private int grade;
	private String state;
	// 1. 전시중(대여가능)
	// 2. 정회원
	// 3. 특별회원
	// 4. 관리자
	
	public int getBookNumber() {
		return bookNumber;
	}
	public void setBookNumber(int bookNumber) {
		this.bookNumber = bookNumber;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "BookDTO [bookNumber=" + bookNumber + ", title=" + title + ", grade=" + grade + ", state=" + state + "]";
	}
	
}
