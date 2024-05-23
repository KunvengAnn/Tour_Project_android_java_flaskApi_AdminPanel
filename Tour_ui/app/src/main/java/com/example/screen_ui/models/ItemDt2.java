package com.example.screen_ui.models;

import java.util.List;

public class ItemDt2 {
    private String title;
    private String subTile;
    private String price;
    private List<Integer> image;

    public ItemDt2(String title, String subTile, String price, List<Integer> image){
        this.title = title;
        this.subTile = subTile;
        this.price = price;
        this.image = image;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setSubTile(String subTile){
        this.subTile = subTile;
    }
    public String getSubTile(){
        return this.subTile;
    }

    public void setPrice(String price){
        this.price = price;
    }
    public String getPrice(){
        return this.price;
    }
    public void setImage(List<Integer> image){
        this.image = image;
    }
    public List<Integer> getImage(){
        return this.image;
    }
}
