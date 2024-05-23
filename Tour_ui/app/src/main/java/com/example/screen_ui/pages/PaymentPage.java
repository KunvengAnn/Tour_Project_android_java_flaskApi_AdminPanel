package com.example.screen_ui.pages;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.screen_ui.R;
import com.example.screen_ui.Services.ApiClient;
import com.example.screen_ui.Services.ApiService;
import com.example.screen_ui.models.BookingResponse;
import com.example.screen_ui.models.CustomerResponse;
import com.example.screen_ui.models.common.Booking;
import com.example.screen_ui.models.common.Customer;
import com.example.screen_ui.models.common.PackageTour;
import com.example.screen_ui.models.common.Payment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentPage extends AppCompatActivity {
    private Customer customer = new Customer();
    private ImageView ImgBtnBackToInfoPage;
    private CardView cardViewBtnSumite;
    private String formattedDate = "";

    /////////////
    private TextView py_TitleTextView, py_FirstNameTextView, py_LastNameTextView, py_EmailTextView, py_TelePhTextView, py_CountryTextView, py_TourNameTextView, py_TourPriceTextView, py_TourLocationTextView;
    private TextView py_TourDurationTextView, NumberChildTextView, NumberAdultTextView, py_TotalAmountTextView, py_DateDepartureTextView;

    ///////////////////
    private double TotalPriceG = 0.0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_page);

        setUpAllField_Id_Initial();
        getIntentFromOtherActivity();
        backToInfoPage();
        insertDataCustomer();
        BtnSubmit();

    }
    //end of onCreate

    private void getIntentFromOtherActivity() {
        Intent intent = getIntent();
        if (intent != null) {
            String dateOfDepartment = intent.getStringExtra("Date_of_department");
            String lastName = intent.getStringExtra("Last_name");
            String firstName = intent.getStringExtra("First_name");
            String customerEmail = intent.getStringExtra("Customer_email");
            String customerPhone = intent.getStringExtra("Customer_phone");
            String customerFromCountry = intent.getStringExtra("Customer_from_country");
            String title = intent.getStringExtra("Title");

            int QuantityChild = intent.getIntExtra("QuantityChild", 0);
            int QuantityAdult = intent.getIntExtra("QuantityAdult", 0);

            formattedDate = dateOfDepartment;

            double priceTourFromDetail = getIntent().getDoubleExtra("priceTour", -1);

            double TotalPrice = getIntent().getDoubleExtra("TotalPrice", 0.0);
            TotalPriceG = TotalPrice;

            String tourName = getIntent().getStringExtra("tourName");
            String tourLocation = getIntent().getStringExtra("tourLocation");
            int tourDuration = getIntent().getIntExtra("tourDuration", 0);

            py_TitleTextView.setText(title);
            py_FirstNameTextView.setText(firstName);
            py_LastNameTextView.setText(lastName);
            py_EmailTextView.setText(customerEmail);
            py_TelePhTextView.setText(customerPhone);
            py_CountryTextView.setText(customerFromCountry);
            py_TourPriceTextView.setText(String.valueOf(priceTourFromDetail) + " $");
            NumberChildTextView.setText(String.valueOf(QuantityChild));
            NumberAdultTextView.setText(String.valueOf(QuantityAdult));

            py_TourLocationTextView.setText(tourLocation);
            py_TourDurationTextView.setText(String.valueOf(tourDuration));
            py_TourNameTextView.setText(tourName);
            py_TotalAmountTextView.setText(String.valueOf(TotalPrice) + " $");
            py_DateDepartureTextView.setText(formattedDate);
        }
    }

    private void setUpAllField_Id_Initial() {
        py_TitleTextView = findViewById(R.id.py_TitleTextView);
        py_FirstNameTextView = findViewById(R.id.py_FirstNameTextView);
        py_LastNameTextView = findViewById(R.id.py_LastNameTextView);
        py_EmailTextView = findViewById(R.id.py_EmailTextView);
        py_TelePhTextView = findViewById(R.id.py_TelePhTextView);
        py_CountryTextView = findViewById(R.id.py_CountryTextView);
        py_TourNameTextView = findViewById(R.id.py_TourNameTextView);
        py_TourPriceTextView = findViewById(R.id.py_TourPriceTextView);
        py_TourLocationTextView = findViewById(R.id.py_TourLocationTextView);
        py_TourDurationTextView = findViewById(R.id.py_TourDurationTextView);
        NumberChildTextView = findViewById(R.id.NumberChildTextView);
        NumberAdultTextView = findViewById(R.id.NumberAdultTextView);
        py_TotalAmountTextView = findViewById(R.id.py_TotalAmountTextView);
        py_DateDepartureTextView = findViewById(R.id.py_DateDepartureTextView);
    }

    private void BtnSubmit() {
        cardViewBtnSumite = findViewById(R.id.cardViewBtnSumite);
        cardViewBtnSumite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    public String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date());
    }

    private void getBookingData() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        Call<BookingResponse> getCall = apiService.getBookingLs();

        getCall.enqueue(new Callback<BookingResponse>() {
            @Override
            public void onResponse(Call<BookingResponse> call, Response<BookingResponse> response) {
                if (response.isSuccessful()) {
                    BookingResponse bookingResponse = response.body();
                    // after get getBookingData success
                    if (bookingResponse != null) {
                        List<Booking> bookingList = bookingResponse.getBookingLs();

                        if (bookingList != null) {
                            if (!bookingList.isEmpty()) {
                                Booking lastBooking = bookingList.get(bookingList.size() - 1);
                                int booking_id = lastBooking.getBookingId();

                                insertDataPayment(booking_id,TotalPriceG,0); //mean status = false

                                Log.d("Info data", "Last booking ID: " + booking_id);
                                Log.d("Info data", "Last booking name: " + lastBooking.getCustomerId());
                            } else {
                                Log.d("Info data", "No bookings found");
                            }
                        } else {
                            Log.d("Info data", "Booking list is null");
                        }
                    } else {
                        showMessageToast("Booking response body is null", PaymentPage.this);
                    }
                } else {
                    showMessageToast("Failed to get booking resources", PaymentPage.this);
                }
            }


            @Override
            public void onFailure(Call<BookingResponse> call, Throwable t) {
                Log.e("Info", "Failed to get booking resources", t);
                showMessageToast("Failed to get booking resources" + t, PaymentPage.this);
            }
        });
    }

    private void insertBooking(int customer_id) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        int idTour = getIntent().getIntExtra("idTour", -1);
        Booking booking = new Booking();

        booking.setTourId(idTour);
        booking.setCustomerId(customer_id);

        String currentDate = getCurrentDate();
        booking.setBookingDate(currentDate);
        booking.setNumberOfAdult(Integer.parseInt(NumberAdultTextView.getText().toString().trim()));
        booking.setNumberOfChild(Integer.parseInt(NumberChildTextView.getText().toString().trim()));
        booking.setTotalPrice(TotalPriceG);

        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(booking);
        Log.d("Info", "JSON booking: " + json);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json);
        Call<Void> insertBooking = apiService.setBooking(requestBody);
        insertBooking.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // after insert booking success
                    getBookingData();
                    Log.d("Info", "Booking resources inserted successfully");
                } else {
                    Log.e("Info", "Failed to insert Booking resources");
                    showMessageToast("Failed to insert Booking resources", PaymentPage.this);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Handle failure for insertion
                Log.e("Info", "Failed to insert Booking resources" + t.getMessage());
                showMessageToast("Failed to insert Booking resources" + t.getMessage(), PaymentPage.this);
            }
        });
    }

    private void backToInfoPage() {
        ImgBtnBackToInfoPage = findViewById(R.id.id_btn_backPy);
        ImgBtnBackToInfoPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentPage.this, InfoPage.class);
                double priceTourFromDetail = getIntent().getDoubleExtra("priceTour", -1);
                intent.putExtra("priceTour", priceTourFromDetail);

                int selectedIndex = getIntent().getIntExtra("selected_index", -1);
                intent.putExtra("selected_index", selectedIndex);

                String tourLsJson = "";
                List<PackageTour> tours;

                int idTour = getIntent().getIntExtra("idTour", -1);
                tourLsJson = getIntent().getStringExtra("lsTour");
                tours = new Gson().fromJson(tourLsJson, new TypeToken<List<PackageTour>>() {
                }.getType());

                if (idTour > 5) {
                    intent.putExtra("recyclerTwo", "twoRc");
                }
                intent.putExtra("idTour", idTour);
                intent.putExtra("lsTour", tourLsJson);
                startActivity(intent);
            }
        });
    }

    private void insertDataPayment(int bookingId,double TotalAmountPaid,int status){
        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        String currentDate = getCurrentDate();
        Payment payment = new Payment();
        payment.setBookingId(bookingId);
        payment.setPaymentDate(currentDate);
        payment.setAmountPaid(TotalAmountPaid);
        payment.setPaymentStatus(status); // boolean

        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(payment);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json);
        Call<Void> insertCall = apiService.setPayment(requestBody);
        insertCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    //showMessageToast("payment successfully",PaymentPage.this);
                    Log.d("Info", "payment resources inserted successfully");
                } else {
                    Log.e("Info", "Failed to insert payment resources");
                    showMessageToast("Failed to add payment resources",PaymentPage.this);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("Info", "Failed to insert payment resources"+t.getMessage());
                showMessageToast("Failed to insert payment resources"+t.getMessage(),PaymentPage.this);
            }
        });
    }

    private void insertDataCustomer() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        py_FirstNameTextView.getText().toString().trim();
        Log.d("py_FirstNameTextView", "py_FirstNameTextView: " + py_FirstNameTextView.getText().toString().trim());
        Customer customer = new Customer();
        customer.setTitle(py_TitleTextView.getText().toString().trim());
        customer.setFirst_name(py_FirstNameTextView.getText().toString().trim());
        customer.setLast_name(py_LastNameTextView.getText().toString().trim());
        //customer.setChildrenCount(1);
        customer.setCustomer_email(py_EmailTextView.getText().toString().trim());
        customer.setCustomer_phone(py_TelePhTextView.getText().toString().trim());
        customer.setCustomer_from_country(py_CountryTextView.getText().toString().trim());

        Log.d("Info", "Customer resources inserted successfully" + formattedDate);

        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/M/yyyy");
            Date date = inputFormat.parse(formattedDate);

            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDateServer = outputFormat.format(date);
            customer.setDate_of_department(formattedDateServer);
        } catch (ParseException e) {
            // Handle the exception
            e.printStackTrace();
        }
        // Serialize the CustomerTour object to JSON
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(customer);
        Log.d("Info", "JSON: " + json);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json);
        Call<Void> insertCall = apiService.setCustomerResLs(requestBody);
        insertCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // after inserted success
                    getDataCustomer();

                    Log.d("Info", "Customer resources inserted successfully");
                } else {
                    Log.e("Info", "Failed to insert customer resources");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Handle failure for insertion
                Log.e("Info", "Failed to insert customer resources", t);
            }
        });
    }

    private void showDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialogs, null);
        dialogBuilder.setView(dialogView);

        // Retrieve references to the buttons
        Button btnYes = dialogView.findViewById(R.id.btnYes);
        Button btnNo = dialogView.findViewById(R.id.btnNo);

        // Create and show the dialog
        final AlertDialog dialog = dialogBuilder.create();

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                Intent intent = new Intent(PaymentPage.this,PaymentSuccessPage.class);
                startActivity(intent);
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }



    private void getDataCustomer() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        Call<CustomerResponse> getCall = apiService.getCustomerResLs();

        getCall.enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
                if (response.isSuccessful()) {
                    CustomerResponse customerResponse = response.body();
                    if (customerResponse != null) {
                        Log.d("Info data", "Response body has data");
                        List<Customer> customerToursLs = customerResponse.getCustomerResLs();

                        // Retrieve the last customer from the list
                        if (!customerToursLs.isEmpty()) {
                            Customer lastCustomer = customerToursLs.get(customerToursLs.size() - 1);
                            int customer_id = lastCustomer.getCustomer_id();

                            insertBooking(customer_id);
                            Log.d("Info data", "Last customer name: " + lastCustomer.getFirst_name());
                        } else {
                            Log.d("Info data", "No customers found");
                        }
                    } else {
                        showMessageToast("Customer response body is null", PaymentPage.this);
                    }
                } else {
                    showMessageToast("Failed to get customer resources", PaymentPage.this);
                }
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {
                Log.e("Info", "Failed to get customer resources", t);
                showMessageToast("Failed to get customer resources" + t, PaymentPage.this);
            }
        });
    }

    private void showMessageToast(String msg, Context context) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

}
