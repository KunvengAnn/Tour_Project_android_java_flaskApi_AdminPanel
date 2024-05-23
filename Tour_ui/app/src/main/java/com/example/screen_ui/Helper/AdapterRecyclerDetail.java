package com.example.screen_ui.Helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.screen_ui.R;

import java.util.List;

public class AdapterRecyclerDetail extends RecyclerView.Adapter<AdapterRecyclerDetail.MyView> {

    private final Context mContext;
    private final List<Integer> drawableList;

    public AdapterRecyclerDetail(Context mContext, List<Integer> drawableList) {
        this.mContext = mContext;
        this.drawableList = drawableList;
    }

    // ViewHolder class
    public class MyView extends RecyclerView.ViewHolder {
        ImageView imageViewDrawable;

        public MyView(View view) {
            super(view);
            imageViewDrawable = view.findViewById(R.id.id_imageViewDetail);
        }
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyler_detail, parent, false);
        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        int resourceId = drawableList.get(position);
        if (drawableList.isEmpty() ) {
            Toast.makeText(mContext, "Recycler no data!", Toast.LENGTH_SHORT).show();
            return;
        }
        holder.imageViewDrawable.setImageResource(resourceId);
    }

    @Override
    public int getItemCount() {
        return drawableList.size();
    }
}
