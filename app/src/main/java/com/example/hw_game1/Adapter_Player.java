package com.example.hw_game1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;


public class Adapter_Player  extends RecyclerView.Adapter<Adapter_Player.PlayerViewHolder> {

    private Context context;
    private ArrayList<Player> players;


    public Adapter_Player(Context context, ArrayList<Player> players) {
        this.context = context;
        this.players = players;
    }


    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_player, parent, false);
        PlayerViewHolder myPlayerViewHolder = new PlayerViewHolder(view);

        return myPlayerViewHolder;    }

    @Override
    public void onBindViewHolder(final PlayerViewHolder holder, int position) {
        Player player = players.get(position);

        holder.player_LBL_name.setText(player.getName());
        holder.player_LBL_score.setText(""+ player.getScore());


    }

    @Override
    public int getItemCount() {
        return players == null ? 0 : players.size();
    }


    class PlayerViewHolder extends RecyclerView.ViewHolder {

        private MaterialTextView player_LBL_name;
        private MaterialTextView player_LBL_score;

        public PlayerViewHolder(View itemView) {
            super(itemView);
            player_LBL_name=itemView.findViewById(R.id.player_LBL_name);
            player_LBL_score=itemView.findViewById(R.id.player_LBL_score);

        }

    }


}
