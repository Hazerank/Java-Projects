package librarymembers;

import java.util.ArrayList;

import books.Book;
/**
 * The instances of the Academic class represents the academicians to utilize the services                         
 * provided by the library. The properties and possible behaviors of the Academic class 
 * are determined. It inherits from LibraryMember.
 * 
 * @author Hazar
 * @see LibraryMember
 */
public class Academic extends LibraryMember{

	/** 
	 * Constructs an Academic object by calling its superior constructor.
	 * It defines the fields which are specific to the class which are
	 * <code>memberType</code>, <code>maxNumberOfBooks</code>, <code>timeLimit</code>.
	 * 
	 * @param id The ID value of this Academic object.
	 */ 
	public Academic(int id) {
		super(id);
		this.memberType = "A";
		this.maxNumberOfBooks = 20;
		this.timeLimit = 50;
	}
	
	/**
	 *  Returns the <code>history</code> ArrayList.
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