package com.boot.security.controller;


import com.boot.security.Dto.UserDto;
import com.boot.security.Model.User;
import com.boot.security.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/error")
    public ModelAndView errorPage() {
        return new ModelAndView("error");
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @PostMapping("/login")
    public ModelAndView loginRedirect(String username, String password){

        User user = userService.getUser(username,password);
        if("USER".equals(user.getRoleid())){
            return new ModelAndView("/user");
        }
        if ("ADMIN".equals(user.getRoleid())){
            return  new ModelAndView("/admin");
        }else
        {
            return new ModelAndView("/login");
        }

    }

    @GetMapping("/register")
    public String getRegistrationPage(@ModelAttribute("user") UserDto userDto) {
        return "register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute("user") UserDto userDto, Model model) {
        userService.save(userDto);
        model.addAttribute("message", "Registered Successfully!");
        return "register";
    }

    @GetMapping("/admin")
    public String adminPage(Model model, Principal principal){
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        return "admin";
    }
    @GetMapping("/user")
    public String userPage(Model model, Principal principal){
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        return "user";
    }



}
