package de.codecentric.psd.worblehat.domain;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Borrowing Entity
 */
@Entity
public class Borrowing implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; // NOSONAR

	private String borrowerEmailAddress;

	@Temporal(TemporalType.DATE)
	private Date borrowDate;

	@OneToOne()
	private Book borrowedBook;

	public String getBorrowerEmailAddress() {
		return borrowerEmailAddress;
	}

	/**
	 * @param book
	 * The borrowed book
	 * @param borrowerEmailAddress
	 * The borrowers e-mail Address
	 * @param borrowDate
	 * The borrow date
     */
	public Borrowing(Book book, String borrowerEmailAddress, DateTime borrowDate) {
		super();
		this.borrowedBook = book;
		this.borrowerEmailAddress = borrowerEmailAddress;
		this.borrowDate = borrowDate.toDate();
	}

	public Borrowing(Book book, String borrowerEmailAddress) {
		this(book, borrowerEmailAddress, DateTime.now());
	}

	private Borrowing() {
		// for JPA
	}

	public Book getBorrowedBook() {
		return borrowedBook;
	}
	
	public String getReturnDate() {
		SimpleDateFormat format = new SimpleDateFormat("EE, yyyy-MM-dd", Locale.GERMAN);
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(borrowDate);
		calendar.add(Calendar.WEEK_OF_YEAR, 2);
		return format.format(calendar.getTime());
	}
}
