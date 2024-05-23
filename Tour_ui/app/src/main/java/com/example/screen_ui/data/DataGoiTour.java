//package com.example.screen_ui.dataHelper;
//
//import com.example.screen_ui.Services.ApiService;
//import com.example.screen_ui.models.GoiTour;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class DataGoiTour {
//    private static ApiService apiService;
//
//    public static void getGoiTourData(){
//        Call<GoiTour> call = apiService.getGoiTour();
//        call.enqueue(new Callback<GoiTour>() {
//            @Override
//            public void onResponse(Call<GoiTour> call, Response<GoiTour> response) {
//                if (response.isSuccessful()) {
//                    GoiTour goiTour = response.body();
//
//                } else {
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<GoiTour> call, Throwable t) {
//
//            }
//        });
//    }
//}
