package com.example.screen_ui.models;

import com.example.screen_ui.models.common.BookingDetail;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookingDetailResponse {
    @SerializedName("booking")
    private BookingDetail bookingDetail;

    public BookingDetail getBookingDetail() {
        return bookingDetail;
    }
}
