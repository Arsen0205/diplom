package com.example.diploma_thesis.service;


import com.example.diploma_thesis.dto.request.LoginDtoRequest;
import com.example.diploma_thesis.dto.request.RegisterUserDtoRequest;
import com.example.diploma_thesis.dto.response.Response;
import com.example.diploma_thesis.models.User;
import com.example.diploma_thesis.repository.SoleTraderRepository;
import com.example.diploma_thesis.repository.SupplierRepository;
import com.example.diploma_thesis.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final SoleTraderRepository soleTraderRepository;
    private final SupplierRepository supplierRepository;
    private final PasswordEncoder passwordEncoder;

    public Response registerUser(RegisterUserDtoRequest request){
        if(userRepository.findByLogin(request.getLogin()).isPresent() && soleTraderRepository.findByLogin(request.getLogin()).isPresent() && supplierRepository.findByLogin(request.getLogin()).isPresent()){
            throw new RuntimeException("Данный пользователь с таким логином уже зарегистрирован");
        }

        User user = new User();
        user.setLogin(request.getLogin());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        userRepository.save(user);
        return new Response("Вы успешно зарегистрировались");
    }

    public Response loginUser(LoginDtoRequest request){
        User user = userRepository.findByLogin(request.getLogin()).orElseThrow(()-> new IllegalArgumentException("Пользователь с таким логином не найден"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Неверный пароль");
        }
        return new Response("Вход выполнен успешно");
    }
}
