package com.example.diploma_thesis.service;


import com.example.diploma_thesis.dto.request.LoginDtoRequest;
import com.example.diploma_thesis.dto.request.RegisterSoleTraderDtoRequest;
import com.example.diploma_thesis.dto.request.RegisterSupplierDtoRequest;
import com.example.diploma_thesis.dto.response.Response;
import com.example.diploma_thesis.models.SoleTrader;
import com.example.diploma_thesis.repository.SoleTraderRepository;
import com.example.diploma_thesis.repository.SupplierRepository;
import com.example.diploma_thesis.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SoleTraderService {
    private final SoleTraderRepository soleTraderRepository;
    private final SupplierRepository supplierRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public Response registerSoleTrader(RegisterSoleTraderDtoRequest request){
        if(soleTraderRepository.findByLogin(request.getLogin()).isPresent() && supplierRepository.findByLogin(request.getLogin()).isPresent() && userRepository.findByLogin(request.getLogin()).isPresent()){
            throw new RuntimeException("Данный пользователь с таким логином уже зарегистрирован");
        }

        SoleTrader soleTrader = new SoleTrader();
        soleTrader.setName(request.getName());
        soleTrader.setSurname(request.getSurname());
        soleTrader.setLogin(request.getLogin());
        soleTrader.setEmail(request.getEmail());
        soleTrader.setAddress(request.getAddress());
        soleTrader.setOgrnip(request.getOgrnip());
        soleTrader.setBankRequisites(request.getBankRequisites());
        soleTrader.setDateOfEntryInTheRegistry(request.getDateOfEntryInTheRegistry());
        soleTrader.setPassword(passwordEncoder.encode(request.getPassword()));
        soleTrader.setRoles(request.getRoles());
        soleTrader.setPhoneNumber(request.getPhoneNumber());
        soleTrader.setFieldOfActivity(request.getFieldOfActivity());
        soleTrader.setRegistryEntryNumber(request.getRegistryEntryNumber());
        soleTrader.setActive(true);

        soleTraderRepository.save(soleTrader);

        return new Response("Вы успешно зарегистрировались!");
    }

    public Response loginSupplier(LoginDtoRequest request){
        SoleTrader soleTrader = soleTraderRepository.findByLogin(request.getLogin()).orElseThrow(()-> new IllegalArgumentException("Пользователя с таким логином не найден!"));
        if(!passwordEncoder.matches(request.getPassword(), soleTrader.getPassword())){
            throw new IllegalArgumentException("Неверный пароль!");
        }
        if(!soleTrader.isActive()){
            throw new IllegalArgumentException("Ваш аккаунт заблокирован");
        }

        return new Response("Вход выполнен успешно");
    }

}
