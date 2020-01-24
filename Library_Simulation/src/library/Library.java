package library;

import java.util.Scanner;
import books.*;
import librarymembers.*;

/**
 * Library class is the terminal class.
 * All operations about the working system of the library
 * are performed in this class.
 * All controls are also done in this class.
 * It is connected with all other classes about library.
 * 
 * 
 * @author Hazar
 * 
 * @see Student
 * @see Academic
 * @see LibraryMember
 * @see Book
 * @see Printed
 * @see Handwritten
 */
public class Library{
	
	/**
	 * The Scanner which will take input from file or user.
	 */
	Scanner scanner;
	
	/**
	 * The Book Array which will store all books in the library system 
	 * in an orderly way
	 */
	private Book[] bookList;
	
	/**
	 * The total number of the books in the library system.
	 */
	private int bookNum = 0; 
	
	/**
	 * The LibraryMember Array which will store all members
	 * in the library system in an orderly way.
	 */
	private LibraryMember[] libraryMemberList;
	
	/**
	 * The total number of the members in the library system.
	 */
	private int memberNum = 0;
	
	/**
	 * The amount of the total fee that is paid to the library.
	 */
	private int totalFee = 0;
	
	/**
	 * The Book instance which will added to the library system,
	 * which is the bookList.
	 */
	private Book book;
	
	/**
	 * The Library Member instance which will added to the library
	 * system which is the libraryMemberList.
	 */
	private LibraryMember libraryMember;
	
	/**
	 * Construct this library with a scanner parameter.
	 * Assigns the parameter scanner to the field one.
	 * Identifies the <code>bookList[]</code> and <code>libraryMemberList[]</code> arrays.
	 * 
	 * @param scanner The scanner which reads input.
	 */
	public Library(Scanner scanner) {
		this.scanner = scanner;
		bookList = new Book[(int) Math.pow(10,6)];
		libraryMemberList = new LibraryMember[(int) Math.pow(10,6)];
	}
	
	/**
	 * Returns list of the books contained in the library
	 * 
	 * @return <code>bookList</code> Array.
	 */
	public Book[] getBooks() {
		return this.bookList;
	}
	
	/**
	 * Returns list of the members contained in the library
	 * 
	 * @return <code>memberList</code> Array.
	 */
	public LibraryMember[] getMembers(){
		return this.libraryMemberList;
	}

	/**
	 * Returns the amount of total fee that is paid to the library.
	 * @return <code>totalFee</code> fields value.
	 */
	public int getTotalFee() {
		return totalFee;
	}
	
	/**
	 * Adds book to the library system.
	 * Creates a Book object and adds to <code>bookList</code> array.
	 * Assigns a bookID to the book.
	 * 
	 */
	public void addBook() {
		int bookID = ++bookNum;
		String bookType = scanner.next();
		if(bookType.equals("P") || bookType.equals("H")) {
			if(bookType.equals("P")) {
				book = new Printed(bookID);
			}
			else {
				book = new Handwritten(bookID);
			}	
			bookList[bookID - 1] = book;
		}
		else
			bookNum--;
	}
	
	/**
	 * Adds member to the library system.
	 * Creates a LibraryMember object and adds to <code>libraryMemberList</code> array.
	 * Assigns a memberID to the book.
	 * 
	 */
	public void addMember() {
		int memberID = ++memberNum;
		String memberType = scanner.next();
		if(memberType.equals("A") || memberType.equals("S")) {
			if(memberType.equals("S")) 
				libraryMember = new Student(memberID);
			else 
				libraryMember = new Academic(memberID);
			libraryMemberList[memberID - 1] = libraryMember;	
		}
		else 
			memberNum--;
	}

	/**
	 * Performs borrowing book action.
	 * Checks the requirements for borrowing by calling <code>isValidBorrow</code> method.
	 * Calls the borrowBook method to complete borrowing.
	 * 
	 * @param tick The value which determines time.
	 */
	public void borrowBook(int tick) {
		int bookID = scanner.nextInt(), memberID = scanner.nextInt();
		if(0 < bookID && bookID <= bookNum && 0 < memberID && memberID <= memberNum) {
			if(isValidBorrow(libraryMemberList[memberID-1], bookID, tick))  {
				((Printed) bookList[bookID - 1]).borrowBook(libraryMemberList[memberID-1],tick);
			}
		}
	}
	
	/**
	 * Controls whether the given book is valid to be borrowed or not.
	 * 
	 * @param libraryMember The member who want to borrow book.
	 * @param bookID The ID value of the book which will be borrowed.
	 * @param tick The value which determines time.
	 * @return True if the book is valid to be borrowed by member; otherwise, false. 
	 */
	private boolean isValidBorrow(LibraryMember libraryMember, int bookID, int tick) {
		if(libraryMember.getMaxNumberOfBooks() > libraryMember.getNumberOfBooks()) {
			for(int i = 0; i< bookNum ; i++) {
				if(bookList[i].getWhoHas() == null) 
					continue;
				if(bookList[i].getWhoHas().getID() == libraryMember.getID() && 
						!bookList[i].isRead() && ((Printed) bookList[i]).getDeadLine() < tick) {
					return false;
				}
			}
			return bookList[bookID - 1].isValidBorrow();
		}
		return false;
	}

	/**
	 * Carry out returning book process.
	 * Reads input with scanner from input and gets
	 * the information about book and member which
	 * will perform returning action.
	 * Checks the requirements for returning by calling <code>isValidReturn</code> method.
	 * 
	 * @param tick The value which determines time.
	 */
	public void returnBook(int tick) {
		int bookID = scanner.nextInt(), memberID = scanner.nextInt();
		if(0 < bookID && bookID <= bookNum && 0 < memberID && memberID <= memberNum) {
			if(isValidReturn(bookID, memberID)) {
				returnBook(bookID,libraryMemberList[memberID - 1], tick);
			}
		}
	}
	
	/**
	 * Controls whether the given book is valid to be returned or not.
	 * 
	 * @param bookID The ID value of the book which will be returned.
	 * @param memberID The ID value of the member which will return book.
	 * @return True if the book is valid to be returned by member; otherwise, false. 
	 */
	private boolean isValidReturn(int bookID, int memberID) {
		if(bookList[bookID - 1].getWhoHas() == null) return false;
		return(bookList[bookID - 1].getWhoHas().getID() == memberID);
	}
	
	/**
	 * Performs the actual returning action.
	 * Calls returnBook method to do that.
	 * @param bookID The ID value of the book which will be returned.
	 * @param libraryMember The LibraryMember which wanted to return book.
	 * @param tick The value which determines time.
	 */
	private void returnBook(int bookID, LibraryMember libraryMember,int tick) {
		if(!bookList[bookID -1].isRead()) {
			totalFee += (tick - (((Printed)bookList[bookID-1]).getDeadLine()) > 0) ? tick - (((Printed)bookList[bookID-1]).getDeadLine()) : 0;
		}
		bookList[bookID -1].returnBook(libraryMember);
	}
	
	/**
	 * Performs extending action.
	 * Checks requirements of the extending process with <code>isExtendable</code> method.
	 * If it is valid for the action, extends deadline of the book regarding to  
	 * time limit of the member.
	 * 
	 * @param tick The value which determines time.
	 */
	public void extendBook(int tick) {
		int bookID = scanner.nextInt(), memberID = scanner.nextInt();
		if(0 < bookID && bookID <= bookNum && 0 < memberID && memberID <= memberNum) {
			if(isExtendable(bookList[bookID - 1], memberID, tick)) {
				((Printed)bookList[bookID -1]).extend(libraryMemberList[memberID - 1], tick);
			}
		}
	}
	
	/**
	 * Controls whether the given book is valid to be extended or not.
	 *
	 * @param book The Book object which will be controlled whether it is able to be extended or not.
	 * @param memberID The ID value of the member which will extend book.
	 * @param tick The value which determines time.
	 * @return True if the book is valid to be extended by member; otherwise, false. 
	 */
	private boolean isExtendable(Book book, int memberID,int tick) {
		if(book instanceof Printed && book.getWhoHas().getID() == memberID && 
				((Printed) book).getDeadLine() >= tick && !((Printed) book).isExtended() ){
			return true;
		}
		return false;
	}
	
	/**
	 * Performs reading in the library action.
	 * Controls first whether the member is able to read that specific book
	 * or that book is able to be read in the library.
	 * Calls <code>readBook</code> method to perform reading action.
	 * 
	 */
	public void readInLibrary() {
		int bookID = scanner.nextInt(), memberID = scanner.nextInt();
		if(0 < bookID && bookID <= bookNum && 0 < memberID && memberID <= memberNum) {
			if(!bookList[bookID - 1].isTaken()) {
				if(bookList[bookID - 1].getBookType().equals("H") && libraryMemberList[memberID - 1].getMemberType().equals("A")) {
					((Handwritten) bookList[bookID - 1]).readBook(libraryMemberList[memberID - 1]);
				}
				else if(bookList[bookID - 1].getBookType().equals("P")) {
					((Printed) bookList[bookID - 1]).readBook(libraryMemberList[memberID -1]);
				}
			}
		}
	}
}
