package com.cocktail.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Drink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private String volume;
    @Column(nullable = false)
    private String image;
    @Column(nullable = false)
    private String basis;
    @Column(nullable = false)
    private String nameEng;
    private Integer quantity;
    @ManyToMany(mappedBy = "drinks", cascade = CascadeType.ALL)
    List<Usr> usrList = new ArrayList<>();

    public Drink(String name, Double price, String volume, String image, String basis, String nameEng) {
        this.name = name;
        this.price = price;
        this.volume = volume;
        this.image = image;
        this.basis = basis;
        this.nameEng = nameEng;
    }

    public Drink(){
        super();
    }

    public boolean addUsr(Usr usr){
        usrList.add(usr);
        return true;
    }

    public Drink fromDrinkDTO(DrinkDTO drinkDTO){
        return new Drink(drinkDTO.getName(),drinkDTO.getPrice(),drinkDTO.getVolume(),
                drinkDTO.getImage(),drinkDTO.getBasis(),drinkDTO.getNameEng());
    }

    public DrinkDTO toDrinkDTO(){
        return new DrinkDTO(this.getName(),this.getPrice(),this.getVolume(),this.getImage(),this.getBasis(),
                this.getNameEng());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBasis() {
        return basis;
    }

    public void setBasis(String basis) {
        this.basis = basis;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public List<Usr> getUsrList() {
        return usrList;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
