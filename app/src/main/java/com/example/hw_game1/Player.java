package com.example.hw_game1;

public class Player {
    private String name="";
    private int score=0;

    private double latitude;
    private double longit;

    public int getIsshow() {
        return isshow;
    }

    public Player setIsshow(int isshow) {
        this.isshow = isshow;
        return this;
    }

    private  int isshow;
    public Player() {

    }

    public String getName() {
        return name;
    }

    public Player setName(String name) {
        this.name = name;
        return this;
    }

    public int getScore() {
        return score;
    }

    public Player setScore(int score) {
        this.score = score;
        return this;
    }

    public double getLatitude() {
        return latitude;
    }

    public Player setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public double getLongit() {
        return longit;
    }

    public Player setLongit(double longit) {
        this.longit = longit;
        return this;
    }
}
