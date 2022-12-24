package com.example.hw_game1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Top10Activity  extends Fragment{

    private RecyclerView main_LST_players;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_top10, container, false);
        main_LST_players = view.findViewById(R.id.main_LST_players);
        ArrayList<Player> players = DataManager.getPlayers();
        Adapter_Player adapter_player = new Adapter_Player(getContext(), players);

        main_LST_players.setLayoutManager(new LinearLayoutManager(getContext()));
        main_LST_players.setAdapter(adapter_player);

        return view;
    }
}