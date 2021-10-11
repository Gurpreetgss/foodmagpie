package com.foodmp.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.foodmp.R;

public class MyViewHolder extends RecyclerView.ViewHolder{

    TextView orgname;

    CardView clickLayout;
    public MyViewHolder(View itemView) {
        super(itemView);

        orgname=(TextView) itemView.findViewById(R.id.orgname);

    }
}
