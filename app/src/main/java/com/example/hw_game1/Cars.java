package com.example.hw_game1;

public class Cars {
   private int index;
   private String id;

    public Cars() {
    }

    public Cars (String id) {
        this.id = id;
    }



    public Cars setId(String id) {
        this.id = id;
        return this;
    }

    public Cars setIndex(int index) {
        this.index = index;
        return this;
    }

    public int getIndex() {
        return index;
    }

    public String getId() {
        return id;
    }
}
