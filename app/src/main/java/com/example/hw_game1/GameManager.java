package com.example.hw_game1;

import android.os.Handler;
import android.view.View;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameManager {

    private int wrong = 0;
    private final int roads =3;
    private int life;
    private int nowcar = 1;
    private ArrayList<Cars> cars;
    private final int rocknum =5;

    public GameManager(int life) {
        this.life = life;
        cars = DataManager.getCars();
    }

    public int getWrong() {
        return wrong;
    }


    public int getNowcar() {
        return nowcar;
    }


    public int moveCartoRight() {
        Cars car = cars.get(nowcar);
        if (car.getIndex() == roads-1) {
            return cars.size() - 1;
        } else {
            nowcar = cars.get(car.getIndex()).getIndex() + 1;
            return car.getIndex() + 1;
        }
    }

    public int moveCartoLeft() {
        Cars car = cars.get(nowcar);
        if (car.getIndex() == 0) {
            return 0;
        } else {
            nowcar = cars.get(car.getIndex()).getIndex() - 1;
            return car.getIndex() - 1;

        }

    }



    public boolean checkAnswer(int rod, int rock) {
        if (rod == getNowcar() && rock == rocknum) {
            wrong++;
            return true;
        }
        return false;
    }
    public boolean isLose() {
        return wrong == life;
    }


    public int getRoads() {
        return roads;
    }
    public int getfirstRoads() {
        int nowRoad=0 + (int)(Math.random() * getRoads());
        return nowRoad;
    }
}
