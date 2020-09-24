package com.cocktail.domain;

public class DrinkDTO {
    private String name;
    private Double price;
    private String volume;
    private String image;
    private String basis;
    private String nameEng;

    public DrinkDTO(String name, Double price, String volume, String image, String basis, String nameEng) {
        this.name = name;
        this.price = price;
        this.volume = volume;
        this.image = image;
        this.basis = basis;
        this.nameEng = nameEng;
    }

    public DrinkDTO(){
        super();
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
}
