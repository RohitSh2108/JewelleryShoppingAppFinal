package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.advices.CartException;
import com.entity.Cart;
import com.repository.CartRepository;

public interface CartService {

    public List<Cart> getAllCarts() throws Throwable ;
    public Optional<Cart> getCartById(int cartId) throws Throwable ;
    public Cart createCart(Cart cart) throws CartException ;
    public Cart updateCart(int cartId, Cart updatedCart) throws CartException ;
    public void deleteCart(int cartId) throws CartException ;
}