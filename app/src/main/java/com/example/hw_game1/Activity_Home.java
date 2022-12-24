package com.example.hw_game1;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.radiobutton.MaterialRadioButton;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class Activity_Home extends AppCompatActivity {
    private AppCompatImageView home_IMG_background;
    private MaterialButton home_BTN_play;
    private MaterialButton home_BTN_play1;
    private MaterialButton home_BTN_result;
    private String name=null;
    private String radio_level ="";
    private EditText player_name;
    FusedLocationProviderClient client;
    private double LONGITUDE;
    private double LATITUDE;
    DataManager dataManager=new DataManager();
    private RadioGroup home_radiogroup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        DataManager.load();
        DataManager.getPlayers();
        client = LocationServices.getFusedLocationProviderClient(Activity_Home.this);
        if (ActivityCompat.checkSelfPermission(Activity_Home.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
        } else {
            ActivityCompat.requestPermissions(Activity_Home.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

        findViews();
        enteredname();
        startPlay();
        startSenssorPlay();
        getresult();



    }

    public int checkRadio(){
        int radioId=home_radiogroup.getCheckedRadioButtonId();
        Log.d("message2", String.valueOf(radioId));
        if(radioId!=-1){
        MaterialRadioButton radioButton=findViewById(radioId);
        radio_level=radioButton.getText().toString();
        Log.d("message2",radioButton.getText().toString());}
        return (radioId);
    }
    @SuppressLint("SetTextI18n")
    private void findViews() {
        home_IMG_background = findViewById(R.id.home_IMG_background);
        home_BTN_play=findViewById(R.id.home_BTN_play);
        home_BTN_play.setVisibility(View.INVISIBLE);
        home_BTN_play1=findViewById(R.id.home_BTN_play1);
        home_BTN_play1.setVisibility(View.INVISIBLE);
        home_BTN_result=findViewById(R.id.home_BTN_result);
        home_radiogroup=findViewById(R.id.home_radiogroup);
        home_radiogroup.setVisibility(View.INVISIBLE);
        player_name=findViewById(R.id.player_name);



    }




    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        client.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                Log.d("message2",Double.toString(location.getLongitude()));

                if (location != null) {
                    try {
                        Geocoder geocoder = new Geocoder(Activity_Home.this, Locale.getDefault());
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        Log.d("message2",Double.toString(location.getLongitude()));
                        LATITUDE=(long)addresses.get(0).getLatitude();
                        LONGITUDE=(long)addresses.get(0).getLongitude();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }

        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 44){
            getCurrentLocation();
        }
        else{
            Toast.makeText(getApplicationContext(),"Permission denied.",Toast.LENGTH_SHORT).show();
        }
    }







    public void enteredname(){
        player_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (player_name.toString().equals("")) {
                        home_BTN_play.setVisibility(View.INVISIBLE);
                        home_BTN_play1.setVisibility(View.INVISIBLE);
                        home_radiogroup.setVisibility(View.INVISIBLE);
                    } else {
                        home_BTN_play.setVisibility(View.VISIBLE);
                        home_BTN_play1.setVisibility(View.VISIBLE);
                        home_radiogroup.setVisibility(View.VISIBLE);
                        name = player_name.getText().toString();
                    }

            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    public void startPlay(){
        home_BTN_play.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(checkRadio()!=-1) {
                    Intent intent = new Intent(Activity_Home.this, Activity_Game.class);
                    intent.putExtra(Activity_Game.KEY_NAME, name);
                    String a=String.valueOf(LONGITUDE);
                    String b=String.valueOf(LATITUDE);
                    Log.d("message3", a);
                    Log.d("message3", b);
                    intent.putExtra(Activity_Game.KEY_LONGITUDE, a);
                    intent.putExtra(Activity_Game.KEY_LATITUDE, b);
                    intent.putExtra(Activity_Game.KEY_LEVEl, radio_level);
                    startActivity(intent);
                }else
                    Toast.makeText(Activity_Home.this,"enter level",Toast.LENGTH_SHORT).show();
            }



        });
    }
    public void startSenssorPlay() {
        home_BTN_play1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkRadio()!=-1) {
                Intent intent = new Intent(Activity_Home.this, SennsorActivity.class);
                intent.putExtra(SennsorActivity.KEY_NAME, name);
                String a=String.valueOf(LONGITUDE);
                String b=String.valueOf(LATITUDE);
                Log.d("message3", a);
                Log.d("message3", b);
                intent.putExtra(SennsorActivity.KEY_LONGITUDE, a);
                intent.putExtra(SennsorActivity.KEY_LATITUDE, b);
                intent.putExtra(SennsorActivity.KEY_LEVEl, radio_level);
                startActivity(intent);
            }else
                    Toast.makeText(Activity_Home.this,"enter level",Toast.LENGTH_SHORT).show();
            }

        });
    }

        public void getresult(){
        home_BTN_result.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_Home.this, ResultActivity.class);
                startActivity(intent);

            }
        });

    }



}
