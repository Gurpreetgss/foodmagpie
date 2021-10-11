package com.foodmp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.foodmp.R;
import com.foodmp.model.DataOrg;

import java.util.List;

public class OrganizationAdapter  extends RecyclerView.Adapter<MyViewHolder> {
    Context ctx;
    List<DataOrg> data;
    public OrganizationAdapter( Context ctx, List<DataOrg> data){
        this.ctx=ctx;
        this.data=data;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.org_list_ui,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final DataOrg item = data.get(position);

        holder.orgname.setText(item.getOrgName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
