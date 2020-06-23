package com.cocktail.controllers;

import com.cocktail.domain.Drink;
import com.cocktail.domain.Usr;
import com.cocktail.services.DrinkService;
import com.cocktail.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ShopController {
    @Autowired
    DrinkService drinkService;
    @Autowired
    UserService userService;

    @GetMapping("/shop")
    public String viewShop(Model model){
        model.addAttribute("drinks", drinkService.findAll());
        return "viewShop";
    }
    @PostMapping("/searchDrink")
    public String searchDrink(@RequestParam("search") String search, Model model){
        if (search.isEmpty()) return "redirect:/shop?emptyfield";
        List<Drink> drinkList = drinkService.findResultSearch(search);
        if (drinkList.isEmpty()) return "redirect:/shop?notfound";
        model.addAttribute("drinks", drinkList);
        return "viewShop";
    }
    @GetMapping("/basket")
    public String showBasket(Model model){
        Usr currentUsr = identifyUser();
        List<Drink> drinks = drinkService.basketList(currentUsr);
        if (drinks == null) {
            model.addAttribute("basketEmpty", "Корзина пустая");
            return viewShop(model);
        }
        model.addAttribute("basketList", drinks);
        double totalPrice =0;
        for (int i=0; i<drinks.size(); i++){
            totalPrice += drinks.get(i).getPrice();
        }
        model.addAttribute("totalPrice", totalPrice);
        return "basket";
    }

    @GetMapping("/basket/{drink}")
    public String addDrinkToBasket(@PathVariable("drink") String nameEng, Model model){
        Usr currentUsr = identifyUser();
        Drink drinkToBasket = drinkService.findDrinkToBasket(nameEng);
        if (drinkToBasket == null){
            model.addAttribute("availabilityOfAnswer", drinkToBasket.getNameEng());
            model.addAttribute("addToBasket", "Товар не найден");
            return viewShop(model);
        }
        if (drinkService.addDrinkToUsr(currentUsr,drinkToBasket)){
            model.addAttribute("availabilityOfAnswer", drinkToBasket.getNameEng());
            model.addAttribute("addToBasket", "Товар добавлен в корзину");
            return viewShop(model);
        }
        return "redirect:/shop";
    }

    @GetMapping("/topay")
    public String toPay(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (userService.deleteListBasket(auth.getName())){
            model.addAttribute("basketEmpty","Товар успешно оплачен");
        }else {
            model.addAttribute("basketEmpty", "Произошла ошибка при оплате");
        }
        return viewShop(model);
    }

    @PostMapping("/adddrinkonbasket")
    public String addDrinkOnBusket(@RequestParam("drink") String nameDrinkEng, Model model){
        Usr currentUsr = identifyUser();
        Drink drinkToBasket = drinkService.findDrinkToBasket(nameDrinkEng);
        drinkService.addDrinkToUsr(currentUsr,drinkToBasket);
        return showBasket(model);
    }

    @PostMapping("/deletedrinkonbasket")
    public String deleteDrinkOnBusket(@RequestParam("drink") String nameDrinkEng, Model model){
        Usr currentUsr = identifyUser();
        drinkService.deleteDrinkOnUsr(currentUsr, drinkService.findDrinkToBasket(nameDrinkEng));
        return showBasket(model);
    }

    private Usr identifyUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usr currentUsr = userService.findByUsername(auth.getName());
        return currentUsr;
    }
}
