package com.cocktail.services;

import com.cocktail.domain.Drink;
import com.cocktail.domain.Roles;
import com.cocktail.domain.Usr;
import com.cocktail.repos.UsrRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UsrRepo usrRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MailSender mailSender;

    @Transactional(readOnly = true)
    public Usr findByUsername(String username){
        return usrRepo.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public Usr findByUsernameAndPassword(String  username, String password){
        return usrRepo.findByUsernameAndPassword(username, password);
    }

    @Transactional
    public boolean addUser(String username, String password, String email){
        Usr usrVerification = usrRepo.findByUsernameAndEmail(username, email);
        if (usrVerification != null) return false;
        Usr usr = new Usr();
        usr.setEmail(email);
        usr.setUsername(username);
        usr.setActive(false);
        usr.setRoles(Roles.USER);
        usr.setPassword(passwordEncoder.encode(password));
        usr.setActivationCode(UUID.randomUUID().toString());
        usrRepo.save(usr);
        String massage = String.format(
                "Hello, %s! \n" +
                        "Welcome to our site. Please, visit next link: " +
                        "http://localhost:8080/activate/%s",
                usr.getUsername(), usr.getActivationCode()
        );

        mailSender.send(email, "Activation code", massage);

        return true;
    }

    @Transactional
    public boolean activatedUsr(String code) {
        Usr usr = usrRepo.findByActivationCode(code);
        if (usr == null) return false;
        usr.setActive(true);
        usr.setActivationCode(null);
        usrRepo.save(usr);
        return true;
    }

    @Transactional
    public boolean deleteListBasket(String username){
        Usr currentUsr = usrRepo.findByUsername(username);
        if (currentUsr == null) return false;
        currentUsr.setDrinks(new ArrayList<Drink>());
        usrRepo.save(currentUsr);
        return true;
    }
}
