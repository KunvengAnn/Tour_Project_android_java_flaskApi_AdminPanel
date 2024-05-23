package com.example.screen_ui.models.common;

import com.google.gson.annotations.SerializedName;

public class PackageTour {
    @SerializedName("tour_id")
    private int tour_id;
    @SerializedName("tour_name")
    private String tour_name;
    @SerializedName("tour_description")
    private String tour_description;
    @SerializedName("tour_duration")
    private int tour_duration;
    @SerializedName("tour_price")
    private double tour_price;
    @SerializedName("tour_location")
    private String tour_location;

    public PackageTour() {}

    public PackageTour(int tour_id, String tour_name, String tour_description, int tour_duration, double tour_price, String tour_location) {
        this.tour_id = tour_id;
        this.tour_name = tour_name;
        this.tour_description = tour_description;
        this.tour_duration = tour_duration;
        this.tour_price = tour_price;
        this.tour_location = tour_location;
    }

    public void setTour_id(int tour_id){
        this.tour_id = tour_id;
    }
    public int getTour_id(){
        return tour_id;
    }
    public void setTour_name(String tour_name){
        this.tour_name = tour_name;
    }
    public String getTour_name(){
        return tour_name;
    }
    public void setTour_description(String tour_description){
        this.tour_description = tour_description;
    }
    public String getTour_description(){
        return tour_description;
    }
    public void setTour_duration(int tour_duration){
        this.tour_duration = tour_duration;
    }
    public int getTour_duration(){
        return tour_duration;
    }
    public void setTour_price(double tour_price){
        this.tour_price = tour_price;
    }
    public double getTour_price(){
        return tour_price;
    }
    public void setTour_location(String tourLocation){
        this.tour_location = tourLocation;
    }
    public String getTour_location(){
        return tour_location;
    }
}
