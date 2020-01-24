package books;

/**
 * In this CLass, the common properties of book that is independent
 * from its type implemented.
 * It is an abstract class. The Printed and
 * Handwritten classes inherits from the Book.java.
 * 
 * @author Hazar
 * 
 * @see Handwritten
 * @see Printed
 */
import librarymembers.LibraryMember;
abstract public class Book{
	
	/**
	 * The ID value of the book object.
	 */
	private int bookID;
	
	/**
	 * The type of the book object.
	 */
	private String bookType;
	
	/**
	 * The information about whether the book is borrowed or not.
	 */
	protected boolean isTaken = false;
	
	/**
	 * The library member instance who has borrowed or read in the library. 
	 */
	protected LibraryMember whoHas = null;
	
	/**
	 * The information about whether the book is being read in the library at that particular time or not 
	 */
	protected boolean isRead = false;
	
	/**
	 * Constructs a new Book object. The book ID and Book Type are given as parameters.
	 * @param bookID The ID value which will assign to this book instance.
	 * @param bookType The Book type value which will assign to this book instance.
	 */
	public Book(int bookID, String bookType){
		this.bookID = bookID;
		this.bookType = bookType;
	}
	
	/**
	 * Returns whether the book is being read in the library at that particular time or not 
	 * @return true if <code>isRead</code> true, otherwise false.
	 */
	public boolean isRead() {
		return isRead;
	}
	
	/**
	 * Returns Book Id value of this book.
	 * @return <code>BookID</code> fields value.
	 */
	public int getBookID() {
		return bookID;
	}
	
	/**
	 * Controls and returns if this Book instance is valid to be borrowed
	 * @return true if the book is not borrowed and the book type is Printed; false otherwise.
	 */
	public boolean isValidBorrow() {
		return (!this.isTaken && this.bookType.equals("P"));
	}
	
	/**
	 * Returns Book Type value of this book.
	 * @return <code>bookType</code> fields value.
	 */
	public String getBookType() {
		return bookType;
	}
	
	/**
	 * Returns whether the book is borrowed from the library before or not 
	 * @return true if <code>isTaken</code> true, otherwise false.
	 */
	public boolean isTaken() {
		return isTaken;
	}
	
	/**
	 * Returns the library member object who has this book as borrowed or as reading in the library.
	 * @return <code>whoHas</code> fields value which is a LibraryMember object.
	 */
	public LibraryMember getWhoHas() {
		return whoHas;
	}
	
	/**
	 * Carry outs the returning action of the borrowed or reading book.
	 * It initializes in the children classes.
	 * @param member The member who has the book and will have return to the library. 
	 */
	public abstract void returnBook(LibraryMember member);
	
}
