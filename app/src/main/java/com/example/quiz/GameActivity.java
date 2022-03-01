package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.quiz.Controllers.QuestionManager;
import com.google.android.material.textfield.TextInputLayout;


public class GameActivity extends AppCompatActivity {
    private String player1_name;
    private String player2_name;
    private String question;

    private TextView TV_player1;
    private TextView TV_player2;
    private TextView TV_Question_P1;
    private TextView TV_Question_P2;

    Runnable questionRunnable=null;
    private QuestionManager myQuestion = MainActivity.myQuestion;

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
        TV_Question_P1 = findViewById(R.id.game_question_p1);
        TV_Question_P2 = findViewById(R.id.game_question_p2);

        //Définit le nom des joueurs
        TV_player1.setText(player1_name);
        TV_player2.setText(player2_name);

        question = myQuestion.getQuestion();
        TV_Question_P1.setText(question);
        TV_Question_P2.setText(question);

        Handler test = new Handler();
        questionRunnable = new Runnable() {
            @Override
            public void run() {
                if(1 == 2){ //CONTROL_LAST_QUESTION

                    handler.removeCallbacks(this);
                    //DO_CODE_LAST_QUESTION
                    //...
                    //DO_OTHER_EXIT_CODE
                }else{
                    //DO_CODE_QUESTION_ITERATION
                    handler.postDelayed(this,"TIMER_MILLIS_QUESTION_DELAY");
                }
            }
        };

    }
}
