package edu.ctu.storykeeperdata.controller;

import edu.ctu.storykeeperdata.model.Book;
import edu.ctu.storykeeperdata.model.Customer;
import edu.ctu.storykeeperdata.model.Order;
import edu.ctu.storykeeperdata.repository.BookRepository;
import edu.ctu.storykeeperdata.repository.CustomerRepository;
import edu.ctu.storykeeperdata.repository.OrderRepository;
import edu.ctu.storykeeperdata.service.BookService;
import edu.ctu.storykeeperdata.service.CustomerService;
import edu.ctu.storykeeperdata.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


//Spring annotations
@Controller
public class ViewController {


    private final BookService bookService;
    private final CustomerService customerService;
    private final OrderService orderService;
    private final BookRepository bookRepo;
    private final CustomerRepository custRepo;
    private final OrderRepository orderRepo;
    private final List<Book> orderList = new ArrayList<>();


    @Autowired
    public ViewController(BookService bookService, CustomerService customerService, OrderRepository orderRepo, OrderService orderService, BookRepository bookRepo, CustomerRepository custRepo) {
        this.bookService = bookService;
        this.customerService = customerService;
        this.orderRepo = orderRepo;
        this.orderService = orderService;
        this.bookRepo = bookRepo;
        this.custRepo = custRepo;

    }

    // *************************** Index View *******************************************
    @RequestMapping({"/", "/index"})
    public String index() {
        return "index";
    }


    // *************************** Book Views

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
        List<Book> list = bookService.getBooksByISBN(keyword);
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

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam String id, Model model) {
        String title = "Update Book";
        model.addAttribute("title", title);
        ModelAndView mav = new ModelAndView("add-book-form");
        Optional<Book> book = bookRepo.findById(id);
        book.ifPresent(value -> mav.addObject("formData", value));
        return mav;
    }

    @PostMapping("/saveBook")
    public String saveEmployee(@ModelAttribute Book book) {
        bookService.save(book);
        return "redirect:/listBook";
    }

    @PostMapping("/remove-book")
    public String deleteBook(@RequestParam String id) {
        bookService.delete(id);
        return "redirect:/listBook";
    }


    // ********************* Customer Views *****************************************

    @RequestMapping("/listCustomer")
    public String customer(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("custList", customers);
        return "customer";
    }

    @GetMapping("/searchCustLastName")
    public String findCustomerByLastName(Model model, String keyword) {
        model.addAttribute("lastname", keyword);
        List<Customer> list = custRepo.findAllByLastNameContains(keyword);
        model.addAttribute("custList", list);
        return "customer";
    }

    @GetMapping("/searchCustEmail")
    public String findCustomerByEmail(Model model, String keyword) {
        model.addAttribute("email", keyword);
        List<Customer> list = customerService.getCustomerByEmail(keyword);
        model.addAttribute("custList", list);
        return "customer";
    }

    @GetMapping("/searchCustPhone")
    public String findCustomerByPhone(Model model, String keyword) {
        model.addAttribute("phone", keyword);
        List<Customer> list = customerService.getCustomerByPhone(keyword);
        model.addAttribute("custList", list);
        return "customer";
    }

    @GetMapping("/addCustomerForm")
    public String addCustomerForm(Model model) {
        model.addAttribute("formData", new Customer());
        String title = "Add New Customer";
        model.addAttribute("title", title);
        return "add-customer-form";
    }

    @GetMapping("/showCustUpdateForm")
    public ModelAndView showCustUpdateForm(@RequestParam String id, Model model) {
        String title = "Update Customer";
        model.addAttribute("title", title);
        ModelAndView mav = new ModelAndView("add-customer-form");
        Optional<Customer> customer = custRepo.findById(id);
        customer.ifPresent(value -> mav.addObject("formData", value));
        return mav;
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute Customer customer) {
        customerService.save(customer);
        return "redirect:/listCustomer";
    }

    @PostMapping("/remove-customer")
    public String deleteCustomer(@RequestParam String id) {
        customerService.delete(id);
        return "redirect:/listCustomer";
    }


    // *********************** Order Views ************************************************

    @RequestMapping("/listOrder")
    public String order(Model model) {
        List<Order> orders = orderRepo.findAll();
        model.addAttribute("orderList", orders);
        return "order";
    }

    @GetMapping("/searchOrderEmail")
    public String findOrderByEmail(Model model, String keyword) {
        model.addAttribute("email", keyword);
        List<Order> orders = orderService.getOrderByEmail(keyword);
        model.addAttribute("orderList", orders);
        return "order";
    }

    @GetMapping("/addOrderForm")
    public String addOrderForm(Model model) {
        model.addAttribute("formData", new Order());
        model.addAttribute("books", bookRepo.findAll());
        return "add-order-form";
    }

    @PostMapping("/saveOrder")
    public String saveOrder(@ModelAttribute Order order) {
        orderRepo.save(order);
        orderList.clear();
        return "redirect:/listOrder";
    }

    @PostMapping("/remove-order")
    public String deleteOrder(@RequestParam String id) {
        orderService.delete(id);
        return "redirect:/listOrder";
    }

}


