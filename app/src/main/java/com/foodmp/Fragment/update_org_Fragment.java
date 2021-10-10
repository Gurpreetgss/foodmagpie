package com.foodmp.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.foodmp.LoginActivity;
import com.foodmp.R;
import com.foodmp.SharedPref;
import com.foodmp.controller.ApiUtils;
import com.foodmp.controller.UserServices;
import com.foodmp.model.Register;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class update_org_Fragment extends Fragment implements LocationListener {
    TextView errortxt;
    UserServices userServices;
    private LocationManager locationManager;
    private String provider;
    double lat,lang;
    SharedPref shrdpref;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_update_org_, container, false);
        EditText orgName=(EditText) view.findViewById(R.id.orgname_ed);
        EditText orgaddress=(EditText) view.findViewById(R.id.orgaddress_ed);
        EditText orgdesc=(EditText) view.findViewById(R.id.orgdesc_ed);
        EditText orgphone=(EditText) view.findViewById(R.id.orgphone_ed);
        Button submitOrgInfoBtn=(Button) view.findViewById(R.id.submit_org_info);
        shrdpref=new SharedPref(getActivity());
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        // Define the criteria how to select the locatioin provider -> use
        // default
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (ActivityCompat.checkSelfPermission
                    (getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    &&
                    ActivityCompat.checkSelfPermission
                            (getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                }, 1); // 1 is requestCode

            }
        }
        Location location = locationManager.getLastKnownLocation(provider);

        // Initialize the location fields
        if (location != null) {
            System.out.println("Provider " + provider + " has been selected.");
            onLocationChanged(location);
        } else {
            Toast.makeText(getContext(), "Location not available", Toast.LENGTH_SHORT).show();

        }


        userServices = ApiUtils.getUserService();
        errortxt=(TextView) view.findViewById(R.id.errortext_org);
        submitOrgInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String OrgName=orgName.getText().toString();
                String OrgAddress=orgaddress.getText().toString();
                String OrgDec=orgdesc.getText().toString();
                String OrgPhn=orgphone.getText().toString();


                if (validateOrg(OrgName,OrgAddress,OrgDec,OrgPhn)) {
                    registerUser(OrgName,OrgAddress,OrgDec,OrgPhn);
                }
            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (ActivityCompat.checkSelfPermission
                    (getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    &&
                    ActivityCompat.checkSelfPermission
                            (getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                }, 1); // 1 is requestCode

            }
        }
        locationManager.requestLocationUpdates(provider, 400, 1, this);
    }

    @Override
    public void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }

    private void registerUser(String OrgName, String OrgAddress, String OrgDec, String OrgPhn)  {
        try {

            int user_id=shrdpref.getUserid();
            Call<Register> call = userServices.insertOrganzation(user_id,OrgName,OrgAddress,OrgDec,OrgPhn,lat,lang);
            call.enqueue(new Callback<Register>() {
                @Override
                public void onResponse(Call<Register> call, Response<Register> response) {
                    if (response.isSuccessful()) {

                        if(response.body().getStatus()){
                            new MaterialAlertDialogBuilder(getActivity()).setMessage("Information Inserted  Successfull")
                                    .setPositiveButton("ok",(dialog, which) -> {
                                        shrdpref.setFirstTimeLaunchOrg(response.body().getValue());

                                        startActivity(new Intent(getActivity(), Individual_Organization_Activity.class));


                                    }).show();
                        }else{
                            new MaterialAlertDialogBuilder(getActivity()).setMessage(response.body().getMessage())
                                    .setPositiveButton("OK",(dialog, which) -> {
                                        shrdpref.setFirstTimeLaunchOrg(response.body().getValue());

                                        startActivity(new Intent(getActivity(), Individual_Organization_Activity.class));

                                    }).show();
                        }
                    } else {
                        //       Toast.makeText(getContext(), "Error! Please try again!", Toast.LENGTH_SHORT).show();
                        new MaterialAlertDialogBuilder(getActivity()).setMessage(response.body().getMessage())
                                .setPositiveButton("OK",(dialog, which) -> {
                                    /////
                                }).show();
                    }
                }

                @Override
                public void onFailure(Call<Register> call, Throwable t) {
                    System.out.println("error");
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





    private boolean validateOrg(String OrgName,String OrgAddress,String OrgDec,String OrgPhn) {

        if (OrgName == null || OrgName.trim().length() == 0) {
            errortxt.setText("Organization Name is required");
            return false;
        }
        if (OrgAddress == null || OrgAddress.trim().length() == 0) {
            errortxt.setText("Organization Address is required");
            return false;
        }
        if (OrgDec == null || OrgDec.trim().length() == 0) {
            errortxt.setText("Organization Description is required");
            return false;
        }

        if (OrgPhn == null || OrgPhn.trim().length() == 0) {
            errortxt.setText("Phone Number is required");
            return false;
        }

        return true;
    }
    @Override
    public void onLocationChanged(@NonNull Location location) {
        lat=location.getLatitude();
        lang=location.getLongitude();
        System.out.println(lang);
    }
}