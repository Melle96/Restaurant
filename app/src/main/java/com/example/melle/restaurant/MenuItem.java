package com.example.melle.restaurant;

import java.io.Serializable;

// class voor menu eigenschappen maken
    public class MenuItem implements Serializable{
    private String description, menuname,  sort_menu, url;
    private Double price;

    // naam, beschrijving, plaatje, prijs en menusoort worden allen opgeslagen
    MenuItem(String menuname, String description, String url, Double price, String sort_menu) {
        this.url = url;
        this.menuname = menuname;
        this.price = price;
        this.description = description;
        this.sort_menu = sort_menu;

    }

    String getUrl() {
        return url;
    }

    String getDescription() {
        return description;
    }

    Double getPrice() {
        return price;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

}
