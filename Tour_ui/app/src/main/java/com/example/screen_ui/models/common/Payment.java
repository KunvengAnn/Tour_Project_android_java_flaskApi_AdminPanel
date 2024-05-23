package com.example.screen_ui.models.common;

import com.google.gson.annotations.SerializedName;

public class Payment {

    @SerializedName("payment_id")
    private int paymentId;

    @SerializedName("booking_id")
    private int bookingId;

    @SerializedName("payment_date")
    private String paymentDate;

    @SerializedName("amount_paid")
    private double amountPaid;

    @SerializedName("payment_status")
    private int paymentStatus;

    // Getters and Setters
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
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

    public int getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(int paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
