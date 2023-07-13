package com.example.imsmanagemnt.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.imsmanagemnt.order.Order;
import com.example.imsmanagemnt.order.OrderRepository;

@RestController
@RequestMapping("/api")
public class SalesController {

    private final OrderRepository orderRepository;

    public SalesController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/sales-data")
    public List<Object[]> getSalesData() {
        List<Object[]> salesDataList = new ArrayList<>();

        // Fetch the necessary data from the orders table
        List<Order> orders = orderRepository.findAll();

        // Calculate the sales data for each product
        Map<String, Object[]> salesDataMap = new HashMap<>();
        for (Order order : orders) {
            String productName = order.getProductName();
            Object[] salesData = salesDataMap.getOrDefault(productName, new Object[]{productName, 0.0, 0});
            salesData[1] = (double) salesData[1] + (order.getQuantity() * order.getPrice());
            salesData[2] = (int) salesData[2] + order.getQuantity();
            salesDataMap.put(productName, salesData);
        }

        // Add the calculated sales data to the list
        salesDataList.addAll(salesDataMap.values());

        return salesDataList;
    }
}

