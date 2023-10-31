package com.belfarz.controller;

import com.belfarz.model.UserModel;
import com.belfarz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public  String getRegisterPage(Model model) {
        model.addAttribute("registerRequest", new UserModel());
        return  "register_page";
    }

    @GetMapping("/login")
    public  String getLoginPage(Model model){
        model.addAttribute("loginRequest", new UserModel());
        return  "login_page";
    }

    @PostMapping("/login")
    public  String login(@ModelAttribute UserModel userModel, Model model){
        System.out.println("register request" + userModel);
        UserModel loggedUser = userService.authenticate(userModel.getLogin(),userModel.getPassword());
        if(loggedUser != null ){
            model.addAttribute("user_name", loggedUser.getLogin());
            return "personal_page";
        }else {
            return "error_page";
        }
    }
    @PostMapping("/register")
    public  String register(@ModelAttribute UserModel userModel){
        System.out.println("register request" + userModel);
        UserModel registeredUser = userService.registerUser(userModel.getLogin(),userModel.getEmail(),userModel.getPassword());
        return  registeredUser == null ? "error_page" : "redirect:/login";
    }

}
