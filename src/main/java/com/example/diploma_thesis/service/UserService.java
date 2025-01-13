package com.example.diploma_thesis.service;


import com.example.diploma_thesis.dto.request.RegisterUserDtoRequest;
import com.example.diploma_thesis.dto.response.Response;
import com.example.diploma_thesis.models.User;
import com.example.diploma_thesis.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Response registerUser(RegisterUserDtoRequest request){
        if(userRepository.findByLogin(request.getLogin()).isPresent()){
            throw new RuntimeException("Данный логин уже существует");
        }

        User user = new User();
        user.setLogin(request.getLogin());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        userRepository.save(user);
        return new Response("Вы успешно зарегистрировались");
    }
}
