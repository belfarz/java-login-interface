package com.belfarz.service;

import com.belfarz.model.UserModel;
import com.belfarz.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel registerUser(String login, String email, String password){
        if (login == null || password == null) {
            return null;
        } else {
            if (userRepository.findFirstByLogin(login).isPresent()){
                System.out.println("duplicate login");
                return  null;


            }
            UserModel userModel = new UserModel();
            userModel.setLogin(login);
            userModel.setPassword(password);
            userModel.setEmail(email);
            return userRepository.save(userModel);
        }
    }

    public UserModel authenticate(String login, String password){
        return  userRepository.findByLoginAndPassword(login, password).orElse(null);
    }
}

