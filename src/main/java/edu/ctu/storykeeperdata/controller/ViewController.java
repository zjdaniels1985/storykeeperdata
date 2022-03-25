package edu.ctu.storykeeperdata.controller;

import edu.ctu.storykeeperdata.model.Book;
import edu.ctu.storykeeperdata.model.Customer;
import edu.ctu.storykeeperdata.model.Order;
import edu.ctu.storykeeperdata.repository.BookRepository;
import edu.ctu.storykeeperdata.repository.OrderRepository;
import edu.ctu.storykeeperdata.service.BookService;
import edu.ctu.storykeeperdata.service.CustomerService;
import edu.ctu.storykeeperdata.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;



//Spring annotations
@Controller
public class ViewController {


    private final BookService bookService;
    private final CustomerService customerService;
    private final BookRepository bookRepo;
    private final OrderService orderService;
    private final OrderRepository orderRepo;

    @Autowired
    public ViewController(BookService bookService, CustomerService customerService, OrderRepository orderRepo, OrderService orderService, BookRepository bookRepo) {
        this.bookService = bookService;
        this.customerService = customerService;
        this.orderRepo = orderRepo;
        this.orderService = orderService;
        this.bookRepo = bookRepo;
    }


    @RequestMapping({"/", "/index"})
    public String index() {
        return "index";
    }


    @PostMapping("/remove-book")
    public String deleteBook(@RequestParam String isbn) {
            bookService.delete(isbn);
            return "redirect:/listBook";
    }


    @GetMapping("/listBook")
    public ModelAndView getAllBooks() {
        ModelAndView mav = new ModelAndView("book");
        mav.addObject("bookList", bookRepo.findAll());
        return mav;
    }


    @GetMapping("/searchTitle")
    public String findBookByTitle(Model model, String keyword) {
        model.addAttribute("title", keyword);
        List<Book> list = bookRepo.findAllByTitleContains(keyword);
        model.addAttribute("bookList", list);
        return "book";
    }

    @GetMapping("/searchAuthor")
    public String findBookByAuthor(Model model, String keyword) {
        model.addAttribute("author", keyword);
        List<Book> list = bookRepo.findAllByAuthorContains(keyword);
        model.addAttribute("bookList", list);
        return "book";
    }

    @GetMapping("/searchISBN")
    public String findBookByISBN(Model model, String keyword) {
        model.addAttribute("isbn", keyword);
        Book list = bookRepo.findByIsbnContains(keyword);
        model.addAttribute("bookList", list);
        return "book";
    }


    @GetMapping("/addBookForm")
    public String addBookForm(Model model) {
        model.addAttribute("formData", new Book());
        String title = "Add New Book";
        model.addAttribute("title", title);
        return "add-book-form";
    }

    @PostMapping("/saveBook")
    public String saveEmployee(@ModelAttribute Book book) {
        bookRepo.save(book);
        return "redirect:/listBook";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam String bookIsbn, Model model) {
        String title = "Update Book";
        model.addAttribute("title", title);
        ModelAndView mav = new ModelAndView("add-book-form");
        Book book = bookRepo.findByIsbnContains(bookIsbn);
        mav.addObject("formData", book);
        return mav;
    }


    @RequestMapping("/customer")
    public String customer(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("custList", customers);
        return "customer";
    }

    @RequestMapping("/order")
    public String order(Model model) {
        List<Order> orders = orderRepo.findAll();
        model.addAttribute("orderList", orders);
        return "order";
    }

    @GetMapping("/searchOrderEmail")
    public String findOrderByEmail(Model model, String keyword) {
        model.addAttribute("email", keyword);
        Order orders = orderRepo.findDistinctByCustomerEmailContains(keyword);
        model.addAttribute("orderList", orders);
        return "order";
    }
}
