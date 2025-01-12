package com.example.capstone.controller;

import com.example.capstone.database.dao.UserDAO;
import com.example.capstone.database.entity.User;
import com.example.capstone.form.CreateUserFormBean;
import com.example.capstone.security.AuthenticatedUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
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
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticatedUserService authenticatedUserService;


    @GetMapping(value = {"/login/signUp", "/"})
    public ModelAndView signUp(){
        ModelAndView response = new ModelAndView();
        response.setViewName("login/signUp");
        return response;
    }


    @PostMapping("/login/signUpSubmit")
    public ModelAndView submit(@Valid CreateUserFormBean form, BindingResult bindingResult, HttpSession session){

        if(!StringUtils.equals(form.getPassword(), form.getConfirmPassword())){
            bindingResult.rejectValue("confirmPassword", "Passwords code","Passwords do not match");
        }
        ModelAndView response = new ModelAndView();
        if ( bindingResult.hasErrors() ) {
            response.setViewName("login/signUp");
            response.addObject("bindingResult", bindingResult);
            response.addObject("form", form);

        } else {

            User user = new User();
            user.setUserName(form.getUsername());
            user.setEmail(form.getEmail());

            String encryptedPassword = passwordEncoder.encode(form.getPassword());
            user.setPassword(encryptedPassword);


            userDAO.save(user);
            response.setViewName("redirect:/login/login");
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
