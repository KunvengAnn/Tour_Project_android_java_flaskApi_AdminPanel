package com.example.screen_ui.models.common;

import com.google.gson.annotations.SerializedName;
import java.util.Date;

public class Customer {
    @SerializedName("customer_id")
    private int customer_id;
    @SerializedName("title")
    private String title;
    @SerializedName("first_name")
    private String first_name;
    @SerializedName("last_name")
    private String last_name;
    @SerializedName("customer_phone")
    private String customer_phone;
    @SerializedName("customer_email")
    private String customer_email;
    @SerializedName("customer_from_country")
    private String customer_from_country;
    @SerializedName("Date_of_department")
    private String Date_of_department;

    public Customer() {}

    public Customer(int customer_id, String title, String first_name, String last_name, String customer_phone, String customer_email, String customer_from_country, String Date_of_department) {
        this.customer_id = customer_id;
        this.title = title;
        this.first_name = first_name;
        this.last_name = last_name;
        this.customer_phone = customer_phone;
        this.customer_email = customer_email;
        this.customer_from_country = customer_from_country;
        this.Date_of_department = Date_of_department;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getCustomer_from_country() {
        return customer_from_country;
    }

    public void setCustomer_from_country(String customer_from_country) {
        this.customer_from_country = customer_from_country;
    }

    public String getDate_of_department() {
        return Date_of_department;
    }

    public void setDate_of_department(String Date_of_department) {
        this.Date_of_department = Date_of_department;
    }
}
