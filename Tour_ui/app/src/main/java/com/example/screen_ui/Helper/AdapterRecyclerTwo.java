package com.example.screen_ui.Helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.screen_ui.R;
import com.example.screen_ui.models.common.PackageTour;

import com.example.screen_ui.pages.DetailPage;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.util.List;

public class AdapterRecyclerTwo extends RecyclerView.Adapter<AdapterRecyclerTwo.MyView> {
    private Context context;
    private List<String> titleTwo;
    private List<String> price;
    private List<Integer> imageDrawer;
    private List<Integer> idTour;
    private List<PackageTour> tours;

    public AdapterRecyclerTwo(Context context, List<String> titleTwo, List<String> price, List<Integer> imageDrawer, List<Integer> idTour, List<PackageTour> tours) {
        this.context = context;
        this.titleTwo = titleTwo;
        this.price = price;
        this.imageDrawer = imageDrawer;
        this.idTour = idTour;
        this.tours = tours;
    }

    // ViewHolder class
    public class MyView extends RecyclerView.ViewHolder {
        TextView titleTwoTextView;
        TextView priceTextView;
        ImageView imageViewDrawable;

        public MyView(View view) {
            super(view);
            titleTwoTextView = view.findViewById(R.id.id_titleTextView2);
            priceTextView = view.findViewById(R.id.id_price);
            imageViewDrawable = view.findViewById(R.id.id_imageRecycler2);

            //view setClick entry item Recycler
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Intent intent = new Intent(context, DetailPage.class);
                        intent.putExtra("selected_index", position);
                        intent.putExtra("recyclerTwo", "twoRc");
                        intent.putExtra("idTour", idTour.get(position));
                        String tourLsJson = new Gson().toJson(tours);
                        intent.putExtra("lsTour", tourLsJson);
                        context.startActivity(intent);
                        ((Activity)context).overridePendingTransition(R.anim.fade_in, R.anim.slide_out_left);
                    }
                }
            });
        }
    }


    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler2, parent, false);
        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(MyView holder, int position) {
        // Check if lists are empty or null
        if (titleTwo.isEmpty() || imageDrawer.isEmpty() || price.isEmpty()) {
            Toast.makeText(context, "Recycler no data!", Toast.LENGTH_SHORT).show();
            return;
        }
        holder.titleTwoTextView.setText(titleTwo.get(position));

//        String priceString = String.valueOf(price.get(position));
//        double priceDouble = Double.parseDouble(priceString);
//
//        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
//        String formattedPrice = decimalFormat.format(priceDouble);
//        holder.priceTextView.setText(formattedPrice);
        holder.priceTextView.setText(price.get(position));
        holder.imageViewDrawable.setImageResource(imageDrawer.get(position));
    }

    @Override
    public int getItemCount() {
        return titleTwo.size();
    }
}
