package com.example.screen_ui.pages;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.screen_ui.R;
import com.example.screen_ui.Services.ApiClient;
import com.example.screen_ui.Services.ApiService;
import com.example.screen_ui.models.CustomerResponse;
import com.example.screen_ui.models.common.Customer;
import com.example.screen_ui.models.common.PackageTour;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoPage extends AppCompatActivity {
    private EditText editTextDate;
    private EditText FirstNameEditText;
    private EditText LastNameEditText;
    private EditText EmailEditText;
    private EditText PhoneNumberEditText;
    private EditText CountryEditText;
    private TextView TotalAmontTextView;
    private ImageView imgBtnBack;
    private double priceTourFromDetail;

    private CardView cardFloatingBtn;
    private Spinner spinnerTitle;
    private int selectedIndex = 0;
    private int QuantityAdult = 0;
    private int QuantityChild = 0;
    private double totalQuantityPrice = 0.0;

    ////////////////////////////////////////////////
    private Customer customer = new Customer();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_page);

        priceTourFromDetail = getIntent().getDoubleExtra("priceTour", -1);

        FirstNameEditText = findViewById(R.id.FirstNameEditText);
        LastNameEditText = findViewById(R.id.LastNameEditText);
        EmailEditText = findViewById(R.id.EmailEditText);
        PhoneNumberEditText = findViewById(R.id.PhoneNumberEditText);
        CountryEditText = findViewById(R.id.CountryEditText);
        TotalAmontTextView = findViewById(R.id.id_ToTalAmountTextView);

        spinnerWidget();
        EditTextDate();
        btnBack();
        AddMinusQuantityAdult();
        AddMinusQuantityChild();
        cardBtnFloatingBtnPayment();
    }
    // end of OnCreate

    private boolean checkFieldEmpty() {
        String dateText = editTextDate.getText().toString().trim();
        int selectedPosition = spinnerTitle.getSelectedItemPosition();

        if (selectedPosition == 0) {
            showMessageToast("Select Title", InfoPage.this);
            return true;
        } else if (dateText.isEmpty()) {
            showMessageToast("Select Date Field", InfoPage.this);
            return true;
        } else if (FirstNameEditText.getText().toString().trim().isEmpty()) {
            showMessageToast("Input First Name", InfoPage.this);
            return true;
        } else if (LastNameEditText.getText().toString().trim().isEmpty()) {
            showMessageToast("Input Last Name", InfoPage.this);
            return true;
        } else if (EmailEditText.getText().toString().trim().isEmpty()) {
            showMessageToast("Input Email", InfoPage.this);
            return true;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(EmailEditText.getText().toString().trim()).matches()) {
            showMessageToast("Invalid Email Format", InfoPage.this);
            return true;
        } else if (PhoneNumberEditText.getText().toString().trim().isEmpty()) {
            showMessageToast("Input Phone Number", InfoPage.this);
            return true;
        } else if (CountryEditText.getText().toString().trim().isEmpty()) {
            showMessageToast("Input Country", InfoPage.this);
            return true;
        }else if(QuantityAdult+QuantityChild==0){
            showMessageToast("Add Quantity People", InfoPage.this);
            return true;
        }

        return false; // Return false if all fields are filled correctly
    }


    private void getAllFieldValue() {
        customer.setFirst_name(FirstNameEditText.getText().toString().trim());
        customer.setLast_name(LastNameEditText.getText().toString().trim());
        customer.setCustomer_email(EmailEditText.getText().toString().trim());
        customer.setCustomer_phone(PhoneNumberEditText.getText().toString().trim());
        customer.setCustomer_from_country(CountryEditText.getText().toString().trim());
        customer.setTitle((String) spinnerTitle.getSelectedItem());

        String dateString = editTextDate.getText().toString().trim();
        if (!dateString.isEmpty()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Correct format
            customer.setDate_of_department(dateString);
            Log.d("data date", "Date parsed successfully: " + dateString);
        } else {
            Log.e("data date", "Date string is empty or null");
        }
    }

    private void AddMinusQuantityAdult() {
        ImageView btnAddAdult = findViewById(R.id.id_btn_add_adult);
        ImageView btnMinusAdult = findViewById(R.id.id_btn_minus_adult);
        TextView quantityOfAdultTextView = findViewById(R.id.id_quantity_adult);

        btnAddAdult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (QuantityAdult < 9) {
                    QuantityAdult++;
                    quantityOfAdultTextView.setText(String.valueOf(QuantityAdult));

                    totalQuantityPrice = (QuantityAdult + QuantityChild) * priceTourFromDetail;
                    TotalAmontTextView.setText(String.valueOf(totalQuantityPrice) + " $");
                } else {
                    showMessageToast("Quantity higher is 9", InfoPage.this);
                }
            }
        });
        btnMinusAdult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (QuantityAdult > 0) {
                    QuantityAdult--;
                    quantityOfAdultTextView.setText(String.valueOf(QuantityAdult));

                    totalQuantityPrice = (QuantityAdult + QuantityChild) * priceTourFromDetail;
                    TotalAmontTextView.setText(String.valueOf(totalQuantityPrice) + " $");
                }
            }
        });
    }

    private void AddMinusQuantityChild() {
        ImageView btnAddChild = findViewById(R.id.id_btn_add_child);
        ImageView btnMinusChild = findViewById(R.id.id_btn_minus_child);
        TextView quantityOfChildTextView = findViewById(R.id.id_quantity_child);

        btnAddChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (QuantityChild < 9) {
                    QuantityChild++;
                    quantityOfChildTextView.setText(String.valueOf(QuantityChild));

                    totalQuantityPrice = (QuantityAdult + QuantityChild) * priceTourFromDetail;
                    TotalAmontTextView.setText(String.valueOf(totalQuantityPrice) + " $");
                } else {
                    showMessageToast("Quantity higher is 9", InfoPage.this);
                }
            }
        });
        btnMinusChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (QuantityChild > 0) {
                    QuantityChild--;
                    quantityOfChildTextView.setText(String.valueOf(QuantityChild));

                    totalQuantityPrice = (QuantityAdult + QuantityChild) * priceTourFromDetail;
                    TotalAmontTextView.setText(String.valueOf(totalQuantityPrice) + " $");
                }
            }
        });
    }

    private void showMessageToast(String msg, Context context) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    private void EditTextDate() {
        editTextDate = findViewById(R.id.editTextDate);
        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
    }

    private void spinnerWidget() {
        spinnerTitle = findViewById(R.id.spinnerTitle);
        ArrayAdapter<CharSequence> defaultAdapter = ArrayAdapter.createFromResource(this,
                R.array.titles, android.R.layout.simple_spinner_item);
        defaultAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTitle.setAdapter(defaultAdapter);
    }

    private void cardBtnFloatingBtnPayment() {
        //btn payment
        cardFloatingBtn = findViewById(R.id.cardViewBtnInfo);
        cardFloatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkFieldEmpty()) {
                    getAllFieldValue();

                    Intent intent = new Intent(InfoPage.this, PaymentPage.class);
                    intent.putExtra("Date_of_department", customer.getDate_of_department());
                    intent.putExtra("Last_name", customer.getLast_name());
                    intent.putExtra("First_name", customer.getFirst_name());
                    intent.putExtra("Customer_email", customer.getCustomer_email());
                    intent.putExtra("Customer_phone", customer.getCustomer_phone());
                    intent.putExtra("Customer_from_country", customer.getCustomer_from_country());
                    intent.putExtra("Title", customer.getTitle());

                    intent.putExtra("QuantityChild", QuantityChild);
                    intent.putExtra("QuantityAdult", QuantityAdult);
                    intent.putExtra("priceTour",priceTourFromDetail);
                    intent.putExtra("TotalPrice",totalQuantityPrice);

                    String tourName =  getIntent().getStringExtra("tourName");
                    String tourLocation = getIntent().getStringExtra("tourLocation");
                    int tourDuration = getIntent().getIntExtra("tourDuration",0);
                    intent.putExtra("tourName", tourName);
                    intent.putExtra("tourLocation", tourLocation);
                    intent.putExtra("tourDuration",tourDuration);

                    ////////
                    selectedIndex = getIntent().getIntExtra("selected_index", -1);
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
            }
        });
    }
    private void btnBack() {
        imgBtnBack = findViewById(R.id.id_btn_backInfo);
        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoPage.this, DetailPage.class);
                selectedIndex = getIntent().getIntExtra("selected_index", -1);
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

    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year1, monthOfYear, dayOfMonth) -> {
                    // Do something with the selected date
                    String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1;
                    editTextDate.setText(selectedDate);
                }, year, month, day);

        // Set minimum date to current date
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        datePickerDialog.show();
    }
}
