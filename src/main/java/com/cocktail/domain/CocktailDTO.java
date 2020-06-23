package com.cocktail.domain;

public class CocktailDTO {
    private String nameCocktail;
    private String ingredients;
    private String ingredientsEng;
    private String recipe;
    private String logo;

    public CocktailDTO(String nameCocktail, String ingredients, String ingredientsEng, String recipe, String logo) {
        this.nameCocktail = nameCocktail;
        this.ingredients = ingredients;
        this.ingredientsEng = ingredientsEng;
        this.recipe = recipe;
        this.logo = logo;
    }

    public CocktailDTO() {
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
}
