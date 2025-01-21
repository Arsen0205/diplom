package com.example.diploma_thesis.service;


import com.example.diploma_thesis.models.Cart;
import com.example.diploma_thesis.models.CartItem;
import com.example.diploma_thesis.models.Order;
import com.example.diploma_thesis.models.OrderItem;
import com.example.diploma_thesis.repository.CartRepository;
import com.example.diploma_thesis.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    public Order createOrder(Long userId){
        Cart cart = cartRepository.findBySoleTraderId(userId).orElseThrow(()-> new IllegalArgumentException("Нет корзины у пользователя"));

        if(cart.getItems().isEmpty()){
            throw new IllegalArgumentException("Корзина пустая");
        }

        Order order = new Order();
        order.setSoleTrader(cart.getSoleTrader());

        for(CartItem cartItem : cart.getItems()){
            OrderItem orderItem= new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            order.getItems().add(orderItem);
        }

        cart.getItems().clear();
        cartRepository.save(cart);

        return orderRepository.save(order);
    }
}
