package librarymembers;

import java.util.ArrayList;

import books.Book;
/**
 * The class holds the properties for the Students and related methods. 
 * It is the child class of the LibrarMember.java
 * 
 * 
 * @author Hazar
 * @see LibraryMember
 *
 */
public class Student extends LibraryMember{

	
	/**
	 * Constructs a Student object by calling its superior constructor.
	 * It defines the fields which are specific to the class which are
	 * <code>memberType</code>, <code>maxNumberOfBooks</code>, <code>timeLimit</code>.
	 * 
	 * @param id The ID value of this Student object.
	 */
	public Student(int id) {
		super(id);
		this.memberType = "S";
		this.maxNumberOfBooks = 10;
		this.timeLimit = 20;
	}
	
	/**
	 * Returns the <code>history</code> ArrayList.
	 * This method inherited from super class.
	 * 
	 * @see librarymembers.LibraryMember#getTheHistory()
	 * @return The <code>history</code> ArrayList.
	 */
	@Override
	public ArrayList<Book> getTheHistory() {
		return this.history;
	}
	
}