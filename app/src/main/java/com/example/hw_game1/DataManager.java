package com.example.hw_game1;

import android.util.Log;

import java.util.ArrayList;

public class DataManager {
    static String json;

    private static ArrayList<Player> players = new ArrayList<>();
    private static int playersCounter;


    public static ArrayList<Cars> getCars() {
        ArrayList<Cars> cars = new ArrayList<>();
        cars.add(new Cars().setId("game_ic_car_1").setIndex(0)
        );
        cars.add(new Cars().setId("game_ic_car_2").setIndex(1)
        );
        cars.add(new Cars().setId("game_ic_car_3").setIndex(2)
        );
        cars.add(new Cars().setId("game_ic_car_4").setIndex(3)
        );
        cars.add(new Cars().setId("game_ic_car_5").setIndex(4)
        );
        return cars;
    }

    public static ArrayList<Player> getPlayers() {
        ArrayList<Player> p=new ArrayList<>();
        for (int i = 0; i <players.size()-1 ; i++) {
            for (int j = 0; j < players.size()-1; j++) {
                if(players.get(j).getScore()<players.get(j+1).getScore()){
                    Player temp=players.get(j+1);
                    players.set((j+1),players.get(j));
                    players.set(j,temp);
                }
            }
        }
        p=players;
        if(p.size()>10){
            for (int i = 9; i <p.size() ; i++) {
                p.remove(i);
            }
        }

        return p;
    }


        public static void setPlayer(Player p){
        players.add(p);
        //save(p);
    }
    public static void setList(ArrayList<Player> player){
       players=player;
    }

    public static int getPlayersCounter() {
        return playersCounter;

    }
    public static void save(Player player) {
        Log.d("message5", String.valueOf(player.getLongit()));
        Log.d("message5", String.valueOf(player.getLatitude()));

            MySPV3.getInstance().putString("name"+playersCounter,player.getName());
            MySPV3.getInstance().putInt("score"+playersCounter,player.getScore());
            MySPV3.getInstance().putString("lat"+playersCounter, String.valueOf(player.getLatitude()));
            MySPV3.getInstance().putString("lo"+playersCounter, String.valueOf(player.getLongit()));

        Log.d("message5", "saved");
    }

    public static void setPlayersCounter(int playersCounter) {
        DataManager.playersCounter = playersCounter;
        MySPV3.getInstance().putInt("COUNT",playersCounter);

    }

    public static void load() {
        //Log.d("message5", "load");
        int x=MySPV3.getInstance().getInt("COUNT",0);
        setPlayersCounter(x);

        Log.d("message5", String.valueOf(x));

        for (int i = 0; i <playersCounter ; i++) {
            Log.d("message5", String.valueOf(i));
            String a=MySPV3.getInstance().getString("name"+i,"");
            Player p=new Player();
            p.setName(MySPV3.getInstance().getString("name"+i,""));
            p.setScore(MySPV3.getInstance().getInt("score"+i,0));
            String x1=(MySPV3.getInstance().getString("lo"+(i),""));
            String y1=(MySPV3.getInstance().getString("lat"+(i),""));
            double d=Double.parseDouble(x1);
            double d2=Double.parseDouble(y1);
            p.setLongit(d);
            Log.d("message5", String.valueOf(d));
            Log.d("message5", String.valueOf(d2));
            p.setLatitude(d2);
            p.setLongit(d);
            setPlayer(p);
        }

    }



}