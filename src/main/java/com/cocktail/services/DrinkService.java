package com.cocktail.services;

import com.cocktail.controllers.ShopController;
import com.cocktail.domain.Cocktail;
import com.cocktail.domain.Drink;
import com.cocktail.domain.Usr;
import com.cocktail.repos.DrinkRepo;
import com.cocktail.repos.UsrRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class DrinkService {
    @Autowired
    DrinkRepo drinkRepo;
    @Autowired
    UsrRepo usrRepo;

    @Transactional(readOnly = true)
    public List<Drink> findAll(){
        List<Drink> drinks = drinkRepo.findAll();
        for (int i=0; i<drinks.size(); i++){
            drinks.get(i).setImage("..".concat(drinks.get(i).getImage()));
        }
        return drinks;
    }
    @Transactional(readOnly = true)
    public List<Drink> findResultSearch(String drink){
        String drinkToSearch = "%".concat(drink).concat("%");
        List<Drink> drinksFromBasis = drinkRepo.findSearchResultFromBasis(drinkToSearch);
        if (drinksFromBasis.isEmpty()) {
            drinksFromBasis.addAll(drinkRepo.findSearchResultFromName(drinkToSearch));
            return drinksFromBasis;
        }
        List<Drink> drinksFromName = drinkRepo.findSearchResultFromName(drinkToSearch);
        for (int i=0; i<drinksFromName.size(); i++){
            if (!drinksFromBasis.contains(drinksFromName.get(i)))
                drinksFromBasis.add(drinksFromName.get(i));
        }
        return drinksFromBasis;
    }
    @Transactional(readOnly = true)
    public Drink findDrinkToBasket(String nameEng){
        return drinkRepo.findByNameEng(nameEng);
    }

    @Transactional
    public boolean addDrinkToUsr(Usr usr, Drink drink){
        if (usr.addDrink(drink) && drink.addUsr(usr)) {
            drinkRepo.save(drink);
            usrRepo.save(usr);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean deleteDrinkOnUsr(Usr usr, Drink drink){
        int indexToDelete = usr.getDrinks().lastIndexOf(drink);
        usr.getDrinks().remove(indexToDelete);
        return true;
    }

    @Transactional(readOnly = true)
    public List<Drink> basketList(Usr usr){
        List<Drink> drinks = usr.getDrinks();
        if (drinks.isEmpty() || drinks == null) return null;
        drinks = formattedListToBasket(drinks);
        Collections.sort(drinks, new Comparator<Drink>() {
            @Override
            public int compare(Drink drink1, Drink drink2) {
                return drink1.getName().compareTo(drink2.getName());
            }
        });
        return drinks;
    }

    private List<Drink> formattedListToBasket(List<Drink> drinks) {
        for (int i=0; i<drinks.size(); i++){
            for (int j=0; j<drinks.size(); j++){
                if (drinks.get(i).getId() == drinks.get(j).getId()){
                    if (drinks.get(i).getQuantity() == null) drinks.get(i).setQuantity(0);
                    drinks.get(i).setQuantity(drinks.get(i).getQuantity() + 1);
                    if (drinks.get(i).getQuantity() != 1)
                        drinks.get(i).setPrice(drinks.get(i).getPrice() +
                                drinks.get(i).getPrice()/(drinks.get(i).getQuantity()-1));
                    if (i == j) continue;
                    drinks.remove(j);
                    j--;
                }
            }
        }
        return drinks;
    }
}
