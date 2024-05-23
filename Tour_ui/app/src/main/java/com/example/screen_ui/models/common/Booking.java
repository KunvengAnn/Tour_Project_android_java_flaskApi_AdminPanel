package com.example.screen_ui.models.common;

import com.google.gson.annotations.SerializedName;

public class Booking {
    @SerializedName("booking_id")
    private int bookingId;

    @SerializedName("tour_id")
    private int tourId;

    @SerializedName("customer_id")
    private int customerId;

    @SerializedName("booking_date")
    private String bookingDate;

    @SerializedName("number_of_child")
    private int numberOfChild;

    @SerializedName("number_of_adult")
    private int numberOfAdult;

    @SerializedName("total_price")
    private double totalPrice;

    public Booking(){}
    public Booking(int bookingId, int tourId, int customerId, String bookingDate, int numberOfChild, int numberOfAdult, double totalPrice) {
        this.bookingId = bookingId;
        this.tourId = tourId;
        this.customerId = customerId;
        this.bookingDate = bookingDate;
        this.numberOfChild = numberOfChild;
        this.numberOfAdult = numberOfAdult;
        this.totalPrice = totalPrice;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getNumberOfChild() {
        return numberOfChild;
    }

    public void setNumberOfChild(int numberOfChild) {
        this.numberOfChild = numberOfChild;
    }

    public int getNumberOfAdult() {
        return numberOfAdult;
    }

    public void setNumberOfAdult(int numberOfAdult) {
        this.numberOfAdult = numberOfAdult;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
