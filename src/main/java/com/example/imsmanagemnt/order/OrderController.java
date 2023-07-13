package com.example.imsmanagemnt.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/orders")
    public ModelAndView showOrders() {
        ModelAndView mav = new ModelAndView("orders");
        List<Order> list = orderRepository.findAll();
        mav.addObject("orders", list);
        return mav;
    }

    @GetMapping("/addOrderForm")
    public ModelAndView addOrderForm() {
        ModelAndView mav = new ModelAndView("add-order-form");
        Order newOrder = new Order();
        mav.addObject("order", newOrder);
        return mav;
    }

    @PostMapping("/saveOrder")
    public String saveOrder(@ModelAttribute Order order) {
        orderRepository.save(order);
        return "redirect:/orders";
    }

    @GetMapping("/showOrderUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long orderId) {
        ModelAndView mav = new ModelAndView("add-order-form");
        Order order = orderRepository.findById(orderId).get();
        mav.addObject("order", order);
        return mav;
    }

    @GetMapping("/deleteOrder")
    public String deleteOrder(@RequestParam Long orderId) {
        orderRepository.deleteById(orderId);
        return "redirect:/orders";
    }
}
