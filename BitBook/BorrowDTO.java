package bitBook;

import java.sql.Date;

public class BorrowDTO {
	private int memberNumber;
	private int bookNumber;
	private Date borrowDate;
	private String state;
	
	public int getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}
	public int getBookNumber() {
		return bookNumber;
	}
	public void setBookNumber(int bookNumber) {
		this.bookNumber = bookNumber;
	}

	public Date getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "BorrowDTO [memberNumber=" + memberNumber + ", bookNumber=" + bookNumber + ", borrowDate=" + borrowDate
				+ ", state=" + state + "]";
	}


}
