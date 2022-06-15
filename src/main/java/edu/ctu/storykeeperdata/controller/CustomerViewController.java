package edu.ctu.storykeeperdata.controller;

import edu.ctu.storykeeperdata.model.Customer;
import edu.ctu.storykeeperdata.service.CustomerService;
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
public class CustomerViewController {

    private final CustomerService customerService;

    @Autowired
    public CustomerViewController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/app/listCustomer")
    public String customer(Model model) {
        List<Customer> customerList = customerService.getAllCustomers();
        model.addAttribute("custList", customerList);
        return "customer";
    }

    @GetMapping("/app/searchCustLastName")
    public String findCustomerByLastName(Model model, String keyword) {
        model.addAttribute("lastname", keyword);
        List<Customer> customerList = customerService.getCustomersByLastName(keyword);
        model.addAttribute("custList", customerList);
        return "customer";
    }

    @GetMapping("/app/searchCustEmail")
    public String findCustomerByEmail(Model model, String keyword) {
        model.addAttribute("email", keyword);
        List<Customer> customerList = customerService.getCustomersByEmail(keyword);
        model.addAttribute("custList", customerList);
        return "customer";
    }

    @GetMapping("/app/searchCustPhone")
    public String findCustomerByPhone(Model model, String keyword) {
        model.addAttribute("phone", keyword);
        List<Customer> customerList = customerService.getCustomersByPhone(keyword);
        model.addAttribute("custList", customerList);
        return "customer";
    }

    @GetMapping("/app/addCustomerForm")
    public String addCustomerForm(Model model) {
        model.addAttribute("formData", new Customer());
        String title = "Add New Customer";
        model.addAttribute("title", title);
        return "add-customer-form";
    }

    @GetMapping("/app/showCustUpdateForm")
    public ModelAndView showCustUpdateForm(@RequestParam String id, Model model) {
        String title = "Update Customer";
        model.addAttribute("title", title);
        ModelAndView mav = new ModelAndView("add-customer-form");
        Customer customer = customerService.getCustomerById(id);
        mav.addObject("formData", customer);
        return mav;
    }

    @PostMapping("/app/saveCustomer")
    public String saveCustomer(@ModelAttribute Customer customer) {
        customerService.save(customer);
        return "redirect:/app/listCustomer";
    }

    @PostMapping("/app/remove-customer")
    public String deleteCustomer(@RequestParam String id) {
        customerService.delete(id);
        return "redirect:/app/listCustomer";
    }
}
