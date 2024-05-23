package com.example.screen_ui.models;

import com.example.screen_ui.models.common.Booking;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookingResponse {
    @SerializedName("bookings")
    private List<Booking> bookingLs;

    public List<Booking> getBookingLs(){
        return bookingLs;
    }
}
