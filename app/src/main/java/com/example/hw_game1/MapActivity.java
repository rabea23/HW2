package com.example.hw_game1;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapActivity extends Fragment {

    private double latitude;
    private double longit;
    int PERMISSION_ID = 44;
    FusedLocationProviderClient mFusedLocationClient;
    DataManager d=new DataManager();
    ArrayList<Player> p=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_map, container, false);
        p=d.getPlayers();
        for (int i=0;i<p.size();i++){
            double x=p.get(i).getLatitude();
            double y =p.get(i).getLongit();
            String name=p.get(i).getName();
        marker(x, y,name);
        }
        return view;
    }





    public void marker(double latitude,double longit,String name) {
        SupportMapFragment supportMapFragment=(SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                LatLng sydney = new LatLng(latitude, longit);
                googleMap.addMarker(new MarkerOptions()
                        .position(sydney)
                        .title(name));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 0));


            }
        });
    }


}