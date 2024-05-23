package com.example.screen_ui.models;

import com.example.screen_ui.models.common.Customer;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CustomerResponse {
    @SerializedName("customers")
    private List<Customer> customerResLs;


    //    public void setCustomerResLs(CustomerTour CustomerRes){
//        this.CustomerResLs = CustomerResLs;
//    }
    public List<Customer> getCustomerResLs(){
        return customerResLs;
    }

}
