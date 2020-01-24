package books;

import interfaces.ReadInLibrary;
import librarymembers.LibraryMember;
/**
 * The class holds the properties for the Handwritten books and related methods.
 * It is the child class of Book.java and implements ReadInLibrary interface.
 * 
 * @author Hazar
 *
 * @see Book
 * @see ReadInLibrary
 */
public class Handwritten extends Book implements ReadInLibrary{
	
	
	/**
	 * Constructs a Handwritten Book object with given Book Id as parameter.
	 * It calls its superior constructor with 2 parameter which are BookID and BookType.
	 * @param bookID The Book Id value which will be assigned to this Book object.
	 */
	public Handwritten(int bookID) {
		super(bookID,"H");
	}

	/** 
	 * Implement action of returning a book which has taken to read in library.
	 * Reassigns the values of the relevant fields value
	 * 
	 * @see books.Book#returnBook(librarymembers.LibraryMember)
	 * @param member The member who will return this book to the library
	 */
	@Override
	public void returnBook(LibraryMember member) {
		this.isTaken = false;
		this.whoHas = null;
		this.isRead = false;
	}

	/**
	 * Implements the action of reading this Handwritten book in the library.
	 * Assign the values of the relevant fields value regarding to reading action.
	 * Changes <code>isTaken</code>, <code>whoHas</code>, <code>isRead</code> fields.
	 * Calls <code>addHistory</code> method to add this book to history of the member who want to read the book.
	 * 
	 * @see interfaces.ReadInLibrary#readBook(librarymembers.LibraryMember)
	 * @param member The member who will read the book.
	 */
	@Override
	public void readBook(LibraryMember member) {
		this.isTaken = true;
		this.whoHas = member;
		this.isRead = true;
		member.addHistory(this);
	}
	
}