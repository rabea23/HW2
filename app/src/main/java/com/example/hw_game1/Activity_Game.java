package com.example.hw_game1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;



public class Activity_Game extends AppCompatActivity {
    private ShapeableImageView[] game_IMG_hearts;
    private ShapeableImageView[] game_IMG_cars;
    private MaterialButton game_BTN_right;
    private MaterialButton game_BTN_left;
    private  int now_rod;
    private ShapeableImageView[][] rod1;
    private int i=0;
    int current=0;
    final Handler handler = new Handler();
    GameManager gameManager;
    private boolean stop = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        findViews();
        gameManager = new GameManager(game_IMG_hearts.length);
        initViews();
        int rod=gameManager.getfirstRoads();
        animateImages(rod);
        refreshUI();

    }

    private void findViews() {
        game_BTN_right = findViewById(R.id.game_BTN_right);
        game_BTN_left = findViewById(R.id.game_BTN_left);

        game_IMG_cars=new ShapeableImageView[]{
                findViewById(R.id.game_ic_car_1),
                findViewById(R.id.game_ic_car_2),
                findViewById(R.id.game_ic_car_3),

        };
        game_IMG_hearts = new ShapeableImageView[]{
                findViewById(R.id.game_IMG_hurt3),
                findViewById(R.id.game_IMG_hurt2),
                findViewById(R.id.game_IMG_hurt1),
        };
        game_IMG_cars[0].setVisibility(View.INVISIBLE);
        game_IMG_cars[2].setVisibility(View.INVISIBLE);
       // game_IMG_rock=findViewById(R.id.game_ic_stone);
        rod1=new ShapeableImageView[][]{{
                findViewById(R.id.game_ic_stone1),
                findViewById(R.id.game_ic_stone2),
                findViewById(R.id.game_ic_stone3),
                findViewById(R.id.game_ic_stone4),
                findViewById(R.id.game_ic_stone5),
                findViewById(R.id.game_ic_stone6),},
                { findViewById(R.id.game_ic_stone2_1),
                findViewById(R.id.game_ic_stone2_2),
                findViewById(R.id.game_ic_stone2_3),
                findViewById(R.id.game_ic_stone2_4),
                findViewById(R.id.game_ic_stone2_5),
                        findViewById(R.id.game_ic_stone2_6),},
                { findViewById(R.id.game_ic_stone3_1),
                        findViewById(R.id.game_ic_stone3_2),
                        findViewById(R.id.game_ic_stone3_3),
                        findViewById(R.id.game_ic_stone3_4),
                        findViewById(R.id.game_ic_stone3_5),
                        findViewById(R.id.game_ic_stone3_6),},

        };
        for (int i = 0; i < rod1.length; i++) {
            for (int j = 0; j < rod1[i].length; j++) {
                rod1[i][j].setVisibility(View.INVISIBLE);

            }

        }

    }

    private void initViews() {

        game_BTN_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nowcar = gameManager.getNowcar();
                game_IMG_cars[nowcar].setVisibility(View.INVISIBLE);
                int newcar = gameManager.moveCartoLeft();
                game_IMG_cars[newcar].setVisibility(View.VISIBLE);



            }
        });
        game_BTN_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nowcar = gameManager.getNowcar();
                game_IMG_cars[nowcar].setVisibility(View.INVISIBLE);
                int newcar = gameManager.moveCartoRight();
                game_IMG_cars[newcar].setVisibility(View.VISIBLE);


            }
        });
    }

    public void animateImages(int rod) {
        Intent previousIntent = getIntent();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!stop) {
                 now_rod=rod;
                //loop for all images and animate it
                    for ( i = 0; i < rod1[now_rod].length; i++) {

                            if (i == current) {
                                rod1[now_rod][i].setVisibility(View.VISIBLE);//show
                                if (gameManager.checkAnswer(now_rod, i)) {
                                    vibrate();
                                    Toast.makeText(Activity_Game.this, "oops! watch out!", Toast.LENGTH_SHORT).show();
                                    refreshUI();
                                }


                            } else
                                rod1[now_rod][i].setVisibility(View.INVISIBLE);
                            ;//hide

                        }

                if (current<rod1[now_rod].length)
                    current++;
                else{
                    current=0;//again first one
                    now_rod=gameManager.getfirstRoads();
                }
                //recurring using the same method - infinite loop
               if(gameManager.isLose()!=true)
                    animateImages(now_rod);

                }

            }
        },250);

        }

    @Override
    protected void onPause() {
        super.onPause();
      stop=true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(stop==true){
        stop=false;
        animateImages(now_rod);}
    }

    private void refreshUI() {
        for (int i = 0; i < gameManager.getWrong(); i++) {
            game_IMG_hearts[i].setVisibility(View.INVISIBLE);
        }
        if (gameManager.isLose()) {
            Toast.makeText(Activity_Game.this,"GAME OVER",Toast.LENGTH_SHORT).show();
            openScorePage() ;
        }
    }

    private void openScorePage() {
        Intent intent = new Intent(this, Activity_score.class);
        startActivity(intent);

        finish();
    }

    private void vibrate() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(500);
        }

    }










}