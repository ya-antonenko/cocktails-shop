package com.cocktail.repos;

import com.cocktail.domain.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrinkRepo extends JpaRepository<Drink, Long> {
    @Query("select c from Drink c")
    List<Drink> findAll();
    @Query("select c from Drink c where c.name like :drink")
    List<Drink> findSearchResultFromName(@Param("drink") String drink);
    @Query("select c from Drink c where c.basis like :drink")
    List<Drink> findSearchResultFromBasis(@Param("drink") String drink);
    Drink findByNameEng(String nameEng);
}
