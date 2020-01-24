package books;

import interfaces.Borrow;
import interfaces.ReadInLibrary;
import librarymembers.LibraryMember;
/**
 * The class holds the properties for the printed books and related methods. 
 * It is the child class of the Book.java and implements ReadInLibrary, Borrow 
 * interfaces.
 * 
 * @author Hazar
 *
 * @see Book
 * @see ReadInLibrary
 * @see Borrow
 */
public class Printed extends Book implements ReadInLibrary, Borrow{
	
	/**
	 * The value of the deadLine which is the last day which is allowed to return this borrowed book without paying fee.
	 * It is 0 if it is not borrowed. 
	 */
	private int deadLine = 0;
	
	/**
	 * The value if the extending action performed on this book before.
	 * It is false if it has never extended; true otherwise.
	 */
	private boolean isExtended = false;
	
	/**
	 * Constructs a Printed Book object with given Book Id as parameter.
	 * It calls its superior constructor with 2 parameter which are BookID and BookType.
	 * @param bookID The Book Id value which will be assigned to this Book object.
	 */
	public Printed(int bookID) {
		super(bookID,"P");
	}
	
	/**
	 * Returns deadLine value of this Book.
	 * @return <code>deadLine</code> fields value.
	 */
	public int getDeadLine() {
		return deadLine;
	}

	/**
	 * Returns whether the book is extended before or not 
	 * @return true if <code>isExtended</code> true, otherwise false.
	 */
	public boolean isExtended() {
		return isExtended;
	}

	/**
	 * Implement action of returning a book which has taken to read in library or borrowed.
	 * Reassigns the values of the relevant fields value
	 * which are <code>isTaken</code>, <code>whoHas</code>, <code>isRead</code> and <code>deadLine</code>.
	 * Calls <code>decrementNumberOfBooks</code> method to decrease the number of the books which
	 * are taken by member.
	 * 
	 * @see books.Book#returnBook(librarymembers.LibraryMember)
	 * @param member The member who want to return this book to the library.
	 */
	@Override
	public void returnBook(LibraryMember member) {
		this.whoHas = null;
		this.deadLine = 0;
		this.isTaken = false;
		this.isExtended = false;
		if(!isRead)
			member.decrementNumberOfBooks();
		else
			this.isRead = false;
	}

	/**
	 * Implements the action of borrowing this Printed book from the library.
	 * Assign the values of the relevant fields value regarding to borrowing action.
	 * which are <code>isTaken</code>, <code>whoHas</code>, <code>deadLine</code> fields.
	 * Calculates the deadLine of this book with the helps of <code>tick</code> parameter
	 * and <code>getTimeLimit</code> method; and assign this value to <code>deadLine</code> field. 
	 * Calls <code>addHistory</code> method to add this book to history of the member who want to read the book.
	 * Calls <code>incrementNumberOfBooks</code> method to increase the number of the books 
	 * which are taken by the member.
	 * 
	 * @see interfaces.Borrow#borrowBook(librarymembers.LibraryMember, int)
	 * @param member The member who want to borrow this book from the library. 
	 * @param tick The actual time indicator.
	 */
	@Override
	public void borrowBook(LibraryMember member, int tick) {
		this.whoHas = member;
		this.deadLine = tick + member.getTimeLimit();
		this.isTaken = true;
		member.incrementNumberOfBooks();
		member.addHistory(this);
	}

	/**
	 * Implements the action of extending this Printed book taken from the library.
	 * Assign the values of the relevant fields value regarding to extending action.
	 * which are <code>isExtended</code> and the <code>deadLine</code> fields.
	 * Calculates the new deadLine of this book with the helps of <code>tick</code> parameter
	 * and <code>getTimeLimit</code> method; and reassign this value to <code>deadLine</code> field. 
	 *
	 * @see interfaces.Borrow#extend(librarymembers.LibraryMember, int)
	 * @param member The member who want to extend this book. 
	 * @param tick The actual time indicator.
	 */
	@Override
	public void extend(LibraryMember member, int tick) {
		this.isExtended = true;
		this.deadLine += member.getTimeLimit();
	}

	/**
	 * Implements the action of reading this Printed book in the library.
	 * Assign the values of the relevant fields value regarding to reading action.
	 * Changes <code>isTaken</code>, <code>whoHas</code>, <code>isRead</code> fields.
	 * Calls <code>addHistory</code> method to add this book to history of the member who want to read the book.
	 * 
	 * @see interfaces.ReadInLibrary#readBook(librarymembers.LibraryMember)
	 * @param member The member who want to read the book.
	 */
	@Override
	public void readBook(LibraryMember member) {
		this.isTaken = true;
		this.whoHas = member;
		this.isRead = true;
		member.addHistory(this);
	}
	
}