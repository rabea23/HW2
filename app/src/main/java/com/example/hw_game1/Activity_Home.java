package com.example.hw_game1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.google.android.material.button.MaterialButton;


public class Activity_Home extends AppCompatActivity {
    private AppCompatImageView home_IMG_background;
    private MaterialButton home_BTN_play;
    private MaterialButton home_BTN_result;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findViews();
        startPlay();

    }
    private void findViews() {
        home_IMG_background = findViewById(R.id.home_IMG_background);
        home_BTN_play=findViewById(R.id.home_BTN_play);
        home_BTN_result=findViewById(R.id.home_BTN_result);
        home_BTN_result.setVisibility(View.INVISIBLE);
    }
    public void startPlay(){
        home_BTN_play.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_Home.this, Activity_Game.class);
                startActivity(intent);

            }
        });
    }

}
