package com.service;

import java.util.List;
import java.util.Optional;

import com.entity.Orders;
import com.entity.Product;



public interface OrdersService {

    public List<Orders> getAllOrders() ;
    public Optional<Orders> getOrderById(int orderId) ;
    public Orders createOrder(Orders order) ;
    public Orders updateOrder(int orderId, Orders updatedOrder) ;
	public boolean deleteOrder(int orderId);
	
       
}
