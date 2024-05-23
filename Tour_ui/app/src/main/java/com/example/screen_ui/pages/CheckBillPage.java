package com.example.screen_ui.pages;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.screen_ui.MainActivity;
import com.example.screen_ui.R;
import com.example.screen_ui.Services.ApiClient;
import com.example.screen_ui.Services.ApiService;
import com.example.screen_ui.models.BookingDetailResponse;
import com.example.screen_ui.models.CustomerResponse;
import com.example.screen_ui.models.common.BookingDetail;
import com.example.screen_ui.models.common.Customer;
import com.example.screen_ui.utils.LoadingDialogUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckBillPage extends AppCompatActivity {
    private boolean showContent = false;
    private RelativeLayout contentShow;
    private TextView TextViewCheckBill;
    private EditText EditTextSearchBill;
    private ImageView btnBackCheckBill;
    private TextView Bill_ID_TextView;
    private TextView Bill_TitleTextView;
    private TextView Bill_FirstNameTextView;
    private TextView Bill_LastNameTextView;
    private TextView Bill_EmailTextView;
    private TextView Bill_PhoneTextView;
    private TextView Bill_CountryTextView;
    private TextView Bill_TourNameTextView;
    private TextView Bill_TourPriceTextView;
    private TextView Bill_DateDepartureTextView;
    private TextView Bill_TourLocationTextView;
    private TextView Bill_TourDurationTextView;
    private TextView BillNumberChildTextView;
    private TextView BillNumberAdultTextView;
    private TextView Bill_TotalAmountTextView;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_bill_page);

        allFieldIDInitial();
        btnBtnBack();
        searchEditText();
    }

    private void allFieldIDInitial() {
        contentShow = findViewById(R.id.contenBilltShow);
        TextViewCheckBill = findViewById(R.id.TextView_CheckBill);
        EditTextSearchBill = findViewById(R.id.EditTextSearchBill);
        btnBackCheckBill = findViewById(R.id.id_btnBackCheckBill);

        Bill_ID_TextView = findViewById(R.id.Bill_ID_TextView);
        Bill_TitleTextView = findViewById(R.id.Bill_TitleTextView);
        Bill_FirstNameTextView = findViewById(R.id.Bill_FirstNameTextView);
        Bill_LastNameTextView = findViewById(R.id.Bill_LastNameTextView);

        Bill_PhoneTextView = findViewById(R.id.Bill_PhoneTextView);
        Bill_EmailTextView = findViewById(R.id.Bill_EmailTextView);
        Bill_TourNameTextView = findViewById(R.id.Bill_TourNameTextView);
        Bill_CountryTextView = findViewById(R.id.Bill_CountryTextView);
        Bill_DateDepartureTextView = findViewById(R.id.Bill_DateDepartureTextView);
        Bill_TourPriceTextView = findViewById(R.id.Bill_TourPriceTextView);
        Bill_TourDurationTextView = findViewById(R.id.Bill_TourDurationTextView);
        Bill_TourLocationTextView = findViewById(R.id.Bill_TourLocationTextView);

        BillNumberAdultTextView = findViewById(R.id.BillNumberAdultTextView);
        BillNumberChildTextView = findViewById(R.id.BillNumberChildTextView);
        Bill_TotalAmountTextView = findViewById(R.id.Bill_TotalAmountTextView);
    }

    private void btnBtnBack() {
        btnBackCheckBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckBillPage.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void Loading() {
//        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                LoadingDialogUtils.showCustomLoadingDialog(CheckBillPage.this);
//            }
//        }, 1000);
        LoadingDialogUtils.showCustomLoadingDialog(CheckBillPage.this);
    }

    private void DisableLoading() {
        LoadingDialogUtils.dismissCustomLoadingDialog();
    }
    private void getDataBookingDetails(int bookingId) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        Call<BookingDetailResponse> getCall = apiService.getBookingById(bookingId);

        getCall.enqueue(new Callback<BookingDetailResponse>() {
            @Override
            public void onResponse(Call<BookingDetailResponse> call, Response<BookingDetailResponse> response) {
                if (response.isSuccessful()) {
                    DisableLoading();
                    showContent = true; // show bill when search by booking id ok
                    updateContentVisible();
                    BookingDetailResponse bookingDetailResponse = response.body();
                    if (bookingDetailResponse != null) {
                        BookingDetail bookingDetail = bookingDetailResponse.getBookingDetail();
                        if (bookingDetail != null) {
                            Bill_ID_TextView.setText(String.valueOf(bookingDetail.getBookingId()));
                            Bill_TitleTextView.setText(bookingDetail.getTitle());
                            Bill_FirstNameTextView.setText(bookingDetail.getFirstName());
                            Bill_LastNameTextView.setText(bookingDetail.getLastName());

                            Bill_PhoneTextView.setText(bookingDetail.getCustomerPhone());
                            Bill_EmailTextView.setText(bookingDetail.getCustomerEmail());
                            Bill_TourNameTextView.setText(bookingDetail.getTourName());
                            Bill_CountryTextView.setText(bookingDetail.getCustomerFromCountry());
                            // Convert and display the date
                            String dateOfDepartment = bookingDetail.getDateOfDepartment();
                            String formattedDate = formatDateString(dateOfDepartment, "yyyy-MM-dd", "dd MMM yyyy");
                            Bill_DateDepartureTextView.setText(formattedDate);

                            Bill_TourPriceTextView.setText(String.valueOf(bookingDetail.getTourPrice()+"$"));
                            Bill_TourDurationTextView.setText(String.valueOf(bookingDetail.getTourDuration()));
                            Bill_TourLocationTextView.setText(bookingDetail.getTourLocation());
                            BillNumberAdultTextView.setText(String.valueOf(bookingDetail.getNumberOfChild()));
                            BillNumberChildTextView.setText(String.valueOf( bookingDetail.getNumberOfAdult()));
                            Bill_TotalAmountTextView.setText(String.valueOf(bookingDetail.getAmountPaid()+"$"));

                            Log.d("Info data", "Booking ID: " + bookingId);
                        } else {
                            Log.d("Info data", "No booking details found");
                            showContent = false;
                            updateContentVisible();
                            TextViewCheckBill.setText("No booking details found");
                        }
                    } else {
                        showMessageToast("Booking detail response body is null", CheckBillPage.this);
                        DisableLoading();
                    }
                } else {
                    showContent = false;
                    updateContentVisible();
                    showMessageToast("Failed to get booking details", CheckBillPage.this);
                    TextViewCheckBill.setText("No booking details found");
                    DisableLoading();
                }
            }

            @Override
            public void onFailure(Call<BookingDetailResponse> call, Throwable t) {
                DisableLoading();
                showContent = false;
                updateContentVisible();
                TextViewCheckBill.setText("No booking details found");
                Log.e("Info", "Failed to get booking details"+t.getMessage());
                showMessageToast("Failed to get booking details" + t.getMessage(), CheckBillPage.this);
            }
        });
    }

    private String formatDateString(String dateString, String inputFormat, String outputFormat) {
        SimpleDateFormat inputDateFormat = new SimpleDateFormat(inputFormat, Locale.getDefault());
        SimpleDateFormat outputDateFormat = new SimpleDateFormat(outputFormat, Locale.getDefault());
        Date date;
        String formattedDate = null;
        try {
            date = inputDateFormat.parse(dateString);
            if (date != null) {
                formattedDate = outputDateFormat.format(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedDate;
    }
    public void showMessageToast(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
    private void searchEditText() {
        EditTextSearchBill.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // key press done ,enter
                    // Toast.makeText(CheckBillPage.this, "enter", Toast.LENGTH_LONG).show();
                    Loading();
                    String bookingID = EditTextSearchBill.getText().toString().trim();
                    getDataBookingDetails(Integer.parseInt(bookingID));
                    clearTextEditTextSearch();
                    closeKeyboard(); // Close the keyboard after search
                    return true;
                }
                return false;
            }
        });
    }

    private void updateContentVisible() {
        if (showContent) {
            TextViewCheckBill.setVisibility(View.GONE);
            contentShow.setVisibility(View.VISIBLE);
        } else {
            TextViewCheckBill.setVisibility(View.VISIBLE);
            contentShow.setVisibility(View.GONE);
        }
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void clearTextEditTextSearch() {
        EditTextSearchBill.setText("");
    }
}
