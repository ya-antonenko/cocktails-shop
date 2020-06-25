package com.cocktail.controllers;

import com.cocktail.domain.Cocktail;
import com.cocktail.services.CocktailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Controller
public class CocktailController {
    @Autowired
    CocktailService cocktailService;

    @GetMapping("/cocktails/{drink}")
    public String cocktailPage(@PathVariable("drink") String drink, Model model){
        List<Cocktail> cocktails = cocktailService.cocktailsSearchByURL(drink);
        if (cocktails.isEmpty()){
            return "redirect:/generalpage?notfound";
        }
        for (int i=0; i<cocktails.size(); i++){
            cocktails.get(i).setQuantity(i);
        }
        model.addAttribute("cocktails", cocktails);
        return "cocktailsPage";
    }

    @PostMapping("/searchCocktails")
    public String searchCocktail(@RequestParam("search") String search,
            Model model){
        if (search.isEmpty()) return "redirect:/generalpage?emptyfield";
        List<Cocktail> cocktails = cocktailService.findResultSearch(search);
        if (cocktails.isEmpty() || cocktails == null) return "redirect:/generalpage?notfound";
        for (int i=0; i<cocktails.size(); i++){
            cocktails.get(i).setQuantity(i);
        }
        model.addAttribute("cocktails", cocktails);
        return "cocktailsPage";
    }

    @GetMapping("/cocktail/{cocktail_name_eng}")
    public String viewRecipeCocktail(@PathVariable("cocktail_name_eng") String cocktailNameEng, Model model){
        Cocktail cocktail = cocktailService.findCocktailByNameEng(cocktailNameEng);
        Pattern pattern = Pattern.compile(";");
        String[] arrayRecipe = pattern.split(cocktail.getRecipe());
        Map<String, String> stepRecipe = new HashMap<String, String>();
        for (int i=0; i<arrayRecipe.length; i++){
            stepRecipe.put(Integer.toString(i), arrayRecipe[i]);
        }
        model.addAttribute("cocktail", cocktail);
        model.addAttribute("cocktailRecipe", stepRecipe);
        return "viewRecipeCocktail";
    }
}
