package com.example.screen_ui.models;

import com.example.screen_ui.models.common.Payment;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PaymentResponse {
    @SerializedName("payments")
    private List<Payment> paymentsLs;

    private List<Payment> getPaymentsLs(){
        return paymentsLs;
    }

}
