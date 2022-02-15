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

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private Button BT_player;
    private Button BT_play;
    private TextInputLayout ET_saisie_player1_layout;
    private TextInputLayout ET_saisie_player2_layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar mainToolBar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolBar);

        BT_player = findViewById(R.id.main_player_button);
        ET_saisie_player1_layout = findViewById(R.id.main_edit_text_player1_layout);
        ET_saisie_player2_layout = findViewById(R.id.main_edit_text_player2_layout);
        BT_play = findViewById(R.id.main_play_button);

        ET_saisie_player1_layout.setVisibility(EditText.GONE);
        ET_saisie_player2_layout.setVisibility(EditText.GONE);
        BT_play.setVisibility(Button.GONE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        BT_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ET_saisie_player1_layout.getVisibility() == View.GONE) {
                    ET_saisie_player1_layout.setVisibility(EditText.VISIBLE);
                    ET_saisie_player2_layout.setVisibility(EditText.VISIBLE);
                    BT_play.setVisibility(Button.VISIBLE);
                } else if (ET_saisie_player1_layout.getVisibility() == View.VISIBLE) {
                    ET_saisie_player1_layout.setVisibility(EditText.GONE);
                    ET_saisie_player2_layout.setVisibility(EditText.GONE);
                    BT_play.setVisibility(Button.GONE);
                    BT_play.setVisibility(Button.GONE);
                }
            }
        });

        BT_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent game = new Intent(getApplicationContext(), GameActivity.class);
                //game.putExtra();
                startActivity(game);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_favorite:
                //Do action

                break;
            case R.id.action_parametre:
               // resetField();
                break;
            case R.id.action_question:
                //LAY_aPropos.setVisibility(RelativeLayout.VISIBLE);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

}