package edu.ctu.storykeeperdata.controller;

import edu.ctu.storykeeperdata.model.Order;
import edu.ctu.storykeeperdata.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OrderViewController {

    private final OrderService orderService;

    @Autowired
    public OrderViewController(OrderService orderService) {
        this.orderService = orderService;
    }


    @RequestMapping("/listOrder")
    public String order(Model model) {
        List<Order> orders = orderService.getAllOrders();
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

    // TODO: Fix Show Update Order Form
//    @GetMapping("/showOrderUpdateForm")
//    public ModelAndView showOrderUpdateForm(@RequestParam String id, Model model){
//        String title = "Update Order";
//        model.addAttribute("title", title);
//        ModelAndView mav = new ModelAndView("add-order-form");
//        Optional<Order> order = orderService.get (id);
//        order.ifPresent(value -> mav.addObject("formData", value));
//        return mav;
//    }

    @GetMapping("/addOrderForm")
    public String addOrderForm(Model model) {
        model.addAttribute("formData", new Order());
        return "add-order-form";
    }

    @PostMapping("/saveOrder")
    public String saveOrder(@ModelAttribute Order order) {
        orderService.save(order);
        return "redirect:/listOrder";
    }

    @PostMapping("/remove-order")
    public String deleteOrder(@RequestParam String id) {
        orderService.delete(id);
        return "redirect:/listOrder";
    }
}
