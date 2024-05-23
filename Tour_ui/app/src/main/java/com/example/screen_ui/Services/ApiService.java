package com.example.screen_ui.Services;

import com.example.screen_ui.models.BookingDetailResponse;
import com.example.screen_ui.models.BookingResponse;
import com.example.screen_ui.models.CustomerResponse;
import com.example.screen_ui.models.PackageTourResponse;
import com.example.screen_ui.models.PaymentResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @GET("tours") //endPoint
    Call<PackageTourResponse> getTours();

    @GET("customer") // endpoint
    Call<CustomerResponse> getCustomerResLs();

    @POST("customer") // endpoint
    Call<Void> setCustomerResLs(@Body RequestBody requestBody);

    @POST("booking")
    Call<Void> setBooking(@Body RequestBody requestBody);
    @GET("booking")
    Call<BookingResponse> getBookingLs();

    @GET("payment")
    Call<PaymentResponse> getPaymentResLs();
    @POST("payment")
    Call<Void> setPayment(@Body RequestBody requestBody);

    @GET("booking/{booking_id}") // endPoint get Booking by id
    Call<BookingDetailResponse> getBookingById(@Path("booking_id") int bookingId);

}