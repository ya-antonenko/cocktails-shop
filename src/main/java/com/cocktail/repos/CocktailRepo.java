package com.cocktail.repos;

import com.cocktail.domain.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.xml.transform.sax.SAXTransformerFactory;
import java.util.List;

@Repository
public interface CocktailRepo extends JpaRepository<Cocktail, Long> {
    @Query("select c from Cocktail c where c.ingredientsEng like :drink")
    List<Cocktail> searchByIngredientsEng(@Param("drink") String drink);
    @Query("select c from Cocktail c where c.ingredients like :drink")
    List<Cocktail> searchByIngredient(@Param("drink") String drink);
    Cocktail findCocktailById(long id);
    Cocktail findCocktailByNameCocktailEng(String nameCocktailEng);
    @Query("select c from Cocktail c where c.nameCocktail like :drink")
    Cocktail findByNameCocktailLike(@Param("drink") String drink);
    List<Cocktail> findByIngredientsContaining(String ingredients);
}
