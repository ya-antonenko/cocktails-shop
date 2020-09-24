package com.cocktail.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Usr {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private boolean active;
    @Column
    private String email;
    @Column
    private String activationCode;
    private Integer totalPrice;
    @ManyToMany
    @JoinTable(name = "basket",
            joinColumns = {@JoinColumn(name = "usr_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "drink_id", referencedColumnName = "id")})
    List<Drink> drinks = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Roles roles;

    public Usr(String username, String password, boolean active, String email, Roles roles) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.email = email;
        this.roles = roles;
    }

    public boolean addDrink(Drink drink){
        drinks.add(drink);
        return true;
    }

    public UsrDTO toDTO() {
        return new UsrDTO(this.getUsername(), this.getPassword(), this.isActive(), this.getActivationCode(),
                this.getRoles());
    }

    public Usr fromDTO(UsrDTO usrDTO) {
        return new Usr(usrDTO.getUsername(), usrDTO.getPassword(), usrDTO.isActive(), usrDTO.getActivationCode(),
                usrDTO.getRoles());
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public Usr() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }
}
