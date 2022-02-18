package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private Button BT_player;
    private Button BT_play;
    private TextInputLayout ET_saisie_player1_layout;
    private TextInputLayout ET_saisie_player2_layout;
    private TextInputLayout ET_saisie_question_layout;
    private TextInputEditText Et_saisie_question;
    private TextInputEditText ET_saisie_player1;
    private TextInputEditText ET_saisie_player2;
    private RelativeLayout LAY_question;
    private RelativeLayout LAY_parametre;
    private RelativeLayout LAY_favorite;

    private Button BT_cancel_favorite;
    private Button BT_cancel_question;
    private Button BT_validate_question;
    private Button BT_cancel_parametre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar mainToolBar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolBar);

        BT_player = findViewById(R.id.main_player_button);
        ET_saisie_player1_layout = findViewById(R.id.main_edit_text_player1_layout);
        ET_saisie_player2_layout = findViewById(R.id.main_edit_text_player2_layout);
        ET_saisie_question_layout = findViewById(R.id.main_edit_text_question_layout);
        Et_saisie_question = findViewById(R.id.main_edit_text_question);
        ET_saisie_player1 = findViewById(R.id.main_edit_text_player1);
        ET_saisie_player2 = findViewById(R.id.main_edit_text_player2);
        BT_play = findViewById(R.id.main_play_button);
        LAY_question = findViewById(R.id.main_relativeLayout);
        LAY_parametre = findViewById(R.id.main_relativeLayout_parametre);
        LAY_favorite = findViewById(R.id.main_relativeLayout_favorite);
        BT_cancel_question = findViewById(R.id.main_cancel_question_button);
        BT_cancel_parametre = findViewById(R.id.main_cancel_parametre_button);
        BT_cancel_favorite = findViewById(R.id.main_cancel_favorite_button);

        ET_saisie_question_layout.setVisibility(EditText.GONE);
        ET_saisie_player1_layout.setVisibility(EditText.GONE);
        ET_saisie_player2_layout.setVisibility(EditText.GONE);
        LAY_question.setVisibility(RelativeLayout.GONE);
        LAY_parametre.setVisibility(RelativeLayout.GONE);
        LAY_favorite.setVisibility(RelativeLayout.GONE);

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
                game.putExtra("Player1", getPlayerName(true));
                game.putExtra( "Player2", getPlayerName(false));
                startActivity(game);
            }
        });

        BT_cancel_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Et_saisie_question.setText("");
                LAY_question.setVisibility(RelativeLayout.GONE);
                ET_saisie_question_layout.setVisibility(EditText.GONE);
                final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

            }
        });
        BT_cancel_parametre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LAY_parametre.setVisibility(RelativeLayout.GONE);
            }
        });

        BT_cancel_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LAY_favorite.setVisibility(View.GONE);
            }
        });
    }

    private String getPlayerName(Boolean player1) {
        String name = "";
        if (player1) {
            name = ET_saisie_player1.getText().toString();
        } else {
            name = ET_saisie_player2.getText().toString();
        }
        return name;
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_favorite:
                //Do action
                LAY_favorite.setVisibility(RelativeLayout.VISIBLE);
                break;
            case R.id.action_parametre:
               // resetField();
                LAY_parametre.setVisibility(RelativeLayout.VISIBLE);
                break;
            case R.id.action_question:
                ET_saisie_question_layout.setVisibility(EditText.VISIBLE);
                LAY_question.setVisibility(RelativeLayout.VISIBLE);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

}