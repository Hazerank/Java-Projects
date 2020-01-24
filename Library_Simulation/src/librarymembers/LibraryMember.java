package librarymembers;

import java.util.ArrayList;
import books.*;
/**
 * LibraryMember class holds the properties for the members and related methods.
 * The Academic and Student classes inherits from the LibraryMember.
 * It is an abstract class.
 *  
 * @author Hazar
 * 
 * @see Academic
 * @see Student
 */
public abstract class LibraryMember{
	
	/**
	 * The ID value of the LibraryMember object.
	 */
	private int id;
	
	/**
	 * The member type of the LibraryMember object.
	 */
	protected String memberType;
	
	/**
	 * The maximum number of the books which can be borrowed by this member.
	 */
	protected int maxNumberOfBooks = 0;
	
	/**
	 * The time limit of the books which are borrowed from library.
	 * Member can return book without paying any feed if the deadline 
	 * which calculated with this timeLimit is not exceeded.
	 */
	protected int timeLimit = 0;
	
	/**
	 * The number of the books which was borrowed by this member before.
	 * It indicates the number of the books that this member have at that time.
	 */
	private int numberOfBooks = 0;
	
	/**
	 * The inclusive list of the all books which were either borrowed or read in the library by this member.
	 */
	protected ArrayList<Book> history;
	
	/**
	 * Constructs a LibraryMember object with given Id as parameter.
	 * It also identifies the history ArrayList .
	 * @param id The Id value which will be assigned to this LibraryMember object.
	 */
	public LibraryMember(int id) {
		this.id = id;
		history = new ArrayList<Book>();
	}
	
	/**
	 * Returns the book history of this member.
	 * @return <code>history</code> field.
	 */
	abstract public ArrayList<Book> getTheHistory();

	/**
	 * Returns the maximum number of the books.
	 * @return <code>maxNumberOfBooks</code> Fields value.
	 */
	public int getMaxNumberOfBooks() {
		return maxNumberOfBooks;
	}

	/**
	 * Returns Member Type value of this LibraryMember.
	 * @return <code>memberType</code> fields value.
	 */
	public String getMemberType() {
		return memberType;
	}

	/**
	 * Returns Time Limit of this member.
	 * @return <code>timeLimit</code> fields value.
	 */
	public int getTimeLimit() {
		return timeLimit;
	}

	/**
	 * Returns the number of the books that are borrowed by the member.
	 * @return <code>numberOfBooks</code> Fields value.
	 */
	public int getNumberOfBooks() {
		return numberOfBooks;
	}

	/**
	 * Increments the value of the <code>numberOfTheBooks</code> field by 1.
	 */
	public void incrementNumberOfBooks() {
		this.numberOfBooks += 1;
	}
	
	/**
	 * Decrements the value of the <code>numberOfTheBooks</code> field by 1.
	 */
	public void decrementNumberOfBooks() {
		this.numberOfBooks -= 1;
	}
	
	/**
	 * Adds the Book object given as parameter to the <code>history</code> ArrayList
	 * after control whether the book is already in the list once or not.
	 * @param book The Book object which is wanted to add history ArrayList.
	 */
	public void addHistory(Book book) {
		for(int i = 0 ; i < this.history.size() ; i++) {
			if(history.get(i).getBookID() == book.getBookID())
				return;
		}
		this.history.add(book);
	}
	
	/**
	 * Returns Id value of this LibraryMember.
	 * @return <code>id</code> fields value.
	 */ 
	public int getID() {
		return id;
	}
}