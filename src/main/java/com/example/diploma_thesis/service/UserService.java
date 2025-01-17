package com.example.diploma_thesis.service;


import com.example.diploma_thesis.dto.request.AddCategoryDtoRequest;
import com.example.diploma_thesis.dto.request.BanUnBanUserDtoRequest;
import com.example.diploma_thesis.dto.request.LoginDtoRequest;
import com.example.diploma_thesis.dto.request.RegisterUserDtoRequest;
import com.example.diploma_thesis.dto.response.Response;
import com.example.diploma_thesis.models.Category;
import com.example.diploma_thesis.models.SoleTrader;
import com.example.diploma_thesis.models.Supplier;
import com.example.diploma_thesis.models.User;
import com.example.diploma_thesis.repository.CategoryRepository;
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
    private final CategoryRepository categoryRepository;

    public Response registerUser(RegisterUserDtoRequest request) {
        if (userRepository.findByLogin(request.getLogin()).isPresent() && soleTraderRepository.findByLogin(request.getLogin()).isPresent() && supplierRepository.findByLogin(request.getLogin()).isPresent()) {
            throw new RuntimeException("Данный пользователь с таким логином уже зарегистрирован");
        }

        User user = new User();
        user.setLogin(request.getLogin());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        userRepository.save(user);
        return new Response("Вы успешно зарегистрировались");
    }

    public Response loginUser(LoginDtoRequest request) {
        User user = userRepository.findByLogin(request.getLogin()).orElseThrow(() -> new IllegalArgumentException("Пользователь с таким логином не найден"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Неверный пароль");
        }
        return new Response("Вход выполнен успешно");
    }

    public Response banUser(BanUnBanUserDtoRequest request) {
        if (supplierRepository.findByLogin(request.getLogin()).isPresent()) {
            Supplier supplier = supplierRepository.findByLogin(request.getLogin()).orElseThrow(() -> new IllegalArgumentException("Пользователя с таким логином не найден"));

            if (!supplier.isActive()) {
                throw new IllegalArgumentException("Пользователь уже заблокирован");
            }

            supplier.setActive(false);

            return new Response("Вы заблокировали пользователя");
        } else if (soleTraderRepository.findByLogin(request.getLogin()).isPresent()) {
            SoleTrader soleTrader = soleTraderRepository.findByLogin(request.getLogin()).orElseThrow(() -> new IllegalArgumentException("Пользователя с таким логином не существует"));

            if (!soleTrader.isActive()) {
                throw new IllegalArgumentException("Пользователь уже заблокирован");
            }

            soleTrader.setActive(false);

            return new Response("Вы заблокировали пользователя");
        }

        return new Response("Данного пользователя не существует");
    }

    public Response unBanUser(BanUnBanUserDtoRequest request) {
        if (supplierRepository.findByLogin(request.getLogin()).isPresent()) {
            Supplier supplier = supplierRepository.findByLogin(request.getLogin()).orElseThrow(() -> new IllegalArgumentException("Пользователя с таким логином не найден"));

            if (supplier.isActive()) {
                throw new IllegalArgumentException("Пользователь уже разблокирован");
            }

            supplier.setActive(true);

            return new Response("Вы разблокировали пользователя");
        } else if (soleTraderRepository.findByLogin(request.getLogin()).isPresent()) {
            SoleTrader soleTrader = soleTraderRepository.findByLogin(request.getLogin()).orElseThrow(() -> new IllegalArgumentException("Пользователя с таким логином не существует"));

            if (!soleTrader.isActive()) {
                throw new IllegalArgumentException("Пользователь уже разблокирован");
            }

            soleTrader.setActive(true);

            return new Response("Вы разблокировали пользователя");
        }

        return new Response("Данного пользователя не существует");
    }

    public Response changeUserRole(BanUnBanUserDtoRequest request) {
        User user = userRepository.findByLogin(request.getLogin()).orElseThrow(()-> new IllegalArgumentException("Пользователь с таким логином не существует"));

        user.getRole().clear();
        user.getRole().add(request.getNewRole());

        userRepository.save(user);
        return new Response("Вы успешно поменяли/добавили роль");
    }


    public Response deleteUser(BanUnBanUserDtoRequest request) {
        if (supplierRepository.findByLogin(request.getLogin()).isPresent()) {
            Supplier supplier = supplierRepository.findByLogin(request.getLogin()).orElseThrow(() -> new IllegalArgumentException("Пользователь с таким логином не найден"));

            supplierRepository.delete(supplier);

            return new Response("Вы удалили поставщика");
        } else if (supplierRepository.findByLogin(request.getLogin()).isPresent()) {
            SoleTrader soleTrader = soleTraderRepository.findByLogin(request.getLogin()).orElseThrow(() -> new IllegalArgumentException("Пользователь с таким логином не найден"));

            soleTraderRepository.delete(soleTrader);

            return new Response("Вы удалили ИП");
        } else if (userRepository.findByLogin(request.getLogin()).isPresent()) {
            User user = userRepository.findByLogin(request.getLogin()).orElseThrow(()->new IllegalArgumentException("Пользователь с таким логином не найден"));

            userRepository.delete(user);

            return new Response("Вы удалили пользователя системы");
        }
        return new Response("Пользователя с таким логином не найден");
    }

    public Response addCategory(AddCategoryDtoRequest request){
        if (categoryRepository.findByLogin(request.getCategory()).isPresent()){
            throw new IllegalArgumentException("Такая категория уже существует");
        }
        Category category = new Category();
        category.setName(request.getCategory());

        categoryRepository.save(category);

        return new Response("Вы успешно добавили новую категорию");
    }

}
