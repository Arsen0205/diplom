package com.example.diploma_thesis.service;


import com.example.diploma_thesis.dto.request.AddCartDtoRequest;
import com.example.diploma_thesis.dto.response.Response;
import com.example.diploma_thesis.models.Cart;
import com.example.diploma_thesis.models.CartItem;
import com.example.diploma_thesis.models.Product;
import com.example.diploma_thesis.models.SoleTrader;
import com.example.diploma_thesis.repository.CartRepository;
import com.example.diploma_thesis.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartService {
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;


    public Response addCart(AddCartDtoRequest request){
        Cart cart = cartRepository.findBySoleTraderId(request.getUserId()).orElseGet(()->{
            Cart newCart = new Cart();
            SoleTrader soleTrader = new SoleTrader();
            soleTrader.setId(request.getUserId());
            newCart.setSoleTrader(soleTrader);
            return cartRepository.save(newCart);
        });

        Product product = productRepository.findById(request.getProductId()).orElseThrow(()-> new IllegalArgumentException("Продукт не найден"));
        CartItem item = cart.getItems().stream()
                .filter(cartItem -> cartItem.getProduct().getId().equals(request.getProductId()))
                .findFirst()
                .orElseGet(() -> {
                    CartItem newItem = new CartItem();
                    newItem.setProduct(product);
                    newItem.setCart(cart);
                    cart.getItems().add(newItem);
                    return newItem;
                });
        item.setQuantity(item.getQuantity() + request.getQuantity());
        cartRepository.save(cart);
        return new Response("Товар добавлен в корзину");
    }
}
