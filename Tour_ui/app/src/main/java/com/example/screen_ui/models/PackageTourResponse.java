package com.example.screen_ui.models;

import com.example.screen_ui.models.common.PackageTour;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PackageTourResponse {
    @SerializedName("tours")
    private List<PackageTour> packageTours;

    public List<PackageTour> getTours() {
        return packageTours;
    }

    public void setTours(List<PackageTour> packageTours) {
        this.packageTours = packageTours;
    }
}