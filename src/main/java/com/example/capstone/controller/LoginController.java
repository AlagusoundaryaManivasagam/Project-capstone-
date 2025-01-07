package com.example.capstone.controller;

import com.example.capstone.database.dao.UserDAO;
import com.example.capstone.database.entity.User;
import com.example.capstone.form.CreateUserFormBean;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class LoginController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDAO userDAO;

    @GetMapping("/login/signUp")
    public ModelAndView signUp(){
        ModelAndView response = new ModelAndView();
        response.setViewName("login/signUp");
        return response;
    }

    @PostMapping("/login/signUpSubmit")
    public ModelAndView submit(@Valid CreateUserFormBean form, BindingResult bindingResult){

        ModelAndView response = new ModelAndView();
        if ( bindingResult.hasErrors() ) {
            response.setViewName("login/signup");
            response.addObject("bindingResult", bindingResult);
            response.addObject("form", form);

        } else {
            response.setViewName("login/signup");
            User user = new User();
            user.setUserName(form.getUsername());
            user.setEmail(form.getEmail());

            String encryptedPassword = passwordEncoder.encode(form.getPassword());
            user.setPassword(encryptedPassword);


            userDAO.save(user);
        }

        return response;
    }

    @GetMapping("/login/login")
    public ModelAndView login(){
        ModelAndView response = new ModelAndView();
        response.setViewName("login/login");
        return response;
    }
}
