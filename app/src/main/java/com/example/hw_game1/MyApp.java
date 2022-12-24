package com.example.hw_game1;

import android.app.Application;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        MySPV3.init(this);

    }
}
