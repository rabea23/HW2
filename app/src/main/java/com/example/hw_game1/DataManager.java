package com.example.hw_game1;

import java.util.ArrayList;

public class DataManager {
    public static ArrayList<Cars> getCars() {
        ArrayList<Cars> cars = new ArrayList<>();
        cars.add(new Cars().setId("game_ic_car_1").setIndex(0)
                );
        cars.add(new Cars().setId("game_ic_car_2").setIndex(1)
        );
        cars.add(new Cars().setId("game_ic_car_3").setIndex(2)
        );
        return cars;
    }

    }
