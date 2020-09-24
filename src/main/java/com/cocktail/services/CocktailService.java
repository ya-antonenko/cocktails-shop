package com.cocktail.services;

import com.cocktail.domain.Cocktail;
import com.cocktail.repos.CocktailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CocktailService {
    @Autowired
    CocktailRepo cocktailRepo;

    @Transactional(readOnly = true)
    public List<Cocktail> cocktailsSearchByURL(String drink) {
        List<Cocktail> cocktails = cocktailRepo.searchByIngredientsEng("%".concat(drink).concat("%"));
        return cocktails;
    }

    @Transactional(readOnly = true)
    public List<Cocktail> cocktailsSearchByRequest(String drink) {
        List<Cocktail> cocktails = cocktailRepo.searchByIngredient("%".concat(drink).concat("%"));
        return cocktails;
    }

    @Transactional(readOnly = true)
    public List<Cocktail> findResultSearch(String drink) {
        String toSearchLike = "%".concat(drink).concat("%");
        List<Cocktail> cocktailsByIngredientsEng = cocktailRepo.searchByIngredientsEng(toSearchLike);
        List<Cocktail> cocktailByIngredient = cocktailRepo.searchByIngredient(toSearchLike);
        Cocktail cocktailByName = cocktailRepo.findByNameCocktailLike(toSearchLike);

        if (cocktailsByIngredientsEng.isEmpty()) {
            cocktailsByIngredientsEng.addAll(cocktailByIngredient);
            if (!cocktailsByIngredientsEng.contains(cocktailByName) && cocktailByName != null)
                cocktailsByIngredientsEng.add(cocktailByName);
            return cocktailsByIngredientsEng;
        }

        for (int i = 0; i < cocktailByIngredient.size(); i++) {
            if (!cocktailsByIngredientsEng.contains(cocktailByIngredient.get(i)))
                cocktailsByIngredientsEng.add(cocktailByIngredient.get(i));
        }
        if (!cocktailsByIngredientsEng.contains(cocktailByName) && cocktailByName != null)
            cocktailsByIngredientsEng.add(cocktailByName);
        return cocktailsByIngredientsEng;
    }

    @Transactional(readOnly = true)
    public Cocktail findCocktailByID(long id) {
        Cocktail cocktail = cocktailRepo.findCocktailById(id);
        return cocktail;
    }

    @Transactional(readOnly = true)
    public Cocktail findCocktailByNameEng(String cocktailNameEng) {
        Cocktail cocktail = cocktailRepo.findCocktailByNameCocktailEng(cocktailNameEng);
        return cocktail;
    }
}
