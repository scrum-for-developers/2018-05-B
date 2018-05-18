package de.codecentric.psd.worblehat.web.controller;

import de.codecentric.psd.worblehat.domain.Book;
import de.codecentric.psd.worblehat.domain.BookService;
import de.codecentric.psd.worblehat.domain.Borrowing;
import de.codecentric.psd.worblehat.web.formdata.EmailAddressFormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

/**
 * Controller class for the
 */
@Controller
@RequestMapping("/showBorrowings")
public class ShowBorrowingsController {

	private BookService bookService;
	private List<Borrowing> borrowings = new ArrayList<>();

	@Autowired
	public ShowBorrowingsController(BookService bookService) {
		this.bookService = bookService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public void prepareView(ModelMap modelMap) {
		modelMap.put("showBookFormData", new EmailAddressFormData());
	}

	@RequestMapping(method = RequestMethod.POST)
	public String showAllBooks(ModelMap modelMap,
			@ModelAttribute("showBookFormData") @Valid EmailAddressFormData formData,
			BindingResult result) {
		if (result.hasErrors()) {
		} else {
			borrowings.clear();
			borrowings.addAll(bookService.findBorrowedBooks(formData.getEmailAddress()));
			modelMap.addAttribute("borrowings", borrowings);
		}
		return "showBorrowings";
	}

}
