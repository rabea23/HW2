package com.example.hw_game1;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class Activity_score extends AppCompatActivity {
    private MaterialButton score_BTN_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        findViews();
        Intent previousIntent = getIntent();
        back();

    }

    private void findViews() {
        score_BTN_back = findViewById(R.id.score_BTN_back);
    }
    public void back(){
        score_BTN_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
              finish();
            }
        });
    }
}
