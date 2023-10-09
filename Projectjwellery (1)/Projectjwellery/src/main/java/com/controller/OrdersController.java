package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.entity.Product;
import com.advices.OrderException;
import com.entity.Orders;
import com.service.OrdersService;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @GetMapping("/getallorders")
    public ResponseEntity<List<Orders>> getAllOrders() throws Throwable{
        List<Orders> orders = ordersService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/getorderbyid/{orderId}")
    public ResponseEntity<Orders> getOrderById(@PathVariable int orderId) throws Throwable{
        Optional<Orders> order = ordersService.getOrderById(orderId);
        if (order.isPresent()) {
            return ResponseEntity.ok(order.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/placeorder")
    public ResponseEntity<Orders> createOrder(@RequestBody Orders order) throws OrderException{
        Orders createdOrder = ordersService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @PutMapping("/updateorderbyid/{orderId}")
    public ResponseEntity<Orders> updateOrder(@PathVariable int orderId, @RequestBody Orders updatedOrder) throws OrderException{
        Orders updated = ordersService.updateOrder(orderId, updatedOrder);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteorderbyid/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int orderId) throws OrderException{
        boolean deleted = ordersService.deleteOrder(orderId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
