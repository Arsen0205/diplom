package com.example.diploma_thesis.service;


import com.example.diploma_thesis.dto.request.LoginDtoRequest;
import com.example.diploma_thesis.dto.request.RegisterSupplierDtoRequest;
import com.example.diploma_thesis.dto.response.Response;
import com.example.diploma_thesis.models.Order;
import com.example.diploma_thesis.models.Supplier;
import com.example.diploma_thesis.models.enums.OrderStatus;
import com.example.diploma_thesis.repository.OrderRepository;
import com.example.diploma_thesis.repository.SoleTraderRepository;
import com.example.diploma_thesis.repository.SupplierRepository;
import com.example.diploma_thesis.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SupplierService {
    private final SupplierRepository supplierRepository;
    private final SoleTraderRepository soleTraderRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final OrderRepository orderRepository;

    public Response registerSupplier(RegisterSupplierDtoRequest request){
        if(supplierRepository.findByLogin(request.getLogin()).isPresent() && userRepository.findByLogin(request.getLogin()).isPresent() && supplierRepository.findByLogin(request.getLogin()).isPresent()){
            throw new RuntimeException("Данный пользователь с таким логином уже зарегистрирован");
        }

        Supplier supplier = new Supplier();

        supplier.setLogin(request.getLogin());
        supplier.setName(request.getName());
        supplier.setSurname(request.getSurname());
        supplier.setEmail(request.getEmail());
        supplier.setPassword(passwordEncoder.encode(request.getPassword()));
        supplier.setAddress(request.getAddress());
        supplier.setCompany(request.getCompany());
        supplier.setInn(request.getInn());
        supplier.setDescription(request.getDescription());
        supplier.setPhoneNumber(request.getPhoneNumber());
        supplier.setRegistrationNumber(request.getRegistrationNumber());
        supplier.setRoles(request.getRole());
        supplier.setActive(true);

        supplierRepository.save(supplier);

        return new Response("Пользователь успешно зарегистрирован");
    }

    public Response loginSupplier(LoginDtoRequest request){
        Supplier supplier = supplierRepository.findByLogin(request.getLogin()).orElseThrow(()-> new IllegalArgumentException("Пользователя с таким логином не существует"));

        if(!passwordEncoder.matches(request.getPassword(), supplier.getPassword())){
            throw new IllegalArgumentException("Неверный пароль!");
        }
        if(!supplier.isActive()){
            throw new IllegalArgumentException("Ваш аккаунт заблокирован");
        }

        return new Response("Вход выполнен успешно");
    }

    public void updateOrderStatus(Long orderId, OrderStatus newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        order.setStatus(newStatus);
        orderRepository.save(order);
    }
}
