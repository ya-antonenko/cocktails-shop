package com.cocktail.repos;

import com.cocktail.domain.Usr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsrRepo extends JpaRepository<Usr,Long> {
    Usr findByUsername(String username);

    @Query("select c from Usr c where c.password = :password and c.username = :username")
    Usr findUser(@Param("password") String password, @Param("username") String username);
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Usr u WHERE u.username =:username")
    boolean existsByUsername(@Param("username") String username);
    Usr findByUsernameAndPassword(String username, String password);
    Usr findByActivationCode(String ActivationCode);
    Usr findByUsernameAndEmail(String username, String email);
}
