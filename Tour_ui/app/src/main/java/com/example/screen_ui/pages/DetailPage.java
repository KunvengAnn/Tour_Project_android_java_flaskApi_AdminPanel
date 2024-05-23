package com.example.screen_ui.pages;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.screen_ui.Helper.AdapterRecyclerDetail;
import com.example.screen_ui.MainActivity;
import com.example.screen_ui.R;
import com.example.screen_ui.data.DataTour;
import com.example.screen_ui.models.ItemClass;
import com.example.screen_ui.models.ItemDt2;
import java.util.ArrayList;
import java.util.List;

import com.example.screen_ui.models.common.PackageTour;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class DetailPage extends AppCompatActivity {

    private int selectedIndex = 0;
    private RecyclerView recyclerView;
    private AdapterRecyclerDetail adapterRecyclerDetail;
    private TextView NameTourTextView;
    private TextView priceTextView;
    private TextView desciptionTextView;
    private ImageView imgBtnBack;
    private CardView cardFloatingBtn;
    private TextView LocationDetailTextView;
    private TextView durationTextView;
    ////////////////
    private String tourNameG = "";
    private String tourLocationG = "";
    private int tourDurationG = 0;


    ////////////
    String tourLsJson = "";
    List<PackageTour> tours;
    int idTour = 0;
    double priceTour = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_page);

        btnBackHome();
        setupRecyclerView();
        cardFloatingBtn();
    }
    //end onCreate
    private void cardFloatingBtn() {
        //btn booking
        cardFloatingBtn = findViewById(R.id.id_cardFloatBtn);
        cardFloatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailPage.this, InfoPage.class);
                intent.putExtra("selected_index", selectedIndex);

                intent.putExtra("idTour", idTour);
                String tourLsJson = new Gson().toJson(tours);
                intent.putExtra("lsTour", tourLsJson);
                intent.putExtra("priceTour",priceTour);

                intent.putExtra("tourName",tourNameG);
                intent.putExtra("tourLocation",tourLocationG);
                intent.putExtra("tourDuration",tourDurationG);

                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

    private void btnBackHome() {
        imgBtnBack = findViewById(R.id.id_btn_back);
        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailPage.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

    private void setupRecyclerView() {
        NameTourTextView = findViewById(R.id.id_NameTourDetail);
        priceTextView = findViewById(R.id.id_priceDetail);
        desciptionTextView = findViewById(R.id.id_descriptionDetails);
        LocationDetailTextView = findViewById(R.id.id_LocationDetail);
        durationTextView = findViewById(R.id.id_Duration);

        tourLsJson = getIntent().getStringExtra("lsTour");
        tours = new Gson().fromJson(tourLsJson, new TypeToken<List<PackageTour>>() {
        }.getType());
        idTour = getIntent().getIntExtra("idTour", -1);

        if (tours != null && !tours.isEmpty()) {
            for (PackageTour tour : tours) {
                if (idTour == tour.getTour_id()) {
                    priceTour = tour.getTour_price();
                    priceTextView.setText(String.valueOf(tour.getTour_price()) + "$");
                    NameTourTextView.setText(tour.getTour_name());
                    desciptionTextView.setText(tour.getTour_description());
                    durationTextView.setText(String.valueOf(tour.getTour_duration())+ "h");
                    LocationDetailTextView.setText(tour.getTour_location());

                    tourNameG = tour.getTour_name();
                    tourLocationG = tour.getTour_location();
                    tourDurationG = tour.getTour_duration();
                    break; // Exit the loop once matching is found
                }
            }
        }
        // Get the index passed from the previous activity
        selectedIndex = getIntent().getIntExtra("selected_index", -1);

        recyclerView = findViewById(R.id.id_recyclerDetail);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        List<Integer> drawableList = new ArrayList<>();
        String recyclerTwo = getIntent().getStringExtra("recyclerTwo");
        Log.d("data", "data recyclerTwo: " + recyclerTwo);

        List<ItemClass> itemList = DataTour.listDataTour();
        if (itemList == null || itemList.isEmpty()) {
            Toast.makeText(this, "something went wrong with detail pages", Toast.LENGTH_LONG).show();
        } else {
            if (selectedIndex >= 0 && selectedIndex < itemList.size()) {
                drawableList.addAll(itemList.get(selectedIndex).getDrawableList());
            } else {
                Toast.makeText(this, "Invalid selected index", Toast.LENGTH_LONG).show();
            }
        }
        if (recyclerTwo != null && recyclerTwo.equals("twoRc")) {
            List<ItemDt2> itemDt2 = DataTour.listDataTourDetail();
            drawableList.clear();
            drawableList.addAll(itemDt2.get(selectedIndex).getImage());
        }

        adapterRecyclerDetail = new AdapterRecyclerDetail(this, drawableList);
        recyclerView.setAdapter(adapterRecyclerDetail);
        adapterRecyclerDetail.notifyDataSetChanged();
    }
}
