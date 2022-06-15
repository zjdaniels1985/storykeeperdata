package edu.ctu.storykeeperdata.controller;

import edu.ctu.storykeeperdata.model.Book;
import edu.ctu.storykeeperdata.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookViewController {

    private final BookService bookService;

    @Autowired
    public BookViewController (BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/app/listBook")
    public ModelAndView getAllBooks() {
        ModelAndView mav = new ModelAndView("book");
        mav.addObject("bookList", bookService.getAllBooks());
        return mav;
    }


    @GetMapping("/app/searchTitle")
    public String findBookByTitle(Model model, String keyword) {
        model.addAttribute("title", keyword);
        List<Book> bookList = bookService.getBooksByTitle(keyword);
        model.addAttribute("bookList", bookList);
        return "book";
    }

    @GetMapping("/app/searchAuthor")
    public String findBookByAuthor(Model model, String keyword) {
        model.addAttribute("author", keyword);
        List<Book> bookList = bookService.getBooksByAuthor(keyword);
        model.addAttribute("bookList", bookList);
        return "book";
    }

    @GetMapping("/app/searchISBN")
    public String findBookByISBN(Model model, String keyword) {
        model.addAttribute("isbn", keyword);
        List<Book> bookList = bookService.getBooksByIsbn(keyword);
        model.addAttribute("bookList", bookList);
        return "book";
    }


    @GetMapping("/app/addBookForm")
    public String addBookForm(Model model) {
        model.addAttribute("formData", new Book());
        String title = "Add New Book";
        model.addAttribute("title", title);
        return "add-book-form";
    }

    @GetMapping("/app/showBookUpdateForm")
    public ModelAndView showBookUpdateForm(@RequestParam String id, Model model) {
        String title = "Update Book";
        model.addAttribute("title", title);
        ModelAndView mav = new ModelAndView("add-book-form");
        Book book = bookService.getBookById(id);
        mav.addObject("formData", book);
        return mav;
    }

    @PostMapping("/app/saveBook")
    public String saveEmployee(@ModelAttribute Book book) {
        bookService.save(book);
        return "redirect:/app/listBook";
    }

    @PostMapping("/app/remove-book")
    public String deleteBook(@RequestParam String id) {
        bookService.delete(id);
        return "redirect:/app/listBook";
    }
}
