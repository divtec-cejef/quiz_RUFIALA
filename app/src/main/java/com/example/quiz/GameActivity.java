package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;


public class GameActivity extends AppCompatActivity {
    private String player1_name;
    private String player2_name;

    private TextView TV_player1;
    private  TextView TV_player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent ret = getIntent();


        player1_name = ret.getStringExtra("Player1");
        player2_name = ret.getStringExtra("Player2");

        //Initialiser les textView
        TV_player1 = findViewById(R.id.game_name_p1);
        TV_player2 = findViewById(R.id.game_name_p2);

        //Définit le nom des joueurs
        TV_player1.setText(player1_name);
        TV_player2.setText(player2_name);
    }


}
