package com.example.diploma_thesis.service;


import com.example.diploma_thesis.dto.request.RegisterSupplierDtoRequest;
import com.example.diploma_thesis.dto.response.Response;
import com.example.diploma_thesis.models.Supplier;
import com.example.diploma_thesis.repository.SupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SupplierService {
    private final SupplierRepository supplierRepository;
    private final PasswordEncoder passwordEncoder;


    public Response registerSupplier(RegisterSupplierDtoRequest request){
        if(supplierRepository.findByLogin(request.getLogin()).isPresent()){
            throw new RuntimeException("Данный пользователь уже зарегистрирован");
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

        supplierRepository.save(supplier);

        return new Response("Пользователь успешно зарегистрирован");
    }
}
