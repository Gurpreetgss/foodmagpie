package com.foodmp.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foodmp.Adapter.OrganizationAdapter;
import com.foodmp.R;
import com.foodmp.controller.ApiUtils;
import com.foodmp.controller.UserServices;
import com.foodmp.model.DataOrg;
import com.foodmp.model.OrgList;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrganizationListFragment extends Fragment {
    UserServices userServices;
    List<OrgList> apart=new ArrayList<>();
    OrganizationAdapter orgAdapter;


    private ProgressDialog progress;
    RecyclerView lisRecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

            View view=inflater.inflate(R.layout.fragment_organization_list, container, false);
        lisRecyclerView=(RecyclerView) view.findViewById(R.id.org_list);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        lisRecyclerView.setLayoutManager(layoutManager);
        lisRecyclerView.addItemDecoration(new DividerItemDecoration(lisRecyclerView.getContext(), DividerItemDecoration.VERTICAL));

        userServices = ApiUtils.getUserService();
//        progress=new ProgressDialog(getActivity());
//        progress.setMessage("Loading");
//        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//
//        progress.show();
        getData();
        return view;
    }

    private void getData(){
        try {
            Call<OrgList> call=userServices.getOrgList();
            call.enqueue(new Callback<OrgList>() {
                @Override
                public void onResponse(Call<OrgList> call, Response<OrgList> response) {
                    if (response.isSuccessful()) {

                        if(response.body().getStatus()){

                        //    apart.add(response.body().getData());
                           orgAdapter=new OrganizationAdapter(getActivity(),response.body().getData());
                           lisRecyclerView.setAdapter(orgAdapter);

                        }
                    } else {
                        new MaterialAlertDialogBuilder(getActivity()).setMessage("Error! Please try again!")
                                .setPositiveButton("OK",(dialog, which) -> {
                                    /////
                                }).show();
                    }
                }
                @Override
                public void onFailure(Call<OrgList> call, Throwable t) {
                    new MaterialAlertDialogBuilder(getActivity()).setMessage("Error! Please try again!")
                            .setPositiveButton("OK",(dialog, which) -> {
                                /////
                            }).show();
                }
            });
        }catch (Exception ex){
            new MaterialAlertDialogBuilder(getActivity()).setMessage(ex.toString())
                    .setPositiveButton("OK",(dialog, which) -> {
                        /////
                    }).show();
        }
    }


}