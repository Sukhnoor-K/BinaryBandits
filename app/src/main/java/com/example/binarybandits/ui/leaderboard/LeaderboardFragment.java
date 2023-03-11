package com.example.binarybandits.ui.leaderboard;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.binarybandits.R;
import com.example.binarybandits.models.Player;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LeaderboardFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LeaderboardViewModel leaderboardViewModel = new LeaderboardViewModel();
        leaderboardViewModel.sortPlayer_list();
        ArrayList<Player> players = leaderboardViewModel.getPlayerList();

        // Inflate the layout for this fragment
        View leaderboard = inflater.inflate(R.layout.fragment_leaderboard, container, false);

        // instantiate variables for top three players
        ListView playerList = leaderboard.findViewById(R.id.playerList);
        TextView nameOne = leaderboard.findViewById(R.id.player1);
        TextView nameTwo = leaderboard.findViewById(R.id.player2);
        TextView nameThree = leaderboard.findViewById(R.id.player3);
        TextView scoreOne = leaderboard.findViewById(R.id.player1_score);
        TextView scoreTwo = leaderboard.findViewById(R.id.player2_score);
        TextView scoreThree = leaderboard.findViewById(R.id.player3_score);

        // set values of top three players
        if(players.size()>0) {
            nameOne.setText(players.get(0).getUsername());
            scoreOne.setText(Integer.toString(players.get(0).getTotalScore()));
            String url1 = "https://api.dicebear.com/5.x/avataaars-neutral/png?seed=" + players.get(0).getUsername();
            ImageView image1 = leaderboard.findViewById(R.id.limage1);
            Picasso.get().load(url1).into(image1);
        }

        if(players.size()>1) {
            nameTwo.setText(players.get(1).getUsername());
            scoreTwo.setText(Integer.toString(players.get(1).getTotalScore()));
            String url2 = "https://api.dicebear.com/5.x/avataaars-neutral/png?seed=" + players.get(1).getUsername();
            ImageView image2 = leaderboard.findViewById(R.id.limage2);
            Picasso.get().load(url2).into(image2);
        }
        if(players.size()>2) {
            nameThree.setText(players.get(2).getUsername());
            scoreThree.setText(Integer.toString(players.get(2).getTotalScore()));
            String url3 = "https://api.dicebear.com/5.x/avataaars-neutral/png?seed=" + players.get(2).getUsername();
            ImageView image3 = leaderboard.findViewById(R.id.limage3);
            Picasso.get().load(url3).into(image3);
        }

        // call ArrayAdapter to add each item in array to ListView
        if(players.size()>3) {
            // remove top three players from players array
            removePlayers(players);
            ArrayAdapter<Player> playerArrayAdapter = new LeaderboardArrayAdapter(getActivity(), players);
            playerList.setAdapter(playerArrayAdapter);
        }
        return leaderboard;
    }

    /**
     * remove top three players from array of players
     * @param players
     */
    public void removePlayers(ArrayList<Player> players){
        for(int i = 0; i<3; i++)
            players.remove(0);
    }

}
