package com.cocktail.controllers;

import com.cocktail.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam String username, @RequestParam String email,
                          @RequestParam String password, @RequestParam String passwordConfirm) {
        if (username.isEmpty()||password.isEmpty()||passwordConfirm.isEmpty() || email.isEmpty()) {
            return "redirect:/registration?error";
        }
        if (!password.equals(passwordConfirm)){
            return "redirect:/registration?notconfirm";
        }
        if (!userService.addUser(username,password, email)) return "redirect:/registration?exist";

        return "redirect:/login";
    }

    /*
    @GetMapping("/activate/{code}")
    public String activate(@PathVariable String code, Model model){
        boolean isActivated = userService.activatedUsr(code);
        if (isActivated){
            model.addAttribute("registrationMassage", "Пользователь зарегестрирован");
        }else {
            model.addAttribute("registrationMassage", "Пользователь не найден");
        }
        return "login";
    }
     */
}
