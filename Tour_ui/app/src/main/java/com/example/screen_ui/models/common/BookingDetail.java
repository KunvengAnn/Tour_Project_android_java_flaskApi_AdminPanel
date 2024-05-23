package com.example.screen_ui.models.common;

import com.google.gson.annotations.SerializedName;
import java.util.Date;

public class BookingDetail {

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

    @SerializedName("title")
    private String title;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    @SerializedName("customer_phone")
    private String customerPhone;

    @SerializedName("customer_email")
    private String customerEmail;

    @SerializedName("customer_from_country")
    private String customerFromCountry;

    @SerializedName("Date_of_department")
    private String dateOfDepartment;

    @SerializedName("payment_id")
    private int paymentId;

    @SerializedName("payment_date")
    private String paymentDate;

    @SerializedName("amount_paid")
    private double amountPaid;

    @SerializedName("payment_status")
    private String paymentStatus;

    @SerializedName("tour_name")
    private String tourName;

    @SerializedName("tour_description")
    private String tourDescription;

    @SerializedName("tour_duration")
    private int tourDuration;

    @SerializedName("tour_price")
    private double tourPrice;

    @SerializedName("tour_location")
    private String tourLocation;

    // Getters and Setters

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerFromCountry() {
        return customerFromCountry;
    }

    public void setCustomerFromCountry(String customerFromCountry) {
        this.customerFromCountry = customerFromCountry;
    }

    public String getDateOfDepartment() {
        return dateOfDepartment;
    }

    public void setDateOfDepartment(String dateOfDepartment) {
        this.dateOfDepartment = dateOfDepartment;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getTourDescription() {
        return tourDescription;
    }

    public void setTourDescription(String tourDescription) {
        this.tourDescription = tourDescription;
    }

    public int getTourDuration() {
        return tourDuration;
    }

    public void setTourDuration(int tourDuration) {
        this.tourDuration = tourDuration;
    }

    public double getTourPrice() {
        return tourPrice;
    }

    public void setTourPrice(double tourPrice) {
        this.tourPrice = tourPrice;
    }

    public String getTourLocation() {
        return tourLocation;
    }

    public void setTourLocation(String tourLocation) {
        this.tourLocation = tourLocation;
    }
}
