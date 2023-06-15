package SimpleJsonTest;

public class BookDTO {
	private int bookNumber;
	private String bookName;
	private String burrowState;
	private String bookState;
	
	public BookDTO(int bookNumber, String bookName, String burrowState, String bookState) {
		super();
		this.bookNumber = bookNumber;
		this.bookName = bookName;
		this.burrowState = burrowState;
		this.bookState = bookState;
	}

	public int getBookNumber() {
		return bookNumber;
	}

	public void setBookNumber(int bookNumber) {
		this.bookNumber = bookNumber;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBurrowState() {
		return burrowState;
	}

	public void setBurrowState(String burrowState) {
		this.burrowState = burrowState;
	}

	public String getBookState() {
		return bookState;
	}

	public void setBookState(String bookState) {
		this.bookState = bookState;
	}

	@Override
	public String toString() {
		return "BookDTO [bookNumber=" + bookNumber + ", bookName=" + bookName + ", burrowState=" + burrowState
				+ ", bookState=" + bookState + "]";
	}
}
