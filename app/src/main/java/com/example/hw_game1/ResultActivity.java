package com.example.hw_game1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class ResultActivity extends AppCompatActivity {
    private MapActivity mapactivity;
    private Top10Activity top10Activity;
    private MaterialButton score_BTN_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        mapactivity = new MapActivity();
        top10Activity=new Top10Activity();
        Intent previousIntent = getIntent();

        getSupportFragmentManager().beginTransaction().add(R.id.panel_LAY_list,top10Activity).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.panel_LAY_map,mapactivity).commit();
        score_BTN_back=findViewById(R.id.score_BTN_back);
        score_BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}