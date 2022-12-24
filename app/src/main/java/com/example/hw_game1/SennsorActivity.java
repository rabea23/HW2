package com.example.hw_game1;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

public class SennsorActivity extends AppCompatActivity {

    private ShapeableImageView[] game_IMG_hearts;
    private ShapeableImageView[] game_IMG_cars;
    private MaterialTextView game_LBL_score;
    private  int now_rod;
    private ShapeableImageView[][] rod1;
    private int i=0;
    int current=0;
    final Handler handler = new Handler();
    GameManager gameManager;
    private boolean stop = false;
    public static final String KEY_NAME = "KEY_NAME";
    public static final String KEY_LONGITUDE = "KEY_LONGITUDE";
    public static final String KEY_LATITUDE = "KEY_LATITUDE";
    public static final String KEY_LEVEl = "KEY_LEVEl";
    int delay;
    private String playerName;
    private String level;
    private String getKeyLongitude;
    private String getKeyLatitude;
    private StepDetector stepDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sennsor);
        findViews();
        gameManager = new GameManager(game_IMG_hearts.length);
        Intent previousIntent = getIntent();
        playerName= previousIntent.getExtras().getString(KEY_NAME);
        level=previousIntent.getExtras().getString(KEY_LEVEl);
        getKeyLongitude=previousIntent.getExtras().getString(KEY_LONGITUDE);
        getKeyLatitude=previousIntent.getExtras().getString(KEY_LATITUDE);

        stepDetector = new StepDetector(this, callBack_steps);
        stepDetector.start();

        checklevel();
        findViews();
        int rod=gameManager.getfirstRoads();
        animateImages(rod);
        refreshUI();

    }
    private void checklevel(){
        Log.d("message2",level.toString());
        if(level.equals("easy")){
            delay=250;
            Log.d("message2",""+delay);}
        if(level.equals("hard")){
            delay=100;
            Log.d("message2",""+delay);}


    }

    private StepDetector.CallBack_steps callBack_steps = new StepDetector.CallBack_steps() {
        @Override
        public void oneStep() {
            //Log.d("message4", String.valueOf(stepDetector.getStepCounter()));
            if(stepDetector.getStepCounter()>2){
                //Log.d("message4", String.valueOf(stepDetector.getStepCounter()));
                int nowcar = gameManager.getNowcar();
               // Log.d("message4", String.valueOf(nowcar));

                game_IMG_cars[nowcar].setVisibility(View.INVISIBLE);
                int newcar = gameManager.moveCartoLeft();
                game_IMG_cars[newcar].setVisibility(View.VISIBLE);
            }
            if(stepDetector.getStepCounter()<-2) {
                int nowcar = gameManager.getNowcar();
                game_IMG_cars[nowcar].setVisibility(View.INVISIBLE);
                int newcar = gameManager.moveCartoRight();
                game_IMG_cars[newcar].setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void bonos() {
           // Log.d("message4", String.valueOf(stepDetector.getStep2()));
            checklevel();
            if(stepDetector.getStep2()<6){
                checklevel();
                delay= (int) (delay-40+stepDetector.getStep2());
                Log.d("message4", String.valueOf(delay));

            }
            if(stepDetector.getStep2()>6){
                checklevel();

                delay= (int) (delay+40+stepDetector.getStep2());
                Log.d("message4", String.valueOf(delay));

            }

        }


    };
    private void findViews() {
        game_LBL_score=findViewById(R.id.game_LBL_score);
        game_IMG_cars=new ShapeableImageView[]{
                findViewById(R.id.game_ic_car_1),
                findViewById(R.id.game_ic_car_2),
                findViewById(R.id.game_ic_car_3),
                findViewById(R.id.game_ic_car_4),
                findViewById(R.id.game_ic_car_5),

        };
        game_IMG_hearts = new ShapeableImageView[]{
                findViewById(R.id.game_IMG_hurt3),
                findViewById(R.id.game_IMG_hurt2),
                findViewById(R.id.game_IMG_hurt1),
        };
        game_IMG_cars[0].setVisibility(View.INVISIBLE);
        game_IMG_cars[1].setVisibility(View.INVISIBLE);
        game_IMG_cars[3].setVisibility(View.INVISIBLE);
        game_IMG_cars[4].setVisibility(View.INVISIBLE);
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
                { findViewById(R.id.game_ic_stone4_1),
                        findViewById(R.id.game_ic_stone4_2),
                        findViewById(R.id.game_ic_stone4_3),
                        findViewById(R.id.game_ic_stone4_4),
                        findViewById(R.id.game_ic_stone4_5),
                        findViewById(R.id.game_ic_stone4_6),},
                { findViewById(R.id.game_ic_stone5_1),
                        findViewById(R.id.game_ic_stone5_2),
                        findViewById(R.id.game_ic_stone5_3),
                        findViewById(R.id.game_ic_stone5_4),
                        findViewById(R.id.game_ic_stone5_5),
                        findViewById(R.id.game_ic_stone5_6),}

        };
        for (int i = 0; i < rod1.length; i++) {
            for (int j = 0; j < rod1[i].length; j++) {
                rod1[i][j].setVisibility(View.INVISIBLE);

            }

        }

    }
    private boolean bool;

    public void animateImages(int rod) {
        Intent previousIntent = getIntent();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!stop) {
                    now_rod=rod;
                    if(current==0){
                        if(coinOrstone(now_rod)==true)
                            bool=true;
                        else
                            bool=false;
                    }
                    //loop for all images and animate it
                    for ( i = 0; i < rod1[now_rod].length; i++) {

                        if (i == current) {
                            rod1[now_rod][i].setVisibility(View.VISIBLE);//show
                            if(bool==false) {
                                if (gameManager.checkAnswer(now_rod, i)) {
                                    vibrate();
                                    crashSound();
                                    Toast.makeText(SennsorActivity.this, "oops! watch out!", Toast.LENGTH_SHORT).show();
                                    refreshUI();
                                }
                            }else{
                                if(gameManager.checkAnswer1(now_rod, i))
                                    coinSound();
                                refreshUI();
                            }
                        } else
                            rod1[now_rod][i].setVisibility(View.INVISIBLE);
                        ;//hide
                    }
                    // boolean b=coinOrstone(rod);
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
        },delay);

    }
    private boolean coinOrstone(int rod){
        int num=1 + (int)(Math.random() * 2);
        if(num==2){
            rod1[rod][0].setImageResource(R.drawable.ic_coin);
            rod1[rod][1].setImageResource(R.drawable.ic_coin);
            rod1[rod][2].setImageResource(R.drawable.ic_coin);
            rod1[rod][3].setImageResource(R.drawable.ic_coin);
            rod1[rod][4].setImageResource(R.drawable.ic_coin);
            rod1[rod][5].setImageResource(R.drawable.ic_coin);
            return true;
        }else{
            rod1[rod][0].setImageResource(R.drawable.ic_stone);
            rod1[rod][1].setImageResource(R.drawable.ic_stone);
            rod1[rod][2].setImageResource(R.drawable.ic_stone);
            rod1[rod][3].setImageResource(R.drawable.ic_stone);
            rod1[rod][4].setImageResource(R.drawable.ic_stone);
            rod1[rod][5].setImageResource(R.drawable.ic_stone);
        }
        return false;
    }
    private void refreshUI() {
        for (int i = 0; i < gameManager.getWrong(); i++) {
            game_IMG_hearts[i].setVisibility(View.INVISIBLE);
        }
        if (gameManager.isLose()) {
            Toast.makeText(SennsorActivity.this,"GAME OVER",Toast.LENGTH_SHORT).show();
            openScorePage() ;
        }
        game_LBL_score.setText("" + gameManager.getScore());
    }
    private void openScorePage() {
        Player p=new Player();
        p.setName(playerName);
        p.setScore(gameManager.getScore());
        p.setLatitude(Double.parseDouble(((getKeyLatitude))));
        p.setLongit(Double.parseDouble((getKeyLongitude)));
        Log.d("message6", String.valueOf(p.getLongit()));
        DataManager.setPlayer(p);
        DataManager.save(p);
        DataManager.setPlayersCounter(DataManager.getPlayersCounter()+1);
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


    private void crashSound(){
        MediaPlayer crashSound= MediaPlayer.create(SennsorActivity.this,R.raw.crash_sound);
        crashSound.start();
    }

    private void coinSound(){
        MediaPlayer coinSound= MediaPlayer.create(SennsorActivity.this,R.raw.coins_sound);
        coinSound.start();
    }

}