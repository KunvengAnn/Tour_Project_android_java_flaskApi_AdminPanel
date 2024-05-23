package com.example.screen_ui;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.screen_ui.Helper.AdapterRecycler;
import com.example.screen_ui.Helper.AdapterRecyclerTwo;
import com.example.screen_ui.Services.ApiClient;
import com.example.screen_ui.Services.ApiService;
import com.example.screen_ui.data.DataTour;
import com.example.screen_ui.models.ItemClass;
import com.example.screen_ui.models.ItemDt2;
import com.example.screen_ui.models.PackageTourResponse;
import com.example.screen_ui.models.common.PackageTour;
import com.example.screen_ui.pages.AboutUsPage;
import com.example.screen_ui.pages.CheckBillPage;
import com.example.screen_ui.utils.LoadingDialogUtils;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ApiService apiService;
    private RecyclerView recyclerView;
    private AdapterRecycler adapterRecycler;
    private AdapterRecyclerTwo adapterRecycler2;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;
    private LinearLayout showContentError;
    private TextView textViewWhenError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //            LoadingDialogUtils.showCustomLoadingDialog(this);
//            // dismiss the loading dialog after some delay
//            new android.os.Handler().postDelayed(
//                    new Runnable() {
//                        public void run() {
//                            LoadingDialogUtils.dismissCustomLoadingDialog();
//                        }
//                    }, 3000);


        setUpToolBar();
        NavigationView navigationView = findViewById(R.id.nav_main_view);
        navigationView.setNavigationItemSelectedListener(this); // Set the listener here

        fetchToursData();
    }

    // end onCreated
    private void showContentError() {
        showContentError = findViewById(R.id.id_contentWhenError);
        textViewWhenError = findViewById(R.id.id_TextViewWhenError);

        showContentError.setVisibility(View.VISIBLE);
        //showContentError.setVisibility(View.GONE);
    }


    private void setUpToolBar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(""); // hide default Title

        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void showMessageToast(String msg, Context context) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    private void Loading() {
        LoadingDialogUtils.showCustomLoadingDialog(this);
    }

    private void DisableLoading() {
        LoadingDialogUtils.dismissCustomLoadingDialog();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();

        if (itemId == R.id.nav_item1) {
            // AboutUs page
            Log.d("Navigation", "AboutUs item clicked");
            Intent intent = new Intent(this, AboutUsPage.class);
            startActivity(intent);
            drawerLayout.closeDrawer(GravityCompat.START); // Close drawer
            return true;
        } else if (itemId == R.id.nav_item2) {
            // check bill page
            Intent intent = new Intent(this, CheckBillPage.class);
            startActivity(intent);
            drawerLayout.closeDrawer(GravityCompat.START); // Close drawer
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void setupRecyclerView(List<PackageTour> tours) {
        recyclerView = findViewById(R.id.id_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        List<Integer> drawableList = new ArrayList<>();
        List<ItemClass> ItemList = DataTour.listDataTour();

        for (ItemClass item : ItemList) {
            drawableList.add(item.getDrawableList().get(0));
        }

        List<String> nameTourList = new ArrayList<>();
        List<String> priceTourList = new ArrayList<>();
        List<Integer> idTourLs = new ArrayList<>();

        for (PackageTour tour : tours) {
            if (tour.getTour_id() <= 5) {
                nameTourList.add(tour.getTour_name());
                priceTourList.add(String.valueOf(tour.getTour_price()));
                idTourLs.add(tour.getTour_id());
            }
        }

        adapterRecycler = new AdapterRecycler(MainActivity.this, drawableList, nameTourList, priceTourList, idTourLs, tours);
        recyclerView.setAdapter(adapterRecycler);
        adapterRecycler.notifyDataSetChanged();
    }

    private void fetchToursData() {
        Loading();
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<PackageTourResponse> call = apiService.getTours();
        call.enqueue(new Callback<PackageTourResponse>() {
            @Override
            public void onResponse(Call<PackageTourResponse> call, Response<PackageTourResponse> response) {
                if (response.isSuccessful()) {
                    DisableLoading();
                    PackageTourResponse tourResponse = response.body();
                    if (tourResponse != null) {
                        List<PackageTour> tours = tourResponse.getTours();

                        setupRecyclerView(tours);
                        setupRecyclerViewTwo(tours);
                    }
                } else {
                    showContentError();
                    textViewWhenError.setText("Failed to fetch tours: " + response.message());
                    // Handle unsuccessful response
                    Log.e("Network Error", "Failed to fetch tours: " + response.message());
                    showMessageToast("Failed to fetch tours:" + response.message(), MainActivity.this);
                }
            }

            @Override
            public void onFailure(Call<PackageTourResponse> call, Throwable t) {
                DisableLoading();
                showContentError();
                textViewWhenError.setText("Failed to fetch tours:" + t.getMessage());
                // Handle failure
                showMessageToast("Failed to fetch tours:" + t.getMessage(), MainActivity.this);
                Log.e("Network Error", "OnFailure Failed to fetch tours: " + t.getMessage());
            }
        });
    }

    private void setupRecyclerViewTwo(List<PackageTour> tours) {
        recyclerView = findViewById(R.id.id_recycler2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        List<Integer> drawableList = new ArrayList<>();
        List<String> nameTourList = new ArrayList<>();
        List<String> priceTourList = new ArrayList<>();
        List<Integer> idTourLs = new ArrayList<>();

        for (PackageTour tour : tours) {
            if (tour.getTour_id() > 5) {
                nameTourList.add(tour.getTour_name());
                priceTourList.add(String.valueOf(tour.getTour_price()));
                idTourLs.add(tour.getTour_id());
            }
        }

        List<ItemDt2> ItemPList = DataTour.listDataTourDetail();
        for (ItemDt2 item : ItemPList) {
            drawableList.add(item.getImage().get(0));
        }

        adapterRecycler2 = new AdapterRecyclerTwo(this, nameTourList, priceTourList, drawableList, idTourLs, tours);
        recyclerView.setAdapter(adapterRecycler2);
        adapterRecycler2.notifyDataSetChanged();
    }
}