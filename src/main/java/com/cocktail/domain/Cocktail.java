package com.cocktail.domain;

import javax.persistence.*;

@Entity
@Table
public class Cocktail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String nameCocktail;
    @Column
    private String ingredients;
    @Column
    private String ingredientsEng;
    @Column
    private String recipe;
    @Column
    private String nameCocktailEng;
    @Column
    private String logo;

    private Integer quantity;

    public Cocktail(String nameCocktail, String ingredients, String ingredientsEng, String recipe, String logo) {
        this.nameCocktail = nameCocktail;
        this.ingredients = ingredients;
        this.ingredientsEng = ingredientsEng;
        this.recipe = recipe;
        this.logo = logo;
    }

    public Cocktail fromCocktailDTO(CocktailDTO cocktailDTO){
        return new Cocktail(cocktailDTO.getNameCocktail(), cocktailDTO.getIngredients(),
                cocktailDTO.getIngredientsEng(), cocktailDTO.getRecipe(), cocktailDTO.getLogo());
    }

    public CocktailDTO toCocktailDTO(){
        return new CocktailDTO(this.getNameCocktail(), this.getIngredients(),
                this.getIngredientsEng(), this.getRecipe(), this.getLogo());
    }

    public Cocktail() {
    }

    public Long getId() {
        return id;
    }

    public String getIngredientsEng() {
        return ingredientsEng;
    }

    public void setIngredientsEng(String ingredientsEng) {
        this.ingredientsEng = ingredientsEng;
    }

    public String getNameCocktail() {
        return nameCocktail;
    }

    public void setNameCocktail(String nameCocktail) {
        this.nameCocktail = nameCocktail;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getNameCocktail_eng() {
        return nameCocktailEng;
    }

    public void setNameCocktail_eng(String nameCocktail_eng) {
        this.nameCocktailEng = nameCocktail_eng;
    }

    public String getNameCocktailEng() {
        return nameCocktailEng;
    }

    public void setNameCocktailEng(String nameCocktailEng) {
        this.nameCocktailEng = nameCocktailEng;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
