package com.serviceImpl;

import java.util.List;
import java.util.Optional;

//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.advices.CartException;
import com.entity.Cart;
import com.repository.CartRepository;
import com.service.CartService;

import jakarta.validation.Valid;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<Cart> getAllCarts() throws Throwable {
        List<Cart> carts = cartRepository.findAll();
        if (carts.isEmpty()) {
            throw new CartException("No Carts found");
        }
        return carts;
    }

    @Override
    public Optional<Cart> getCartById(int cartId) throws Throwable {
        Optional<Cart> cart = cartRepository.findById(cartId);
        if (!cart.isPresent()) {
            throw new CartException("Cart with ID " + cartId + " not found");
        }
        return cart;
    }

    @Override
    public Cart createCart(@Valid Cart cart) throws CartException {
        return cartRepository.save(cart);
    }

    @Override
    public Cart updateCart(int cartId, @Valid Cart updatedCart) throws CartException {
        if (!cartRepository.existsById(cartId)) {
            throw new CartException("Cart with ID " + cartId + " not found");
        }
        updatedCart.setCartId(cartId);
        return cartRepository.save(updatedCart);
    }

    @Override
    public void deleteCart(int cartId) throws CartException {
        if (!cartRepository.existsById(cartId)) {
            throw new CartException("Cart with ID " + cartId + " not found");
        }
        cartRepository.deleteById(cartId);
    }
}
