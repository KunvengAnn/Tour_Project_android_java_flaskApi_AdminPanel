package com.example.screen_ui.Helper;

// Import RecyclerView from AndroidX
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.widget.Toast;

import com.example.screen_ui.R;
import com.example.screen_ui.models.common.PackageTour;
import com.example.screen_ui.pages.DetailPage;
import com.google.gson.Gson;

public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.MyView> {

    private Context mContext;
    private AdapterView.OnItemClickListener listener;

    private List<Integer> drawableList;
    private List<String> titleList;
    private List<String> subList;
    private List<Integer> idTour;
    private List<PackageTour> tours;


    public AdapterRecycler(Context context,List<Integer> dawIntegerList,List<String> titleList,List<String> subList,List<Integer> idTour,List<PackageTour> tours){
        mContext = context;
        this.drawableList = dawIntegerList;
        this.titleList = titleList;
        this.subList = subList;
        this.idTour = idTour;
        this.tours = tours;
    }
    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        this.listener = listener;
    }

    // ViewHolder class
    public class MyView extends RecyclerView.ViewHolder {
      TextView titleTextView;
      TextView subTileTextView;
      ImageView imageViewRecycler;

        public MyView(View view) {
            super(view);
            titleTextView = view.findViewById(R.id.id_titleTextView);
            subTileTextView = view.findViewById(R.id.id_subTitle);
            imageViewRecycler = view.findViewById(R.id.id_imageRecycler);

            // Set click listener on the entire item view
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        // Handle the click event here
                        //Toast.makeText(mContext, "Item clicked at position: " + position, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(mContext, DetailPage.class);
                        intent.putExtra("selected_index", position);
                        intent.putExtra("idTour", idTour.get(position));
                        String tourLsJson = new Gson().toJson(tours);
                        intent.putExtra("lsTour", tourLsJson);
                        mContext.startActivity(intent);
                        ((Activity)mContext).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }
                }
            });
        }
    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);

        return new MyView(itemView);
    }

//    @Override
//    public void onViewRecycled(@NonNull MyView holder) {
//        super.onViewRecycled(holder);
//        holder.itemView.clearAnimation();
//    }

    @Override
    public void onBindViewHolder(final MyView holder, final int position) {
        if (subList.isEmpty() || drawableList.isEmpty() || titleList.isEmpty()) {
            Toast.makeText(mContext, "Recycler no data!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Set data to views
        holder.titleTextView.setText(titleList.get(position));
        holder.subTileTextView.setText(subList.get(position));
        int iconResourceId = drawableList.get(position);
        holder.imageViewRecycler.setImageResource(iconResourceId);

        // Load animation
//        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_in);
//        holder.itemView.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return titleList.size();
    }
}
